package pastebin

import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils
import java.io.File
import java.io.InputStream
import javax.imageio.ImageIO

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
        val kindred = "https://ddragon.leagueoflegends.com/cdn/13.4.1/img/champion/$ddragonChampionName.png"
        HttpClients.createDefault().use { httpClient ->
            println("Отправялем запрос для $ddragonChampionName")
            val request = HttpGet(kindred)
            httpClient.execute(request).use { response ->
                if (response.statusLine.statusCode != 200) {
                    throw RuntimeException("Не удалось получить список чемпионов")
                }
                val inputStream : InputStream = response.entity.content
                val read = ImageIO.read(inputStream)
                ImageIO.write(read, "jpg", File("src/jvmMain/resources/drawable/$ddragonChampionName.jpg") );
            }
        }
    }
}