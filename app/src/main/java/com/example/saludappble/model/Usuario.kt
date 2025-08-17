package com.example.saludappble.model

data class Usuarios (
    val nombreUsuario: String,
    val nombre: String,
    val ApellidoP: String,
    val ApellidoM: String,
    val correo: String,
    val contrasena: String,
    val genero: String
)

object UsuarioRepositorio {
    val usuarioslist = mutableListOf<Usuarios>()

    init {
        // Usuario de prueba estático
        usuarioslist.add(
            Usuarios(
                nombreUsuario = "admin",
                nombre = "Administrador",
                ApellidoP = "Prueba",
                ApellidoM = "Demo",
                correo = "admin@demo.com",
                contrasena = "1234",
                genero = "masculino"
            )
        )
    }
    fun agregarUsuario(usuario: Usuarios) {
        usuarioslist.add(usuario)
    }
    fun obtenerUsuarios(): List<Usuarios> {
        return usuarioslist.toList()
    }
    fun buscarUsuarioPorNombreUsuario(nombreUsuario: String, contrasena: String): Usuarios? =
        usuarioslist.find { it.nombreUsuario.equals(nombreUsuario, ignoreCase = true) && it.contrasena == contrasena }
    fun buscarUsuarioPorCorreo(correo: String, contrasena: String): Usuarios? {
        return usuarioslist.find { it.correo == correo && it.contrasena == contrasena }
    }
}