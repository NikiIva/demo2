import DTO.InGameSummonerInfo
import cache.Cache
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import pastebin.GetAllChampionsService

object Start {
    fun run(session : String): ArrayList<UIRow> {
        val mapper = ObjectMapper()
        var jsonNode: JsonNode = mapper.readTree(session)
        val benchChampionIds = SessionUtils.getBenchChampionIds(jsonNode)
        val allies : List<SummonerInfo> = SessionUtils.getAllies(jsonNode)
        for (ally in allies) {
            jsonNode = mapper.readTree(ClientRESTs.getSummonerInfoById(ally.summonerId))    //nут иногда ошибка почему-то
            ally.summonerName = jsonNode.get("displayName").textValue()
            ally.accountId = jsonNode.get("accountId").longValue().toString()
        }
        val champions = Cache.getAllChampions()?.champions

        val uiRow = ArrayList<UIRow>()
        for (ally in allies) {
            uiRow.add(UIRow(ally, GetAllChampionsService.getChampionById(ally.championId, champions)))
        }
        for (benchId in benchChampionIds){
            uiRow.add(UIRow(null, GetAllChampionsService.getChampionById(benchId, champions)))
        }
        for (row in uiRow){
            println(row)
        }
        for (i in 0 ..15 - uiRow.size){
            uiRow.add(UIRow(null, null))
        }
        return uiRow
    }

    fun runGame() : String{
        val session = ServerRESTs.mockGame1()
        val mapper = ObjectMapper()
        var jsonNode: JsonNode = mapper.readTree(session)
        jsonNode = jsonNode.get("participants")
        val list = ArrayList<InGameSummonerInfo>()
        val summoners = ArrayList<SummonerInfo>()
        for (i in 0..9) {
            list.add(
                InGameSummonerInfo(
                    jsonNode.get(i).get("teamId").intValue(),
                    jsonNode.get(i).get("championId").intValue(),
                    jsonNode.get(i).get("summonerName").toString()
                )
            )
        }
        val uiRow = ArrayList<UIRow>()
//        for ()
        return "qwe"
    }
}

