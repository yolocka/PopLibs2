package com.example.poplibs.domain.entities

data class UserProfile(
        val id: Int,
        val login: String,
        val password: String,
        val email: String
        )