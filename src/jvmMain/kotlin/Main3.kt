import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import cache.Cache
import hints.HintType
import kotlinx.coroutines.delay

@Composable
@Preview
fun App() {
    MaterialTheme {
        makeRow()
    }
}

@Composable
private fun makeRow() {
    val uiRows = remember {
        mutableStateOf(Start.run(
            ClientRESTs.getSession()
//            ClientRESTs.mockSession()
        ))
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
    val accountId = remember {
        mutableListOf(
            uiRows.value[0].summonerInfo?.accountId ?: "",
            uiRows.value[1].summonerInfo?.accountId ?: "",
            uiRows.value[2].summonerInfo?.accountId ?: "",
            uiRows.value[3].summonerInfo?.accountId ?: "",
            uiRows.value[4].summonerInfo?.accountId ?: "",
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
        uiRows.value.stream()
            .map {v -> v.championInfo?.name ?: ""}
            .toList()
            .toMutableList()
    }
    val championInfoKey = remember {
        uiRows.value.stream()
            .map {v -> v.championInfo?.key ?: ""}
            .toList()
            .toMutableList()
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
                for (i in 0..14) {
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
                Box(
                    modifier = Modifier
                        .padding(3.dp)
                        .width(135.dp)
                        .height(30.dp)
                        .background(color = color.value),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = q.value.toString(), style = TextStyle(color = Color.White, fontSize = 14.sp))
//                    Text(text = uiRows.value.)
                }
            }


//            LaunchedEffect(Unit) {
//                while (true) {
//                    delay(5_000)
//                    SessionUpdate.updateSession(uiRows, summonerName, accountId, championInfoName,
//                        championInfoKey, damageDealt, damageReceived, abilityHaste, attackSpeed,
//                        shield, healing, tenacity, energy, ddragonChampionInfoName, extra, q, null)
//                }
//            }
        }
    }
}




fun toAttackSpeed(string:String?):String {
    var double = string?.toDoubleOrNull() ?: return ""
    double = double * 100
    println("attackspeed $double")
    if (double < 0){
        return "$double%"
    }
    return "+$double%"
}

fun toPercent(percent:String?) : String {
    val double = percent?.toDoubleOrNull() ?: return ""
    val integer = double * 100
    return "${integer.toInt()} %"
}

fun toIntegerWithoutPlus(percent:String?):String {
    val ret = percent?.toDoubleOrNull() ?: return ""
    val res = ret.toInt()
    if (res == 0) {
        return ""
    }
    return "${ret.toInt()}"
}

fun toIntegerWithPlus(string:String?):String{
    val ret = toIntegerWithoutPlus(string)
    if (ret == ""){
        return ""
    }
    if (ret.toInt() < 0) {
        return ret
    }
    return "+$ret"
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
    val windowState = rememberWindowState(size = DpSize.Unspecified)
//    val icon = painterResource("main.png")
    Window(onCloseRequest = ::exitApplication,
        resizable = false,
        state = windowState,
//        icon = icon

    ) {
        println(DpSize.Unspecified)
        App()
    }
}
