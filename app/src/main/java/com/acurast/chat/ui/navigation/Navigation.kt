package com.acurast.chat.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.acurast.chat.ui.screens.ChatScreen
import com.acurast.chat.ui.screens.SettingsScreen

@Composable
fun AcurastChatNavigation() {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = "chat"
    ) {
        composable("chat") {
            ChatScreen(
                onNavigateToSettings = {
                    navController.navigate("settings")
                }
            )
        }
        
        composable("settings") {
            SettingsScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}