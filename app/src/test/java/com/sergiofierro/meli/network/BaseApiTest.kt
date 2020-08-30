package com.sergiofierro.meli.network

import java.io.IOException
import java.nio.charset.StandardCharsets
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@RunWith(JUnit4::class)
abstract class BaseApiTest<T> {

  lateinit var mockWebServer: MockWebServer

  @Throws(IOException::class)
  @Before
  fun mockServer() {
    mockWebServer = MockWebServer()
    mockWebServer.start()
  }

  fun enqueueMockResponse(fileName: String) {
    val inputStream = javaClass.classLoader!!.getResourceAsStream("api-response/$fileName")
    val source = inputStream.source().buffer()
    val mockResponse = MockResponse()
    mockWebServer.enqueue(mockResponse.setBody(source.readString(StandardCharsets.UTF_8)))
  }

  fun createService(clazz: Class<T>): T {
    return Retrofit.Builder()
      .baseUrl(mockWebServer.url("/"))
      .addConverterFactory(MoshiConverterFactory.create())
      .build()
      .create(clazz)
  }
}
