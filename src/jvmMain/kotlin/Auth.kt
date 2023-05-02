import org.apache.http.HttpEntity
import org.apache.http.HttpResponse
import org.apache.http.auth.AuthScope
import org.apache.http.auth.Credentials
import org.apache.http.auth.UsernamePasswordCredentials
import org.apache.http.client.CredentialsProvider
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.BasicCredentialsProvider
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils
import java.util.*
import javax.net.ssl.*


object UserAuthenticationExample {
    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
/*
//        /* Start of Fix */
//        /* Start of Fix */
//        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
//
//            override fun getAcceptedIssuers(): Array<X509Certificate> {
//                TODO("Not yet implemented")
//            }
//
//            override fun checkClientTrusted(certs: Array<X509Certificate?>?, authType: String?) {}
//            override fun checkServerTrusted(certs: Array<X509Certificate?>?, authType: String?) {}
//        })
//
//        val sc = SSLContext.getInstance("SSL")
//        sc.init(null, trustAllCerts, SecureRandom())
//        HttpsURLConnection.setDefaultSSLSocketFactory(sc.socketFactory)
//
//        // Create all-trusting host name verifier
//        val allHostsValid = HostnameVerifier { hostname, session -> true }
//        // Install the all-trusting host verifier
//        // Install the all-trusting host verifier
//        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid)
//        /* End of the fix*/
*/
        getData()
        //Create an object of credentialsProvider

    }

    fun getData(val port:String, val password: String){
        val request = HttpGet("https://127.0.0.1:57587/lol-summoner/v1/current-summoner")

        val provider: CredentialsProvider = BasicCredentialsProvider()
        provider.setCredentials(
            AuthScope.ANY,
            UsernamePasswordCredentials("riot", "qYeyPe39_Jz-3UWk_cjDew")
        )

        HttpClientBuilder.create()
            .setDefaultCredentialsProvider(provider)
            .build().use { httpClient ->
                httpClient.execute(request).use { response ->

                    // 401 if wrong user/password
                    System.out.println(response.getStatusLine().getStatusCode())
                    val entity: HttpEntity = response.getEntity()
                    if (entity != null) {
                        // return it as a String
                        val result: String = EntityUtils.toString(entity)
                        println(result)
                    }
                }
            }

    }

    fun qwe(){
        val credentialsPovider: CredentialsProvider = BasicCredentialsProvider()

//        https://127.0.0.1:51016/lol-summoner/v1/current-summoner
        //Set the credentials
        val scope = AuthScope("https://127.0.0.1:51016/lol-summoner/v1/current-summoner", 51016)
        val credentials: Credentials = UsernamePasswordCredentials("riot", "qYeyPe39_Jz-3UWk_cjDew")
        credentialsPovider.setCredentials(scope, credentials)

        //Creating the HttpClientBuilder
        var clientbuilder = HttpClients.custom()

        //Setting the credentials
        clientbuilder = clientbuilder.setDefaultCredentialsProvider(credentialsPovider)

        //Building the CloseableHttpClient object
        val httpclient = clientbuilder.build()

        //Creating a HttpGet object
        val httpget = HttpGet("https://127.0.0.1:51016/lol-summoner/v1/current-summoner") //https://www.tutorialspoint.com/questions/index.php

        //Printing the method used
        println(httpget.method)

        //Executing the Get request
        val httpresponse: HttpResponse = httpclient.execute(httpget)

        //Printing the status line
        println(httpresponse.statusLine)
        val statusCode = httpresponse.statusLine.statusCode
        println(statusCode)
        val headers = httpresponse.allHeaders
        for (i in headers.indices) {
            println(headers[i].name)
        }
    }
}