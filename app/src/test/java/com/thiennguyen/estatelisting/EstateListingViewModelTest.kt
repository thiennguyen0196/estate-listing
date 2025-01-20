package com.thiennguyen.estatelisting

import app.cash.turbine.test
import com.thiennguyen.estatelisting.domain.usecase.BookmarkEstateUseCase
import com.thiennguyen.estatelisting.domain.usecase.GetBookmarkedEstateIdsUseCase
import com.thiennguyen.estatelisting.domain.usecase.GetEstateListingUseCase
import com.thiennguyen.estatelisting.domain.usecase.RemoveBookmarkedEstateUseCase
import com.thiennguyen.estatelisting.models.toUiModel
import com.thiennguyen.estatelisting.ui.screens.list.EstateListingViewModel
import com.thiennguyen.estatelisting.utils.DispatchersProvider
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class EstateListingViewModelTest {

    @get:Rule
    val coroutinesRule = CoroutineTestRule()

    private val mockGetEstateListingUseCase: GetEstateListingUseCase = mockk()
    private val mockGetBookmarkedEstateIdsUseCase: GetBookmarkedEstateIdsUseCase = mockk()
    private val mockBookmarkEstateUseCase: BookmarkEstateUseCase = mockk()
    private val mockRemoveBookmarkedEstateUseCase: RemoveBookmarkedEstateUseCase = mockk()

    private lateinit var viewModel: EstateListingViewModel

    @Before
    fun setUp() {
        every { mockGetEstateListingUseCase() } returns flowOf(MockUtil.estates)
        every { mockGetBookmarkedEstateIdsUseCase() } returns flowOf(emptyList())
        every { mockBookmarkEstateUseCase(any()) } returns flowOf(Unit)
        every { mockRemoveBookmarkedEstateUseCase(any()) } returns flowOf(Unit)
    }

    @Test
    fun `When loading estates successfully, it shows the estate list`() = runTest {
        initViewModel()
        viewModel.estates.test {
            expectMostRecentItem() shouldBe MockUtil.estates.map {
                it.toUiModel(false)
            }
        }
    }

    @Test
    fun `When loading estates failed, it shows the corresponding error`() = runTest {
        val error = Exception()
        every { mockGetEstateListingUseCase() } returns flow { throw error }
        initViewModel(dispatchers = CoroutineTestRule(StandardTestDispatcher()).testDispatcherProvider)

        viewModel.error.test {
            advanceUntilIdle()

            expectMostRecentItem() shouldBe error
        }
    }

    @Test
    fun `When loading models, it shows and hides loading correctly`() = runTest {
        initViewModel(dispatchers = CoroutineTestRule(StandardTestDispatcher()).testDispatcherProvider)

        viewModel.isLoading.test {
            awaitItem() shouldBe false
            awaitItem() shouldBe true
            awaitItem() shouldBe false
        }
    }

    @Test
    fun `When calling bookmark an estate, it call the correct method`() = runTest {
        initViewModel()
        viewModel.updateBookmarkedItem(MockUtil.estates.first().toUiModel(false))
        viewModel.estates.test {
            expectMostRecentItem().first() shouldBe MockUtil.estates.first().toUiModel(true)

            verify { mockBookmarkEstateUseCase.invoke("id") }
        }
    }

    private fun initViewModel(dispatchers: DispatchersProvider = coroutinesRule.testDispatcherProvider) {
        viewModel = EstateListingViewModel(
            dispatchers,
            mockGetEstateListingUseCase,
            mockGetBookmarkedEstateIdsUseCase,
            mockBookmarkEstateUseCase,
            mockRemoveBookmarkedEstateUseCase,
        )
    }
}