package com.example.alarmapp

import android.app.AlarmManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.alarmapp.ui.components.AppConfig
import com.example.alarmapp.ui.components.GetPermission
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    lateinit var navHostController : NavHostController
    lateinit var nm : NotificationManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {


            nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            navHostController = rememberNavController()
            AppConfig(navController = navHostController , nm){
                startActivity(Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS))
                restartApp()
            }
            /*
            val calendar = Calendar.getInstance()

            var hour by remember { mutableStateOf(0) }
            var min by remember { mutableStateOf(0) }
            var sec by remember { mutableStateOf(0) }

            hour = calendar.get(Calendar.HOUR)
            min = calendar.get(Calendar.MINUTE)
            sec = calendar.get(Calendar.SECOND)

            LaunchedEffect(Unit) {
                while (true) {
                    val cal = Calendar.getInstance()
                    hour = cal.get(Calendar.HOUR_OF_DAY)
                    min = cal.get(Calendar.MINUTE)
                    sec = cal.get(Calendar.SECOND)

                    delay(1000)
                }
            }




            val audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
            val viewModel by viewModels<TimeDbViewModel>()
            viewModel.allTimesInDay("th")
            var allList by remember {
                mutableStateOf(emptyList<Time>())
            }

            LaunchedEffect(true){
                viewModel.allTimeInDayList.collectLatest {
                    allList = it
                }
            }*/
        }
    }
    private fun restartApp() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        val mPendingIntentId: Int = 102
        val mPendingIntent = PendingIntent.getActivity(
            applicationContext,
            mPendingIntentId,
            intent,
            PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val mgr = applicationContext.getSystemService(ALARM_SERVICE) as AlarmManager
        mgr[AlarmManager.RTC, System.currentTimeMillis() + 100] = mPendingIntent
        System.exit(0)
    }

}