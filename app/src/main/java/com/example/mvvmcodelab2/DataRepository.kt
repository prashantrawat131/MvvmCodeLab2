package com.example.mvvmcodelab2

import retrofit2.Call
import retrofit2.Response

class DataRepository constructor(private val retrofitService: RetrofitService) {
    suspend fun getRepos(): Response<List<Repo>> {
        return retrofitService.getRepos()
    }

    suspend fun createUser(dataModal: DataModal): Response<DataModal> {
        return retrofitService.createPost(dataModal)
    }
}