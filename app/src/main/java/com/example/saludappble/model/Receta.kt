package com.example.saludappble.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class Receta(
    val dia: String,                // Lunes, Martes, etc.
    val nombre: String,             // Nombre de la receta
    val tipo: String,               // Desayuno, Almuerzo, Cena
    val calorias: Int,              // Calorías aproximadas
    val proteinas: Int,             // Proteínas en gramos
    val carbohidratos: Int,         // Carbohidratos en gramos
    val grasas: Int,                // Grasas en gramos
    val recomendaciones: String,    // Texto con recomendaciones nutricionales
    var favorito: MutableState<Boolean> = mutableStateOf(false)
)

val recetasSemana = listOf(
    // Lunes
    Receta("Lunes", "Ensalada César", "Almuerzo", 350, 20, 30, 15, "Alto en fibra y bajo en grasas"),
    Receta("Lunes", "Pollo a la plancha", "Cena", 400, 35, 20, 12, "Proteínas magras"),
    Receta("Lunes", "Sopa de verduras", "Almuerzo", 200, 10, 25, 5, "Bajo en calorías"),
    Receta("Lunes", "Frutas mixtas", "Desayuno", 150, 2, 35, 0, "Vitaminas y antioxidantes"),

    // Martes
    Receta("Martes", "Pasta integral", "Cena", 400, 15, 60, 10, "Energía sostenible"),
    Receta("Martes", "Tacos de pescado", "Almuerzo", 500, 35, 40, 20, "Rico en Omega-3"),
    Receta("Martes", "Ensalada de quinoa", "Almuerzo", 300, 12, 35, 8, "Fibra y proteínas"),
    Receta("Martes", "Yogur con frutas", "Desayuno", 180, 8, 25, 2, "Vitaminas y probióticos"),

    // Miércoles
    Receta("Miércoles", "Sopa de lentejas", "Almuerzo", 350, 20, 40, 10, "Proteínas vegetales"),
    Receta("Miércoles", "Pechuga de pavo", "Cena", 400, 35, 10, 8, "Bajo en grasas"),
    Receta("Miércoles", "Ensalada de espinaca", "Desayuno", 180, 5, 15, 5, "Vitaminas y fibra"),
    Receta("Miércoles", "Smoothie de frutas", "Snack", 150, 2, 30, 1, "Energía rápida"),

    // Jueves
    Receta("Jueves", "Arroz integral con verduras", "Almuerzo", 350, 12, 50, 8, "Fibra y carbohidratos"),
    Receta("Jueves", "Salmón al horno", "Cena", 500, 40, 10, 20, "Omega-3 y proteínas"),
    Receta("Jueves", "Tortilla de huevo y espinaca", "Desayuno", 250, 15, 5, 12, "Proteínas y vitaminas"),
    Receta("Jueves", "Frutos secos", "Snack", 200, 5, 10, 15, "Grasas saludables"),

    // Viernes
    Receta("Viernes", "Pasta con vegetales", "Almuerzo", 400, 12, 60, 10, "Energía sostenible"),
    Receta("Viernes", "Pollo al curry", "Cena", 450, 35, 25, 15, "Proteínas y sabor"),
    Receta("Viernes", "Ensalada de frutas", "Desayuno", 150, 2, 35, 0, "Vitaminas y antioxidantes"),
    Receta("Viernes", "Barritas de avena", "Snack", 180, 5, 30, 5, "Fibra y energía rápida"),

    // Sábado
    Receta("Sábado", "Tacos de carne magra", "Almuerzo", 500, 35, 40, 15, "Proteínas y fibra"),
    Receta("Sábado", "Pescado al vapor", "Cena", 400, 35, 10, 10, "Proteínas y Omega-3"),
    Receta("Sábado", "Huevos revueltos con verduras", "Desayuno", 300, 20, 10, 15, "Proteínas y vitaminas"),
    Receta("Sábado", "Smoothie verde", "Snack", 150, 2, 30, 1, "Vitaminas y energía"),

    // Domingo
    Receta("Domingo", "Lasaña de vegetales", "Almuerzo", 450, 20, 50, 18, "Fibra y carbohidratos"),
    Receta("Domingo", "Pollo al limón", "Cena", 400, 35, 15, 12, "Proteínas magras"),
    Receta("Domingo", "Pan integral con aguacate", "Desayuno", 250, 5, 30, 12, "Grasas saludables y fibra"),
    Receta("Domingo", "Yogur con granola", "Snack", 180, 8, 25, 3, "Proteínas y energía")
)