import org.apache.http.HttpEntity
import org.apache.http.auth.AuthScope
import org.apache.http.auth.UsernamePasswordCredentials
import org.apache.http.client.CredentialsProvider
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.BasicCredentialsProvider
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.util.EntityUtils

object ClientRESTs {
    private val data = GetAuthData.data


    fun getSummonerInfoById(id:String) : String{
        return getData("lol-summoner/v1/summoners/$id", data["port"], data["token"])
    }

    fun getSession():String {
        return getData("lol-champ-select/v1/session", data["port"], data["token"])
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
                    println(response.statusLine.statusCode)
                    val entity: HttpEntity = response.entity
                    if (entity != null) {
                        val result: String = EntityUtils.toString(entity)
                        println(result)
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
}