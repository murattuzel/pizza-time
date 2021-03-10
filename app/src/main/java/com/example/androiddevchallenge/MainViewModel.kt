package com.example.androiddevchallenge

import android.os.CountDownTimer
import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.ui.theme.cheese0
import com.example.androiddevchallenge.ui.theme.cheese1
import com.example.androiddevchallenge.ui.theme.cheese10
import com.example.androiddevchallenge.ui.theme.cheese2
import com.example.androiddevchallenge.ui.theme.cheese3
import com.example.androiddevchallenge.ui.theme.cheese4
import com.example.androiddevchallenge.ui.theme.cheese5
import com.example.androiddevchallenge.ui.theme.cheese6
import com.example.androiddevchallenge.ui.theme.cheese7
import com.example.androiddevchallenge.ui.theme.cheese8
import com.example.androiddevchallenge.ui.theme.cheese9
import com.example.androiddevchallenge.ui.theme.crust0
import com.example.androiddevchallenge.ui.theme.crust1
import com.example.androiddevchallenge.ui.theme.crust10
import com.example.androiddevchallenge.ui.theme.crust2
import com.example.androiddevchallenge.ui.theme.crust3
import com.example.androiddevchallenge.ui.theme.crust4
import com.example.androiddevchallenge.ui.theme.crust5
import com.example.androiddevchallenge.ui.theme.crust6
import com.example.androiddevchallenge.ui.theme.crust7
import com.example.androiddevchallenge.ui.theme.crust8
import com.example.androiddevchallenge.ui.theme.crust9

class MainViewModel : ViewModel() {
    private lateinit var countdownTimer: CountDownTimer

    private val _remainingTime = MutableLiveData(11_000L)
    val remainingTime: LiveData<Long> get() = _remainingTime

    val crustColor: LiveData<Color> = Transformations.map(_remainingTime) {
        when (it) {
            10L -> crust10
            9L -> crust9
            8L -> crust8
            7L -> crust7
            6L -> crust6
            5L -> crust5
            4L -> crust4
            3L -> crust3
            2L -> crust2
            1L -> crust1
            0L -> crust0
            else -> Color.Transparent
        }
    }

    val cheeseColor: LiveData<Color> = Transformations.map(_remainingTime) {
        when (it) {
            10L -> cheese10
            9L -> cheese9
            8L -> cheese8
            7L -> cheese7
            6L -> cheese6
            5L -> cheese5
            4L -> cheese4
            3L -> cheese3
            2L -> cheese2
            1L -> cheese1
            0L -> cheese0
            else -> Color.Transparent
        }
    }

    init {
        start()
    }

    private fun start() {
        countdownTimer = object : CountDownTimer(11_000L, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                Log.d("MainViewModel", "remainingTime: ${millisUntilFinished / 1000}")
                _remainingTime.value = millisUntilFinished / 1000
            }

            override fun onFinish() {
                // do nothing
            }
        }
        countdownTimer.start()
    }
}
