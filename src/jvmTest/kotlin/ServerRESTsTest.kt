import org.junit.Test
import kotlin.test.assertTrue

class ServerRESTsTest {

    @Test
    fun testEncryptedId() {
        val encryptedSummonerInfo = ServerRESTs.getEncryptedSummonerInfo("LightWin")
        assertTrue { encryptedSummonerInfo.id == "yjHnPKitY4h3wxBdMo9Fif8aC_OHtS0Bgf7AH79ngqZO" }
        assertTrue { encryptedSummonerInfo.accountId == "F0VKeDNhVFcNGxdLf_7QLTON6XU61Z4XUNlXCsXatnwPXPs" }
        assertTrue { encryptedSummonerInfo.puuid == "1To7ifufxfhHPqgpct9wTLZv6JZ_0Exfpx23-nAnu3qzuwA2pJGC2opXpagRa3nAbO3sabnCPafUCQ" }
        assertTrue { encryptedSummonerInfo.name == "LightWin" }
    }
}