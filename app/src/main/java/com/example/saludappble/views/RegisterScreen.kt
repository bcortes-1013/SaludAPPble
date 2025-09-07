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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.saludappble.utils.NetworkUtils
import com.example.saludappble.viewModel.UsuarioViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController, viewModel: UsuarioViewModel) {
    val context = LocalContext.current

    var rutError by remember { mutableStateOf(false) }
    var rut by remember { mutableStateOf("") }
    var nombreUsuario by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var apellidoP by remember { mutableStateOf("") }
    var apellidoM by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("") } // "" | "Masculino" | "Femenino"
    var aceptoTerminos by remember { mutableStateOf(false) }
    var passwordVisible by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { TopAppBar(
            title = {
                Text("Registro de usuario",
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
            OutlinedTextField(
                value = rut,
                onValueChange = {
                    rut = it
                    rutError = !esRutValido(it) // true si es inválido
                },
                label = { Text("Rut usuario (xxxxxxxx-x)") },
                isError = rutError,
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

            Spacer(modifier = Modifier.height(8.dp))

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
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
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
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = apellidoP,
                onValueChange = { apellidoP = it },
                label = { Text("Apellido Paterno") },
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
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = apellidoM,
                onValueChange = { apellidoM = it },
                label = { Text("Apellido Materno") },
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
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = correo,
                onValueChange = { correo = it },
                label = { Text("Correo") },
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
            Spacer(modifier = Modifier.height(8.dp))

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

            // 🎨 Colores de referencia
            val bordeColor = Color(0xFF272635)   // gris oscuro (borde/activo)
            val fondoColor = Color(0xFFCECECE)   // gris claro (fondo)
            val textoColor = Color(0xFF474652)   // texto

            // RadioButtons de género
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = genero == "Masculino",
                    onClick = { genero = "Masculino" },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = bordeColor,
                        unselectedColor = bordeColor
                    )
                )
                Text(
                    "Masculino",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(8.dp),
                    color = Color(0xFF474652)
                )

                Spacer(modifier = Modifier.width(16.dp))

                RadioButton(
                    selected = genero == "Femenino",
                    onClick = { genero = "Femenino" },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = bordeColor,
                        unselectedColor = bordeColor
                    )
                )

                Text(
                    "Femenino",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(8.dp),
                    color = Color(0xFF474652)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Checkbox de términos y condiciones
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = aceptoTerminos,
                    onCheckedChange = { aceptoTerminos = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = bordeColor,    // cuando está marcado
                        uncheckedColor = bordeColor,  // cuando está desmarcado
                        checkmarkColor = fondoColor   // ✔ interior
                    )
                )
                Text(
                    "Acepto términos y condiciones",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(8.dp),
                    color = Color(0xFF474652)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón de registro
            Button(
                onClick = {
                    if (!NetworkUtils.requireInternet(context)) return@Button
                    if (rut.isNotBlank() &&
                        nombreUsuario.isNotBlank() &&
                        correo.isNotBlank() &&
                        contrasena.isNotBlank() &&
                        genero.isNotBlank() &&
                        aceptoTerminos
                    ) {
                        // Guardar usuario
                        viewModel.agregarUsuario(
                            rut,
                            nombreUsuario,
                            nombre,
                            apellidoP,
                            apellidoM,
                            correo,
                            contrasena,
                            genero
                        )
                        Toast.makeText(context, "Usuario registrado", Toast.LENGTH_SHORT).show()
                        // Regresar al login
                        navController.navigate("login") {
                            popUpTo("register") { inclusive = true }
                        }
                    } else {
                        Toast.makeText(context, "Completa todos los campos y acepta los términos", Toast.LENGTH_SHORT).show()
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
                Text("Registrarse")
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

fun esRutValido(rut: String): Boolean {
    // Limpiar puntos y espacios
    val cleanRut = rut.replace(".", "").replace(" ", "").uppercase()

    // Validar formato
    if (!cleanRut.matches(Regex("^\\d{7,8}-[0-9K]$"))) return false

    val (numero, dv) = cleanRut.split("-")
    return calcularDv(numero) == dv
}

// Función que calcula el dígito verificador
fun calcularDv(rut: String): String {
    var suma = 0
    var factor = 2
    for (i in rut.reversed()) {
        suma += (i.toString().toInt()) * factor
        factor = if (factor < 7) factor + 1 else 2
    }
    val dv = 11 - (suma % 11)
    return when (dv) {
        11 -> "0"
        10 -> "K"
        else -> dv.toString()
    }
}