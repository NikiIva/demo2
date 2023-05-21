import cache.Cache
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import pastebin.GetAllChampionsService

object Start {
    @JvmStatic
    fun main(args: Array<String>) {
        val session = ClientRESTs.mockSession()//
        print(session)
        val mapper = ObjectMapper()
        var jsonNode: JsonNode = mapper.readTree(session)
        val benchChampionIds = SessionUtils.getBenchChampionIds(jsonNode)
        val allies = SessionUtils.getAllies(jsonNode)
        for (ally in allies) {
            jsonNode = mapper.readTree(ClientRESTs.getSummonerInfoById(ally.summonerId))
            ally.summonerName = jsonNode.get("displayName").textValue()
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
    }


    fun run(session : String): ArrayList<UIRow> {
        val mapper = ObjectMapper()
        var jsonNode: JsonNode = mapper.readTree(session)
        val benchChampionIds = SessionUtils.getBenchChampionIds(jsonNode)
        val allies = SessionUtils.getAllies(jsonNode)
        for (ally in allies) {
            jsonNode = mapper.readTree(ClientRESTs.getSummonerInfoById(ally.summonerId))
            ally.summonerName = jsonNode.get("displayName").textValue()
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
}

