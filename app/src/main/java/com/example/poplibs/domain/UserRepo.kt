package com.example.poplibs.domain

import com.example.poplibs.domain.entities.UserProfile

interface UserRepo {
    fun addUser(user: UserProfile)
    fun getUser(id: Int)
    fun getAllUsers()
    fun changeUser(id: Int, user: UserProfile)
    fun deleteUser(id: Int)
    fun deleteAllUsers()
}