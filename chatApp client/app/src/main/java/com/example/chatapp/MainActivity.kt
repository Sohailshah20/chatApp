package com.example.chatapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.chatapp.presentation.chat.ChatScreen
import com.example.chatapp.presentation.username.UsernameScreen
import com.example.chatapp.ui.theme.ChatAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatAppTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "username_screen"
                ) {
                    composable("username_screen") {
                        UsernameScreen(onNavigate = navController::navigate)
                    }
                    composable(
                        route = "chat_screen/{username}",
                        arguments = listOf(
                            navArgument(name = "username") {
                                type = NavType.StringType
                                nullable = true
                            }
                        )
                    ) {
                        val username = it.arguments?.getString("username")
                        ChatScreen(username = username)
                    }
                }
            }
        }
    }
}
