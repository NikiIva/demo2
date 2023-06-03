package pastebin

import cache.Cache
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils
import java.io.File
import java.io.InputStream
import javax.imageio.ImageIO
import javax.imageio.stream.FileImageOutputStream

object ExternalRESTs {
    fun getCloseableHttpResponse(url: String?): String? {
        HttpClients.createDefault().use { httpClient ->
            val request = HttpGet(url)
            httpClient.execute(request).use { response ->
                if (response.statusLine.statusCode != 200) {
                    throw RuntimeException("Не удалось получить список чемпионов")
                }
                val entity = response.entity
                if (entity != null) {
                    return EntityUtils.toString(entity)
                }
            }
        }
        return null
    }

    fun savePng(ddragonChampionName : String){
        val exists = File("src/jvmMain/resources/drawable/$ddragonChampionName.jpg").exists()
        if (exists){
            return
        }
        val uri = "https://ddragon.leagueoflegends.com/cdn/${Cache.getVersion()}/img/champion/$ddragonChampionName.png"
        HttpClients.createDefault().use { httpClient ->
            println("Отправялем запрос для $ddragonChampionName")
            val request = HttpGet(uri)
            httpClient.execute(request).use { response ->
                if (response.statusLine.statusCode != 200) {
                    throw RuntimeException("Не удалось получить список чемпионов")
                }
                try {
                    val inputStream: InputStream = response.entity.content
                    val read = ImageIO.read(inputStream)
                    inputStream.close()
                    val imout = FileImageOutputStream(File("src/jvmMain/resources/drawable/$ddragonChampionName.jpg"))
                    ImageIO.write(read, "jpg", imout)
                    imout.flush()
                    imout.close()
                } catch (e : Exception){
                    println("Не уадлось скачатьи и сохранить $ddragonChampionName")
                    throw e
                }
            }
        }
    }
}