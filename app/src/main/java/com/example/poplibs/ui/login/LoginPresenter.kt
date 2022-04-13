package com.example.poplibs.ui.login

import com.example.poplibs.domain.LoginUseCase

class LoginPresenter(
    private val loginUseCase: LoginUseCase
): LoginContract.Presenter {
    private var view: LoginContract.View? = null

    override fun onAttach(view: LoginContract.View) {
        this.view = view
    }

    override fun onLogin(login: String, password: String) {
        view?.showProgress()
        loginUseCase.login(login, password) { result ->
            view?.hideProgress()
            if (result) {
                view?.setSuccess()
            } else {
                view?.setError(MainActivity.MSG_WRONG_CREDENTIALS)
            }
        }
    }

    override fun onCredentialsConfirmation(login: String) {
        view?.showProgress()
        loginUseCase.credentialsConfirmation(login) { result ->
            view?.hideProgress()
            if (result) {
                view?.setInfoMessage(MainActivity.MSG_CONFIRMATION_EMAIL_SEND)
            } else {
                view?.setError(MainActivity.MSG_USER_IS_NOT_EXIST)
            }
        }
    }

    override fun onNewUserCreate(login: String, password: String) {
        view?.showProgress()
        loginUseCase.newUserCreate(login, password) { result ->
            view?.hideProgress()
            if (result) {
                view?.setInfoMessage(MainActivity.MSG_NEW_USER_CREATED)
            } else {
                view?.setError(MainActivity.MSG_LOGIN_IS_INCORRECT)
            }
        }
    }
}