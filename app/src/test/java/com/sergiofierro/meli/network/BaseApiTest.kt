package com.sergiofierro.meli.network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.skydoves.sandwich.coroutines.CoroutinesResponseCallAdapterFactory
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException
import java.nio.charset.StandardCharsets

@RunWith(JUnit4::class)
abstract class BaseApiTest<T> {
  @Rule
  @JvmField
  val instantExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

  lateinit var mockWebServer: MockWebServer

  @Throws(IOException::class)
  @Before
  fun mockServer() {
    mockWebServer = MockWebServer()
    mockWebServer.start()
  }

  fun enqueueMockResponse(fileName: String) {
    // val mockResponse = MockResponse()
    // mockResponse.setBody(response)
    // mockWebServer.enqueue(mockResponse)

    val inputStream = javaClass.classLoader!!.getResourceAsStream("api-response/$fileName")
    val source = inputStream.source().buffer()
    val mockResponse = MockResponse()
    mockWebServer.enqueue(mockResponse.setBody(source.readString(StandardCharsets.UTF_8)))
  }

  fun createService(clazz: Class<T>): T {
    return Retrofit.Builder()
      .baseUrl(mockWebServer.url("/"))
      .addConverterFactory(MoshiConverterFactory.create())
      .addCallAdapterFactory(CoroutinesResponseCallAdapterFactory())
      .build()
      .create(clazz)
  }
}
