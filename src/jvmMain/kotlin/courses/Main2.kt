package courses

import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
@Preview
fun App2() {
    MaterialTheme {
        ListItem("qwe","zxc")
    }
}

@Composable
private fun ListItem(name:String, prof:String){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp
    ){
        Box(modifier = Modifier.background(Color.Green)){
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource("drawable/image.png"),
                    contentDescription = "image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(5.dp)
                        .size(64.dp)
                        .clip(CircleShape)
                )
                Column(modifier = Modifier.background(Color.Red)) {
                    Text(text = name)
                    Text(text = prof)
                }
            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App2()
    }
}
