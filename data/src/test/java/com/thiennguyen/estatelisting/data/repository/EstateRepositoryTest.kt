package com.thiennguyen.estatelisting.data.repository

import com.thiennguyen.estatelisting.data.MockUtil
import com.thiennguyen.estatelisting.data.local.dao.BookmarkedEstateDao
import com.thiennguyen.estatelisting.data.remote.response.toModel
import com.thiennguyen.estatelisting.data.repositories.EstateRepositoryImpl
import com.thiennguyen.estatelisting.data.services.ApiService
import com.thiennguyen.estatelisting.domain.repositories.EstateRepository
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class UserRepositoryTest {

    private lateinit var mockService: ApiService
    private lateinit var mockDao: BookmarkedEstateDao
    private lateinit var repository: EstateRepository

    @Before
    fun setUp() {
        mockService = mockk()
        mockDao = mockk()
        repository = EstateRepositoryImpl(mockService, mockDao)
    }

    @Test
    fun `When calling getEstates successful, it returns success`() =
        runTest {
            val expected = MockUtil.response
            coEvery { mockService.getEstates() } returns expected

            repository.getEstates().collect {
                it shouldBe expected.results?.map { it.toModel() }.orEmpty()
            }
        }

    @Test
    fun `When calling getEstates failed, it returns error`() = runTest {
        val expected = Throwable()
        coEvery { mockService.getEstates() } throws expected

        repository.getEstates().catch {
            it shouldBe expected
        }.collect()
    }

    @Test
    fun `When calling getBookmarkedEstateIds success, it returns list of bookmarked estate ids`() =
        runTest {
            val expected = MockUtil.entities
            coEvery { mockDao.getBookmarkedEstates() } returns expected

            repository.getBookmarkedEstateIds().collect {
                it shouldBe expected.map { estate -> estate.id }
            }
        }

    @Test
    fun `When calling bookmarkEstate success, it returns Unit`() =
        runTest {
            coEvery { mockDao.insertBookmarkedEstate(any()) } returns Unit

            repository.bookmarkEstate("id").collect {
                it shouldBe Unit
            }
        }

    @Test
    fun `When calling removeBookmarkedEstate success, it returns Unit`() =
        runTest {
            coEvery { mockDao.removeBookmarkedEstate(any()) } returns Unit

            repository.removeBookmarkedEstate("id").collect {
                it shouldBe Unit
            }
        }
}
