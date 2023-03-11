package com.example.impulsetesttask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.impulsetesttask.nav.NavigationHost
import com.example.impulsetesttask.ui.theme.ImpulseTestTaskTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().setKeepOnScreenCondition { viewModel.isLoading.value }
        super.onCreate(savedInstanceState)
        setContent { ImpulseTestTaskTheme { NavigationHost() } }
    }
}