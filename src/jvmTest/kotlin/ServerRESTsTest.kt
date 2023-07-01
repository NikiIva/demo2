import org.junit.Test
import kotlin.test.assertTrue

class ServerRESTsTest {

    @Test
    fun testEncryptedId() {
        val encryptedSummonerInfo = ServerRESTs.getEncryptedSummonerInfo("LightWin")
        assertTrue { encryptedSummonerInfo.contains("id") }
        assertTrue { encryptedSummonerInfo.contains("puuid") }
        assertTrue { encryptedSummonerInfo.contains("accountId") }
    }

    @Test
    fun testGetSesion(){
        val session = ServerRESTs.getSession()
        println(session)
    }

}