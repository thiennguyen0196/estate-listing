package com.thiennguyen.estatelisting.domain.usecases

import com.thiennguyen.estatelisting.domain.MockUtil
import com.thiennguyen.estatelisting.domain.repositories.EstateRepository
import com.thiennguyen.estatelisting.domain.usecase.GetEstateListingUseCase
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

class GetEstateListingUseCaseTest {

    private lateinit var mockRepository: EstateRepository
    private lateinit var useCase: GetEstateListingUseCase

    @Before
    fun setUp() {
        mockRepository = mockk()
        useCase = GetEstateListingUseCase(mockRepository)
    }

    @Test
    fun `When calling get estate listing use case successful, it returns success`() =
        runTest {
            val expected = MockUtil.estates
            every { mockRepository.getEstates() } returns flowOf(expected)

            useCase().collect {
                it shouldBe expected
            }
        }

    @Test
    fun `When calling get estate listing use case failed, it returns error`() = runTest {
        val expected = Exception()
        every { mockRepository.getEstates() } returns flow { throw expected }

        useCase().catch {
            it shouldBe expected
        }.collect()
    }
}
