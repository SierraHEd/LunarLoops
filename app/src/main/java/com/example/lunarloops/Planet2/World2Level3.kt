package com.example.lunarloops.Planet2

import android.content.Context
import android.media.MediaPlayer
import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageShader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.lunarloops.R
import com.example.lunarloops.data.AppStorage
import com.example.lunarloops.ui.AppPreferences
import kotlinx.coroutines.launch

@Composable
fun World2L3Screen(context: Context, navController: NavController){
    val store = AppStorage(LocalContext.current)
    val appPrefs = store.appPreferencesFlow.collectAsState(AppPreferences())
    val coroutineScope = rememberCoroutineScope()
    val imageBrush =
        ShaderBrush(ImageShader(ImageBitmap.imageResource(id = R.drawable.bbck3)))
    val correct = 10
    val mediaPlayer: MediaPlayer = MediaPlayer.create(context, R.raw.ding)

    Column(modifier = Modifier
        .fillMaxSize()
        .background(imageBrush)
        .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Which Is Neptune?",
            fontSize = 45.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White.copy(0.5f),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 60.dp)
        )

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            //User Clicks on Correct Image
            Box() {
                Image(painter = painterResource(id = R.drawable.neptune), contentDescription = null,
                    modifier = Modifier
                        .size(180.dp)
                        .clickable {
                            coroutineScope.launch {
                                store.saveWorld2Score(appPrefs.value.world2score + correct)
                            }
                            mediaPlayer.start()
                            navController.navigate("world2_complete_screen")
                        })
            }

            Box() {
                Image(painter = painterResource(id = R.drawable.uranus), contentDescription = null,
                    modifier = Modifier
                        .size(180.dp)
                        .clickable {
                            mediaPlayer.start()
                            navController.navigate("world2_complete_screen")
                        })
            }
        }
        //Split into 2 rows
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            Box() {
                Image(painter = painterResource(id = R.drawable.star), contentDescription = null,
                    modifier = Modifier
                        .size(180.dp)
                        .clickable {
                            mediaPlayer.start()
                            navController.navigate("world2_complete_screen")
                        })
            }

            Box() {
                Image(painter = painterResource(id = R.drawable.mars), contentDescription = null,
                    modifier = Modifier
                        .size(180.dp)
                        .clickable {
                            mediaPlayer.start()
                            navController.navigate("world2_complete_screen")
                        })
            }
        }
    }
}