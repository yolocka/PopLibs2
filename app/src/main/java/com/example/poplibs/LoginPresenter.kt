package com.example.poplibs

import java.lang.Thread.sleep

class LoginPresenter: LoginContract.Presenter {
    private var view: LoginContract.View? = null
    private val model: LoginModel by lazy {
        LoginModel()
    }
    var random: Int = 0

    override fun onAttach(view: LoginContract.View) {
        this.view = view
    }

    override fun onLogin(login: String, password: String) {
        view?.showProgress()
        random = (0..1).random()
        Thread {
            sleep (3000)
            view?.getHandler()?.post {
                if (random == 0) {
                    view?.hideProgress()
                    view?.setSuccess()
                } else {
                    view?.hideProgress()
                    view?.setError(R.string.msg_wrong_credentials)
                }
            }
        }.start()
    }

    override fun onCredentialsConfirmation() {
        if (model.getUserEmail() == 0) {
            view?.setInfoMessage(R.string.msg_confirmation_email_send)
        } else {
            view?.setError(R.string.msg_user_is_not_exist)
        }
    }

    override fun onNewUserCreate() {
        if (model.addNewUser() == 0) {
            view?.setInfoMessage(R.string.msg_new_user_created)
        } else {
            view?.setError(R.string.msg_user_is_already_exist)
        }
    }
}