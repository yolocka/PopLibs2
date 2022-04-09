package com.example.poplibs

import android.os.Handler

class LoginContract {

    interface View {
        fun setSuccess()
        fun setError(error: Int)
        fun setInfoMessage(message: Int)
        fun showProgress()
        fun hideProgress()
        fun getHandler(): Handler
    }

    interface Presenter {
        fun onAttach(view: View)
        fun onLogin(login: String, password: String)
        fun onCredentialsConfirmation()
        fun onNewUserCreate()
    }

    interface Model {
        fun addNewUser(): Int
        fun getUserEmail(): Int
    }
}