package com.example.alarmapp.ui.screens.home

import android.annotation.SuppressLint
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.alarmapp.R
import com.example.alarmapp.ui.theme.bottomHomeBrush
import com.example.alarmapp.ui.theme.boxColors
import com.example.alarmapp.ui.theme.boxPlusColors
import com.example.alarmapp.ui.theme.simpleBackground

@Composable
fun Home(navController: NavHostController) {
    HomeScreen(navController = navController)
}

@SuppressLint("UnrememberedMutableState")
@Composable
private fun HomeScreen(navController: NavHostController) {
    val isVisible = rememberSaveable { mutableStateOf(true) }

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
                item {
                    Surface(
                        elevation = 4.dp,
                        color = MaterialTheme.colors.boxColors,
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.padding(vertical = 10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                                .padding(vertical = 6.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(MaterialTheme.colors.boxColors)
                        )
                    }
                    Surface(
                        elevation = 4.dp,
                        color = MaterialTheme.colors.boxColors,
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.padding(vertical = 10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                                .padding(vertical = 6.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(MaterialTheme.colors.boxColors)
                        )
                    }
                    Surface(
                        elevation = 4.dp,
                        color = MaterialTheme.colors.boxColors,
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.padding(vertical = 10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                                .padding(vertical = 6.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(MaterialTheme.colors.boxColors)
                        )
                    }
                    Surface(
                        elevation = 4.dp,
                        color = MaterialTheme.colors.boxColors,
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.padding(vertical = 10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                                .padding(vertical = 6.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(MaterialTheme.colors.boxColors)
                        )
                    }
                    Surface(
                        elevation = 4.dp,
                        color = MaterialTheme.colors.boxColors,
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.padding(vertical = 10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                                .padding(vertical = 6.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(MaterialTheme.colors.boxColors)
                        )
                    }
                    Surface(
                        elevation = 4.dp,
                        color = MaterialTheme.colors.boxColors,
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.padding(vertical = 10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                                .padding(vertical = 6.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(MaterialTheme.colors.boxColors)
                        )
                    }
                    Surface(
                        elevation = 4.dp,
                        color = MaterialTheme.colors.boxColors,
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.padding(vertical = 10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                                .padding(vertical = 6.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(MaterialTheme.colors.boxColors)
                        )
                    }
                    Surface(
                        elevation = 4.dp,
                        color = MaterialTheme.colors.boxColors,
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.padding(vertical = 10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                                .padding(vertical = 6.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(MaterialTheme.colors.boxColors)
                        )
                    }
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
            Surface(shape = CircleShape, elevation = 6.dp,color = MaterialTheme.colors.boxColors,) {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colors.secondary)
                        .clickable {
                            //todo go to add screen
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


