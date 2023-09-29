package com.example.alarmapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.alarmapp.ui.screens.home.Home

@Composable
fun SetupNavGraph(navHostController: NavHostController){
    NavHost(navController = navHostController, startDestination = Screens.Home.route){
        composable(Screens.Home.route){
            Home(navController = navHostController)
        }
    }
}