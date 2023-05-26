import org.junit.Assert
import org.junit.Test

class ClientRESTsTest {
    val clientRESTs = ClientRESTs.data

    @Test
    fun qwe(){
        println(clientRESTs)
    }

    @Test
    fun testPost(){
        val id : String = "36"
        println(ClientRESTs.postData("lol-champ-select/v1/session/bench/swap/$id"))
    }

    @Test
    fun asd(){
        println(ClientRESTs.getCurrentChampion())
    }
}