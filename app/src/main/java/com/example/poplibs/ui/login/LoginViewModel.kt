package com.example.poplibs.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.poplibs.domain.LoginUseCase
import com.example.poplibs.ui.AppState

class LoginViewModel (
    private val loginUseCase: LoginUseCase
        ) : ViewModel(), LoginContract.ViewModel {

    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()

    fun getData(): LiveData<AppState> = liveDataToObserve

    override fun onLogin(login: String, password: String) {
        liveDataToObserve.value = AppState.Loading
        loginUseCase.login(login, password) { result ->
            if (result) {
                liveDataToObserve.value = AppState.Success
            } else {
                liveDataToObserve.value = AppState.Error(MainActivity.MSG_WRONG_CREDENTIALS)
            }
        }
    }

    override fun onCredentialsConfirmation(login: String) {
        liveDataToObserve.value = AppState.Loading
        loginUseCase.credentialsConfirmation(login) { result ->
            if (result) {
                liveDataToObserve.value = AppState.InfoMessage(MainActivity.MSG_CONFIRMATION_EMAIL_SEND)
            } else {
                liveDataToObserve.value = AppState.Error(MainActivity.MSG_USER_IS_NOT_EXIST)
            }
        }
    }

    override fun onNewUserCreate(login: String, password: String) {
        liveDataToObserve.value = AppState.Loading
        loginUseCase.newUserCreate(login, password) { result ->
            if (result) {
                liveDataToObserve.value = AppState.InfoMessage(MainActivity.MSG_NEW_USER_CREATED)
            } else {
                liveDataToObserve.value = AppState.Error(MainActivity.MSG_LOGIN_IS_INCORRECT)
            }
        }
    }
}