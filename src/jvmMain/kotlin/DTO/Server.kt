package DTO


data class SummonerInfoFromServer(
    val id : String?,
    val accountId : String?,
    val puuid:String?,
    val name:String?,
    val profileIconId : String?,
    val revisionDate : Long?,
    val summonerLevel: Int?
)

data class InGameSummonerInfo(
    val teamId : Int?,
    val championId: String?,
    val summonerName : String?,
    val isAlly : Boolean
) {
}