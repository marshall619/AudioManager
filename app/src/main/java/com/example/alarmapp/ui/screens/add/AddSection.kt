package com.example.alarmapp.ui.screens.add

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.alarmapp.ui.theme.simpleBackground

@Composable
fun AddScreen(navController: NavHostController , title : String){
    Add(navController = navController , title)
}

@Composable
private fun Add(navController: NavHostController, title : String){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.simpleBackground)){
        Text(text = title)
    }
}