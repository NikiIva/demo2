import org.apache.http.HttpEntity
import org.apache.http.auth.AuthScope
import org.apache.http.auth.UsernamePasswordCredentials
import org.apache.http.client.CredentialsProvider
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.BasicCredentialsProvider
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.util.EntityUtils

class Data {

    fun getSummonerInfoById(id:String) : String{
        val data = GetAuthData().getData()
        return getData("lol-summoner/v1/summoners/$id", data["port"], data["token"])
    }

    fun getSession():String {
        val data = GetAuthData().getData()
        return getData("lol-champ-select/v1/session", data["port"], data["token"])
    }

    fun getData(url: String, port: String?, password: String?) : String{
        val request = HttpGet("https://127.0.0.1:$port/$url")

        val provider: CredentialsProvider = BasicCredentialsProvider()
        provider.setCredentials(
            AuthScope.ANY,
            UsernamePasswordCredentials("riot", password)
        )

        HttpClientBuilder.create()
            .setDefaultCredentialsProvider(provider)
            .build().use { httpClient ->
                httpClient.execute(request).use { response ->

                    // 401 if wrong user/password
                    System.out.println(response.getStatusLine().getStatusCode())
                    val entity: HttpEntity = response.getEntity()
                    if (entity != null) {
                        val result: String = EntityUtils.toString(entity)
                        println(result)
                        return result
                    }
                }
            }
        throw RuntimeException("Не удалось получить данные для запроса $url")
    }
}