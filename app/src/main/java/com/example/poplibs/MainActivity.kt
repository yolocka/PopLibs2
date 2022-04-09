package com.example.poplibs

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
import com.example.poplibs.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), LoginContract.View {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val presenter: LoginContract.Presenter by lazy {
        restorePresenter()
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
            presenter.onNewUserCreate()
        }

        binding.forgetPasswordButton.setOnClickListener {
            presenter.onCredentialsConfirmation()
        }
    }

    private fun restorePresenter(): LoginPresenter {
        val presenter = lastCustomNonConfigurationInstance as? LoginPresenter
        return presenter ?: LoginPresenter()
    }

    override fun onRetainCustomNonConfigurationInstance(): Any? {
        return presenter
    }

    override fun setSuccess() {
        binding.loginElementsGroup.isVisible = false
        binding.root.setBackgroundColor(Color.GRAY)
    }

    override fun setError(error: Int) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun setInfoMessage(message: Int) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showProgress() {
        binding.progressBarScreen.isVisible = true
        hideKeyboard(this)
    }

    override fun hideProgress() {
        binding.progressBarScreen.isVisible = false
    }

    override fun getHandler(): Handler {
        return Handler(Looper.getMainLooper())
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