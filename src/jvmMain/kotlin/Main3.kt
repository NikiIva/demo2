import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
@Preview
fun App() {
    MaterialTheme {
//        CircleItem()
        makeRow()
    }
}

@Composable
private fun makeRow(){
    val uiRows = remember {
        mutableStateOf(Start.run())
    }

    val color = remember {
        mutableStateOf(Color.Gray)
    }

    MaterialTheme {
        Column(

        ) {
            Row(

            )
            {
                Box(modifier = Modifier
                    .padding(3.dp)
                    .width(150.dp)
                    .height(30.dp)
                    .background(color = color.value),
                    contentAlignment = Alignment.Center){
                    Text(text = "summoner name", style = TextStyle(color = Color.White, fontSize = 14.sp))
                }
                Box(modifier = Modifier
                    .padding(3.dp)
                    .width(100.dp)
                    .height(30.dp)
                    .background(color = color.value),
                    contentAlignment = Alignment.Center){
                    Text(text = "champion", style = TextStyle(color = Color.White, fontSize = 14.sp))
                }
                Box(modifier = Modifier
                    .padding(3.dp)
                    .width(100.dp)
                    .height(30.dp)
                    .background(color = color.value),
                    contentAlignment = Alignment.Center){
                    Text(text = "damage dealt", style = TextStyle(color = Color.White, fontSize = 14.sp))
                }
                Box(modifier = Modifier
                    .padding(3.dp)
                    .width(100.dp)
                    .height(30.dp)
                    .background(color = color.value),
                    contentAlignment = Alignment.Center){
                    Text(text = "damage received", style = TextStyle(color = Color.White, fontSize = 14.sp))
                }
                Box(modifier = Modifier
                    .padding(3.dp)
                    .width(100.dp)
                    .height(30.dp)
                    .background(color = color.value),
                    contentAlignment = Alignment.Center){
                    Text(text = "ability  haste", style = TextStyle(color = Color.White, fontSize = 14.sp))
                }
                Box(modifier = Modifier
                    .padding(3.dp)
                    .width(100.dp)
                    .height(30.dp)
                    .background(color = color.value),
                    contentAlignment = Alignment.Center){
                    Text(text = "attack speed scaling", style = TextStyle(color = Color.White, fontSize = 14.sp))
                }
                Box(modifier = Modifier
                    .padding(3.dp)
                    .width(100.dp)
                    .height(30.dp)
                    .background(color = color.value),
                    contentAlignment = Alignment.Center){
                    Text(text = "shield modifier", style = TextStyle(color = Color.White, fontSize = 14.sp))
                }
                Box(modifier = Modifier
                    .padding(3.dp)
                    .width(100.dp)
                    .height(30.dp)
                    .background(color = color.value),
                    contentAlignment = Alignment.Center){
                    Text(text = "healing modifier", style = TextStyle(color = Color.White, fontSize = 14.sp))
                }
                Box(modifier = Modifier
                    .padding(3.dp)
                    .width(100.dp)
                    .height(30.dp)
                    .background(color = color.value),
                    contentAlignment = Alignment.Center){
                    Text(text = "tenacity", style = TextStyle(color = Color.White, fontSize = 14.sp))
                }
                Box(modifier = Modifier
                    .padding(3.dp)
                    .width(100.dp)
                    .height(30.dp)
                    .background(color = color.value),
                    contentAlignment = Alignment.Center){
                    Text(text = "energy regeneration", style = TextStyle(color = Color.White, fontSize = 14.sp))
                }
            }
            for ( i in 0..14){
                Row(){
                    Box(modifier = Modifier
                        .padding(3.dp)
                        .width(150.dp)
                        .height(30.dp)
                        .background(color = color.value),
                        contentAlignment = Alignment.Center){
                        Text(text = uiRows.value[i].summonerInfo?.summonerName?:"", style = TextStyle(color = Color.White, fontSize = 20.sp))
                    }
                    Box(modifier = Modifier
                        .padding(3.dp)
                        .width(100.dp)
                        .height(30.dp)
                        .background(color = color.value),
                        contentAlignment = Alignment.Center){
                        Text(text = uiRows.value[i].championInfo?.name?: "", style = TextStyle(color = Color.White, fontSize = 14.sp))
                    }
                    val damageDealt = uiRows.value[i].championInfo?.balance?.damageDealt?: ""
                    Box(modifier = Modifier
                        .padding(3.dp)
                        .width(100.dp)
                        .height(30.dp)
                        .background(color = getColor(damageDealt, true, 1.0)),
                        contentAlignment = Alignment.Center){
                        Text(text = damageDealt, style = TextStyle(color = Color.White, fontSize = 14.sp))
                    }
                    val damageReceived = uiRows.value[i].championInfo?.balance?.damageReceived?: ""
                    Box(modifier = Modifier
                        .padding(3.dp)
                        .width(100.dp)
                        .height(30.dp)
                        .background(color = getColor(damageReceived, false, 1.0)),
                        contentAlignment = Alignment.Center){
                        Text(text = damageReceived, style = TextStyle(color = Color.White, fontSize = 14.sp))
                    }
                    val abilityHaste = uiRows.value[i].championInfo?.balance?.abilityHaste?: ""
                    Box(modifier = Modifier
                        .padding(3.dp)
                        .width(100.dp)
                        .height(30.dp)
                        .background(color = getColor(abilityHaste, true, 0.0)),
                        contentAlignment = Alignment.Center){
                        Text(text = abilityHaste, style = TextStyle(color = Color.White, fontSize = 14.sp))
                    }
                    val attackSpeed = uiRows.value[i].championInfo?.balance?.attackSpeed?: ""
                    Box(modifier = Modifier
                        .padding(3.dp)
                        .width(100.dp)
                        .height(30.dp)
                        .background(color = getColor(attackSpeed, true, 0.0)),
                        contentAlignment = Alignment.Center){
                        Text(text = attackSpeed, style = TextStyle(color = Color.White, fontSize = 14.sp))
                    }
                    val sheild = uiRows.value[i].championInfo?.balance?.sheild?: ""
                    Box(modifier = Modifier
                        .padding(3.dp)
                        .width(100.dp)
                        .height(30.dp)
                        .background(color = getColor(sheild, true, 0.0)),
                        contentAlignment = Alignment.Center){
                        Text(text = sheild, style = TextStyle(color = Color.White, fontSize = 14.sp))
                    }
                    val healing =  uiRows.value[i].championInfo?.balance?.healing?: ""
                    Box(modifier = Modifier
                        .padding(3.dp)
                        .width(100.dp)
                        .height(30.dp)
                        .background(color = getColor(healing, true, 1.0)),
                        contentAlignment = Alignment.Center){
                        Text(text = healing, style = TextStyle(color = Color.White, fontSize = 14.sp))
                    }
                    val tenacity = uiRows.value[i].championInfo?.balance?.tenacity?: ""
                    Box(modifier = Modifier
                        .padding(3.dp)
                        .width(100.dp)
                        .height(30.dp)
                        .background(color = getColor(tenacity, true, 0.0)),
                        contentAlignment = Alignment.Center){
                        Text(text = tenacity, style = TextStyle(color = Color.White, fontSize = 14.sp))
                    }
                    val energy = uiRows.value[i].championInfo?.balance?.energy?: ""
                    Box(modifier = Modifier
                        .padding(3.dp)
                        .width(100.dp)
                        .height(30.dp)
                        .background(color = getColor(energy, true, 0.0)),
                        contentAlignment = Alignment.Center){
                        Text(text = energy, style = TextStyle(color = Color.White, fontSize = 14.sp))
                    }
                }
            }
        }
    }
}


private fun getColor(value : String, greaterBetter : Boolean, borderValue : Double) : Color{
    val doubleValue  = value.toDoubleOrNull()
    if (doubleValue == null) {
        return Color.Gray
    }
    if (greaterBetter){
        if (doubleValue > borderValue) {
            return Color.Green
        }
        if (doubleValue < borderValue){
            return Color.Red
        }
    }
    else{
        if (doubleValue < borderValue) {
            return Color.Green
        }
        if (doubleValue > borderValue){
            return Color.Red
        }
    }
    return Color.Gray
}

@Composable
private fun CircleItem(){
    val counter = remember {
        mutableStateOf(0)
    }

    val color = remember {
        mutableStateOf(Color.Blue)
    }
    Box(modifier = Modifier
        .size(100.dp)
        .background(color = color.value, shape = CircleShape).clickable {
            when(++counter.value){
                10 -> color.value = Color.Red
                20 -> color.value = Color.Green
            }
        },
        contentAlignment = Alignment.Center){
        Text(text = counter.value.toString(), style = TextStyle(color = Color.White, fontSize = 20.sp))
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
