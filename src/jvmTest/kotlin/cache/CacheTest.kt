package cache

import hints.HintType
import org.junit.Assert.*
import org.junit.Test

class CacheTest {

    @Test
    fun qwe() {
        val hint = Cache.getHintByType(HintType.DAMAGE_DEALT)
        println(hint)
    }
}