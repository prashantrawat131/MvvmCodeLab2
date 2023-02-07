package com.example.mvvmcodelab2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class RepoViewModel constructor(val repository: DataRepository) : ViewModel() {
    val response = MutableLiveData<Response<List<Repo>>>()
    val response2 = MutableLiveData<Response<DataModal>>()

    fun createUser(dataModal: DataModal) {
        CoroutineScope(Dispatchers.IO).launch {
            response2.postValue(repository.createUser(dataModal))
        }
        /* repository.createUser(dataModal).enqueue(object : Callback<DataModal> {
             override fun onResponse(call: Call<DataModal>, response: Response<DataModal>) {
                 Log.d("tagJi", "name: ${response.body()!!.name} job:${response.body()!!.job}")
             }

             override fun onFailure(call: Call<DataModal>, t: Throwable) {
                 Log.d("tagJi", "error: ${t.message}")
             }
         })*/
    }

    fun getRepos() {
        CoroutineScope(Dispatchers.IO).launch {
            response.postValue(repository.getRepos())
        }
//        return response
//        response.enqueue(object : Callback<List<Repo>> {
//            override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
//                repoList.postValue(response.body())
//            }
//
//            override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
//                errors.postValue(t.message)
//            }
//        })
    }
}