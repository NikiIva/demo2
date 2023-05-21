package courses

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.loadSvgPainter
import androidx.compose.ui.res.loadXmlImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.singleWindowApplication
import com.caverock.androidsvg.SVGParseException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.xml.sax.InputSource
import java.io.*
import java.net.MalformedURLException
import java.net.URL
import javax.imageio.ImageIO


@Throws(IOException::class)
fun givenUsingPlainJava_whenConvertingFileToInputStream_thenCorrect() {
    val initialFile = File("src/main/resources/sample.txt")
    val targetStream: InputStream = FileInputStream(initialFile)
}

fun imageByter(): ByteArray? {
    return try {
        val url = URL("https://ddragon.leagueoflegends.com/cdn/13.4.1/img/champion/Kindred.png")
        val inStream = url.content as InputStream
        val buffer = ByteArray(8192)
        var bytesRead: Int
        val output = ByteArrayOutputStream()
        while (inStream.read(buffer).also { bytesRead = it } != -1) {
            output.write(buffer, 0, bytesRead)
        }
        output.toByteArray()
    } catch (e: MalformedURLException) {
        e.printStackTrace()
        null
    } catch (e: IOException) {
        e.printStackTrace()
        null
    }
}

@Throws(SVGParseException::class)
fun getFromInputStream(inp: InputStream?) {
    val read = ImageIO.read(inp)
    ImageIO.write(read, "jpg", File("outputImage.jpg") );
}

fun main() = singleWindowApplication {
//    val url = URL("https://ddragon.leagueoflegends.com/cdn/13.4.1/img/champion/Kindred.png")
//    val inStream = url.content as InputStream
//    getFromInputStream(inStream)

    val density = LocalDensity.current
    Column {
//        AsyncImage(
//            load = { loadImageBitmap(File("sample.png")) },
//            painterFor = { remember { BitmapPainter(it) } },
//            contentDescription = "Sample",
//            modifier = Modifier.width(200.dp)
//        )
        AsyncImage(
            load = { loadSvgPainter("https://github.com/JetBrains/compose-multiplatform/raw/master/artwork/idea-logo.svg", density) },
            painterFor = { it },
            contentDescription = "Idea logo",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.width(200.dp)
        )
//        AsyncImage(
//            load = { loadXmlImageVector(File("compose-logo.xml"), density) },
//            painterFor = { rememberVectorPainter(it) },
//            contentDescription = "Compose logo",
//            contentScale = ContentScale.FillWidth,
//            modifier = Modifier.width(200.dp)
//        )
    }
}

@Composable
fun <T> AsyncImage(
    load: suspend () -> T,
    painterFor: @Composable (T) -> Painter,
    contentDescription: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit,
) {
    val image: T? by produceState<T?>(null) {
        value = withContext(Dispatchers.IO) {
            try {
                load()
            } catch (e: IOException) {
                // instead of printing to console, you can also write this to log,
                // or show some error placeholder
                e.printStackTrace()
                null
            }
        }
    }

    if (image != null) {
        Image(
            painter = painterFor(image!!),
            contentDescription = contentDescription,
            contentScale = contentScale,
            modifier = modifier
        )
        Image(painter = painterResource("drawable/qwe.jpg"), contentDescription = "")
    }
}

/* Loading from file with java.io API */

fun loadImageBitmap(file: File): ImageBitmap =
    file.inputStream().buffered().use(::loadImageBitmap)

fun loadSvgPainter(file: File, density: Density): Painter =
    file.inputStream().buffered().use { loadSvgPainter(it, density) }

fun loadXmlImageVector(file: File, density: Density): ImageVector =
    file.inputStream().buffered().use { loadXmlImageVector(InputSource(it), density) }

/* Loading from network with java.net API */

fun loadImageBitmap(url: String): ImageBitmap =
    URL(url).openStream().buffered().use(::loadImageBitmap)

fun loadSvgPainter(url: String, density: Density): Painter =
    URL(url).openStream().buffered().use { loadSvgPainter(it, density) }

fun loadXmlImageVector(url: String, density: Density): ImageVector =
    URL(url).openStream().buffered().use { loadXmlImageVector(InputSource(it), density) }

/* Loading from network with Ktor client API (https://ktor.io/docs/client.html). */

/*

suspend fun loadImageBitmap(url: String): ImageBitmap =
    urlStream(url).use(::loadImageBitmap)

suspend fun loadSvgPainter(url: String, density: Density): Painter =
    urlStream(url).use { loadSvgPainter(it, density) }

suspend fun loadXmlImageVector(url: String, density: Density): ImageVector =
    urlStream(url).use { loadXmlImageVector(InputSource(it), density) }

@OptIn(KtorExperimentalAPI::class)
private suspend fun urlStream(url: String) = HttpClient(CIO).use {
    ByteArrayInputStream(it.get(url))
}

 */