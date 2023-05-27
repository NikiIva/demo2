import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.apache.commons.logging.Log

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
                .clickable {
                    try {
                        ClientRESTs.swapBench(championInfoKey)
                    } catch(e:Exception){
                        println("Не удалось поменять чемпиона со скамейки $championInfoKey ошибка:$e")
                    }
                },
            alignment = Alignment.Center
        )
    }
}

@Composable
fun makeIcon(path:String, hint:String?, coroutineScope: CoroutineScope, scaffoldState:ScaffoldState){
    IconButton(
        onClick = {
            coroutineScope.launch{
                if (hint!=null) {
                    val result = scaffoldState.snackbarHostState.showSnackbar(
                        message = hint,
                        actionLabel = "Hide"
                    )
                    if (result == SnackbarResult.ActionPerformed) {
//                                    Toast.makeText(context, "Item recovered", Toast.LENGTH_SHORT)
//                                        .show()
                    }
                }
            }
        }
    ) {
        Image(
            painter = painterResource("icons/$path.png"),
            contentDescription = "image",
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,
        )
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        makeSummonerAndChampion("qwe", "Aatrox", "266", "qwe")
    }
}
