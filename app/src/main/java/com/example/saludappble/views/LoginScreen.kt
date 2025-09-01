package com.example.saludappble.views

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.*
import com.example.saludappble.model.UsuarioRepositorio
import com.example.saludappble.navigation.Routes
import com.example.saludappble.R
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.saludappble.utils.NetworkUtils

@Composable
fun AnimacionLottie() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.menu_principal))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever
    )

    LottieAnimation(
        composition = composition,
        progress = progress,
        modifier = Modifier
            .size(380.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {

    val context = LocalContext.current

    var nombreUsuario by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { TopAppBar(
            title = {
                Text("Iniciar sesión",
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
            AnimacionLottie() // Tu animación arriba
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = nombreUsuario,
                onValueChange = { nombreUsuario = it },
                label = { Text("Nombre de usuario") },
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
                )
            )

            OutlinedTextField(
                value = contrasena,
                onValueChange = { contrasena = it },
                label = { Text("Contraseña") },
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
                    val usuario = UsuarioRepositorio.buscarUsuarioPorNombreUsuario(nombreUsuario, contrasena)
                    if (usuario != null) {
                        // Usuario encontrado → Ir al menú
                        navController.navigate("menu/${usuario.nombreUsuario}")
                    } else {
                        // Usuario incorrecto
                        Toast.makeText(context, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show()
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
                Text(text = "Iniciar sesión", fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(8.dp))

            TextButton(
                onClick = { navController.navigate(Routes.Register.route) }
            ) {
                Text(
                    text = "Registrarse",
                    color = Color(0xFF474652), // color de texto
                    fontSize = 14.sp
                )
            }

            TextButton(
                onClick = { navController.navigate(Routes.Recover.route) }
            ) {
                Text(
                    text = "Recuperar contraseña",
                    color = Color(0xFF474652), // color de texto
                    fontSize = 14.sp
                )
            }
        }
    }
}