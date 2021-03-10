/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalPizza
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.ui.theme.MyTheme

@ExperimentalAnimationApi
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

// Start building your app here!
@ExperimentalAnimationApi
@Composable
fun MyApp() {
    val viewModel: MainViewModel = viewModel()
    val remainingTime by viewModel.remainingTime.observeAsState()

    val crustColor by animateColorAsState(
        targetValue = viewModel.crustColor.observeAsState().value ?: Color.Black
    )
    val cheeseColor by animateColorAsState(
        targetValue = viewModel.cheeseColor.observeAsState().value ?: Color.Black
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Pizza Time", color = Color.White)
                },
                navigationIcon = {
                    Icon(
                        Icons.Filled.LocalPizza, contentDescription = "Pizza",
                        modifier = Modifier.padding(start = 24.dp)
                    )
                }
            )
        },
        content = {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Box(
                    modifier = Modifier.padding(top = 64.dp),
                    contentAlignment = Alignment.TopCenter
                ) {
                    AnimatedVisibility(remainingTime != 0L) {
                        Text(
                            style = MaterialTheme.typography.h3,
                            text = "Ready in $remainingTime",
                        )
                    }
                    AnimatedVisibility(remainingTime == 0L) {
                        Text(
                            style = MaterialTheme.typography.h3,
                            text = "Pizza Time!",
                        )
                    }
                }

                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Pizza(
                        crustColor = crustColor,
                        cheeseColor = cheeseColor
                    )
                }

                Box(
                    modifier = Modifier.padding(bottom = 128.dp),
                    contentAlignment = Alignment.Center
                ) {
                    AnimatedVisibility(remainingTime == 0L) {
                        Pepperoni()
                    }
                }

                Box(
                    modifier = Modifier.padding(start = 96.dp, bottom = 96.dp),
                    contentAlignment = Alignment.Center
                ) {
                    AnimatedVisibility(remainingTime == 0L) {
                        Pepperoni()
                    }
                }

                Box(
                    modifier = Modifier.padding(end = 96.dp, bottom = 96.dp),
                    contentAlignment = Alignment.Center
                ) {
                    AnimatedVisibility(remainingTime == 0L) {
                        Pepperoni()
                    }
                }

                Box(
                    modifier = Modifier.padding(end = 128.dp),
                    contentAlignment = Alignment.Center
                ) {
                    AnimatedVisibility(remainingTime == 0L) {
                        Pepperoni()
                    }
                }

                /*Box(
                    contentAlignment = Alignment.Center
                ) {
                    AnimatedVisibility(remainingTime == 0L) {
                        Pepperoni()
                    }
                }*/

                Box(
                    modifier = Modifier.padding(start = 128.dp),
                    contentAlignment = Alignment.Center
                ) {
                    AnimatedVisibility(remainingTime == 0L) {
                        Pepperoni()
                    }
                }

                Box(
                    modifier = Modifier.padding(start = 96.dp, top = 96.dp),
                    contentAlignment = Alignment.Center
                ) {
                    AnimatedVisibility(remainingTime == 0L) {
                        Pepperoni()
                    }
                }

                Box(
                    modifier = Modifier.padding(end = 96.dp, top = 96.dp),
                    contentAlignment = Alignment.Center
                ) {
                    AnimatedVisibility(remainingTime == 0L) {
                        Pepperoni()
                    }
                }

                Box(
                    modifier = Modifier.padding(top = 128.dp),
                    contentAlignment = Alignment.Center
                ) {
                    AnimatedVisibility(remainingTime == 0L) {
                        Pepperoni()
                    }
                }
            }
        }
    )
}

@Composable
fun Pizza(
    crustColor: Color,
    cheeseColor: Color
) {
    var progress by remember { mutableStateOf(0f) }

    LaunchedEffect(key1 = Unit) {
        animate(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = tween(durationMillis = 10_000, easing = LinearEasing),
        ) { animationValue, _ ->
            progress = animationValue
        }
    }

    Crust(progress = progress, color = crustColor)
    Cheese(progress = progress, color = cheeseColor)
}

@Composable
fun Crust(progress: Float, color: Color) {
    Canvas(
        modifier = Modifier
            .width(224.dp)
            .height(224.dp)
    ) {
        drawArc(
            color = color,
            startAngle = 270f,
            sweepAngle = progress * 360,
            useCenter = false,
            size = size,
            style = Stroke(
                width = 24f,
                cap = StrokeCap.Round
            )
        )
    }
}

@Composable
fun Cheese(progress: Float, color: Color) {
    Canvas(
        modifier = Modifier
            .width(216.dp)
            .height(216.dp)
    ) {
        drawArc(
            color = color,
            startAngle = 270f,
            sweepAngle = progress * 360,
            useCenter = true,
            size = size,
            style = Fill
        )
    }
}

@Composable
fun Pepperoni() {
    Canvas(
        modifier = Modifier
            .width(24.dp)
            .height(24.dp)
    ) {
        drawCircle(
            color = Color.Red
        )
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
@ExperimentalAnimationApi
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
@ExperimentalAnimationApi
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}
