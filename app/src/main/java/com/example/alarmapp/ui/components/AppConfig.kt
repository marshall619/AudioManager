package com.example.alarmapp.ui.components

import android.app.NotificationManager
import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.alarmapp.ui.theme.simpleBackground
import com.example.alarmapp.ui.theme.simpleTextColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun AppConfig(
    navController: NavHostController,
    notificationManager: NotificationManager,
    needPermission: () -> Unit,
) {
    GetPermission(notificationManager) {needPermission()}
    ChangeTopBarColor()
}

@Composable
fun GetPermission(notificationManager: NotificationManager, needPermission: () -> Unit) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !notificationManager.isNotificationPolicyAccessGranted) {
        Column(
            modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.simpleBackground),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Need notification permission please go to setting and grant permission .",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.simpleTextColor,
                fontFamily = FontFamily.SansSerif
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = { needPermission() }) {
                Text(text = "Grant Permission")
            }
        }
    }
}

@Composable
private fun ChangeTopBarColor() {
    val systemUiController = rememberSystemUiController()
    val mainColor = MaterialTheme.colors.simpleBackground
    SideEffect {
        systemUiController.setStatusBarColor(
            color = mainColor
        )
    }
}
