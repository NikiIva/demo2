package courses

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application


//@Composable
//fun Greeting(name: String, context: Context) {
//    val state = remember {
//        mutableStateOf("Unknown")
//    }
//    Column(
//        modifier = Modifier.fillMaxSize()
//    ) {
//        Box(
//            modifier = Modifier
//                .fillMaxHeight(0.5f)
//                .fillMaxWidth(),
//            contentAlignment = Alignment.Center
//        ) {
//            Text(text = "Temperature in $name: ${state.value} Cº")
//        }
//        Box(
//            modifier = Modifier
//                .fillMaxSize(),
//            contentAlignment = Alignment.BottomCenter
//        ) {
//            Button(onClick = {
//                getData(name, context, state)
//            }, modifier = Modifier.fillMaxWidth().padding(5.dp)) {
//                Text(text = "Refresh")
//            }
//        }
//
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    WetherAppComposeTheme {
//
//    }
//}
//
//fun getData(name: String, context: Context, mState: MutableState<String>){
//    val url = "https://api.weatherapi.com/v1/current.json" +
//            "?key=$API_KEY&" +
//            "q=$name" +
//            "&aqi=no"
//    val queue = Volley.newRequestQueue(context)
//    val stringRequest = StringRequest(
//        Request.Method.GET,
//        url,
//        {
//                response->
//            val obj = JSONObject(response)
//            val temp = obj.getJSONObject("current")
//            mState.value = temp.getString("temp_c")
//            Log.d("MyLog","Response: ${temp.getString("temp_c")}")
//        },
//        {
//            Log.d("MyLog","Volley error: $it")
//        }
//    )
//    queue.add(stringRequest)
//}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App3()
    }
}
