package cache

import ddragon.AllChampions
import hints.Hint
import hints.HintType
import pastebin.GetAllChampionsService

object Cache {
    private var allChampions : AllChampions? = null
    private val hints : ArrayList<Hint> = ArrayList()

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
        hints.add(Hint(HintType.DAMAGE_DEALT, "Сколько урона вы наносите", null))
        hints.add(Hint(HintType.DAMAGE_RECEIVED, "Сколько урона вы наносите", null))
        hints.add(Hint(HintType.ABILITY_HASTE, "Сколько урона вы наносите", null))
        hints.add(Hint(HintType.ATTACK_SPEED_SCALING, "Сколько урона вы наносите", null))
        hints.add(Hint(HintType.SHIELD_MODIFIER, "Сколько урона вы наносите", null))
        hints.add(Hint(HintType.HEALING_MODIFIER, "Сколько урона вы наносите", null))
        hints.add(Hint(HintType.TENACITY, "Сколько урона вы наносите", null))
        hints.add(Hint(HintType.ENERGY_REGENERATION, "Сколько урона вы наносите", null))
        hints.add(Hint(HintType.EXTRA, "Сколько урона вы наносите", null))
        return hints
    }

    fun getHintByType(target: HintType):Hint {
        return getHints().stream().filter { it.type ==  target}.findAny().get()
    }
}