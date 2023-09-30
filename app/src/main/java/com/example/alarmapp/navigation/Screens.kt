package com.example.alarmapp.navigation

sealed class Screens(val route : String){
    object Home : Screens("home_screen")
    object Add : Screens("add_screen")

    fun withArgs(vararg args : Any) : String {
        return buildString {
            append(route)
            args.forEach {
                append("/$it")
            }
        }
    }
}
