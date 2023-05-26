import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.launch
import org.apache.commons.logging.Log

@Composable
fun makeSummonerAndChampion(summonerName:String, ddragonChampionInfoName:String, championInfoKey:String) {
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
//                           ClientRESTs.postData()
                    ClientRESTs.swapBench(championInfoKey)
                },
            alignment = Alignment.Center
        )
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        makeSummonerAndChampion("qwe", "Aatrox", "266")
    }
}
