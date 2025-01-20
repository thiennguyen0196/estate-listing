package com.thiennguyen.estatelisting.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.thiennguyen.estatelisting.ui.screens.list.EstateListingScreen
import com.thiennguyen.estatelisting.ui.theme.EstateListingTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EstateListingTheme {
                EstateListingScreen()
            }
        }
    }
}
