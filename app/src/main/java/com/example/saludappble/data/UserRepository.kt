package com.example.saludappble.data

data class User(val username: String, val password: String)

object UserRepository {
    private val users = mutableListOf<User>()

    fun registerUser(username: String, password: String): Boolean {
        // Validar si ya existe
        if (users.any { it.username == username }) return false
        users.add(User(username, password))
        return true
    }

    fun login(username: String, password: String): Boolean {
        return users.any { it.username == username && it.password == password }
    }
}