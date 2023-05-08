import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import ddragon.Champion
import pastebin.GetAllChampionsService
import java.util.stream.Collectors

object Example {
    @JvmStatic
    fun main(args: Array<String>) {
        val session = Data().getSession()
        print(session)
        val mapper = ObjectMapper()
        var jsonNode: JsonNode = mapper.readTree(session)
        val benchChampionIds = BenchChampions().getBenchChampionIds(jsonNode)
        val allies = AlliesData().getAllies(jsonNode)
        for (ally in allies) {
            jsonNode = mapper.readTree(Data().getSummonerInfoById(ally.summonerId))
            ally.summonerName = jsonNode.get("displayName").textValue()
        }
        val champions = GetAllChampionsService.allChampions.champions
        for (champion in champions) {
            if (champion.key in benchChampionIds
                || champion.key in allies.stream()
                    .map{ it.championId }
                    .collect(Collectors.toList())){
                println("qwe")
                println(champion)
            }
        }
        val uiRow = ArrayList<UIRow>()
        for (ally in allies) {
            uiRow.add(UIRow(ally, getChampionById(ally.championId, champions)))
        }
        for (benchId in benchChampionIds){
            uiRow.add(UIRow(null, getChampionById(benchId, champions)))
        }
        for (row in uiRow){
            println(row)
        }
//        println(allies)
//        print(benchChampionIds)
//        print(session)
//        auth.getData("lol-summoner/v1/current-summoner", "57587", "qYeyPe39_Jz-3UWk_cjDew")
    }

    fun getChampionById(id :String, champions : ArrayList<Champion>) : Champion{
        return champions.stream()
            .filter { it.key == id }
            .findAny()
            .orElseThrow { RuntimeException("Не удалось найти чемпиона с key = $id") }
    }

}

