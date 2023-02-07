package com.example.mvvmcodelab2

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface RetrofitService {

    @POST("https://reqres.in/api/users")
    suspend fun createPost(@Body dataModal: DataModal): Response<DataModal>

    @GET("users/prashantrawat131/repos")
    suspend fun getRepos(): Response<List<Repo>>

    companion object {
        var retrofitServiceInstance: RetrofitService? = null

        fun getInstance(): RetrofitService {
            val client = OkHttpClient().newBuilder().apply {
                readTimeout(1, TimeUnit.MINUTES)
            }

            client.interceptors().add(HeaderIntercepter())

            if (retrofitServiceInstance == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.github.com/")
                    .client(client.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                retrofitServiceInstance = retrofit.create(RetrofitService::class.java)
            }

            return retrofitServiceInstance!!
        }

    }
}