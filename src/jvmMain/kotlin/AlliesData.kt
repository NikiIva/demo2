import com.fasterxml.jackson.databind.JsonNode

class AlliesData {
    fun getAllies(jsonNode: JsonNode) : List<SummonerInfo> {
        val ret = ArrayList<SummonerInfo>()
        val myTeam = jsonNode.get("myTeam")
        for (i in myTeam){
            ret.add(
                SummonerInfo(
                    i.get("summonerId").longValue().toString(),
                    i.get("championId").intValue().toString(),
                    i.get("cellId").intValue().toString())
            )
        }
        return ret
    }
}