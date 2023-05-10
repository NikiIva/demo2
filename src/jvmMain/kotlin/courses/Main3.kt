package courses

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
fun App3() {
    MaterialTheme {
        CircleItem()
    }
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
        App3()
    }
}
