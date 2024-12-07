package com.example.lunarloops.Planet2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.lunarloops.R
import com.example.lunarloops.data.AppStorage
import com.example.lunarloops.ui.AppPreferences

@Composable
fun World2CompleteScreen(navController: NavController){
    val imageBrush =
        ShaderBrush(ImageShader(ImageBitmap.imageResource(id = R.drawable.starcbck)))
    val store = AppStorage(LocalContext.current)
    val appPrefs = store.appPreferencesFlow.collectAsState(AppPreferences())

    Column(modifier = Modifier
        .fillMaxSize()
        .background(brush = imageBrush)
        .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Congrats You're Score is",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 40.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "${appPrefs.value.world2score}",
            fontSize = 45.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(40.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        //Return Button and Label
        Text(text = "Ready To Go Back?",
            fontSize = 45.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(60.dp)
        )

        Button(
            onClick = {navController.navigate("home_screen")},
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp),
            contentPadding = PaddingValues(),
            colors = ButtonDefaults.buttonColors(Color.Transparent)
        ){
            Box(modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp)
                .background(color = Color.Black,
                    shape = RoundedCornerShape(50.dp)
                ),
                contentAlignment = Alignment.Center
            ){
                Text(text = "Return",
                    color = Color.White,
                    modifier = Modifier,
                    style = androidx.compose.ui.text.TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Normal
                    ))
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        //Retry Button and Label
        Text(text = "Want to try again?",
            fontSize = 45.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 60.dp)
        )

        Button(
            onClick = {navController.navigate("world2_level1_screen")},
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp),
            contentPadding = PaddingValues(),
            colors = ButtonDefaults.buttonColors(Color.Transparent)
        ){
            Box(modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp)
                .background(color = Color.Black,
                    shape = RoundedCornerShape(50.dp)
                ),
                contentAlignment = Alignment.Center
            ){
                Text(text = "Retry",
                    color = Color.White,
                    modifier = Modifier,
                    style = androidx.compose.ui.text.TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Normal
                    ))
            }
        }
    }
}