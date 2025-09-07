package com.example.saludappble.views

import android.webkit.WebSettings
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.saludappble.utils.NetworkUtils
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.saludappble.viewModel.UsuarioViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecoverScreen(navController: NavController, viewModel: UsuarioViewModel) {
    val context = LocalContext.current

    var correo by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { TopAppBar(
            title = {
                Text("Recuperar contraseña",
                    color = Color(0xFFCECECE),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFF272635) // color del topbar
            )
        ) },
        containerColor = Color(0xFFE8E9F3)
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Ingresa tu correo para recuperar tu contraseña",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp),
                color = Color(0xFF474652)
            )

            OutlinedTextField(
                value = correo,
                onValueChange = { correo = it },
                label = { Text("Correo electrónico") },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFCECECE),
                    unfocusedContainerColor = Color(0xFFCECECE),
                    focusedTextColor = Color(0xFF474652),
                    unfocusedTextColor = Color(0xFF474652),
                    focusedIndicatorColor = Color(0xFF272635),
                    unfocusedIndicatorColor = Color(0xFF272635),
                    focusedLabelColor = Color(0xFF272635),
                    unfocusedLabelColor = Color(0xFF474652),
                ),
                singleLine = true,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    TextButton(onClick = { passwordVisible = !passwordVisible }) {
                        Text(if (passwordVisible) "Ocultar" else "Mostrar", color = Color(0xFF272635))
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (!NetworkUtils.requireInternet(context)) return@Button
                    val usuario = viewModel.obtenerUsuarios().find { it.correo.equals(correo.trim(), ignoreCase = true) }
                    if (usuario != null) {
                        Toast.makeText(context, "Tu contraseña es: ${usuario.contrasena}", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "El correo ingresado no está registrado", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp), // opcional para tamaño consistente
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF474652), // fondo del botón
                    contentColor = Color(0xFFCECECE)    // color del texto
                ),
                shape = RoundedCornerShape(8.dp) // opcional: bordes ligeramente redondeados
            ) {
                Text("Recuperar contraseña")
            }

            Spacer(modifier = Modifier.height(8.dp))

            TextButton(
                onClick = { navController.popBackStack() }
            ) {
                Text(
                    text = "Volver al login",
                    color = Color(0xFF474652), // color de texto
                    fontSize = 14.sp
                )
            }
        }
    }
}