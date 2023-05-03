import com.fasterxml.jackson.databind.JsonNode

class BenchChampions {
    fun getBenchChampionIds(jsonNode: JsonNode) : List<String> {
        val ret = ArrayList<String>()
        val benchChampions = jsonNode.get("benchChampions")
        for (i in benchChampions){
            ret.add(i.get("championId").asInt().toString())
        }
        return ret
    }
}