package com.thiennguyen.estatelisting.domain.usecases

import com.thiennguyen.estatelisting.domain.repositories.EstateRepository
import com.thiennguyen.estatelisting.domain.usecase.BookmarkEstateUseCase
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

class BookmarkEstateUseCaseTest {

    private lateinit var mockRepository: EstateRepository
    private lateinit var useCase: BookmarkEstateUseCase

    @Before
    fun setUp() {
        mockRepository = mockk()
        useCase = BookmarkEstateUseCase(mockRepository)
    }

    @Test
    fun `When calling bookmark estate use case successful, it returns success`() = runTest {
        val expected = Unit
        every { mockRepository.bookmarkEstate(any()) } returns flowOf(expected)

        useCase("id").collect {
            it shouldBe expected
        }
    }

    @Test
    fun `When calling bookmark estate use case failed, it returns error`() = runTest {
        val expected = Exception()
        every { mockRepository.bookmarkEstate(any()) } returns flow { throw expected }

        useCase("id").catch {
            it shouldBe expected
        }.collect()
    }
}
