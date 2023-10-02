package com.example.alarmapp.ui.screens.add

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.alarmapp.R
import com.example.alarmapp.data.model.Time
import com.example.alarmapp.navigation.Screens
import com.example.alarmapp.ui.theme.addButtonColor
import com.example.alarmapp.ui.theme.editTextBackground
import com.example.alarmapp.ui.theme.simpleBackground
import com.example.alarmapp.ui.theme.simpleTextColor
import com.example.alarmapp.viewModel.TimeDbViewModel

@Composable
fun AddScreen(navController: NavHostController, title: String) {
    Add(navController = navController, title)
}

@Composable
private fun Add(
    navController: NavHostController,
    title: String,
    viewModel: TimeDbViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    var sTime by remember { mutableStateOf(SimpleTime(0, 0)) }
    var eTime by remember { mutableStateOf(SimpleTime(0, 0)) }
    var isNext by remember { mutableStateOf(false) }
    var buttonText by remember { mutableStateOf("Enter next time") }

    var myTitle by remember { mutableStateOf("") }

    buttonText = if (isNext) "submit" else "Enter next time"


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.simpleBackground)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            item {
                AddTopBar(navController, title, isNext) {
                    isNext = it
                }
            }
            item {
                if (!isNext) {
                    MyTimePicker { sTime = it }
                } else {
                    MyTimePicker { eTime = it }
                }
            }
            item { MyEditableText { myTitle = it } }
            item {
                Spacer(modifier = Modifier.height(20.dp))
                AddButton(buttonText) {
                    if (!isNext) {
                        isNext = true
                    } else {
                        if (myTitle.isNotBlank()) {
                            Log.e("619", "start =$sTime , end = $eTime")
                            if (checkTimeValidation(sTime, eTime)) {
                                viewModel.insertNewTimeAction(
                                    Time(
                                        s_hour = sTime.hour,
                                        s_min = sTime.min,
                                        e_hour = eTime.hour,
                                        e_min = eTime.min,
                                        title = myTitle,
                                        activeState = false,
                                        day = "th"
                                    )
                                )
                                navController.navigate(Screens.Home.route) {
                                    popUpTo(Screens.Add.route) {
                                        inclusive = true
                                    }
                                }
                            } else Toast.makeText(
                                context,
                                "The start and end time do not matches please change time and try again.",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(context, "Please fill the title !", Toast.LENGTH_SHORT)
                                .show()
                            Log.e("619", "Please fill the title ")
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun AddTopBar(
    navController: NavHostController,
    title: String,
    isNext: Boolean,
    onClickNext: (Boolean) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Box(modifier = Modifier
            .fillMaxWidth(0.1f)
            .padding(6.dp)
            .clip(CircleShape)
            .clickable {
                if (!isNext) {
                    navController.navigate(Screens.Home.route) {
                        popUpTo(Screens.Add.route) {
                            inclusive = true
                        }
                    }
                } else {
                    onClickNext(false)
                }
            }) {
            if (!isNext) {
                Icon(
                    painter = painterResource(id = R.drawable.plus),
                    contentDescription = "close",
                    modifier = Modifier
                        .size(25.dp)
                        .rotate(45f),
                    tint = MaterialTheme.colors.simpleTextColor
                )
            } else {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "close",
                    modifier = Modifier
                        .size(25.dp)
                        .rotate(0f),
                    tint = MaterialTheme.colors.simpleTextColor
                )
            }

        }

        Box(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(6.dp)
                .padding(start = 10.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = title,
                fontSize = 30.sp,
                color = MaterialTheme.colors.simpleTextColor,
                fontFamily = FontFamily.SansSerif
            )
        }

    }
}

@Composable
private fun MyEditableText(onTitle: (String) -> Unit) {
    var text by remember { mutableStateOf("") }

    onTitle(text)

    TextField(
        value = text,
        onValueChange = { text = it },
        placeholder = {
            Text(
                text = "Title",
                fontSize = 25.sp,
                color = MaterialTheme.colors.simpleTextColor
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .clip(RoundedCornerShape(20.dp)),
        colors = TextFieldDefaults.textFieldColors(
            textColor = MaterialTheme.colors.simpleTextColor,
            backgroundColor = MaterialTheme.colors.editTextBackground,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        textStyle = TextStyle(fontSize = 20.sp, color = MaterialTheme.colors.simpleTextColor)
    )
}

@Composable
private fun AddButton(title: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(horizontal = 20.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colors.addButtonColor)
            .clickable {
                onClick()
            }
            .padding(vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = title, color = MaterialTheme.colors.simpleTextColor, fontSize = 25.sp)
    }
}

private fun checkTimeValidation(sTime: SimpleTime, eTime: SimpleTime): Boolean {
    if (sTime.hour > eTime.hour) {
        return false
    } else if (sTime.hour == eTime.hour) {
        return sTime.min < eTime.min
    } else return true
}