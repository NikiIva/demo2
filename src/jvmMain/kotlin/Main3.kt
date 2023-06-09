import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import kotlinx.coroutines.delay

@Composable
@Preview
fun App() {
    MaterialTheme {
//        CircleItem()
        makeRow()
    }
}

@Composable
private fun makeRow() {
    val session = remember {
        mutableStateOf(ClientRESTs.mockSession())
    }

    var uiRows = remember {
        mutableStateOf(Start.run(session.value))
    }

    val summonerName = remember {
        mutableListOf(
            uiRows.value[0].summonerInfo?.summonerName ?: "",
            uiRows.value[1].summonerInfo?.summonerName ?: "",
            uiRows.value[2].summonerInfo?.summonerName ?: "",
            uiRows.value[3].summonerInfo?.summonerName ?: "",
            uiRows.value[4].summonerInfo?.summonerName ?: "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
        )
    }
    val championInfoName = remember {
        mutableListOf(
            uiRows.value[0].championInfo?.name ?: "",
            uiRows.value[1].championInfo?.name ?: "",
            uiRows.value[2].championInfo?.name ?: "",
            uiRows.value[3].championInfo?.name ?: "",
            uiRows.value[4].championInfo?.name ?: "",
            uiRows.value[5].championInfo?.name ?: "",
            uiRows.value[6].championInfo?.name ?: "",
            uiRows.value[7].championInfo?.name ?: "",
            uiRows.value[8].championInfo?.name ?: "",
            uiRows.value[9].championInfo?.name ?: "",
            uiRows.value[10].championInfo?.name ?: "",
            uiRows.value[11].championInfo?.name ?: "",
            uiRows.value[12].championInfo?.name ?: "",
            uiRows.value[13].championInfo?.name ?: "",
            uiRows.value[14].championInfo?.name ?: "",
            uiRows.value[15].championInfo?.name ?: ""
        )
    }
    val damageDealt = remember {
        mutableListOf(
            uiRows.value[0].championInfo?.balance?.damageDealt ?: "",
            uiRows.value[1].championInfo?.balance?.damageDealt ?: "",
            uiRows.value[2].championInfo?.balance?.damageDealt ?: "",
            uiRows.value[3].championInfo?.balance?.damageDealt ?: "",
            uiRows.value[4].championInfo?.balance?.damageDealt ?: "",
            uiRows.value[5].championInfo?.balance?.damageDealt ?: "",
            uiRows.value[6].championInfo?.balance?.damageDealt ?: "",
            uiRows.value[7].championInfo?.balance?.damageDealt ?: "",
            uiRows.value[8].championInfo?.balance?.damageDealt ?: "",
            uiRows.value[9].championInfo?.balance?.damageDealt ?: "",
            uiRows.value[10].championInfo?.balance?.damageDealt ?: "",
            uiRows.value[11].championInfo?.balance?.damageDealt ?: "",
            uiRows.value[12].championInfo?.balance?.damageDealt ?: "",
            uiRows.value[13].championInfo?.balance?.damageDealt ?: "",
            uiRows.value[14].championInfo?.balance?.damageDealt ?: ""
        )
    }
    val damageReceived = remember {
        mutableListOf(
            uiRows.value[0].championInfo?.balance?.damageReceived ?: "",
            uiRows.value[1].championInfo?.balance?.damageReceived ?: "",
            uiRows.value[2].championInfo?.balance?.damageReceived ?: "",
            uiRows.value[3].championInfo?.balance?.damageReceived ?: "",
            uiRows.value[4].championInfo?.balance?.damageReceived ?: "",
            uiRows.value[5].championInfo?.balance?.damageReceived ?: "",
            uiRows.value[6].championInfo?.balance?.damageReceived ?: "",
            uiRows.value[7].championInfo?.balance?.damageReceived ?: "",
            uiRows.value[8].championInfo?.balance?.damageReceived ?: "",
            uiRows.value[9].championInfo?.balance?.damageReceived ?: "",
            uiRows.value[10].championInfo?.balance?.damageReceived ?: "",
            uiRows.value[11].championInfo?.balance?.damageReceived ?: "",
            uiRows.value[12].championInfo?.balance?.damageReceived ?: "",
            uiRows.value[13].championInfo?.balance?.damageReceived ?: "",
            uiRows.value[14].championInfo?.balance?.damageReceived ?: ""
        )
    }
    val abilityHaste = remember {
        mutableListOf(
            uiRows.value[0].championInfo?.balance?.abilityHaste ?: "",
            uiRows.value[1].championInfo?.balance?.abilityHaste ?: "",
            uiRows.value[2].championInfo?.balance?.abilityHaste ?: "",
            uiRows.value[3].championInfo?.balance?.abilityHaste ?: "",
            uiRows.value[4].championInfo?.balance?.abilityHaste ?: "",
            uiRows.value[5].championInfo?.balance?.abilityHaste ?: "",
            uiRows.value[6].championInfo?.balance?.abilityHaste ?: "",
            uiRows.value[7].championInfo?.balance?.abilityHaste ?: "",
            uiRows.value[8].championInfo?.balance?.abilityHaste ?: "",
            uiRows.value[9].championInfo?.balance?.abilityHaste ?: "",
            uiRows.value[10].championInfo?.balance?.abilityHaste ?: "",
            uiRows.value[11].championInfo?.balance?.abilityHaste ?: "",
            uiRows.value[12].championInfo?.balance?.abilityHaste ?: "",
            uiRows.value[13].championInfo?.balance?.abilityHaste ?: "",
            uiRows.value[14].championInfo?.balance?.abilityHaste ?: ""
        )
    }
    val attackSpeed = remember {
        mutableListOf(
            uiRows.value[0].championInfo?.balance?.attackSpeed ?: "",
            uiRows.value[1].championInfo?.balance?.attackSpeed ?: "",
            uiRows.value[2].championInfo?.balance?.attackSpeed ?: "",
            uiRows.value[3].championInfo?.balance?.attackSpeed ?: "",
            uiRows.value[4].championInfo?.balance?.attackSpeed ?: "",
            uiRows.value[5].championInfo?.balance?.attackSpeed ?: "",
            uiRows.value[6].championInfo?.balance?.attackSpeed ?: "",
            uiRows.value[7].championInfo?.balance?.attackSpeed ?: "",
            uiRows.value[8].championInfo?.balance?.attackSpeed ?: "",
            uiRows.value[9].championInfo?.balance?.attackSpeed ?: "",
            uiRows.value[10].championInfo?.balance?.attackSpeed ?: "",
            uiRows.value[11].championInfo?.balance?.attackSpeed ?: "",
            uiRows.value[12].championInfo?.balance?.attackSpeed ?: "",
            uiRows.value[13].championInfo?.balance?.attackSpeed ?: "",
            uiRows.value[14].championInfo?.balance?.attackSpeed ?: ""
        )
    }
    val shield = remember {
        mutableListOf(
            uiRows.value[0].championInfo?.balance?.sheild ?: "",
            uiRows.value[1].championInfo?.balance?.sheild ?: "",
            uiRows.value[2].championInfo?.balance?.sheild ?: "",
            uiRows.value[3].championInfo?.balance?.sheild ?: "",
            uiRows.value[4].championInfo?.balance?.sheild ?: "",
            uiRows.value[5].championInfo?.balance?.sheild ?: "",
            uiRows.value[6].championInfo?.balance?.sheild ?: "",
            uiRows.value[7].championInfo?.balance?.sheild ?: "",
            uiRows.value[8].championInfo?.balance?.sheild ?: "",
            uiRows.value[9].championInfo?.balance?.sheild ?: "",
            uiRows.value[10].championInfo?.balance?.sheild ?: "",
            uiRows.value[11].championInfo?.balance?.sheild ?: "",
            uiRows.value[12].championInfo?.balance?.sheild ?: "",
            uiRows.value[13].championInfo?.balance?.sheild ?: "",
            uiRows.value[14].championInfo?.balance?.sheild ?: ""
        )
    }
    val healing = remember {
        mutableListOf(
            uiRows.value[0].championInfo?.balance?.healing ?: "",
            uiRows.value[1].championInfo?.balance?.healing ?: "",
            uiRows.value[2].championInfo?.balance?.healing ?: "",
            uiRows.value[3].championInfo?.balance?.healing ?: "",
            uiRows.value[4].championInfo?.balance?.healing ?: "",
            uiRows.value[5].championInfo?.balance?.healing ?: "",
            uiRows.value[6].championInfo?.balance?.healing ?: "",
            uiRows.value[7].championInfo?.balance?.healing ?: "",
            uiRows.value[8].championInfo?.balance?.healing ?: "",
            uiRows.value[9].championInfo?.balance?.healing ?: "",
            uiRows.value[10].championInfo?.balance?.healing ?: "",
            uiRows.value[11].championInfo?.balance?.healing ?: "",
            uiRows.value[12].championInfo?.balance?.healing ?: "",
            uiRows.value[13].championInfo?.balance?.healing ?: "",
            uiRows.value[14].championInfo?.balance?.healing ?: ""
        )
    }
    val tenacity = remember {
        mutableListOf(
            uiRows.value[0].championInfo?.balance?.tenacity ?: "",
            uiRows.value[1].championInfo?.balance?.tenacity ?: "",
            uiRows.value[2].championInfo?.balance?.tenacity ?: "",
            uiRows.value[3].championInfo?.balance?.tenacity ?: "",
            uiRows.value[4].championInfo?.balance?.tenacity ?: "",
            uiRows.value[5].championInfo?.balance?.tenacity ?: "",
            uiRows.value[6].championInfo?.balance?.tenacity ?: "",
            uiRows.value[7].championInfo?.balance?.tenacity ?: "",
            uiRows.value[8].championInfo?.balance?.tenacity ?: "",
            uiRows.value[9].championInfo?.balance?.tenacity ?: "",
            uiRows.value[10].championInfo?.balance?.tenacity ?: "",
            uiRows.value[11].championInfo?.balance?.tenacity ?: "",
            uiRows.value[12].championInfo?.balance?.tenacity ?: "",
            uiRows.value[13].championInfo?.balance?.tenacity ?: "",
            uiRows.value[14].championInfo?.balance?.tenacity ?: ""
        )
    }
    val energy = remember {
        mutableListOf(
            uiRows.value[0].championInfo?.balance?.energy ?: "",
            uiRows.value[1].championInfo?.balance?.energy ?: "",
            uiRows.value[2].championInfo?.balance?.energy ?: "",
            uiRows.value[3].championInfo?.balance?.energy ?: "",
            uiRows.value[4].championInfo?.balance?.energy ?: "",
            uiRows.value[5].championInfo?.balance?.energy ?: "",
            uiRows.value[6].championInfo?.balance?.energy ?: "",
            uiRows.value[7].championInfo?.balance?.energy ?: "",
            uiRows.value[8].championInfo?.balance?.energy ?: "",
            uiRows.value[9].championInfo?.balance?.energy ?: "",
            uiRows.value[10].championInfo?.balance?.energy ?: "",
            uiRows.value[11].championInfo?.balance?.energy ?: "",
            uiRows.value[12].championInfo?.balance?.energy ?: "",
            uiRows.value[13].championInfo?.balance?.energy ?: "",
            uiRows.value[14].championInfo?.balance?.energy ?: "",
        )
    }

    val color = remember {
        mutableStateOf(Color.Gray)
    }

    fun customModifier() : Modifier{
        return Modifier
            .padding(3.dp)
            .width(100.dp)
            .height(30.dp)
    }

    MaterialTheme {
        Column(

        ) {
            Row()
            {
                Box(
                    modifier = Modifier
                        .padding(3.dp)
                        .width(150.dp)
                        .height(30.dp)
                        .background(color = color.value),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "summoner name", style = TextStyle(color = Color.White, fontSize = 14.sp))
                }
                Box(
                    modifier = Modifier
                        .padding(3.dp)
                        .width(100.dp)
                        .height(30.dp)
                        .background(color = color.value),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "champion", style = TextStyle(color = Color.White, fontSize = 14.sp))
                }
                Box(
                    modifier = Modifier
                        .padding(3.dp)
                        .width(100.dp)
                        .height(30.dp)
                        .background(color = color.value),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "damage dealt", style = TextStyle(color = Color.White, fontSize = 14.sp))
                }
                Box(
                    modifier = Modifier
                        .padding(3.dp)
                        .width(100.dp)
                        .height(30.dp)
                        .background(color = color.value),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "damage received", style = TextStyle(color = Color.White, fontSize = 14.sp))
                }
                Box(
                    modifier = Modifier
                        .padding(3.dp)
                        .width(100.dp)
                        .height(30.dp)
                        .background(color = color.value),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "ability  haste", style = TextStyle(color = Color.White, fontSize = 14.sp))
                }
                Box(
                    modifier = Modifier
                        .padding(3.dp)
                        .width(100.dp)
                        .height(30.dp)
                        .background(color = color.value),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "attack speed scaling", style = TextStyle(color = Color.White, fontSize = 14.sp))
                }
                Box(
                    modifier = Modifier
                        .padding(3.dp)
                        .width(100.dp)
                        .height(30.dp)
                        .background(color = color.value),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "shield modifier", style = TextStyle(color = Color.White, fontSize = 14.sp))
                }
                Box(
                    modifier = Modifier
                        .padding(3.dp)
                        .width(100.dp)
                        .height(30.dp)
                        .background(color = color.value),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "healing modifier", style = TextStyle(color = Color.White, fontSize = 14.sp))
                }
                Box(
                    modifier = Modifier
                        .padding(3.dp)
                        .width(100.dp)
                        .height(30.dp)
                        .background(color = color.value),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "tenacity", style = TextStyle(color = Color.White, fontSize = 14.sp))
                }
                Box(
                    modifier = Modifier
                        .padding(3.dp)
                        .width(100.dp)
                        .height(30.dp)
                        .background(color = color.value),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "energy regeneration", style = TextStyle(color = Color.White, fontSize = 14.sp))
                }
            }
            for (i in 0..14) {
                Row() {
                    Box(
                        modifier = Modifier
                            .padding(3.dp)
                            .width(150.dp)
                            .height(30.dp)
                            .background(color = color.value),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = summonerName[i],
                            style = TextStyle(color = Color.White, fontSize = 20.sp)
                        )
                    }
                    Box(
                        modifier = customModifier()
                            .background(color = color.value),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = championInfoName[i], style = TextStyle(color = Color.White, fontSize = 14.sp))
                    }
                    Box(
                        modifier = customModifier()
                            .background(color = getColor(damageDealt[i], true, 1.0)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = damageDealt[i], style = TextStyle(color = Color.White, fontSize = 14.sp))
                    }
                    Box(
                        modifier = customModifier()
                            .background(color = getColor(damageReceived[i], false, 1.0)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = damageReceived[i], style = TextStyle(color = Color.White, fontSize = 14.sp))
                    }
                    Box(
                        modifier = Modifier
                            .padding(3.dp)
                            .width(100.dp)
                            .height(30.dp)
                            .background(color = getColor(abilityHaste[i], true, 0.0)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = abilityHaste[i], style = TextStyle(color = Color.White, fontSize = 14.sp))
                    }

                    Box(
                        modifier = Modifier
                            .padding(3.dp)
                            .width(100.dp)
                            .height(30.dp)
                            .background(color = getColor(attackSpeed[i], true, 0.0)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = attackSpeed[i], style = TextStyle(color = Color.White, fontSize = 14.sp))
                    }

                    Box(
                        modifier = Modifier
                            .padding(3.dp)
                            .width(100.dp)
                            .height(30.dp)
                            .background(color = getColor(shield[i], true, 0.0)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = shield[i], style = TextStyle(color = Color.White, fontSize = 14.sp))
                    }

                    Box(
                        modifier = Modifier
                            .padding(3.dp)
                            .width(100.dp)
                            .height(30.dp)
                            .background(color = getColor(healing[i], true, 1.0)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = healing[i], style = TextStyle(color = Color.White, fontSize = 14.sp))
                    }

                    Box(
                        modifier = Modifier
                            .padding(3.dp)
                            .width(100.dp)
                            .height(30.dp)
                            .background(color = getColor(tenacity[i], true, 0.0)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = tenacity[i], style = TextStyle(color = Color.White, fontSize = 14.sp))
                    }
                    Box(
                        modifier = Modifier
                            .padding(3.dp)
                            .width(100.dp)
                            .height(30.dp)
                            .background(color = getColor(energy[i], true, 0.0)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = energy[i], style = TextStyle(color = Color.White, fontSize = 14.sp))
                    }
                }
            }
        }
        LaunchedEffect(Unit) {
            while (true) {
                delay(5_000)
                val newSession = ClientRESTs.mockSession1()
                if (newSession != session.value) {
                    session.value = newSession
                    uiRows = mutableStateOf(Start.run(session.value))
                    for ( i in 0..14) {
                        summonerName[i] = uiRows.value[i].summonerInfo?.summonerName ?: ""
                        championInfoName[i] = uiRows.value[i].championInfo?.name ?: ""
                        damageDealt[i] = uiRows.value[i].championInfo?.balance?.damageDealt ?: ""
                        damageReceived[i] = uiRows.value[i].championInfo?.balance?.damageReceived ?: ""
                        abilityHaste[i] = uiRows.value[i].championInfo?.balance?.abilityHaste ?: ""
                        attackSpeed[i] = uiRows.value[i].championInfo?.balance?.attackSpeed ?: ""
                        shield[i] = uiRows.value[i].championInfo?.balance?.sheild ?: ""
                        healing[i] = uiRows.value[i].championInfo?.balance?.healing ?: ""
                        tenacity[i] = uiRows.value[i].championInfo?.balance?.tenacity ?: ""
                        energy[i] = uiRows.value[i].championInfo?.balance?.energy ?: ""
                    }

                }
            }
        }
    }
}


private fun getColor(value: String, greaterBetter: Boolean, borderValue: Double): Color {
    val doubleValue = value.toDoubleOrNull()
    if (doubleValue == null) {
        return Color.Gray
    }
    if (greaterBetter) {
        if (doubleValue > borderValue) {
            return Color.Green
        }
        if (doubleValue < borderValue) {
            return Color.Red
        }
    } else {
        if (doubleValue < borderValue) {
            return Color.Green
        }
        if (doubleValue > borderValue) {
            return Color.Red
        }
    }
    return Color.Gray
}


fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
