package com.example.poplibs

import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.Looper
import com.example.poplibs.data.LoginUseCaseImpl
import com.example.poplibs.data.MockLoginApiImpl
import com.example.poplibs.domain.LoginApi
import com.example.poplibs.domain.LoginUseCase

class App : Application() {
    private val loginApi: LoginApi by lazy { MockLoginApiImpl() }
    val loginUseCase: LoginUseCase by lazy {
        LoginUseCaseImpl(app.loginApi, Handler(Looper.getMainLooper()))
    }
}

val Context.app: App
    get() {
        return applicationContext as App
    }