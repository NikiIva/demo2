import com.fasterxml.jackson.databind.JsonNode

class AlliesData {
    fun getAllies(jsonNode: JsonNode) : HashMap<String, String> {
        val ret = HashMap<String, String>()
        val myTeam = jsonNode.get("myTeam")
        for (i in myTeam){
            ret[i.get("summonerId").longValue().toString()] = i.get("championId").intValue().toString()
//            ret[""]
//            ret.add(i.get("championId").asInt().toString())
        }
        return ret
    }
}