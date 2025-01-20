package com.thiennguyen.estatelisting.data

import com.thiennguyen.estatelisting.data.local.entity.BookmarkedEstateEntity
import com.thiennguyen.estatelisting.data.remote.response.EstateResponse
import com.thiennguyen.estatelisting.data.remote.response.EstatesResponse
import io.mockk.every
import io.mockk.mockk
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response

object MockUtil {

    val mockHttpException: HttpException
        get() {
            val response = mockk<Response<Any>>()
            val httpException = mockk<HttpException>()
            val responseBody = mockk<ResponseBody>()
            every { response.code() } returns 500
            every { response.message() } returns "message"
            every { response.errorBody() } returns responseBody
            every { httpException.code() } returns response.code()
            every { httpException.message() } returns response.message()
            every { httpException.response() } returns response
            return httpException
        }

    val response = EstatesResponse(
        results = listOf(
            EstateResponse()
        )
    )

    val entities = listOf(
        BookmarkedEstateEntity(
            id = "id"
        )
    )
}
