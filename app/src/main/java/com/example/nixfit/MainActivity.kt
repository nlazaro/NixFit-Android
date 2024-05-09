package com.example.nixfit

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.example.nixfit.presentation.home.HomeScreen
import com.example.nixfit.ui.theme.NixFitTheme
import com.example.nixfit.util.BarcodeScanner
import com.example.nixfit.util.Event
import com.example.nixfit.util.EventBus
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.components.SingletonComponent


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NixFitTheme {
                val lifecycle = LocalLifecycleOwner.current.lifecycle
                LaunchedEffect (key1 = lifecycle){
                    repeatOnLifecycle(state = Lifecycle.State.STARTED){
                        EventBus.events.collect { event ->
                            if (event is Event.Toast){
                                Toast.makeText(this@MainActivity, event.message, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
                //HomeScreen()
                BarcodeScanner()
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    NixFitTheme {
        //HomeScreen()
        BarcodeScanner()
    }
}
