import com.fasterxml.jackson.databind.JsonNode
import java.util.*
import kotlin.collections.ArrayList

object SessionUtils {
    fun getBenchChampionIds(jsonNode: JsonNode) : List<String> {
        val ret = ArrayList<String>()
        val benchChampions = jsonNode.get("benchChampions")
        print("benchChampions:${jsonNode}}")
        for (i in benchChampions){  //todo: выбило когла из лобби в начало игры
            //benchChampions:{"errorCode":"RPC_ERROR","httpStatus":404,"implementationDetails":{},"message":"No active delegate"}}
            ret.add(i.get("championId").asInt().toString())
        }
        return ret
    }

    fun getAllies(jsonNode: JsonNode) : List<SummonerInfo> {
        val ret = ArrayList<SummonerInfo>()
        val myTeam = jsonNode.get("myTeam")
        for (i in myTeam){
            ret.add(
                SummonerInfo(
                    i.get("summonerId").longValue().toString(),
                    i.get("championId").intValue().toString(),
                    i.get("cellId").intValue().toString()
                )
            )
        }
        return ret
    }
}