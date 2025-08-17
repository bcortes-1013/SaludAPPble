package com.example.saludappble.views

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.saludappble.model.Receta
import com.example.saludappble.model.recetasSemana
import com.example.saludappble.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(navController: NavController, nombreUsuario: String) {

    var selectedDia by remember { mutableStateOf("Lunes") }

    // Filtra recetas del día seleccionado
    val recetasDelDia = recetasSemana.filter { it.dia == selectedDia }

    Scaffold(
        topBar = {
            TopAppBar(
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
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "¡Hola, $nombreUsuario!",
                    style = MaterialTheme.typography.headlineSmall
                )

                TextButton(
                    onClick = {
                        navController.navigate(Routes.Login.route) {
                            popUpTo(Routes.Menu.route) { inclusive = true }
                        }
                    }
                ) {
                    Text("Cerrar sesión", color = Color(0xFF474652))
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Dropdown para seleccionar día
            var expanded by remember { mutableStateOf(false) }
            Box(modifier = Modifier.fillMaxWidth()) {
                // Botón del dropdown
                Button(
                    onClick = { expanded = true },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF474652), // fondo del botón
                        contentColor = Color(0xFFCECECE)    // color del texto
                    ),
                    shape = RoundedCornerShape(8.dp)       // opcional, mismo estilo que otros botones
                ) {
                    Text("Día: $selectedDia")
                }

                // Dropdown menu
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFCECECE)) // fondo del menú
                ) {
                    recetasSemana.map { it.dia }.distinct().forEach { dia ->
                        DropdownMenuItem(
                            text = { Text(dia, color = Color(0xFF474652)) },
                            onClick = {
                                selectedDia = dia
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Grid 2x2 de recetas del día
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    recetasDelDia.chunked(2)[0].forEach { receta ->
                        MenuCardFavorito(receta = receta, modifier = Modifier.weight(1f))
                    }
                }
                Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    recetasDelDia.chunked(2)[1].forEach { receta ->
                        MenuCardFavorito(receta = receta, modifier = Modifier.weight(1f))
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Tabla de detalle nutricional
            Text("Detalle Nutricional", style = MaterialTheme.typography.titleMedium)
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                    Text("Nombre", modifier = Modifier.weight(2f))
                    Text("Cal", modifier = Modifier.weight(1f))
                    Text("Prot", modifier = Modifier.weight(1f))
                    Text("Carb", modifier = Modifier.weight(1f))
                    Text("Gras", modifier = Modifier.weight(1f))
                }
                Divider()
                recetasDelDia.forEach { receta ->
                    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                        Text(receta.nombre, modifier = Modifier.weight(2f))
                        Text("${receta.calorias}", modifier = Modifier.weight(1f))
                        Text("${receta.proteinas}", modifier = Modifier.weight(1f))
                        Text("${receta.carbohidratos}", modifier = Modifier.weight(1f))
                        Text("${receta.grasas}", modifier = Modifier.weight(1f))
                    }
                    Divider()
                }
            }
        }
    }
}

@Composable
fun MenuCardFavorito(receta: Receta, modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFCECECE)), // <-- color de fondo
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(
                onClick = { receta.favorito.value = !receta.favorito.value }
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorito",
                    tint = if (receta.favorito.value) Color.Red else Color.Gray,
                    modifier = Modifier.size(32.dp)
                )
            }

            Text(
                text = receta.nombre,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
            Text(
                text = "${receta.tipo} • ${receta.calorias} kcal",
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center
            )
        }
    }
}