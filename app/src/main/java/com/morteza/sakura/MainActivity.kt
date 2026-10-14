
package com.morteza.sakura

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import com.morteza.sakura.data.local.ThemePreferences
import com.morteza.sakura.presentation.navigation.SakuraNavigation
import com.morteza.sakura.ui.theme.SAKURATheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val themePreferences = ThemePreferences(applicationContext)

        setContent {
            val isDarkTheme by themePreferences.isDarkTheme
                .collectAsState(initial = false)

            val coroutineScope = rememberCoroutineScope()

            SAKURATheme(darkTheme = isDarkTheme) {
                CompositionLocalProvider(
                    LocalLayoutDirection provides LayoutDirection.Rtl
                ) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        contentWindowInsets = WindowInsets.safeDrawing,
                        containerColor = MaterialTheme.colorScheme.background
                    ) { innerPadding ->
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colorScheme.background
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(innerPadding)
                            ) {
                                SakuraNavigation(
                                    isDarkTheme = isDarkTheme,
                                    onThemeChange = { enabled ->
                                        coroutineScope.launch {
                                            themePreferences.setDarkTheme(enabled)
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
