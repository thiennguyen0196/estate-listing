package com.thiennguyen.estatelisting.domain.usecases

import com.thiennguyen.estatelisting.domain.repositories.EstateRepository
import com.thiennguyen.estatelisting.domain.usecase.GetBookmarkedEstateIdsUseCase
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetBookmarkedEstateIdsUseCaseTest {

    private lateinit var mockRepository: EstateRepository
    private lateinit var useCase: GetBookmarkedEstateIdsUseCase

    @Before
    fun setUp() {
        mockRepository = mockk()
        useCase = GetBookmarkedEstateIdsUseCase(mockRepository)
    }

    @Test
    fun `When calling get bookmarked estate ids use case successful, it returns success`() =
        runTest {
            val expected = listOf("id")
            every { mockRepository.getBookmarkedEstateIds() } returns flowOf(expected)

            useCase().collect {
                it shouldBe expected
            }
        }

    @Test
    fun `When calling get bookmarked estate ids use case failed, it returns error`() = runTest {
        val expected = Exception()
        every { mockRepository.getBookmarkedEstateIds() } returns flow { throw expected }

        useCase().catch {
            it shouldBe expected
        }.collect()
    }
}
