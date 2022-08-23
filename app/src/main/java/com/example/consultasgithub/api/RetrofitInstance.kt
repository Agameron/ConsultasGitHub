package com.example.consultasgithub.api

import com.example.consultasgithub.util.Constants.Companion.BASE_URL
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitInstance {

    companion object {
        private fun headerInterceptor(): Interceptor {
            return Interceptor { chain ->
                chain.run {
                    proceed(
                        request()
                            .newBuilder()
                            .addHeader(
                                "Authorization",
                                Credentials.basic(
                                    "agameron",
                                    "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"
                                )
                            )
                            .addHeader("Accept", "application/vnd.github.v3+json")
                            .build()
                    )
                }
            }
        }

        private val retrofit by lazy {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(headerInterceptor())
                .addInterceptor(logging)
                .build()
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }
        val api by lazy {
            retrofit.create(GitHubAPI::class.java)
        }
    }
}