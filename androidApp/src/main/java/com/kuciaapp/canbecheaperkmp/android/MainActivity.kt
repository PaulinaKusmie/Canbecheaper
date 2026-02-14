package com.kuciaapp.canbecheaperkmp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kuciaapp.canbecheaperkmp.ui.theme.CanBeCheaperTheme
import com.kuciaapp.canbecheaperkmp.view.ConfigurationScreen
import com.kuciaapp.canbecheaperkmp.view.LoginScreen
import com.kuciaapp.canbecheaperkmp.view.MainScreen
import com.kuciaapp.canbecheaperkmp.view.ProductPriceScreen
import com.kuciaapp.canbecheaperkmp.view.RegisterScreen
import com.kuciaapp.canbecheaperkmp.android.utility.UserSessionAndroid
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    private val userSession by lazy { UserSessionAndroid(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            CanBeCheaperTheme {
                val userId by userSession.getUserIdFlow()
                    .collectAsState(initial = null)

                if (userId == null) {
                    var isChecking by remember { mutableStateOf(true) }
                    LaunchedEffect(Unit) {
                        delay(100)
                        isChecking = false
                    }

                    if (isChecking) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }

                        return@CanBeCheaperTheme
                    }
                }

                val startDestination = if (userId != null) "MainScreen" else "LoginScreen"



                NavHost(navController = navController, startDestination = startDestination) {
                    composable("LoginScreen") {
                        LoginScreen(navController)
                    }
                    composable("RegisterScreen") {
                        RegisterScreen(navController)
                    }
                    composable("MainScreen") {
                        MainScreen(navController, userSession)
                    }
                    composable("ConfigurationScreen") {
                        ConfigurationScreen(
                            navController,
                            onBack = { navController.popBackStack() })
                    }

                    composable(
                        route = "ProductPriceScreen/{type}",
                        arguments = listOf(navArgument("type") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val type = backStackEntry.arguments?.getInt("type") ?: 0

                        ProductPriceScreen(
                            navController = navController,
                            type = type,
                            onBack = { navController.popBackStack() }
                        )
                    }

                }


            }

        }
    }
}




