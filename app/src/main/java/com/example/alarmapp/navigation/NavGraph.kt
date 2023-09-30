package com.example.alarmapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.alarmapp.ui.screens.add.AddScreen
import com.example.alarmapp.ui.screens.home.Home

@Composable
fun SetupNavGraph(navHostController: NavHostController){
    NavHost(navController = navHostController, startDestination = Screens.Home.route){
        composable(Screens.Home.route){
            Home(navController = navHostController)
        }
        composable(
            route = Screens.Add.route + "?title={title}",
            arguments = listOf(navArgument("title"){
                type = NavType.StringType
                defaultValue = ""
                nullable = true
            })
        ){
            val title = it.arguments?.getString("title")
            title?.let {
                AddScreen(navController = navHostController, title = title)
            }
        }
    }
}