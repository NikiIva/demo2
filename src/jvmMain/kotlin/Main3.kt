import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
import kotlinx.coroutines.launch

@Composable
@Preview
fun App() {
    MaterialTheme {
        makeRow()
    }
}

@Composable
private fun makeRow() {

    val session = remember {
        mutableStateOf(
//            ClientRESTs.getSession()
            ClientRESTs.mockSession()
        )
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
    val championInfoKey = remember {
        mutableListOf(
            uiRows.value[0].championInfo?.key ?: "",
            uiRows.value[1].championInfo?.key ?: "",
            uiRows.value[2].championInfo?.key ?: "",
            uiRows.value[3].championInfo?.key ?: "",
            uiRows.value[4].championInfo?.key ?: "",
            uiRows.value[5].championInfo?.key ?: "",
            uiRows.value[6].championInfo?.key ?: "",
            uiRows.value[7].championInfo?.key ?: "",
            uiRows.value[8].championInfo?.key ?: "",
            uiRows.value[9].championInfo?.key ?: "",
            uiRows.value[10].championInfo?.key ?: "",
            uiRows.value[11].championInfo?.key ?: "",
            uiRows.value[12].championInfo?.key ?: "",
            uiRows.value[13].championInfo?.key ?: "",
            uiRows.value[14].championInfo?.key ?: "",
            uiRows.value[15].championInfo?.key ?: ""
        )
    }
    val ddragonChampionInfoName = remember {
        mutableListOf(
            uiRows.value[0].championInfo?.balance?.ddragonChampionName ?: "",
            uiRows.value[1].championInfo?.balance?.ddragonChampionName ?: "",
            uiRows.value[2].championInfo?.balance?.ddragonChampionName ?: "",
            uiRows.value[3].championInfo?.balance?.ddragonChampionName ?: "",
            uiRows.value[4].championInfo?.balance?.ddragonChampionName ?: "",
            uiRows.value[5].championInfo?.balance?.ddragonChampionName ?: "",
            uiRows.value[6].championInfo?.balance?.ddragonChampionName ?: "",
            uiRows.value[7].championInfo?.balance?.ddragonChampionName ?: "",
            uiRows.value[8].championInfo?.balance?.ddragonChampionName ?: "",
            uiRows.value[9].championInfo?.balance?.ddragonChampionName ?: "",
            uiRows.value[10].championInfo?.balance?.ddragonChampionName ?: "",
            uiRows.value[11].championInfo?.balance?.ddragonChampionName ?: "",
            uiRows.value[12].championInfo?.balance?.ddragonChampionName ?: "",
            uiRows.value[13].championInfo?.balance?.ddragonChampionName ?: "",
            uiRows.value[14].championInfo?.balance?.ddragonChampionName ?: ""
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
            uiRows.value[0].championInfo?.balance?.shield ?: "",
            uiRows.value[1].championInfo?.balance?.shield ?: "",
            uiRows.value[2].championInfo?.balance?.shield ?: "",
            uiRows.value[3].championInfo?.balance?.shield ?: "",
            uiRows.value[4].championInfo?.balance?.shield ?: "",
            uiRows.value[5].championInfo?.balance?.shield ?: "",
            uiRows.value[6].championInfo?.balance?.shield ?: "",
            uiRows.value[7].championInfo?.balance?.shield ?: "",
            uiRows.value[8].championInfo?.balance?.shield ?: "",
            uiRows.value[9].championInfo?.balance?.shield ?: "",
            uiRows.value[10].championInfo?.balance?.shield ?: "",
            uiRows.value[11].championInfo?.balance?.shield ?: "",
            uiRows.value[12].championInfo?.balance?.shield ?: "",
            uiRows.value[13].championInfo?.balance?.shield ?: "",
            uiRows.value[14].championInfo?.balance?.shield ?: ""
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

    fun customModifier(): Modifier {
        return Modifier
            .padding(3.dp)
            .width(100.dp)
            .height(30.dp)
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
                        makeIconWithSnakeBar(Cache.getHintByType(HintType.DAMAGE_DEALT), coroutineScope, scaffoldState)
                    }
                    Box(
                        modifier = Modifier
                            .padding(3.dp)
                            .width(50.dp)
                            .height(50.dp)
                            .background(color = color.value),
                        contentAlignment = Alignment.Center
                    ) {
                        makeIconWithSnakeBar(Cache.getHintByType(HintType.DAMAGE_RECEIVED), coroutineScope, scaffoldState)
                    }
                    Box(
                        modifier = Modifier
                            .padding(3.dp)
                            .width(50.dp)
                            .height(50.dp)
                            .background(color = color.value),
                        contentAlignment = Alignment.Center
                    ) {
                        makeIconWithSnakeBar(Cache.getHintByType(HintType.ABILITY_HASTE), coroutineScope, scaffoldState)
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
                        makeIconWithSnakeBar(Cache.getHintByType(HintType.ATTACK_SPEED_SCALING), coroutineScope, scaffoldState)
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
                        makeIconWithSnakeBar(Cache.getHintByType(HintType.SHIELD_MODIFIER), coroutineScope, scaffoldState)
                    }
                    Box(
                        modifier = Modifier
                            .padding(3.dp)
                            .width(50.dp)
                            .height(50.dp)
                            .background(color = color.value),
                        contentAlignment = Alignment.Center
                    ) {
                        makeIconWithSnakeBar(Cache.getHintByType(HintType.HEALING_MODIFIER), coroutineScope, scaffoldState)
                    }
                    Box(
                        modifier = Modifier
                            .padding(3.dp)
                            .width(50.dp)
                            .height(50.dp)
                            .background(color = color.value),
                        contentAlignment = Alignment.Center
                    ) {
                        makeIconWithSnakeBar(Cache.getHintByType(HintType.TENACITY), coroutineScope, scaffoldState)
                    }
                    Box(
                        modifier = Modifier
                            .padding(3.dp)
                            .width(50.dp)
                            .height(50.dp)
                            .background(color = color.value),
                        contentAlignment = Alignment.Center
                    ) {
                        makeIconWithSnakeBar(Cache.getHintByType(HintType.ENERGY_REGENERATION), coroutineScope, scaffoldState)
                    }
                    Box(
                        modifier = Modifier
                            .padding(3.dp)
                            .width(50.dp)
                            .height(50.dp)
                            .background(color = color.value),
                        contentAlignment = Alignment.Center
                    ) {
                        makeIconWithSnakeBar(Cache.getHintByType(HintType.EXTRA), coroutineScope, scaffoldState)
                    }

                }
                for (i in 0..14) {
                    Row() {
                        makeSummonerAndChampion(
                            summonerName[i],
                            ddragonChampionInfoName[i],
                            championInfoKey[i],
                            accountId[i]
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
                            Text(text = toPercent(damageReceived[i]),
                                style = TextStyle(color = Color.Black, fontSize = 14.sp))
                        }
                        Box(
                            modifier = Modifier
                                .padding(3.dp)
                                .width(50.dp)
                                .height(30.dp)
                                .background(color = getColor(abilityHaste[i], true, 0.0)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = toIntegerWithoutPlus(abilityHaste[i]), style = TextStyle(color = Color.White, fontSize = 14.sp))
                        }

                        Box(
                            modifier = Modifier
                                .padding(3.dp)
                                .width(50.dp)
                                .height(30.dp)
                                .background(color = getColor(attackSpeed[i], true, 0.0)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = toAttackSpeed(attackSpeed[i]), style = TextStyle(color = Color.White, fontSize = 14.sp))
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
                            Text(text = toIntegerWithPlus(tenacity[i]), style = TextStyle(color = Color.White, fontSize = 14.sp))
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
                        makeExtraInfoBox(Cache.getHintByType(HintType.EXTRA), coroutineScope, scaffoldState)
                    }
                }
            }

            LaunchedEffect(Unit) {
                while (true) {
                    delay(5_000)
                    val newSession = ClientRESTs.mockSession1()
//                val newSession = ClientRESTs.getSession()

                    session.value = newSession
                    uiRows = mutableStateOf(Start.run(session.value))
                    for (i in 0..14) {
                        summonerName[i] = uiRows.value[i].summonerInfo?.summonerName ?: ""
                        accountId[i] = uiRows.value[i].summonerInfo?.accountId ?: ""
                        championInfoName[i] = uiRows.value[i].championInfo?.name ?: ""
                        championInfoKey[i] = uiRows.value[i].championInfo?.key ?: ""
                        damageDealt[i] = uiRows.value[i].championInfo?.balance?.damageDealt ?: ""
                        damageReceived[i] = uiRows.value[i].championInfo?.balance?.damageReceived ?: ""
                        abilityHaste[i] = uiRows.value[i].championInfo?.balance?.abilityHaste ?: ""
                        attackSpeed[i] = uiRows.value[i].championInfo?.balance?.attackSpeed ?: ""
                        shield[i] = uiRows.value[i].championInfo?.balance?.shield ?: ""
                        healing[i] = uiRows.value[i].championInfo?.balance?.healing ?: ""
                        tenacity[i] = uiRows.value[i].championInfo?.balance?.tenacity ?: ""
                        energy[i] = uiRows.value[i].championInfo?.balance?.energy ?: ""
                        ddragonChampionInfoName[i] = uiRows.value[i].championInfo?.balance?.ddragonChampionName ?: "0.0"
                    }

                }
            }
        }
    }
}

fun toAttackSpeed(string:String?):String {
    val double = string?.toDoubleOrNull() ?: return ""
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
    Window(onCloseRequest = ::exitApplication,
        resizable = false,
        state = windowState
    ) {
        println(DpSize.Unspecified)
        App()
    }
}
