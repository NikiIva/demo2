package courses

import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils
import org.junit.Assert.*
import org.junit.Test
import pastebin.ExternalRESTs
import java.io.File
import java.io.InputStream
import java.net.URL
import javax.imageio.ImageIO

class ImageKtTest{
    val kindred = "https://ddragon.leagueoflegends.com/cdn/13.4.1/img/champion/Kindred.png"

    @Test
    fun tryCache(){
        ExternalRESTs.savePng("Kindred")
    }

    @Test
    fun asd(){
        HttpClients.createDefault().use { httpClient ->
            val request = HttpGet(kindred)
            httpClient.execute(request).use { response ->
                if (response.statusLine.statusCode != 200) {
                    throw RuntimeException("Не удалось получить список чемпионов")
                }
                val inputStream : InputStream = response.entity.content
                val read = ImageIO.read(inputStream)
                ImageIO.write(read, "jpg", File("qwe.jpg") );
            }
        }
    }


//    @Test
//    fun qwe(){
//        val url = URL()
//        val inStream = url.content as InputStream
//        getFromInputStream(inStream)
//        val read = ImageIO.read(inStream)
//        ImageIO.write(read, "jpg", File("outputImage.jpg") );
//    }
}