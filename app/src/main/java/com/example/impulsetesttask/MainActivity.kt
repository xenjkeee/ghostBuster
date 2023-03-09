package com.example.impulsetesttask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.impulsetesttask.nav.NavigationHost
import com.example.impulsetesttask.ui.theme.ImpulseTestTaskTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { ImpulseTestTaskTheme { NavigationHost() } }
    }
}