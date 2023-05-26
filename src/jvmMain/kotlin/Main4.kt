import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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

@Composable
@Preview
fun App123() {
    MaterialTheme {
        makeSummonerAndChampion("qwe", "Aatrox")
    }
}

@Composable
fun makeSummonerAndChampion(summonerName:String, ddragonChampionInfoName:String) {
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
                .clip(CircleShape),
            alignment = Alignment.Center
        )
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App123()
    }
}
