import ddragon.Champion

data class UIRow (
    val summonerInfo : SummonerInfo?,
    val championInfo: Champion?
)

data class SummonerInfo(
    val summonerId : String?,
    var championId :String?,
    var cellId : String?
) {
    var summonerName : String = ""
    override fun toString(): String {
        return "SummonerInfo(summonerId='$summonerId', championId='$championId', cellId='$cellId', summonerName='$summonerName')"
    }
}