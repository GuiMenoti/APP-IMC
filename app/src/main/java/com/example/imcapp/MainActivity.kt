package com.example.imcapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.imcapp.screens.IMCScreen
import com.example.imcapp.screens.LoginScreen
import com.example.imcapp.screens.MenuScreen
import com.example.imcapp.screens.PedidosScreen
import com.example.imcapp.screens.PerfilScreen
import com.example.imcapp.ui.theme.IMCAPPTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.animation.composable

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IMCAPPTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                  val navController = rememberAnimatedNavController()
                    AnimatedNavHost(
                        navController = navController,
                        startDestination = "login",
                        exitTransition = {
                            slideOutOfContainer(towards = AnimatedContentScope.SlideDirection.End,
                                tween(1000))
                        }
                    ){
                       composable(route = "login") {
                           LoginScreen(navController)
                       }
                        composable(route = "perfil/{nome}/{idade}",
                            arguments = listOf(
                                navArgument("nome") {
                                    type = NavType.StringType
                                },
                                navArgument("idade") {
                                    type = NavType.IntType
                                }
                            )

                            ) {
                            val nome: String? =
                                it.arguments?.getString("nome", "")
                            val idade: Int? =
                                it.arguments?.getInt("idade", 0)
                            PerfilScreen(navController, nome!!, idade!!)
                        }
                        composable(route = "menu") {
                            MenuScreen(navController)
                        }
                        composable(
                            route = "pedidos?cliente={cliente}",
                            arguments = listOf(navArgument(name = "cliente") {
                                defaultValue = "Sem Cliente"
                            })
                        ) {
                            PedidosScreen(navController, it.arguments?.getString("cliente")!!)
                        }
                        composable(route = "imc") {
                            IMCScreen(navController)
                        }

                    }
                }
            }
        }
    }
}


        // -- Card Resultado