fun mockSession() : String{
    return "{\n" +
            "  \"actions\": [],\n" +
            "  \"allowBattleBoost\": true,\n" +
            "  \"allowDuplicatePicks\": false,\n" +
            "  \"allowLockedEvents\": false,\n" +
            "  \"allowRerolling\": true,\n" +
            "  \"allowSkinSelection\": true,\n" +
            "  \"bans\": {\n" +
            "    \"myTeamBans\": [],\n" +
            "    \"numBans\": 0,\n" +
            "    \"theirTeamBans\": []\n" +
            "  },\n" +
            "  \"benchChampions\": [\n" +
            "    {\n" +
            "      \"championId\": 498,\n" +
            "      \"isPriority\": false\n" +
            "    },\n" +
            "    {\n" +
            "      \"championId\": 17,\n" +
            "      \"isPriority\": false\n" +
            "    },\n" +
            "    {\n" +
            "      \"championId\": 122,\n" +
            "      \"isPriority\": false\n" +
            "    },\n" +
            "    {\n" +
            "      \"championId\": 31,\n" +
            "      \"isPriority\": false\n" +
            "    }\n" +
            "  ],\n" +
            "  \"benchEnabled\": true,\n" +
            "  \"boostableSkinCount\": 1,\n" +
            "  \"chatDetails\": {\n" +
            "    \"mucJwtDto\": {\n" +
            "      \"channelClaim\": \"\",\n" +
            "      \"domain\": \"\",\n" +
            "      \"jwt\": \"\",\n" +
            "      \"targetRegion\": \"\"\n" +
            "    },\n" +
            "    \"multiUserChatId\": \"389e438c-9e74-41c6-8f7a-ee82103de24f\",\n" +
            "    \"multiUserChatPassword\": \"\"\n" +
            "  },\n" +
            "  \"counter\": 10,\n" +
            "  \"entitledFeatureState\": {\n" +
            "    \"additionalRerolls\": 0,\n" +
            "    \"unlockedSkinIds\": []\n" +
            "  },\n" +
            "  \"gameId\": 6384155728,\n" +
            "  \"hasSimultaneousBans\": true,\n" +
            "  \"hasSimultaneousPicks\": true,\n" +
            "  \"isCustomGame\": false,\n" +
            "  \"isSpectating\": false,\n" +
            "  \"localPlayerCellId\": 2,\n" +
            "  \"lockedEventIndex\": -1,\n" +
            "  \"myTeam\": [\n" +
            "    {\n" +
            "      \"assignedPosition\": \"\",\n" +
            "      \"cellId\": 0,\n" +
            "      \"championId\": 15,\n" +
            "      \"championPickIntent\": 0,\n" +
            "      \"entitledFeatureType\": \"NONE\",\n" +
            "      \"nameVisibilityType\": \"VISIBLE\",\n" +
            "      \"obfuscatedPuuid\": \"\",\n" +
            "      \"obfuscatedSummonerId\": 0,\n" +
            "      \"puuid\": \"8d4bfec2-1619-5a45-ba0d-261ca00c3a3b\",\n" +
            "      \"selectedSkinId\": 15043,\n" +
            "      \"spell1Id\": 4,\n" +
            "      \"spell2Id\": 7,\n" +
            "      \"summonerId\": 2722502438086304,\n" +
            "      \"team\": 1,\n" +
            "      \"wardSkinId\": -1\n" +
            "    },\n" +
            "    {\n" +
            "      \"assignedPosition\": \"\",\n" +
            "      \"cellId\": 1,\n" +
            "      \"championId\": 902,\n" +
            "      \"championPickIntent\": 0,\n" +
            "      \"entitledFeatureType\": \"NONE\",\n" +
            "      \"nameVisibilityType\": \"VISIBLE\",\n" +
            "      \"obfuscatedPuuid\": \"\",\n" +
            "      \"obfuscatedSummonerId\": 0,\n" +
            "      \"puuid\": \"3861a657-0694-5b8d-9e16-cf0bb44c63ab\",\n" +
            "      \"selectedSkinId\": 902001,\n" +
            "      \"spell1Id\": 4,\n" +
            "      \"spell2Id\": 32,\n" +
            "      \"summonerId\": 3222407340689280,\n" +
            "      \"team\": 1,\n" +
            "      \"wardSkinId\": -1\n" +
            "    },\n" +
            "    {\n" +
            "      \"assignedPosition\": \"\",\n" +
            "      \"cellId\": 2,\n" +
            "      \"championId\": 55,\n" +
            "      \"championPickIntent\": 0,\n" +
            "      \"entitledFeatureType\": \"NONE\",\n" +
            "      \"nameVisibilityType\": \"VISIBLE\",\n" +
            "      \"obfuscatedPuuid\": \"\",\n" +
            "      \"obfuscatedSummonerId\": 0,\n" +
            "      \"puuid\": \"3b8ab782-e3cd-595e-ba85-3c59a74e7533\",\n" +
            "      \"selectedSkinId\": 55021,\n" +
            "      \"spell1Id\": 32,\n" +
            "      \"spell2Id\": 4,\n" +
            "      \"summonerId\": 3147324952798720,\n" +
            "      \"team\": 1,\n" +
            "      \"wardSkinId\": 1\n" +
            "    },\n" +
            "    {\n" +
            "      \"assignedPosition\": \"\",\n" +
            "      \"cellId\": 3,\n" +
            "      \"championId\": 24,\n" +
            "      \"championPickIntent\": 0,\n" +
            "      \"entitledFeatureType\": \"NONE\",\n" +
            "      \"nameVisibilityType\": \"VISIBLE\",\n" +
            "      \"obfuscatedPuuid\": \"\",\n" +
            "      \"obfuscatedSummonerId\": 0,\n" +
            "      \"puuid\": \"2822750b-c578-5f18-91d5-66fe18b7db36\",\n" +
            "      \"selectedSkinId\": 24005,\n" +
            "      \"spell1Id\": 6,\n" +
            "      \"spell2Id\": 7,\n" +
            "      \"summonerId\": 3228541901998016,\n" +
            "      \"team\": 1,\n" +
            "      \"wardSkinId\": -1\n" +
            "    },\n" +
            "    {\n" +
            "      \"assignedPosition\": \"\",\n" +
            "      \"cellId\": 4,\n" +
            "      \"championId\": 40,\n" +
            "      \"championPickIntent\": 0,\n" +
            "      \"entitledFeatureType\": \"NONE\",\n" +
            "      \"nameVisibilityType\": \"VISIBLE\",\n" +
            "      \"obfuscatedPuuid\": \"\",\n" +
            "      \"obfuscatedSummonerId\": 0,\n" +
            "      \"puuid\": \"b55a4972-82e4-542d-98d9-ad373c95de71\",\n" +
            "      \"selectedSkinId\": 40027,\n" +
            "      \"spell1Id\": 7,\n" +
            "      \"spell2Id\": 21,\n" +
            "      \"summonerId\": 2973770311173440,\n" +
            "      \"team\": 1,\n" +
            "      \"wardSkinId\": -1\n" +
            "    }\n" +
            "  ],\n" +
            "  \"pickOrderSwaps\": [],\n" +
            "  \"recoveryCounter\": 0,\n" +
            "  \"rerollsRemaining\": 1,\n" +
            "  \"skipChampionSelect\": false,\n" +
            "  \"theirTeam\": [\n" +
            "    {\n" +
            "      \"assignedPosition\": \"\",\n" +
            "      \"cellId\": 5,\n" +
            "      \"championId\": 0,\n" +
            "      \"championPickIntent\": 0,\n" +
            "      \"entitledFeatureType\": \"\",\n" +
            "      \"nameVisibilityType\": \"HIDDEN\",\n" +
            "      \"obfuscatedPuuid\": \"\",\n" +
            "      \"obfuscatedSummonerId\": 0,\n" +
            "      \"puuid\": \"\",\n" +
            "      \"selectedSkinId\": 0,\n" +
            "      \"spell1Id\": 0,\n" +
            "      \"spell2Id\": 0,\n" +
            "      \"summonerId\": 0,\n" +
            "      \"team\": 2,\n" +
            "      \"wardSkinId\": -1\n" +
            "    },\n" +
            "    {\n" +
            "      \"assignedPosition\": \"\",\n" +
            "      \"cellId\": 6,\n" +
            "      \"championId\": 0,\n" +
            "      \"championPickIntent\": 0,\n" +
            "      \"entitledFeatureType\": \"\",\n" +
            "      \"nameVisibilityType\": \"HIDDEN\",\n" +
            "      \"obfuscatedPuuid\": \"\",\n" +
            "      \"obfuscatedSummonerId\": 0,\n" +
            "      \"puuid\": \"\",\n" +
            "      \"selectedSkinId\": 0,\n" +
            "      \"spell1Id\": 0,\n" +
            "      \"spell2Id\": 0,\n" +
            "      \"summonerId\": 0,\n" +
            "      \"team\": 2,\n" +
            "      \"wardSkinId\": -1\n" +
            "    },\n" +
            "    {\n" +
            "      \"assignedPosition\": \"\",\n" +
            "      \"cellId\": 7,\n" +
            "      \"championId\": 0,\n" +
            "      \"championPickIntent\": 0,\n" +
            "      \"entitledFeatureType\": \"\",\n" +
            "      \"nameVisibilityType\": \"HIDDEN\",\n" +
            "      \"obfuscatedPuuid\": \"\",\n" +
            "      \"obfuscatedSummonerId\": 0,\n" +
            "      \"puuid\": \"\",\n" +
            "      \"selectedSkinId\": 0,\n" +
            "      \"spell1Id\": 0,\n" +
            "      \"spell2Id\": 0,\n" +
            "      \"summonerId\": 0,\n" +
            "      \"team\": 2,\n" +
            "      \"wardSkinId\": -1\n" +
            "    },\n" +
            "    {\n" +
            "      \"assignedPosition\": \"\",\n" +
            "      \"cellId\": 8,\n" +
            "      \"championId\": 0,\n" +
            "      \"championPickIntent\": 0,\n" +
            "      \"entitledFeatureType\": \"\",\n" +
            "      \"nameVisibilityType\": \"HIDDEN\",\n" +
            "      \"obfuscatedPuuid\": \"\",\n" +
            "      \"obfuscatedSummonerId\": 0,\n" +
            "      \"puuid\": \"\",\n" +
            "      \"selectedSkinId\": 0,\n" +
            "      \"spell1Id\": 0,\n" +
            "      \"spell2Id\": 0,\n" +
            "      \"summonerId\": 0,\n" +
            "      \"team\": 2,\n" +
            "      \"wardSkinId\": -1\n" +
            "    },\n" +
            "    {\n" +
            "      \"assignedPosition\": \"\",\n" +
            "      \"cellId\": 9,\n" +
            "      \"championId\": 0,\n" +
            "      \"championPickIntent\": 0,\n" +
            "      \"entitledFeatureType\": \"\",\n" +
            "      \"nameVisibilityType\": \"HIDDEN\",\n" +
            "      \"obfuscatedPuuid\": \"\",\n" +
            "      \"obfuscatedSummonerId\": 0,\n" +
            "      \"puuid\": \"\",\n" +
            "      \"selectedSkinId\": 0,\n" +
            "      \"spell1Id\": 0,\n" +
            "      \"spell2Id\": 0,\n" +
            "      \"summonerId\": 0,\n" +
            "      \"team\": 2,\n" +
            "      \"wardSkinId\": -1\n" +
            "    }\n" +
            "  ],\n" +
            "  \"timer\": {\n" +
            "    \"adjustedTimeLeftInPhase\": 25019,\n" +
            "    \"internalNowInEpochMs\": 1682950553322,\n" +
            "    \"isInfinite\": false,\n" +
            "    \"phase\": \"FINALIZATION\",\n" +
            "    \"totalTimeInPhase\": 70000\n" +
            "  },\n" +
            "  \"trades\": [\n" +
            "    {\n" +
            "      \"cellId\": 1,\n" +
            "      \"id\": 1,\n" +
            "      \"state\": \"INVALID\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"cellId\": 4,\n" +
            "      \"id\": 45,\n" +
            "      \"state\": \"AVAILABLE\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"cellId\": 0,\n" +
            "      \"id\": 52,\n" +
            "      \"state\": \"AVAILABLE\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"cellId\": 3,\n" +
            "      \"id\": 31,\n" +
            "      \"state\": \"AVAILABLE\"\n" +
            "    }\n" +
            "  ]\n" +
            "}"
}