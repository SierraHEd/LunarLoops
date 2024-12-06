package com.example.lunarloops.Planet1

import android.provider.ContactsContract.Data
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun World1Level2(navController: NavController){
    GameScreen {
        WorldL2Views(navController)
    }
}

@Composable
fun WorldL2Views(navController: NavController){

    val screenWidth = LocalConfiguration.current.screenWidthDp
    val boxSize = Dp(screenWidth/6f)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Word Game",
            fontSize = 45.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Magenta.copy(0.5f),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 60.dp)
        )

        val gridData: Array<Array<DataItem>> = arrayOf(
            arrayOf(DataItem("0"), DataItem("0"), DataItem("0"), DataItem("0"), DataItem("C", "C"), DataItem("0")),
            arrayOf(DataItem("0"), DataItem("0"), DataItem("0"), DataItem("0"), DataItem("O", "O"), DataItem("0")),
            arrayOf(DataItem("0"), DataItem("S", "S"), DataItem("0"), DataItem("0"), DataItem("M","M"), DataItem("0")),
            arrayOf(DataItem("R", "R"), DataItem("#", "O"), DataItem("C", "C"), DataItem("K", "K"), DataItem("#","E"), DataItem("T", "")),
            arrayOf(DataItem("0"), DataItem("L", "L"), DataItem("0"), DataItem("0"), DataItem("T","T"), DataItem("0")),
            arrayOf(DataItem("0"), DataItem("A", "A"), DataItem("0"), DataItem("0"), DataItem("0"), DataItem("0")),
            arrayOf(DataItem("0"), DataItem("R", "R"), DataItem("0"), DataItem("0"), DataItem("0"), DataItem("0")),
        )

        GridView2(gridData, boxSize, navController)

        val dragItems = listOf(
            DataItem("A"),
            DataItem("O"),
            DataItem("E")
        )

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 60.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            dragItems.forEach{ item ->
                DraggableView(modifier = Modifier, dataToDrop = item) {
                    Box(modifier = Modifier
                        .size(boxSize)
                        .clip(RoundedCornerShape(15.dp))
                        .background(Color.Green.copy(0.5f), RoundedCornerShape(15.dp)),
                        contentAlignment = Alignment.Center
                    ){
                        Text(text = item.name!!,
                            style = MaterialTheme.typography.headlineLarge,
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun GridView2(data: Array<Array<DataItem>>, boxSize: Dp, navController: NavController){
    var dataList by remember { mutableStateOf(data) }
    var currentStep by remember { mutableStateOf(0) } // Tracks the current placeholder to fill
    val correctAnswers = listOf("A", "E")
    val initialGridData = data.map { row -> row.map { it.copy() }.toTypedArray() }.toTypedArray()

    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for(row in dataList){
            Row(modifier = Modifier
                .wrapContentSize()
                .padding(1.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(1.dp)
            ) {
                for(item in row){
                    when(item.name){
                        "#" -> {
                            DropTarget <DataItem>(modifier = Modifier.size(boxSize)
                            ){
                                    isInBound, dataToDrop ->

                                val color = if(isInBound) Color.Red.copy(0.5f)
                                else Color.Gray.copy(0.2f)

                                dataToDrop?.let {
                                    if(isInBound){
                                        val result = findElementIndex(data, DataItem("#", dataToDrop.name))
                                            ?:Pair(0,0)

                                        if(result.first != 0 && result.second != 0){
                                            dataList = dataList.mapIndexed{ rowIndex, row ->
                                                row.mapIndexed{ colIndex, element ->
                                                    if(rowIndex == result.first && colIndex == result.second){
                                                        DataItem(name = dataToDrop.name,
                                                            id = dataToDrop.name,
                                                            Color.Blue.copy(0.5f))
                                                    }else {
                                                        element
                                                    }
                                                }.toTypedArray()
                                            }.toTypedArray()

                                            currentStep += 1

                                            // Navigate if all placeholders are filled
                                            if (currentStep == correctAnswers.size+1) {
                                                navController.navigate("world1_level3_screen")
                                            }
                                        }
                                    }
                                }

                                Box(modifier = Modifier
                                    .size(boxSize)
                                    .clip(RoundedCornerShape(15.dp))
                                    .background(color, RoundedCornerShape(15.dp)),
                                    contentAlignment = Alignment.Center
                                ){
                                    if(dataToDrop != null) {
                                        Text(
                                            text = item.name,
                                            style = MaterialTheme.typography.headlineLarge,
                                            color = Color.White,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    }
                                }
                            }
                        }

                        "0" -> {
                            Box(modifier = Modifier.size(boxSize)){

                            }
                        }

                        else -> {
                            Box(modifier = Modifier
                                .size(boxSize)
                                .clip(RoundedCornerShape(15.dp))
                                .background(item.color, RoundedCornerShape(15.dp)),
                                contentAlignment = Alignment.Center
                            ){
                                Text(text = item.name!!,
                                    style = MaterialTheme.typography.headlineLarge,
                                    color = Color.White,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                // Reset the grid and step counter
                dataList = initialGridData
                currentStep = 0
            },
            modifier = Modifier.padding(16.dp) // Add padding around the button
        ) {
            Text(text = "Reset", fontSize = 18.sp)
        }
    }

}