package pastebin

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import ddragon.*

object GetAllChampionsService {

    fun getChampionById(id :String?, champions : ArrayList<Champion>?) : Champion{
        if (champions == null) {
            throw RuntimeException("champions = null")
        }
        return champions.stream()
            .filter { it.key == id }
            .findAny()
            .orElseThrow { RuntimeException("Не удалось найти чемпиона с key = $id") }
    }

    val allChampions: AllChampions
        get() {
//            val version : String? =
//                ExternalRESTs.getCloseableHttpResponse("https://pastebin.com/raw/LmrmUjxD")
            val version : String? =
                ExternalRESTs.getCloseableHttpResponse("https://ddragon.leagueoflegends.com/api/versions.json")
            val versionJson = ObjectMapper().readValue(
                version,
                JsonNode::class.java
            )
            val allChampionsStringData: String? =
                ExternalRESTs.getCloseableHttpResponse("http://ddragon.leagueoflegends.com/cdn/${versionJson.get(0).textValue()}/data/en_US/champion.json")
            var readValue = ObjectMapper().readValue(
                allChampionsStringData,
                JsonNode::class.java
            )
            val champions = ArrayList<Champion>()
            for (jsonNode in readValue["data"]) {
                champions.add(getChampion(jsonNode))
            }
            val allChampionsBalanceData: String? =
                ExternalRESTs.getCloseableHttpResponse("https://pastebin.com/raw/Z6SWQNA9")
            val pastebin = ObjectMapper().readValue(
                allChampionsBalanceData,
                JsonNode::class.java
            )
            for (champion in champions){
                champion.balance = getBalance(pastebin, champion.name)
            }

            return AllChampions(
                readValue["type"].textValue(),
                readValue["format"].textValue(),
                readValue["version"].textValue(),
                champions
            )
        }

    private fun getBalance(nodes: JsonNode?, championName: String?) : Balance{
        if (nodes != null) {
            for (node in nodes){
                if (championName == node["championNameType"].textValue()){
                    val damageDealt = if ( node["damageDealtType"]?.doubleValue() == null) "" else node["damageDealtType"]?.doubleValue().toString()
                    val damageReceivedType = if ( node["damageReceivedType"]?.doubleValue() == null) "" else node["damageReceivedType"]?.doubleValue().toString()
                    val abilityHasteType = if ( node["abilityHasteType"]?.doubleValue() == null) "" else node["abilityHasteType"]?.doubleValue().toString()
                    val attackSpeedType = if ( node["attackSpeedType"]?.doubleValue() == null) "" else node["attackSpeedType"]?.doubleValue().toString()
                    val sheldType = if ( node["shieldType"]?.doubleValue() == null) "" else node["shieldType"]?.doubleValue().toString()
                    val healType = if ( node["healType"]?.doubleValue() == null) "" else node["healType"]?.doubleValue().toString()
                    val tenacityType = if ( node["tenacityType"]?.doubleValue() == null) "" else node["tenacityType"]?.doubleValue().toString()
                    val energyType = if ( node["energyType"]?.doubleValue() == null) "" else node["energyType"]?.doubleValue().toString()
                    val ddragonChampionName = if ( node["ddragonChampionName"]?.textValue() == null) "" else node["ddragonChampionName"]?.textValue().toString()
                    ExternalRESTs.savePng("$ddragonChampionName")
                    return Balance(
                        damageDealt,
                        damageReceivedType,
                        abilityHasteType,
                        attackSpeedType,
                        sheldType,
                        healType,
                        tenacityType,
                        energyType,
                        ddragonChampionName
                    )
                }

            }
        }
        println("Не удалось получить баланс арама для $championName")
        return Balance(
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null
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
            getStats(jsonNode["stats"]),
            null
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