package com.example.poplibs.ui.login

import android.app.Activity
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.poplibs.app
import com.example.poplibs.databinding.ActivityMainBinding
import com.example.poplibs.ui.AppState

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var viewModel: LoginViewModel

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

        viewModel = ViewModelProvider(
            this,
            LoginViewModelFactory (app.loginUseCase)
        ).get(LoginViewModel::class.java)

        viewModel.getData().observe(this) { state
            ->
            render(state)
        }
        binding.enterButton.setOnClickListener {
            viewModel.onLogin(
                binding.loginEditText.text.toString(),
                binding.passwordEditText.text.toString()
            )
        }
        binding.registrationButton.setOnClickListener {
            viewModel.onNewUserCreate(
                binding.loginEditText.text.toString(),
                binding.passwordEditText.text.toString()
            )
        }

        binding.forgetPasswordButton.setOnClickListener {
            viewModel.onCredentialsConfirmation(
                binding.loginEditText.text.toString()
            )
        }
    }

    private fun render(state: AppState?) {
        when (state) {
            is AppState.Success -> {
                hideProgress()
                setSuccess()
            }
            is AppState.Error -> {
                hideProgress()
                setError(state.error)
            }
            is AppState.Loading -> {
                showProgress()
            }
            is AppState.InfoMessage -> {
                hideProgress()
                setInfoMessage(state.message)
            }
            else -> {}
        }
    }

    private fun setSuccess() {
        binding.loginElementsGroup.isVisible = false
        binding.root.setBackgroundColor(Color.GRAY)
    }


    private fun setError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }


    private fun setInfoMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    private fun showProgress() {
        binding.progressBarScreen.isVisible = true
        hideKeyboard(this)
    }


    private fun hideProgress() {
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