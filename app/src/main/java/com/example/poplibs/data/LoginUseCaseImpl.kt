package com.example.poplibs.data

import android.os.Handler
import com.example.poplibs.domain.LoginApi
import com.example.poplibs.domain.LoginUseCase

class LoginUseCaseImpl (
    private val api: LoginApi,
    private val uiHandler: Handler
    ) : LoginUseCase {
    override fun login(login: String, password: String, callback: (Boolean) -> Unit) {
        Thread {
            val result = api.login(login, password)
            uiHandler.post {
                callback(result)
            }
        }.start()
    }

    override fun credentialsConfirmation(login: String, callback: (Boolean) -> Unit) {
        Thread {
            val result = api.restorePassword(login)
            uiHandler.post {
                callback(result)
            }
        }.start()
    }

    override fun newUserCreate(login: String, password: String, callback: (Boolean) -> Unit) {
        Thread {
            val result = api.newUser(login, password)
            uiHandler.post {
                callback(result)
            }
        }.start()
    }
}