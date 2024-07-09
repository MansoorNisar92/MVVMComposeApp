package com.android.app.mvvmcomposeapp.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.app.mvvmcomposeapp.data.local.AppDatabase
import com.android.app.mvvmcomposeapp.data.local.entities.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val appDatabase: AppDatabase
) : ViewModel() {

    init {
        viewModelScope.launch {
            appDatabase.medicationDao().deleteAllMedications()
        }
    }
    private val _userInserted = MutableStateFlow(false)
    val userInserted: StateFlow<Boolean> = _userInserted
    fun setUsername(username: String) {
        viewModelScope.launch {
            appDatabase.userDao().deleteUser()
            appDatabase.userDao().insertUser(User(username = username))
            _userInserted.value = true
        }
    }
}