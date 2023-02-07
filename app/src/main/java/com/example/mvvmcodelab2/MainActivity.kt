package com.example.mvvmcodelab2

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmcodelab2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    /*
    API interceptor - okHttpClient
    Response keyword in retrofit
    Suspend function
    */
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofitService = RetrofitService.getInstance()
        val viewModel: RepoViewModel = ViewModelProvider(
            this,
            ViewModelFactory(DataRepository(retrofitService))
        ).get(RepoViewModel::class.java)

//        val viewModel = ViewModelProvider(this)[RepoViewModel(DataRepository(retrofitService))::class.java]

        val adapter = RvAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        viewModel.response.observe(this, Observer {
            if (it.isSuccessful) {
                Log.d("tagJi", it.body().toString())
                adapter.setMovieList(it.body()!!)
            } else {
                Log.d("tagJi", "Error: " + it.message())
            }
        })

        viewModel.getRepos()

        viewModel.response2.observe(this, Observer {
            if (it.isSuccessful) {
                Log.d("tagJi", "Name--> ${it.body()!!.name} Job--> ${it.body()!!.job}")
            } else {
                Log.d("tagJi", "Error: ${it.message()}")
            }
        })
        viewModel.createUser(DataModal("Prashant", "Developer"))



        /* viewModel.repoList.observe(this, Observer {
             Log.d("tagJi", "Data: $it")
             adapter.setMovieList(it)
         })

         viewModel.errors.observe(this, Observer {
             Log.d("tagJi", "Error: $it")
         })

         val response = viewModel.getRepos()
         if (response.isSuccessful) {
             viewModel.repoList.postValue(response.body())
         } else {

         }*/
    }


}