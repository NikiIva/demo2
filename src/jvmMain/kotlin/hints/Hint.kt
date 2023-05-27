package hints

data class Hint(
    val type:HintType,
    val description:String,
    val extra:String?
)

enum class HintType{
    DAMAGE_DEALT,
    DAMAGE_RECEIVED,
    ABILITY_HASTE,
    ATTACK_SPEED_SCALING,
    SHIELD_MODIFIER,
    HEALING_MODIFIER,
    TENACITY,
    ENERGY_REGENERATION,
    EXTRA
}