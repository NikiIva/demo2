import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import hints.Hint
import hints.HintType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun makeSummonerAndChampion(summonerName:String, ddragonChampionInfoName:String, championInfoKey:String, playerId:String) { //у меня сейчас summonerId, возможно, надо accountId
    println("PlayerId=$playerId")
    Row(
        modifier = Modifier.padding(3.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(modifier = Modifier
            .width(100.dp)
            .height(30.dp),
            shape = RoundedCornerShape(5.dp)
        ) {
            Text(
                modifier = Modifier
                    .background(Color.Gray)
                    .padding(start=3.dp)
                    .size(35.dp),
                softWrap = false,
                text = summonerName,
                style = TextStyle(fontSize = 20.sp),
                textAlign = TextAlign.Left,
                overflow = TextOverflow.Ellipsis
            )
        }
        var ddragonName = ddragonChampionInfoName
        if (ddragonName == "") {
            ddragonName = "0.0"
        }
        Image(
            painter = painterResource("drawable/${ddragonName}.jpg"),
            contentDescription = "image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(start = 3.dp)
                .size(35.dp)
                .clip(CircleShape)
//                .clickable {
//                    try {
//                        ClientRESTs.swapBench(championInfoKey)
//                    } catch(e:Exception){
//                        println("Не удалось поменять чемпиона со скамейки $championInfoKey ошибка:$e")
//                    }
//                }
            ,
            alignment = Alignment.Center
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun makeIconWithSnakeBar(hint: Hint, coroutineScope: CoroutineScope, scaffoldState:ScaffoldState){
    TooltipArea(
        tooltip = {
            Surface(
                modifier = Modifier.shadow(4.dp),
                color = Color(255, 255, 210),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(
                    text = "Tooltip for ${hint}",
                    modifier = Modifier.padding(10.dp)
                )
            }
        },
        delayMillis = 600, // in milliseconds
        tooltipPlacement = TooltipPlacement.CursorPoint(
            alignment = Alignment.BottomEnd,
            offset =  DpOffset.Zero // tooltip offset
        )
    ) {
        IconButton(
            onClick = {
                coroutineScope.launch{
                    val message = when(hint.type){
                        HintType.EXTRA -> "${hint.description}\nTODO:Подгрузить дополнительную информацию"
                        else -> hint.description
                    }

                    val result = scaffoldState.snackbarHostState.showSnackbar(
                        message = message,
                        actionLabel = "Hide"
                    )
                    if (result == SnackbarResult.ActionPerformed) {
//                                    Toast.makeText(context, "Item recovered", Toast.LENGTH_SHORT)
//                                        .show()
                    }

                }
            }
        ) {
            Image(
                painter = painterResource("icons/${hint.type.toString()}.png"),
                contentDescription = "image",
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
            )
        }
    }

}

@Composable
fun makeExtraInfoBox(hint: Hint, coroutineScope: CoroutineScope, scaffoldState:ScaffoldState, extra:String){
    Box(
        modifier = Modifier
            .padding(3.dp)
            .width(50.dp)
            .height(30.dp),
        contentAlignment = Alignment.Center
    ) {
        if (extra != null && !extra.isEmpty()) {
            IconButton(
                modifier = Modifier.background(Color.Yellow)
                    .padding(3.dp)
                    .width(50.dp)
                    .height(30.dp),
                onClick = {

                    coroutineScope.launch {
                        val message = when (hint.type) {
                            HintType.EXTRA -> "${extra}"
                            else -> hint.description
                        }

                        val result = scaffoldState.snackbarHostState.showSnackbar(
                            message = message,
                            actionLabel = "Hide"
                        )
                        if (result == SnackbarResult.ActionPerformed) {
//                                    Toast.makeText(context, "Item recovered", Toast.LENGTH_SHORT)
//                                        .show()
                        }

                    }

                }
            ) {
//                Image(
//                    modifier = Modifier
//                        .padding(3.dp)
//                        .fillMaxSize(),
//                    painter = painterResource("icons/${hint.type}.png"),
//                    contentDescription = "image",
//                    contentScale = ContentScale.Crop,
//                    alignment = Alignment.Center,
//                )
            }

        }
        else {
            Text(
                modifier = Modifier
                    .background(Color.Black)
                    .padding(start=3.dp)
                    .size(35.dp),
                softWrap = false,
                text = "",
                textAlign = TextAlign.Left,
                overflow = TextOverflow.Ellipsis
            )
//            Image(
//                modifier = Modifier
//                    .padding(3.dp)
//                    .fillMaxSize(),
//                painter = painterResource("drawable/0.0.jpg"),
//                contentDescription = "image",
//                contentScale = ContentScale.Crop,
//                alignment = Alignment.Center,
//            )
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        makeSummonerAndChampion("qwe", "Aatrox", "266", "qwe")
    }
}
