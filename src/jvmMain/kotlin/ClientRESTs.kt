import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.http.HttpEntity
import org.apache.http.auth.AuthScope
import org.apache.http.auth.UsernamePasswordCredentials
import org.apache.http.client.CredentialsProvider
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.BasicCredentialsProvider
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.util.EntityUtils

object ClientRESTs {
    val data = GetAuthData.data

    fun getCurrentSummonerInfo() : String {
        return getData("lol-summoner/v1/current-summoner", data["port"], data["token"])
        //{"accountId":200220790,"displayName":"LightWin","internalName":"LightWin","
    }

    fun getCurrentChampion():String{
        return getData("lol-champ-select/v1/grid-champions/53", data["port"], data["token"])
    }

    fun getSummonerInfoById(id:String?) : String{
        return getData("lol-summoner/v1/summoners/$id", data["port"], data["token"])
    }

    fun getSession():String {
        var session = getData("lol-champ-select/v1/session", data["port"], data["token"])
        val mapper = ObjectMapper()
        var jsonNode: JsonNode = mapper.readTree(session)
        while (jsonNode["httpStatus"]!=null && jsonNode["httpStatus"].asText() == "404"){
            Thread.sleep(10_000)
            session = getData("lol-champ-select/v1/session", data["port"], data["token"])
            jsonNode= mapper.readTree(session)
        }
        return session
    }

    fun swapPlayer(id: String){
        post("lol-champ-select/v1/session/trades/$id/request", data["port"], data["token"])
    }

    fun swapBench(key: String){
        post("lol-champ-select/v1/session/bench/swap/$key", data["port"], data["token"])
    }

    fun postData(url:String) {
        post(url, data["port"], data["token"])
    }

    //lol-champ-select/v1/session/trades/{id}
    //lol-champ-select/v1/session/bench/swap/{championId}
    fun post(url:String, port:String?, password: String?) {
        val request = HttpPost("https://127.0.0.1:$port/$url")

        val provider: CredentialsProvider = BasicCredentialsProvider()
        provider.setCredentials(
            AuthScope.ANY,
            UsernamePasswordCredentials("riot", password)
        )

        HttpClientBuilder.create()
            .setDefaultCredentialsProvider(provider)
            .build().use { httpClient ->
                httpClient.execute(request).use { response ->
                    // 401 if wrong user/password
                    println("Для запроса $url получили responseCode ${response.statusLine.statusCode}")
                }
            }
    }

