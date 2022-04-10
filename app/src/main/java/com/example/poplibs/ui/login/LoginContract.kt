package com.example.poplibs.ui.login

import android.os.Handler

class LoginContract {

    interface View {
        fun setSuccess()
        fun setError(error: String)
        fun setInfoMessage(message: String)
        fun showProgress()
        fun hideProgress()
    }

    interface Presenter {
        fun onAttach(view: View)
        fun onLogin(login: String, password: String)
        fun onCredentialsConfirmation(login: String)
        fun onNewUserCreate(login: String, password: String)
    }
}