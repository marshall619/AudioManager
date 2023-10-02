package com.example.alarmapp.ui.screens.home

import android.annotation.SuppressLint
import android.content.Context
import android.media.AudioManager
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getSystemService
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.alarmapp.R
import com.example.alarmapp.data.model.Time
import com.example.alarmapp.navigation.Screens
import com.example.alarmapp.ui.screens.add.SimpleTime
import com.example.alarmapp.ui.theme.bottomHomeBrush
import com.example.alarmapp.ui.theme.boxColors
import com.example.alarmapp.ui.theme.boxPlusColors
import com.example.alarmapp.ui.theme.simpleBackground
import com.example.alarmapp.util.Constants
import com.example.alarmapp.viewModel.TimeDbViewModel
import kotlinx.coroutines.delay
import java.util.Calendar

@Composable
fun Home(navController: NavHostController) {
    HomeScreen(navController = navController)
}

@SuppressLint("UnrememberedMutableState")
@Composable
private fun HomeScreen(
    navController: NavHostController,
    viewModel: TimeDbViewModel = hiltViewModel(),
) {
    val isVisible = rememberSaveable { mutableStateOf(true) }

    LaunchedEffect(true) {
        viewModel.allTimesInDay("th")
    }


    val timesList by viewModel.allTimeInDayList.collectAsState()

    LogicSection(timesList)

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                if (available.y < -1) {
                    isVisible.value = false
                }
                if (available.y > 1) {
                    isVisible.value = true
                }
                return Offset.Zero
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.simpleBackground),
        contentAlignment = Alignment.BottomCenter
    )
    {
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 10.dp)
                    .nestedScroll(nestedScrollConnection)
            ) {
                items(timesList) {
                    AlarmCardItem(
                        item = it
                    ) {
                        navController.navigate(Screens.Add.route + "?title=Edit Time")
                    }
                }
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp)
                            .background(Color.Transparent)
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(Brush.verticalGradient(MaterialTheme.colors.bottomHomeBrush))
        )
        AnimatedVisibility(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 40.dp),
            visible = isVisible.value,
            enter = slideInVertically(initialOffsetY = { it * 2 }),
            exit = slideOutVertically(targetOffsetY = { it * 2 }),
        ) {
            Surface(
                shape = CircleShape,
                elevation = 6.dp,
                color = MaterialTheme.colors.boxColors,
            ) {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colors.secondary)
                        .clickable {
                            navController.navigate(Screens.Add.route + "?title=Add Time")
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.add),
                        contentDescription = "add Icon",
                        tint = MaterialTheme.colors.boxPlusColors,
                        modifier = Modifier.size(25.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun LogicSection(timeList: List<Time>) {
    val calendar = Calendar.getInstance()
    var currentTime by remember { mutableStateOf(SimpleTime(0, 0)) }

    for (item in timeList) {
        if (item.activeState) {
            DoOnOffSilentMode(item, currentTime)
        }
    }

    var hour by remember { mutableStateOf(0) }
    var min by remember { mutableStateOf(0) }
    var sec by remember { mutableStateOf(0) }

    hour = calendar.get(Calendar.HOUR_OF_DAY)
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
    currentTime.hour = hour
    currentTime.min = min

    LaunchedEffect(key1 = sec) {
        // Log.e("619","$hour:$min:$sec")
    }
}

@Composable
private fun DoOnOffSilentMode(time: Time, currentTime: SimpleTime) {
    val audioManager = Constants.audioManager

    Log.e(
        "619",
        "${time.s_hour} -- ${time.e_hour} , currentTime = ${currentTime.hour} : ${currentTime.min}"
    )
    if (currentTime.hour >= time.s_hour && currentTime.hour < time.e_hour){
        if (currentTime.min < time.e_min){
            audioManager.ringerMode = AudioManager.RINGER_MODE_VIBRATE
        }
    }

    if (currentTime.hour == time.e_hour && time.e_hour == time.s_hour){
        if (currentTime.min in time.s_min until time.e_min){
            audioManager.ringerMode = AudioManager.RINGER_MODE_VIBRATE
        }
    }

    if (currentTime.hour == time.s_hour && currentTime.min == time.s_min){
        audioManager.ringerMode = AudioManager.RINGER_MODE_VIBRATE
    }
    if (currentTime.hour == time.e_hour && currentTime.min == time.e_min){
        audioManager.ringerMode = AudioManager.RINGER_MODE_NORMAL
    }
}