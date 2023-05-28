import org.junit.Assert.*
import org.junit.Test

class Main3KtTest {
    @Test
    fun qwe(){
        println(toPercent(null))
        println(toPercent(""))
        println(toPercent("0.85"))
        println(toPercent("aaa"))
    }

    @Test
    fun testToIntegerWithoutPlus(){
        println(toIntegerWithoutPlus(null))
        println(toIntegerWithoutPlus("aa"))
        println(toIntegerWithoutPlus(".85"))
        println(toIntegerWithoutPlus("20"))
        println(toIntegerWithoutPlus("20.0"))
        println(toIntegerWithoutPlus("-20"))
        println(toIntegerWithoutPlus("-20.0"))
    }

    @Test
    fun testToIntegerWithPlus(){
        println(toIntegerWithPlus(null))
        println(toIntegerWithPlus("aa"))
        println(toIntegerWithPlus(".85"))
        println(toIntegerWithPlus("20"))
        println(toIntegerWithPlus("-20"))
        println(toIntegerWithPlus("20.0"))
        println(toIntegerWithPlus("-20.0"))
    }

    @Test
    fun testToAttackSpeed(){
        println(toAttackSpeed(null))
        println(toAttackSpeed("aa"))
        println(toAttackSpeed(".85"))
        println(toAttackSpeed("20"))
        println(toAttackSpeed("-20"))
        println(toAttackSpeed("20.0"))
        println(toAttackSpeed("-20.0"))
    }
}