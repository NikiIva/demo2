package screens

import ServerRESTs
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cache.Cache
import hints.HintType
import kotlinx.coroutines.CoroutineScope
import makeExtraInfoBox
import makeIconWithSnackBar
import makeSummonerAndChampion
import navcontroller.NavController

@Composable
fun InGame(
    navController: NavController
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val session = remember { ServerRESTs.getSession("LightWin")}
        println("Произвели запрос к серверу для активной сессии")
        if (session.contains("404")) {
            Text(text = "Не удалось найти игру")
            println("Не удалось найти игру")
        }
        else {
            InGameApp(session)
        }
    }
}


@Composable
@Preview
fun InGameApp(session : String) {
    MaterialTheme {
        makeRow(session)
    }
}


@Composable
private fun makeRow(session: String) {
    val uiRows = remember {
        mutableStateOf(Start.runGame(session))
    }

    val summonerName = remember {
        mutableListOf(
            uiRows.value[0].inGameSummonerInfo?.summonerName ?: "",
            uiRows.value[1].inGameSummonerInfo?.summonerName ?: "",
            uiRows.value[2].inGameSummonerInfo?.summonerName ?: "",
            uiRows.value[3].inGameSummonerInfo?.summonerName ?: "",
            uiRows.value[4].inGameSummonerInfo?.summonerName ?: "",
            uiRows.value[5].inGameSummonerInfo?.summonerName ?: "",
            uiRows.value[6].inGameSummonerInfo?.summonerName ?: "",
            uiRows.value[7].inGameSummonerInfo?.summonerName ?: "",
            uiRows.value[8].inGameSummonerInfo?.summonerName ?: "",
            uiRows.value[9].inGameSummonerInfo?.summonerName ?: "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
        )
    }
    val ddragonChampionInfoName = remember {
        uiRows.value.stream()
            .map {v -> v.championInfo?.balance?.ddragonChampionName ?: ""}
            .toList()
            .toMutableList()
    }
    val damageDealt =  remember {
        uiRows.value.stream()
            .map {v -> v.championInfo?.balance?.damageDealt ?: ""}
            .toList()
            .toMutableList()
    }
    val damageReceived = remember {
        uiRows.value.stream()
            .map {v -> v.championInfo?.balance?.damageReceived ?: ""}
            .toList()
            .toMutableList()
    }
    val abilityHaste = remember {
        uiRows.value.stream()
            .map {v -> v.championInfo?.balance?.abilityHaste ?: ""}
            .toList()
            .toMutableList()
    }
    val attackSpeed = remember {
        uiRows.value.stream()
            .map {v -> v.championInfo?.balance?.attackSpeed ?: ""}
            .toList()
            .toMutableList()
    }
    val shield = remember {
        uiRows.value.stream()
            .map {v -> v.championInfo?.balance?.shield ?: ""}
            .toList()
            .toMutableList()
    }
    val healing = remember {
        uiRows.value.stream()
            .map {v -> v.championInfo?.balance?.healing ?: ""}
            .toList()
            .toMutableList()
    }
    val tenacity = remember {
        uiRows.value.stream()
            .map {v -> v.championInfo?.balance?.tenacity ?: ""}
            .toList()
            .toMutableList()
    }
    val energy = remember {
        uiRows.value.stream()
            .map {v -> v.championInfo?.balance?.energy ?: ""}
            .toList()
            .toMutableList()
    }
    val extra = remember {
        uiRows.value.stream()
            .map {v -> v.championInfo?.balance?.extra ?: ""}
            .toList()
            .toMutableList()
    }
    val q = remember {
        mutableStateOf(1)
    }

    val color = remember {
        mutableStateOf(Color.Gray)
    }


    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        modifier = Modifier
            .width(700.dp)
            .height(800.dp),
        scaffoldState = scaffoldState,
        snackbarHost = { host ->
            SnackbarHost(hostState = host){ data ->
                Snackbar(
//                    backgroundColor = Color.White,
                    snackbarData = data,
                    shape = RoundedCornerShape(20.dp),
//                    modifier = Modifier
//                        .padding(bottom = 100.dp)
                )
            }
        }) {
        MaterialTheme {
            Column(
                horizontalAlignment = Alignment.Start
            ) {
                Row(
                    modifier = Modifier.padding(3.dp),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Box(
                        modifier = Modifier
                            .padding(3.dp)
                            .width(135.dp)
                            .height(30.dp)
                            .background(color = color.value),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "summoner name", style = TextStyle(color = Color.White, fontSize = 14.sp))
                    }
                    Box(
                        modifier = Modifier
                            .padding(3.dp)
                            .width(50.dp)
                            .height(50.dp)
                            .background(color = color.value),
                        contentAlignment = Alignment.Center
                    ) {
                        makeIconWithSnackBar(Cache.getHintByType(HintType.DAMAGE_DEALT), coroutineScope, scaffoldState)
                    }
                    Box(
                        modifier = Modifier
                            .padding(3.dp)
                            .width(50.dp)
                            .height(50.dp)
                            .background(color = color.value),
                        contentAlignment = Alignment.Center
                    ) {
                        makeIconWithSnackBar(
                            Cache.getHintByType(HintType.DAMAGE_RECEIVED),
                            coroutineScope,
                            scaffoldState
                        )
                    }
                    Box(
                        modifier = Modifier
                            .padding(3.dp)
                            .width(50.dp)
                            .height(50.dp)
                            .background(color = color.value),
                        contentAlignment = Alignment.Center
                    ) {
                        makeIconWithSnackBar(Cache.getHintByType(HintType.ABILITY_HASTE), coroutineScope, scaffoldState)
//                        Text(text = "ability  haste", style = TextStyle(color = Color.White, fontSize = 14.sp))
                    }
                    Box(
                        modifier = Modifier
                            .padding(3.dp)
                            .width(50.dp)
                            .height(50.dp)
                            .background(color = color.value),
                        contentAlignment = Alignment.Center
                    ) {
                        makeIconWithSnackBar(
                            Cache.getHintByType(HintType.ATTACK_SPEED_SCALING),
                            coroutineScope,
                            scaffoldState
                        )
//                        Text(text = "attack speed scaling", style = TextStyle(color = Color.White, fontSize = 14.sp))
                    }
                    Box(
                        modifier = Modifier
                            .padding(3.dp)
                            .width(50.dp)
                            .height(50.dp)
                            .background(color = color.value),
                        contentAlignment = Alignment.Center
                    ) {
                        makeIconWithSnackBar(
                            Cache.getHintByType(HintType.SHIELD_MODIFIER),
                            coroutineScope,
                            scaffoldState
                        )
                    }
                    Box(
                        modifier = Modifier
                            .padding(3.dp)
                            .width(50.dp)
                            .height(50.dp)
                            .background(color = color.value),
                        contentAlignment = Alignment.Center
                    ) {
                        makeIconWithSnackBar(
                            Cache.getHintByType(HintType.HEALING_MODIFIER),
                            coroutineScope,
                            scaffoldState
                        )
                    }
                    Box(
                        modifier = Modifier
                            .padding(3.dp)
                            .width(50.dp)
                            .height(50.dp)
                            .background(color = color.value),
                        contentAlignment = Alignment.Center
                    ) {
                        makeIconWithSnackBar(Cache.getHintByType(HintType.TENACITY), coroutineScope, scaffoldState)
                    }
                    Box(
                        modifier = Modifier
                            .padding(3.dp)
                            .width(50.dp)
                            .height(50.dp)
                            .background(color = color.value),
                        contentAlignment = Alignment.Center
                    ) {
                        makeIconWithSnackBar(
                            Cache.getHintByType(HintType.ENERGY_REGENERATION),
                            coroutineScope,
                            scaffoldState
                        )
                    }
                    Box(
                        modifier = Modifier
                            .padding(3.dp)
                            .width(50.dp)
                            .height(50.dp)
                            .background(color = color.value),
                        contentAlignment = Alignment.Center
                    ) {
                        makeIconWithSnackBar(Cache.getHintByType(HintType.EXTRA), coroutineScope, scaffoldState)
                    }

                }
                makeOneTeam(0,4,summonerName,
                    ddragonChampionInfoName, damageDealt, damageReceived,
                    abilityHaste, attackSpeed, shield, healing, tenacity,
                    energy, extra, coroutineScope, scaffoldState)
                Row {
                    Spacer(modifier = Modifier.size(30.dp))
                }
                makeOneTeam(5,9,summonerName,
                    ddragonChampionInfoName, damageDealt, damageReceived,
                    abilityHaste, attackSpeed, shield, healing, tenacity,
                    energy, extra, coroutineScope, scaffoldState)
                Box(
                    modifier = Modifier
                        .padding(3.dp)
                        .width(135.dp)
                        .height(30.dp)
                        .background(color = color.value),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = q.value.toString(), style = TextStyle(color = Color.White, fontSize = 14.sp))
                }
            }
        }
    }
}

