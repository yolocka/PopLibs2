package com.example.poplibs.data

import com.example.poplibs.domain.LoginApi

class WebLoginApiImpl: LoginApi {
    override fun login(login: String, password: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun newUser(login: String, password: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun restorePassword(email: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun logout(): Boolean {
        TODO("Not yet implemented")
    }
}