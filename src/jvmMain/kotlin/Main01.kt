import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
@Preview
fun App1() {
    var text by remember { mutableStateOf("Hello, World!") }

    MaterialTheme {
        Row(
            modifier = Modifier
                .background(Color.Gray)
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
        )
        {
            Column(
                modifier = Modifier
                    .background(Color.Red)
                    .fillMaxWidth(0.5f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text("hello")
                Text("Gerona")
                Text("Madrid")
            }
            Column(
                modifier = Modifier.background(Color.Green).fillMaxHeight(0.85f),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.End
            ) {
                Text("hello")
                Text("Gerona")
                Text("Madrid")
            }

        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App1()
    }
}
