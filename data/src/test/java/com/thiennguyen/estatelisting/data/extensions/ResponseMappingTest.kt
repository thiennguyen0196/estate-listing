package com.thiennguyen.estatelisting.data.extensions

import com.thiennguyen.estatelisting.data.MockUtil
import com.thiennguyen.estatelisting.data.extension.flowTransform
import com.thiennguyen.estatelisting.data.remote.response.EstatesResponse
import com.thiennguyen.estatelisting.domain.exceptions.ApiException
import com.thiennguyen.estatelisting.domain.exceptions.NoConnectivityException
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.io.IOException
import java.io.InterruptedIOException
import java.net.UnknownHostException

class ResponseMappingTest {

    @Test
    fun `When mapping API request flow failed with UnknownHostException, it returns mapped NoConnectivityException error`() =
        runTest {
            flowTransform<EstatesResponse> {
                throw UnknownHostException()
            }.catch {
                it shouldBe NoConnectivityException
            }.collect()
        }

    @Test
    fun `When mapping API request flow failed with InterruptedIOException, it returns mapped NoConnectivityException error`() =
        runTest {
            flowTransform<EstatesResponse> {
                throw InterruptedIOException()
            }.catch {
                it shouldBe NoConnectivityException
            }.collect()
        }

    @Test
    fun `When mapping API request flow failed with HttpException, it returns mapped ApiException error`() =
        runTest {
            val httpException = MockUtil.mockHttpException
            flowTransform<EstatesResponse> {
                throw httpException
            }.catch {
                it shouldBe ApiException(
                    httpException.code(),
                    httpException.message()
                )
            }.collect()
        }

    @Test
    fun `When mapping API request flow failed with unhandled exceptions, it should throw that error`() =
        runTest {
            val exception = IOException("Canceled")
            flowTransform<EstatesResponse> {
                throw exception
            }.catch {
                it shouldBe exception
            }.collect()
        }
}
