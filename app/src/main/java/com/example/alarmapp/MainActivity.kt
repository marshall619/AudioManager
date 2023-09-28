package com.example.alarmapp

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.alarmapp.data.model.Time
import com.example.alarmapp.ui.theme.AlarmAppTheme
import com.example.alarmapp.viewModel.TimeDbViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import java.util.Calendar

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

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


            val nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !nm.isNotificationPolicyAccessGranted) {
                startActivity(Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS))
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
            }
            
            AlarmAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "$hour : $min : $sec")
                        if (hour == 9 && min == 46) {
                            audioManager.ringerMode = AudioManager.RINGER_MODE_VIBRATE
                        }
                        if (hour == 9 && min == 47) {
                            audioManager.ringerMode = AudioManager.RINGER_MODE_NORMAL
                        }
                        Spacer(modifier = Modifier.height(40.dp))
                        Button(onClick = { 
                            viewModel.insertNewTimeAction(
                                Time(s_hour = 5, s_min =4 , e_min = 3, e_hour = 2, title = "idk", day = "th", activeState = true)
                            )
                        }) {
                            Text(text = "insert")
                        }
                        Button(onClick = { 
                            viewModel.deleteAllTimes()
                        }) {
                            Text(text = "delete all")
                        }
                        
                        LazyColumn(modifier = Modifier.fillMaxWidth()){
                            items(allList){
                                Text(text = it.title)
                            }
                        }
                    }
                }
            }
        }
    }
}