    fun getData(url: String, port: String?, password: String?) : String{
        val request = HttpGet("https://127.0.0.1:$port/$url")

        val provider: CredentialsProvider = BasicCredentialsProvider()
        provider.setCredentials(
            AuthScope.ANY,
            UsernamePasswordCredentials("riot", password)
        )

        HttpClientBuilder.create()
            .setDefaultCredentialsProvider(provider)
            .build().use { httpClient ->
                httpClient.execute(request).use { response ->
                    // 401 if wrong user/password
                    println("Для запроса $url получили statusCode = ${response.statusLine.statusCode}")
                    val entity: HttpEntity = response.entity
                    if (entity != null) {
                        val result: String = EntityUtils.toString(entity)
                        print(result)
                        return result
                    }
                }
            }
        throw RuntimeException("Не удалось получить данные для запроса $url")
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
                "      \"championId\": 523,\n" +
                "      \"isPriority\": false\n" +
                "    },\n" +
                "    {\n" +
                "      \"championId\": 420,\n" +
                "      \"isPriority\": false\n" +
                "    },\n" +
                "    {\n" +
                "      \"championId\": 245,\n" +
                "      \"isPriority\": false\n" +
                "    },\n" +
                "    {\n" +
                "      \"championId\": 11,\n" +
                "      \"isPriority\": false\n" +
                "    },\n" +
                "    {\n" +
                "      \"championId\": 17,\n" +
                "      \"isPriority\": false\n" +
                "    },\n" +
                "    {\n" +
                "      \"championId\": 234,\n" +
                "      \"isPriority\": false\n" +
                "    },\n" +
                "    {\n" +
                "      \"championId\": 117,\n" +
                "      \"isPriority\": false\n" +
                "    },\n" +
                "    {\n" +
                "      \"championId\": 526,\n" +
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
                "    \"multiUserChatId\": \"ab8c6cb6-5cfc-40e7-801a-ef421a2018f3\",\n" +
                "    \"multiUserChatPassword\": \"\"\n" +
                "  },\n" +
                "  \"counter\": 28,\n" +
                "  \"entitledFeatureState\": {\n" +
                "    \"additionalRerolls\": 0,\n" +
                "    \"unlockedSkinIds\": []\n" +
                "  },\n" +
                "  \"gameId\": 440957910,\n" +
                "  \"hasSimultaneousBans\": true,\n" +
                "  \"hasSimultaneousPicks\": true,\n" +
                "  \"isCustomGame\": false,\n" +
                "  \"isSpectating\": false,\n" +
                "  \"localPlayerCellId\": 0,\n" +
                "  \"lockedEventIndex\": -1,\n" +
                "  \"myTeam\": [\n" +
                "    {\n" +
                "      \"assignedPosition\": \"\",\n" +
                "      \"cellId\": 0,\n" +
                "      \"championId\": 136,\n" +
                "      \"championPickIntent\": 0,\n" +
                "      \"entitledFeatureType\": \"NONE\",\n" +
                "      \"nameVisibilityType\": \"VISIBLE\",\n" +
                "      \"obfuscatedPuuid\": \"\",\n" +
                "      \"obfuscatedSummonerId\": 0,\n" +
                "      \"puuid\": \"d9f2d463-a9e6-5044-b3fe-beb59bbc5d7a\",\n" +
                "      \"selectedSkinId\": 136002,\n" +
                "      \"spell1Id\": 32,\n" +
                "      \"spell2Id\": 4,\n" +
                "      \"summonerId\": 318091,\n" +
                "      \"team\": 1,\n" +
                "      \"wardSkinId\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"assignedPosition\": \"\",\n" +
                "      \"cellId\": 1,\n" +
                "      \"championId\": 59,\n" +
                "      \"championPickIntent\": 0,\n" +
                "      \"entitledFeatureType\": \"NONE\",\n" +
                "      \"nameVisibilityType\": \"VISIBLE\",\n" +
                "      \"obfuscatedPuuid\": \"\",\n" +
                "      \"obfuscatedSummonerId\": 0,\n" +
                "      \"puuid\": \"ea317624-c5fd-52ac-a57e-f03b750b331f\",\n" +
                "      \"selectedSkinId\": 59007,\n" +
                "      \"spell1Id\": 32,\n" +
                "      \"spell2Id\": 4,\n" +
                "      \"summonerId\": 21670290,\n" +
                "      \"team\": 1,\n" +
                "      \"wardSkinId\": -1\n" +
                "    },\n" +
                "    {\n" +
                "      \"assignedPosition\": \"\",\n" +
                "      \"cellId\": 2,\n" +
                "      \"championId\": 101,\n" +
                "      \"championPickIntent\": 0,\n" +
                "      \"entitledFeatureType\": \"NONE\",\n" +
                "      \"nameVisibilityType\": \"VISIBLE\",\n" +
                "      \"obfuscatedPuuid\": \"\",\n" +
                "      \"obfuscatedSummonerId\": 0,\n" +
                "      \"puuid\": \"435edd61-f464-5bfe-9319-871974649cca\",\n" +
                "      \"selectedSkinId\": 101012,\n" +
                "      \"spell1Id\": 7,\n" +
                "      \"spell2Id\": 4,\n" +
                "      \"summonerId\": 4051076,\n" +
                "      \"team\": 1,\n" +
                "      \"wardSkinId\": -1\n" +
                "    },\n" +
                "    {\n" +
                "      \"assignedPosition\": \"\",\n" +
                "      \"cellId\": 3,\n" +
                "      \"championId\": 122,\n" +
                "      \"championPickIntent\": 0,\n" +
                "      \"entitledFeatureType\": \"NONE\",\n" +
                "      \"nameVisibilityType\": \"VISIBLE\",\n" +
                "      \"obfuscatedPuuid\": \"\",\n" +
                "      \"obfuscatedSummonerId\": 0,\n" +
                "      \"puuid\": \"7b0fe660-5add-5920-9c25-fc77ce6859ab\",\n" +
                "      \"selectedSkinId\": 122015,\n" +
                "      \"spell1Id\": 6,\n" +
                "      \"spell2Id\": 4,\n" +
                "      \"summonerId\": 16541909,\n" +
                "      \"team\": 1,\n" +
                "      \"wardSkinId\": -1\n" +
                "    },\n" +
                "    {\n" +
                "      \"assignedPosition\": \"\",\n" +
                "      \"cellId\": 4,\n" +
                "      \"championId\": 223,\n" +
                "      \"championPickIntent\": 0,\n" +
                "      \"entitledFeatureType\": \"NONE\",\n" +
                "      \"nameVisibilityType\": \"VISIBLE\",\n" +
                "      \"obfuscatedPuuid\": \"\",\n" +
                "      \"obfuscatedSummonerId\": 0,\n" +
                "      \"puuid\": \"eefbf0da-d016-5169-b8b2-f6c410b8cc46\",\n" +
                "      \"selectedSkinId\": 223000,\n" +
                "      \"spell1Id\": 32,\n" +
                "      \"spell2Id\": 4,\n" +
                "      \"summonerId\": 4051008,\n" +
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
                "    \"adjustedTimeLeftInPhase\": 7572,\n" +
                "    \"internalNowInEpochMs\": 1683478951407,\n" +
                "    \"isInfinite\": false,\n" +
                "    \"phase\": \"FINALIZATION\",\n" +
                "    \"totalTimeInPhase\": 70000\n" +
                "  },\n" +
                "  \"trades\": [\n" +
                "    {\n" +
                "      \"cellId\": 4,\n" +
                "      \"id\": 101,\n" +
                "      \"state\": \"INVALID\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"cellId\": 3,\n" +
                "      \"id\": 103,\n" +
                "      \"state\": \"INVALID\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"cellId\": 1,\n" +
                "      \"id\": 113,\n" +
                "      \"state\": \"INVALID\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"cellId\": 2,\n" +
                "      \"id\": 121,\n" +
                "      \"state\": \"SENT\"\n" +
                "    }\n" +
                "  ]\n" +
                "}\n"
    }

