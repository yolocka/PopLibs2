package com.example.poplibs.ui

sealed class AppState {
    object Success : AppState()
    data class Error(val error: String) : AppState()
    data class InfoMessage(val message: String) : AppState()
    object Loading : AppState()
}
