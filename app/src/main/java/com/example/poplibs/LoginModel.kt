package com.example.poplibs

class LoginModel: LoginContract.Model {
    private var random: Int = 0

    companion object {
        const val ERROR: Int = 0
        const val SUCCESS: Int = 1
    }


    override fun addNewUser(): Int {
        random = (0..1).random()
        return if (random == 0) {
            ERROR
        } else {
            SUCCESS
        }
    }

    override fun getUserEmail(): Int {
        random = (0..1).random()
        return if (random == 0) {
            ERROR
        } else {
            SUCCESS
        }
    }

}