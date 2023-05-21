package pastebin

import org.junit.Assert.*
import org.junit.Test

class ExternalRESTsTest {

    @Test
    fun  qwe(){
        print(ExternalRESTs.getCloseableHttpResponse("https://pastebin.com/raw/Z6SWQNA9").toString())
    }

    @Test
    fun testPastebin(){
        assertTrue(ExternalRESTs.getCloseableHttpResponse("https://pastebin.com/raw/Z6SWQNA9").toString().contains("\"championNameType\": \"Ahri\""))
    }
}