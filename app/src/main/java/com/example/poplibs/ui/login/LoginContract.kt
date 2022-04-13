package com.example.poplibs.ui.login

import android.os.Handler
import com.example.poplibs.domain.LoginUseCase

class LoginContract {

    interface ViewModel {
        fun onLogin(login: String, password: String)
        fun onCredentialsConfirmation(login: String)
        fun onNewUserCreate(login: String, password: String)
    }
}