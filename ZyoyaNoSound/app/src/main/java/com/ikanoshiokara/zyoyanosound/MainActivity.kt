package com.ikanoshiokara.zyoyanosound

import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.ikanoshiokara.zyoyanosound.ui.theme.ZyoyaNoSoundTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZyoyaNoSoundTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainPage()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ZyoyaNoSoundTheme {
        Greeting("Android")
    }
}

@Composable
fun GoneGone(count: Int, onClick: () -> Unit) {
    val context = LocalContext.current
    // 音系変数
    val attr: AudioAttributes = AudioAttributes.Builder()
        .setUsage(AudioAttributes.USAGE_MEDIA)
        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
        .build()
    val soundPool = SoundPool.Builder()
        .setAudioAttributes(attr)
        .setMaxStreams(2)
        .build()

    val zyoyaNoSound = soundPool.load(context, R.raw.gonegone, 1)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.bell),
            contentDescription = "",
            modifier = Modifier.clickable {
                onClick()
                soundPool.play(zyoyaNoSound, 1.0f, 1.0f, 1, 0, 1.0f)
            }
        )

        Text(
            "$count",
            fontSize = 30.sp
        )
    }
}

@Composable
fun MainPage() {
    val zyoyaCount = remember { mutableStateOf(100) }

    if (zyoyaCount.value < 108) {
        GoneGone(zyoyaCount.value) {
            zyoyaCount.value = zyoyaCount.value + 1
        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Happy New Year!!", fontSize = 30.sp)
            Button(onClick = { zyoyaCount.value = 0 }) {
                Text(text = "新しい煩悩を滅する")
            }
        }
    }

}