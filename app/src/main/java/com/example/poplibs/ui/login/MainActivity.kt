package com.example.poplibs.ui.login

import android.app.Activity
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.poplibs.app
import com.example.poplibs.data.LoginUseCaseImpl
import com.example.poplibs.databinding.ActivityMainBinding
import com.example.poplibs.domain.LoginUseCase

class MainActivity : AppCompatActivity(), LoginContract.View {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val presenter: LoginContract.Presenter by lazy {
        restorePresenter()
    }

    companion object {
        const val MSG_NEW_USER_CREATED: String = "Учетная запись создана"
        const val MSG_CONFIRMATION_EMAIL_SEND: String = "Код восстановления отправлен на ваш Email"
        const val MSG_WRONG_CREDENTIALS: String = "Неправильный логин или пароль"
        const val MSG_USER_IS_NOT_EXIST: String = "Такой пользователь не зарегистирован"
        const val MSG_LOGIN_IS_INCORRECT: String = "Имя пользователя некорректно"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        presenter.onAttach(this)
        binding.enterButton.setOnClickListener {
            presenter.onLogin(
                binding.loginEditText.text.toString(),
                binding.passwordEditText.text.toString()
            )
        }
        binding.registrationButton.setOnClickListener {
            presenter.onNewUserCreate(
                binding.loginEditText.text.toString(),
                binding.passwordEditText.text.toString()
            )
        }

        binding.forgetPasswordButton.setOnClickListener {
            presenter.onCredentialsConfirmation(
                binding.loginEditText.text.toString(),
            )
        }
    }

    private fun restorePresenter(): LoginPresenter {
        val presenter = lastCustomNonConfigurationInstance as? LoginPresenter
        return presenter ?: LoginPresenter(app.loginUseCase)
    }

    override fun onRetainCustomNonConfigurationInstance(): Any? {
        return presenter
    }

    override fun setSuccess() {
        binding.loginElementsGroup.isVisible = false
        binding.root.setBackgroundColor(Color.GRAY)
    }

    override fun setError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun setInfoMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showProgress() {
        binding.progressBarScreen.isVisible = true
        hideKeyboard(this)
    }

    override fun hideProgress() {
        binding.progressBarScreen.isVisible = false
    }

    private fun hideKeyboard(activity: Activity) {
        val inputMethodManager: InputMethodManager = activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        var view: View? = activity.currentFocus
        if (view == null) {
            view = View(activity)
        }
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}