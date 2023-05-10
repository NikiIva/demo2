package pastebin

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import ddragon.*

object GetAllChampionsService {

    fun getChampionById(id :String, champions : ArrayList<Champion>) : Champion{
        return champions.stream()
            .filter { it.key == id }
            .findAny()
            .orElseThrow { RuntimeException("Не удалось найти чемпиона с key = $id") }
    }

    val allChampions: AllChampions
        get() {
            val allChampionsStringData: String? =
                ExternalRESTs.getCloseableHttpResponse("http://ddragon.leagueoflegends.com/cdn/13.4.1/data/en_US/champion.json") //todo 13.4.1 научиться менять
            val readValue = ObjectMapper().readValue(
                allChampionsStringData,
                JsonNode::class.java
            )
            val champions = ArrayList<Champion>()
            for (jsonNode in readValue["data"]) {
                champions.add(getChampion(jsonNode))
            }
            return AllChampions(
                readValue["type"].textValue(),
                readValue["format"].textValue(),
                readValue["version"].textValue(),
                champions
            )
        }

    private fun getChampion(jsonNode: JsonNode): Champion {
        return Champion(
            jsonNode["version"].textValue(),
            jsonNode["id"].textValue(),
            jsonNode["key"].textValue(),
            jsonNode["name"].textValue(),
            jsonNode["title"].textValue(),
            jsonNode["blurb"].textValue(),
            getInfo(jsonNode["info"]),
            getImage(jsonNode["image"]),
            getStrings(jsonNode["tags"]),
            jsonNode["partype"].textValue(),
            getStats(jsonNode["stats"])
        )
    }

    fun getStats(node: JsonNode): ChampionStats? {
        return ChampionStats(
            getDoubleFromNode(node, "hp"),
            getDoubleFromNode(node, "hpperlevel"),
            getDoubleFromNode(node, "mp"),
            getDoubleFromNode(node, "mpperlevel"),
            getDoubleFromNode(node, "movespeed"),
            getDoubleFromNode(node, "armor"),
            getDoubleFromNode(node, "armorperlevel"),
            getDoubleFromNode(node, "spellblock"),
            getDoubleFromNode(node, "spellblockperlevel"),
            getDoubleFromNode(node, "attackrange"),
            getDoubleFromNode(node, "hpregen"),
            getDoubleFromNode(node, "hpregenperlevel"),
            getDoubleFromNode(node, "mpregen"),
            getDoubleFromNode(node, "mpregenperlevel"),
            getDoubleFromNode(node, "crit"),
            getDoubleFromNode(node, "critperlevel"),
            getDoubleFromNode(node, "attackdamage"),
            getDoubleFromNode(node, "attackdamageperlevel"),
            getDoubleFromNode(node, "attackspeed"),
            getDoubleFromNode(node, "attackspeedperlevel")
        )
    }

    private fun getDoubleFromNode(node: JsonNode, field: String): Double? {
        return node[field].doubleValue()
    }

    fun getInfo(node: JsonNode): ChampionInfo? {
        return ChampionInfo(
            node["attack"].intValue(),
            node["defense"].intValue(),
            node["magic"].intValue(),
            node["difficulty"].intValue()
        )
    }

    fun getImage(node: JsonNode): ChampionImage? {
        return ChampionImage(
            node["full"].textValue(),
            node["sprite"].textValue(),
            node["group"].textValue(),
            node["x"].intValue(),
            node["y"].intValue(),
            node["w"].intValue(),
            node["h"].intValue()
        )
    }

    fun getStrings(node: JsonNode?): List<String>? {
        val ret: MutableList<String> = ArrayList()
        if (node == null) {
            return emptyList()
        }
        for (jsonNode in node) {
            ret.add(jsonNode.textValue())
        }
        return ret
    }

    fun getIntegers(jsonNode: JsonNode): List<Int>? {
        val ret: List<Int> = ArrayList()
        for (node in jsonNode) {
            node.intValue()
        }
        return ret
    }
}