package com.example.catalogapp

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.android.showkase.models.Showkase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CatalogApp : Application()

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Showkase.getBrowserIntent(this))
    }
}

@Preview
@Composable
fun ComposeSample() {
    Box {
        Text(text = "Hi, I am a compose sample target!")
    }
}

