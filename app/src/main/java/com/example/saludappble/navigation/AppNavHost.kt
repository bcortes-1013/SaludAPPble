package com.example.saludappble.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.saludappble.viewModel.UsuarioViewModel
import com.example.saludappble.views.LoginScreen
import com.example.saludappble.views.RegisterScreen
import com.example.saludappble.views.RecoverScreen
import com.example.saludappble.views.MenuScreen

sealed class Routes(val route: String) {
    object Login : Routes("login")
    object Register : Routes("register")
    object Recover : Routes("recover")
    object Menu : Routes("menu/{nombreUsuario}") {
        fun createRoute(nombreUsuario: String) = "menu/$nombreUsuario"
    }
}
@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val usuarioViewModel: UsuarioViewModel = viewModel()

    NavHost(navController, startDestination = Routes.Login.route) {
        composable(Routes.Login.route) { LoginScreen(navController, usuarioViewModel) }
        composable(Routes.Register.route) { RegisterScreen(navController, usuarioViewModel) }
        composable(Routes.Recover.route) { RecoverScreen(navController, usuarioViewModel) }
        composable(
            route = Routes.Menu.route,
            arguments = listOf(navArgument("nombreUsuario") { type = NavType.StringType })
        ) { backStackEntry ->
            val nombreUsuario = backStackEntry.arguments?.getString("nombreUsuario") ?: ""
            MenuScreen(navController = navController, nombreUsuario = nombreUsuario)
        }
    }
}

@Composable
fun MenuScreen(x0: NavHostController, x1: String) {
    TODO("Not yet implemented")
}