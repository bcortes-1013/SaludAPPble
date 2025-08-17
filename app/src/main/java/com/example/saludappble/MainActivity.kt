package com.example.saludappble

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.saludappble.navigation.AppNavHost
import com.example.saludappble.ui.theme.SaludappbleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            setContent {
                SaludappbleTheme {
                    AppNavHost()
                }
            }
        } catch (e: Exception) {
            Log.e("ComposeError", "Error en MenuScreen", e)
        }
    }
}