    fun mockSession1() :String {
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
                "      \"championId\": 201,\n" +
                "      \"isPriority\": false\n" +
                "    },\n" +
                "    {\n" +
                "      \"championId\": 518,\n" +
                "      \"isPriority\": false\n" +
                "    },\n" +
                "    {\n" +
                "      \"championId\": 102,\n" +
                "      \"isPriority\": false\n" +
                "    },\n" +
                "    {\n" +
                "      \"championId\": 427,\n" +
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
                "    \"multiUserChatId\": \"fb1bb30a-4098-4c70-90db-ce03f8ec2ed6\",\n" +
                "    \"multiUserChatPassword\": \"\"\n" +
                "  },\n" +
                "  \"counter\": 14,\n" +
                "  \"entitledFeatureState\": {\n" +
                "    \"additionalRerolls\": 0,\n" +
                "    \"unlockedSkinIds\": []\n" +
                "  },\n" +
                "  \"gameId\": 440956198,\n" +
                "  \"hasSimultaneousBans\": true,\n" +
                "  \"hasSimultaneousPicks\": true,\n" +
                "  \"isCustomGame\": false,\n" +
                "  \"isSpectating\": false,\n" +
                "  \"localPlayerCellId\": 0,\n" +
                "  \"lockedEventIndex\": -1,\n" +
                "  \"myTeam\": [\n" +
                "    {\n" +
                "      \"assignedPosition\": \"\",\n" +
                "      \"cellId\": 0,\n" +
                "      \"championId\": 30,\n" +
                "      \"championPickIntent\": 0,\n" +
                "      \"entitledFeatureType\": \"NONE\",\n" +
                "      \"nameVisibilityType\": \"VISIBLE\",\n" +
                "      \"obfuscatedPuuid\": \"\",\n" +
                "      \"obfuscatedSummonerId\": 0,\n" +
                "      \"puuid\": \"d9f2d463-a9e6-5044-b3fe-beb59bbc5d7a\",\n" +
                "      \"selectedSkinId\": 99042,\n" +
                "      \"spell1Id\": 32,\n" +
                "      \"spell2Id\": 4,\n" +
                "      \"summonerId\": 318091,\n" +
                "      \"team\": 1,\n" +
                "      \"wardSkinId\": 1\n" +
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
                "      \"puuid\": \"435edd61-f464-5bfe-9319-871974649cca\",\n" +
                "      \"selectedSkinId\": 15008,\n" +
                "      \"spell1Id\": 7,\n" +
                "      \"spell2Id\": 4,\n" +
                "      \"summonerId\": 4051076,\n" +
                "      \"team\": 1,\n" +
                "      \"wardSkinId\": -1\n" +
                "    },\n" +
                "    {\n" +
                "      \"assignedPosition\": \"\",\n" +
                "      \"cellId\": 2,\n" +
                "      \"championId\": 22,\n" +
                "      \"championPickIntent\": 0,\n" +
                "      \"entitledFeatureType\": \"NONE\",\n" +
                "      \"nameVisibilityType\": \"VISIBLE\",\n" +
                "      \"obfuscatedPuuid\": \"\",\n" +
                "      \"obfuscatedSummonerId\": 0,\n" +
                "      \"puuid\": \"0cfd8337-6e8d-5066-948d-dbff7deabc92\",\n" +
                "      \"selectedSkinId\": 267000,\n" +
                "      \"spell1Id\": 4,\n" +
                "      \"spell2Id\": 32,\n" +
                "      \"summonerId\": 23973621,\n" +
                "      \"team\": 1,\n" +
                "      \"wardSkinId\": -1\n" +
                "    },\n" +
                "    {\n" +
                "      \"assignedPosition\": \"\",\n" +
                "      \"cellId\": 3,\n" +
                "      \"championId\": 69,\n" +
                "      \"championPickIntent\": 0,\n" +
                "      \"entitledFeatureType\": \"NONE\",\n" +
                "      \"nameVisibilityType\": \"VISIBLE\",\n" +
                "      \"obfuscatedPuuid\": \"\",\n" +
                "      \"obfuscatedSummonerId\": 0,\n" +
                "      \"puuid\": \"077e3d86-aa8b-57fd-9d5f-0cdc69d118ae\",\n" +
                "      \"selectedSkinId\": 69014,\n" +
                "      \"spell1Id\": 32,\n" +
                "      \"spell2Id\": 4,\n" +
                "      \"summonerId\": 69669,\n" +
                "      \"team\": 1,\n" +
                "      \"wardSkinId\": -1\n" +
                "    },\n" +
                "    {\n" +
                "      \"assignedPosition\": \"\",\n" +
                "      \"cellId\": 4,\n" +
                "      \"championId\": 7,\n" +
                "      \"championPickIntent\": 0,\n" +
                "      \"entitledFeatureType\": \"NONE\",\n" +
                "      \"nameVisibilityType\": \"VISIBLE\",\n" +
                "      \"obfuscatedPuuid\": \"\",\n" +
                "      \"obfuscatedSummonerId\": 0,\n" +
                "      \"puuid\": \"6422a073-1455-5ab5-86d9-8780b98367ae\",\n" +
                "      \"selectedSkinId\": 7020,\n" +
                "      \"spell1Id\": 4,\n" +
                "      \"spell2Id\": 6,\n" +
                "      \"summonerId\": 4352600,\n" +
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
                "    \"adjustedTimeLeftInPhase\": 48043,\n" +
                "    \"internalNowInEpochMs\": 1683477324991,\n" +
                "    \"isInfinite\": false,\n" +
                "    \"phase\": \"FINALIZATION\",\n" +
                "    \"totalTimeInPhase\": 70000\n" +
                "  },\n" +
                "  \"trades\": [\n" +
                "    {\n" +
                "      \"cellId\": 2,\n" +
                "      \"id\": 96,\n" +
                "      \"state\": \"AVAILABLE\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"cellId\": 3,\n" +
                "      \"id\": 33,\n" +
                "      \"state\": \"AVAILABLE\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"cellId\": 4,\n" +
                "      \"id\": 73,\n" +
                "      \"state\": \"AVAILABLE\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"cellId\": 1,\n" +
                "      \"id\": 85,\n" +
                "      \"state\": \"AVAILABLE\"\n" +
                "    }\n" +
                "  ]\n" +
                "}"
    }


}