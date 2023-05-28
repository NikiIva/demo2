package cache

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import ddragon.AllChampions
import hints.Hint
import hints.HintType
import pastebin.ExternalRESTs
import pastebin.GetAllChampionsService

object Cache {
    private var allChampions : AllChampions? = null
    private val hints : ArrayList<Hint> = ArrayList()
    private val version : String? = null

    fun getVersion(): String {
        if (version != null) {
            return version
        }
        val version : String? =
            ExternalRESTs.getCloseableHttpResponse("https://ddragon.leagueoflegends.com/api/versions.json")
        val versionJson = ObjectMapper().readValue(
            version,
            JsonNode::class.java
        )
        return versionJson.get(0).textValue()
    }

    fun getAllChampions(): AllChampions? {
        if (allChampions == null){
            allChampions = GetAllChampionsService.allChampions
        }
        return allChampions
    }

    fun getHints(): ArrayList<Hint> {
        if (hints.isNotEmpty()) {
            return hints
        }
        hints.add(Hint(HintType.DAMAGE_DEALT, "damage dealt", "Сколько урона вы наносите", null))
        hints.add(Hint(HintType.DAMAGE_RECEIVED, "damage received", "Сколько урона вы получаете", null))
        hints.add(Hint(HintType.ABILITY_HASTE, "ability haste", "Ускорение перезерядки умений", null))
        hints.add(Hint(HintType.ATTACK_SPEED_SCALING, "attack speed scaling","Ускорение скорости атаки", null))
        hints.add(Hint(HintType.SHIELD_MODIFIER, "shield modifier", "Сила накладываемых щитов", null))
        hints.add(Hint(HintType.HEALING_MODIFIER, "healing modifier","сила лечения", null))
        hints.add(Hint(HintType.TENACITY, "tenacity","Сопротивление контролю", null))
        hints.add(Hint(HintType.ENERGY_REGENERATION, "energy regeneration", "Восстановление энергии", null))
        hints.add(Hint(HintType.EXTRA, "extra","Уникальные изменения чемпионов", null))
        return hints
    }

    fun getHintByType(target: HintType):Hint {
        return getHints().stream().filter { it.type ==  target}.findAny().get()
    }
}