@Composable
fun makeOneTeam(start: Int,
                end : Int,
                summonerName : MutableList<String>,
                ddragonChampionInfoName : MutableList<String>,
                damageDealt : MutableList<String>,
                damageReceived : MutableList<String>,
                abilityHaste : MutableList<String>,
                attackSpeed : MutableList<String>,
                shield : MutableList<String>,
                healing : MutableList<String>,
                tenacity : MutableList<String>,
                energy : MutableList<String>,
                extra : MutableList<String>,
                coroutineScope: CoroutineScope,
                scaffoldState : ScaffoldState
                ){
    for(i in start..end){
        Row() {
            makeSummonerAndChampion(
                summonerName[i],
                ddragonChampionInfoName[i]
            )
            Box(
                modifier = Modifier
                    .padding(3.dp)
                    .width(50.dp)
                    .height(30.dp)
                    .background(color = getColor(damageDealt[i], true, 1.0)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = toPercent(damageDealt[i]),
                    style = TextStyle(color = Color.Black, fontSize = 14.sp)
                )
            }
            Box(
                modifier = Modifier
                    .padding(3.dp)
                    .width(50.dp)
                    .height(30.dp)
                    .background(color = getColor(damageReceived[i], false, 1.0)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = toPercent(damageReceived[i]),
                    style = TextStyle(color = Color.Black, fontSize = 14.sp)
                )
            }
            Box(
                modifier = Modifier
                    .padding(3.dp)
                    .width(50.dp)
                    .height(30.dp)
                    .background(color = getColor(abilityHaste[i], true, 0.0)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = toIntegerWithoutPlus(abilityHaste[i]),
                    style = TextStyle(color = Color.White, fontSize = 14.sp)
                )
            }

            Box(
                modifier = Modifier
                    .padding(3.dp)
                    .width(50.dp)
                    .height(30.dp)
                    .background(color = getColor(attackSpeed[i], true, 0.0)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = toAttackSpeed(attackSpeed[i]),
                    style = TextStyle(color = Color.White, fontSize = 14.sp)
                )
            }

            Box(
                modifier = Modifier
                    .padding(3.dp)
                    .width(50.dp)
                    .height(30.dp)
                    .background(color = getColor(shield[i], true, 1.0)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = toPercent(shield[i]), style = TextStyle(color = Color.White, fontSize = 14.sp))
            }

            Box(
                modifier = Modifier
                    .padding(3.dp)
                    .width(50.dp)
                    .height(30.dp)
                    .background(color = getColor(healing[i], true, 1.0)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = toPercent(healing[i]), style = TextStyle(color = Color.White, fontSize = 14.sp))
            }

            Box(
                modifier = Modifier
                    .padding(3.dp)
                    .width(50.dp)
                    .height(30.dp)
                    .background(color = getColor(tenacity[i], true, 0.0)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = toIntegerWithPlus(tenacity[i]),
                    style = TextStyle(color = Color.White, fontSize = 14.sp)
                )
            }
            Box(
                modifier = Modifier
                    .padding(3.dp)
                    .width(50.dp)
                    .height(30.dp)
                    .background(color = getColor(energy[i], true, 0.0)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = toPercent(energy[i]), style = TextStyle(color = Color.White, fontSize = 14.sp))
            }
            makeExtraInfoBox(Cache.getHintByType(HintType.EXTRA), coroutineScope, scaffoldState, extra[i])
        }
    }
}
