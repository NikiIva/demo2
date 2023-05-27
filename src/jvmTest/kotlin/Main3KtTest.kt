import org.junit.Assert.*
import org.junit.Test

class Main3KtTest {
    @Test
    fun qwe(){
        println(toPercent(null))
        println(toPercent(""))
        println(toPercent(".85"))
        println(toPercent("aaa"))
    }
}