package pastebin

import org.junit.Assert.*
import org.junit.Test

class ExternalRESTsTest {

    @Test
    fun  qwe(){
        print(ExternalRESTs.getCloseableHttpResponse("https://ddragon.leagueoflegends.com/cdn/13.4.1/img/champion/Kindred.png").toString())
    }

    @Test
    fun testPastebin(){
        assertTrue(ExternalRESTs.getCloseableHttpResponse("https://pastebin.com/raw/Z6SWQNA9").toString().contains("\"championNameType\": \"Ahri\""))
    }
}