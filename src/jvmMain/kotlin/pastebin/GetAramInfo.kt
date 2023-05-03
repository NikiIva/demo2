package pastebin

import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils

object GetAramInfo {
    //    https://pastebin.com/raw/Z6SWQNA9

    @JvmStatic
    fun main(args: Array<String>) {
        println(getCloseableHttpResponse("https://pastebin.com/raw/Z6SWQNA9"))
    }

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
}