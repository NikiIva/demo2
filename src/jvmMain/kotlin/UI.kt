import DTO.Champion
import DTO.InGameSummonerInfo

data class UIRow (
    val summonerInfo : SummonerInfo? = null,
    val championInfo: Champion?,
    val inGameSummonerInfo : InGameSummonerInfo? = null
)

data class SummonerInfo(
    val summonerId : String?,
    var championId :String?,
    var cellId : String?
) {
    var summonerName : String = ""
    var accountId: String? = ""
    override fun toString(): String {
        return "SummonerInfo(summonerId='$summonerId', accountId='$accountId', championId='$championId', cellId='$cellId', summonerName='$summonerName')"
    }
}