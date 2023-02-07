package com.example.mvvmcodelab2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory constructor(private val repository: DataRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(RepoViewModel::class.java)) {
            RepoViewModel(this.repository) as T
        } else {
            throw java.lang.IllegalArgumentException("ViewModel not found")
        }
    }
}