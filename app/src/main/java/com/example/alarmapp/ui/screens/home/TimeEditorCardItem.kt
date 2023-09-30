package com.example.alarmapp.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alarmapp.data.model.Time
import com.example.alarmapp.ui.theme.boxColors
import com.example.alarmapp.ui.theme.simpleTextColor
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.foundation.shape.CircleShape

@Composable
fun AlarmCardItem(item: Time, fontSize: Int = 25, onClick: () -> Unit) {

    val startHour = if (item.s_hour < 10) "0${item.s_hour}" else "${item.s_hour}"
    val startMin = if (item.s_min < 10) "0${item.s_min}" else "${item.s_min}"
    val endHour = if (item.e_hour < 10) "0${item.e_hour}" else "${item.e_hour}"
    val endMin = if (item.e_min < 10) "0${item.e_min}" else "${item.e_min}"
    var isButtonOn by remember{ mutableStateOf(item.activeState)}

    Surface(
        elevation = 4.dp,
        color = MaterialTheme.colors.boxColors,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.padding(vertical = 10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
                .clickable { onClick() }
                .padding(vertical = 6.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colors.boxColors)
                .padding(horizontal = 28.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "$startHour:$startMin",
                            color = MaterialTheme.colors.simpleTextColor,
                            fontSize = fontSize.sp
                        )
                        Box(
                            modifier = Modifier
                                .height(2.dp)
                                .width(60.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .background(MaterialTheme.colors.secondary)
                        )
                        Text(
                            text = "$endHour:$endMin",
                            color = MaterialTheme.colors.simpleTextColor,
                            fontSize = fontSize.sp
                        )
                    }

                    Text(
                        text = item.title,
                        color = Color(0xff5C5C5C),
                        fontSize = 16.sp
                    )
                }
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ThemeSwitcher(
                        isOn = isButtonOn,
                        size = 35.dp,
                        padding = 5.dp,
                    ){
                        isButtonOn = !isButtonOn
                    }
                }
            }
        }
    }
}

@Composable
fun ThemeSwitcher(
    isOn : Boolean,
    size: Dp = 150.dp,
    padding: Dp = 10.dp,
    parentShape: Shape = CircleShape,
    toggleShape: Shape = CircleShape,
    animationSpec: AnimationSpec<Dp> = tween(durationMillis = 300),
    onClick: () -> Unit
) {
    val offset by animateDpAsState(
        targetValue = if (isOn) 0.dp else size,
        animationSpec = animationSpec
    )

    val backGroundColor = if (!isOn) MaterialTheme.colors.secondary else Color(0xffE6E6E6)
    val backGroundCircleColor = if (isOn) MaterialTheme.colors.secondary else Color(0xffE6E6E6)

    Box(modifier = Modifier
        .width(size * 2)
        .height(size)
        .clip(shape = parentShape)
        .clickable { onClick() }
        .background(backGroundColor)
    ) {
        Box(
            modifier = Modifier
                .size(size)
                .offset(x = offset)
                .padding(all = padding)
                .clip(shape = toggleShape)
                .background(backGroundCircleColor)
        )
        Row(
            modifier = Modifier
        ) {
            Box(
                modifier = Modifier.size(size),
            )
            Box(
                modifier = Modifier.size(size),
            )
        }
    }
}