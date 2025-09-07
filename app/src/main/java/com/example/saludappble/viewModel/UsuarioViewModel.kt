package com.example.saludappble.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.saludappble.model.Usuario

class UsuarioViewModel: ViewModel() {

    private val _usuarios = mutableStateListOf<Usuario>();

//    val usuarios: List<Usuario> get() = _usuarios

    init {
        // Usuario de prueba estático
        _usuarios.add(
            Usuario(
                rut = "111111111",
                nombreUsuario = "admin",
                nombre = "Administrador",
                apellidoP = "Prueba",
                apellidoM = "Demo",
                correo = "admin@demo.com",
                contrasena = "1234",
                genero = "masculino"
            )
        )
    }

    fun agregar(usuario: Usuario) {

        if(_usuarios.any { it.rut.equals(usuario.rut, ignoreCase = true) }) return

        _usuarios.add(usuario)

    }

    fun buscar(query: String): List<Usuario> {

        if(query.isBlank()) return _usuarios

        val q = query.trim().lowercase()

        return _usuarios.filter {
            it.rut.lowercase().contains(q) ||
            it.nombre.lowercase().contains(q) ||
            it.apellidoP.lowercase().contains(q) ||
            it.apellidoM.lowercase().contains(q)
        }

    }

    fun agregarUsuario(
        rut: String,
        nombreUsuario: String,
        nombre: String,
        apellidoP: String,
        apellidoM: String,
        correo: String,
        contrasena: String,
        genero: String
    ) {
        val nuevo = Usuario(
            rut,
            nombreUsuario,
            nombre,
            apellidoP,
            apellidoM,
            correo,
            contrasena,
            genero
        )
        _usuarios.add(nuevo)
        Log.d("UsuarioViewModel", "Usuarios actuales: $_usuarios")
    }

    fun obtenerUsuarios(): List<Usuario> {
        return _usuarios.toList()
    }

    fun validarLogin(nombreUsuario: String, contrasena: String): Usuario? =
        _usuarios.find { it.nombreUsuario.equals(nombreUsuario, ignoreCase = true) && it.contrasena == contrasena
    }

}