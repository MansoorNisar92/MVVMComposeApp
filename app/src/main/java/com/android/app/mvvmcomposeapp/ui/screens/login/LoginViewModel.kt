package com.android.app.mvvmcomposeapp.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.app.mvvmcomposeapp.data.local.AppDatabase
import com.android.app.mvvmcomposeapp.data.local.entities.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val appDatabase: AppDatabase
) : ViewModel() {

    fun setUsername(username: String) {
        viewModelScope.launch {
            appDatabase.userDao().insertUser(User(username = username))
        }
    }
}