import DTO.ServerSummonerInfo
import cache.Cache
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.http.HttpEntity
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.util.EntityUtils
import java.lang.Exception

object ServerRESTs {

    /**
     * accountId: Encrypted account ID. Max length 56 characters.
     * profileIcon: ID of the summoner icon associated with the summoner.
     * revisionDate: Date summoner was last modified specified as epoch milliseconds. The following events will update this timestamp: summoner name change, summoner level change, or profile icon change.
     * name: Summoner name.
     * id: Encrypted summoner ID. Max length 63 characters.
     * puuid: Encrypted PUUID. Exact length of 78 characters.
     * summonerLevel: Summoner level associated with the summoner.
     */
    fun getEncryptedSummonerInfo(summonerName: String) : ServerSummonerInfo {
        val url = "https://ru.api.riotgames.com/lol/summoner/v4/summoners/by-name/${summonerName}?api_key=${Cache.getAramApiKey()}"
        val request = HttpGet(url)

        HttpClientBuilder.create()
            .build().use { httpClient ->
                httpClient.execute(request).use { response ->
                    println("Для запроса $url получили statusCode = ${response.statusLine.statusCode}")
                    val entity: HttpEntity = response.entity
                    if (entity != null) {
                        val result: String = EntityUtils.toString(entity)
                        print(result)
                        val versionJson = ObjectMapper().readValue(
                            result,
                            JsonNode::class.java
                        )
                        try {
                            return ServerSummonerInfo(
                                versionJson.get("id").textValue(),
                                versionJson.get("accountId").textValue(),
                                versionJson.get("puuid").textValue(),
                                versionJson.get("name").textValue(),
                                versionJson.get("profileIconId").intValue().toString(),
                                versionJson.get("revisionDate").longValue(),
                                versionJson.get("summonerLevel").intValue().toString()
                            )
                        }
                        catch (exception : Exception) {
                            throw java.lang.RuntimeException("Не удалось получить информацию по аккаунту", exception)
                        }

                    }
                }
            }
        throw RuntimeException("Не удалось получить данные для запроса $url")
    }

    fun getSession(summonerName:String) : String {
        val url = "https://ru.api.riotgames.com/lol/spectator/v4/active-games/by-summoner/${getEncryptedSummonerInfo(summonerName).id}?api_key=${Cache.getAramApiKey()}"
        val request = HttpGet(url)

        HttpClientBuilder.create()
            .build().use { httpClient ->
                httpClient.execute(request).use { response ->
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



    fun mockGame() : String {
        return "{\n" +
                "    \"gameId\": 448219968,\n" +
                "    \"mapId\": 12,\n" +
                "    \"gameMode\": \"ARAM\",\n" +
                "    \"gameType\": \"MATCHED_GAME\",\n" +
                "    \"gameQueueConfigId\": 450,\n" +
                "    \"participants\": [\n" +
                "        {\n" +
                "            \"teamId\": 100,\n" +
                "            \"spell1Id\": 4,\n" +
                "            \"spell2Id\": 32,\n" +
                "            \"championId\": 777,\n" +
                "            \"profileIconId\": 3839,\n" +
                "            \"summonerName\": \"Шляпкa Лулу\",\n" +
                "            \"bot\": false,\n" +
                "            \"summonerId\": \"YD-XuRgu4aKuKY1TEJd02qP1IiXYib00U5LNlmSsBIuEaQ\",\n" +
                "            \"gameCustomizationObjects\": [],\n" +
                "            \"perks\": {\n" +
                "                \"perkIds\": [\n" +
                "                    8010,\n" +
                "                    9111,\n" +
                "                    9104,\n" +
                "                    8014,\n" +
                "                    8139,\n" +
                "                    8135,\n" +
                "                    5005,\n" +
                "                    5008,\n" +
                "                    5002\n" +
                "                ],\n" +
                "                \"perkStyle\": 8000,\n" +
                "                \"perkSubStyle\": 8100\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"teamId\": 100,\n" +
                "            \"spell1Id\": 4,\n" +
                "            \"spell2Id\": 14,\n" +
                "            \"championId\": 82,\n" +
                "            \"profileIconId\": 977,\n" +
                "            \"summonerName\": \"Иди в фею я баня\",\n" +
                "            \"bot\": false,\n" +
                "            \"summonerId\": \"oHTUlBDMyJr6UolxIjngjXhdU2P78sk_txbb0fR_DNDqzQ\",\n" +
                "            \"gameCustomizationObjects\": [],\n" +
                "            \"perks\": {\n" +
                "                \"perkIds\": [\n" +
                "                    8010,\n" +
                "                    9111,\n" +
                "                    9105,\n" +
                "                    8299,\n" +
                "                    8126,\n" +
                "                    8106,\n" +
                "                    5005,\n" +
                "                    5008,\n" +
                "                    5001\n" +
                "                ],\n" +
                "                \"perkStyle\": 8000,\n" +
                "                \"perkSubStyle\": 8100\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"teamId\": 100,\n" +
                "            \"spell1Id\": 4,\n" +
                "            \"spell2Id\": 6,\n" +
                "            \"championId\": 4,\n" +
                "            \"profileIconId\": 1113,\n" +
                "            \"summonerName\": \"Apobiosis\",\n" +
                "            \"bot\": false,\n" +
                "            \"summonerId\": \"ODFtLbT4Sc8jO8U34kw_28msBG_PUwx_O0AHrYazAsfu8g\",\n" +
                "            \"gameCustomizationObjects\": [],\n" +
                "            \"perks\": {\n" +
                "                \"perkIds\": [\n" +
                "                    8128,\n" +
                "                    8139,\n" +
                "                    8138,\n" +
                "                    8135,\n" +
                "                    8009,\n" +
                "                    8014,\n" +
                "                    5008,\n" +
                "                    5008,\n" +
                "                    5002\n" +
                "                ],\n" +
                "                \"perkStyle\": 8100,\n" +
                "                \"perkSubStyle\": 8000\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"teamId\": 100,\n" +
                "            \"spell1Id\": 4,\n" +
                "            \"spell2Id\": 32,\n" +
                "            \"championId\": 412,\n" +
                "            \"profileIconId\": 29,\n" +
                "            \"summonerName\": \"Призыватель 3\",\n" +
                "            \"bot\": false,\n" +
                "            \"summonerId\": \"HLS1Gm84qRmpa_QXHyrsHcOqpYQnGlD5s_P_8fvxFaHraA\",\n" +
                "            \"gameCustomizationObjects\": [],\n" +
                "            \"perks\": {\n" +
                "                \"perkIds\": [\n" +
                "                    8439,\n" +
                "                    8446,\n" +
                "                    8444,\n" +
                "                    8451,\n" +
                "                    8234,\n" +
                "                    8226,\n" +
                "                    5007,\n" +
                "                    5003,\n" +
                "                    5003\n" +
                "                ],\n" +
                "                \"perkStyle\": 8400,\n" +
                "                \"perkSubStyle\": 8200\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"teamId\": 100,\n" +
                "            \"spell1Id\": 4,\n" +
                "            \"spell2Id\": 32,\n" +
                "            \"championId\": 887,\n" +
                "            \"profileIconId\": 682,\n" +
                "            \"summonerName\": \"sintex\",\n" +
                "            \"bot\": false,\n" +
                "            \"summonerId\": \"WGW1c9Ygm79k7sZDvxAMYlsabULPRhdLiZCou0Yejv1kIA\",\n" +
                "            \"gameCustomizationObjects\": [],\n" +
                "            \"perks\": {\n" +
                "                \"perkIds\": [\n" +
                "                    8010,\n" +
                "                    9111,\n" +
                "                    9104,\n" +
                "                    8014,\n" +
                "                    8139,\n" +
                "                    8135,\n" +
                "                    5005,\n" +
                "                    5008,\n" +
                "                    5002\n" +
                "                ],\n" +
                "                \"perkStyle\": 8000,\n" +
                "                \"perkSubStyle\": 8100\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"teamId\": 200,\n" +
                "            \"spell1Id\": 32,\n" +
                "            \"spell2Id\": 4,\n" +
                "            \"championId\": 516,\n" +
                "            \"profileIconId\": 5769,\n" +
                "            \"summonerName\": \"LightWin\",\n" +
                "            \"bot\": false,\n" +
                "            \"summonerId\": \"yjHnPKitY4h3wxBdMo9Fif8aC_OHtS0Bgf7AH79ngqZO\",\n" +
                "            \"gameCustomizationObjects\": [],\n" +
                "            \"perks\": {\n" +
                "                \"perkIds\": [\n" +
                "                    8437,\n" +
                "                    8463,\n" +
                "                    8429,\n" +
                "                    8451,\n" +
                "                    9111,\n" +
                "                    9105,\n" +
                "                    5007,\n" +
                "                    5002,\n" +
                "                    5003\n" +
                "                ],\n" +
                "                \"perkStyle\": 8400,\n" +
                "                \"perkSubStyle\": 8000\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"teamId\": 200,\n" +
                "            \"spell1Id\": 4,\n" +
                "            \"spell2Id\": 6,\n" +
                "            \"championId\": 21,\n" +
                "            \"profileIconId\": 4655,\n" +
                "            \"summonerName\": \"Meiko Neko Nyan\",\n" +
                "            \"bot\": false,\n" +
                "            \"summonerId\": \"Ybwx9ViKFkehva2rmk3LAzsCxVhZoFEgO3TIHrRxSzHUZA\",\n" +
                "            \"gameCustomizationObjects\": [],\n" +
                "            \"perks\": {\n" +
                "                \"perkIds\": [\n" +
                "                    8008,\n" +
                "                    8009,\n" +
                "                    9103,\n" +
                "                    8014,\n" +
                "                    8138,\n" +
                "                    8106,\n" +
                "                    5005,\n" +
                "                    5008,\n" +
                "                    5002\n" +
                "                ],\n" +
                "                \"perkStyle\": 8000,\n" +
                "                \"perkSubStyle\": 8100\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"teamId\": 200,\n" +
                "            \"spell1Id\": 6,\n" +
                "            \"spell2Id\": 4,\n" +
                "            \"championId\": 122,\n" +
                "            \"profileIconId\": 4793,\n" +
                "            \"summonerName\": \"Маэстро\",\n" +
                "            \"bot\": false,\n" +
                "            \"summonerId\": \"UexA_1kHnlypPzMGd8T4FHX4u7qS6TLW6dfCw2OUJIeybg\",\n" +
                "            \"gameCustomizationObjects\": [],\n" +
                "            \"perks\": {\n" +
                "                \"perkIds\": [\n" +
                "                    8437,\n" +
                "                    8463,\n" +
                "                    8429,\n" +
                "                    8451,\n" +
                "                    9105,\n" +
                "                    9111,\n" +
                "                    5005,\n" +
                "                    5008,\n" +
                "                    5002\n" +
                "                ],\n" +
                "                \"perkStyle\": 8400,\n" +
                "                \"perkSubStyle\": 8000\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"teamId\": 200,\n" +
                "            \"spell1Id\": 32,\n" +
                "            \"spell2Id\": 4,\n" +
                "            \"championId\": 131,\n" +
                "            \"profileIconId\": 4499,\n" +
                "            \"summonerName\": \"Beriaru\",\n" +
                "            \"bot\": false,\n" +
                "            \"summonerId\": \"8Oot7uMhVudzQX9p1VJxYghefyKFZPuTZBh26uPR2NPwkw\",\n" +
                "            \"gameCustomizationObjects\": [],\n" +
                "            \"perks\": {\n" +
                "                \"perkIds\": [\n" +
                "                    8128,\n" +
                "                    8143,\n" +
                "                    8138,\n" +
                "                    8106,\n" +
                "                    8014,\n" +
                "                    8009,\n" +
                "                    5007,\n" +
                "                    5008,\n" +
                "                    5002\n" +
                "                ],\n" +
                "                \"perkStyle\": 8100,\n" +
                "                \"perkSubStyle\": 8000\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"teamId\": 200,\n" +
                "            \"spell1Id\": 4,\n" +
                "            \"spell2Id\": 32,\n" +
                "            \"championId\": 57,\n" +
                "            \"profileIconId\": 5840,\n" +
                "            \"summonerName\": \"Woоdy Woodpecker\",\n" +
                "            \"bot\": false,\n" +
                "            \"summonerId\": \"UIpZEmpBP2qqV1pz0_AHsiikJG1lsnkkByKbsgd2IKOffQ\",\n" +
                "            \"gameCustomizationObjects\": [],\n" +
                "            \"perks\": {\n" +
                "                \"perkIds\": [\n" +
                "                    8437,\n" +
                "                    8463,\n" +
                "                    8429,\n" +
                "                    8451,\n" +
                "                    9105,\n" +
                "                    9111,\n" +
                "                    5007,\n" +
                "                    5002,\n" +
                "                    5001\n" +
                "                ],\n" +
                "                \"perkStyle\": 8400,\n" +
                "                \"perkSubStyle\": 8000\n" +
                "            }\n" +
                "        }\n" +
                "    ],\n" +
                "    \"observers\": {\n" +
                "        \"encryptionKey\": \"T4OYQY8aSJ6gaoaQatgp4i4InZHdw0aZ\"\n" +
                "    },\n" +
                "    \"platformId\": \"RU\",\n" +
                "    \"bannedChampions\": [],\n" +
                "    \"gameStartTime\": 1688143836900,\n" +
                "    \"gameLength\": -136\n" +
                "}";
    }

    fun mockGame1() : String {
        return "{\"gameId\":448229997,\"mapId\":12,\"gameMode\":\"ARAM\",\"gameType\":\"MATCHED_GAME\",\"gameQueueConfigId\":450,\"participants\":[{\"teamId\":100,\"spell1Id\":4,\"spell2Id\":3,\"championId\":147,\"profileIconId\":4961,\"summonerName\":\"PrettyWolfy\",\"bot\":false,\"summonerId\":\"fxaBRsMljLKKBtTPkT4k90S_TEiK1kE_Hm-Dz2a0KjRG82k\",\"gameCustomizationObjects\":[],\"perks\":{\"perkIds\":[8229,8226,8210,8237,8014,8009,5008,5008,5002],\"perkStyle\":8200,\"perkSubStyle\":8000}},{\"teamId\":100,\"spell1Id\":6,\"spell2Id\":4,\"championId\":33,\"profileIconId\":713,\"summonerName\":\"Она плавает в п4\",\"bot\":false,\"summonerId\":\"5YzudbSnKxhL7a769YqSdyFQyf6vtYAZv5P11uNsUO-1Mg\",\"gameCustomizationObjects\":[],\"perks\":{\"perkIds\":[8439,8463,8429,8451,8126,8106,5005,5002,5002],\"perkStyle\":8400,\"perkSubStyle\":8100}},{\"teamId\":100,\"spell1Id\":3,\"spell2Id\":4,\"championId\":202,\"profileIconId\":5611,\"summonerName\":\"KAPUSHA\",\"bot\":false,\"summonerId\":\"esUMzmLrzB5l45vbR9frN66QIbttzo_WOVd9lq18lO4Oog\",\"gameCustomizationObjects\":[],\"perks\":{\"perkIds\":[8128,8139,8138,8135,9103,8009,5008,5008,5002],\"perkStyle\":8100,\"perkSubStyle\":8000}},{\"teamId\":100,\"spell1Id\":6,\"spell2Id\":4,\"championId\":876,\"profileIconId\":5859,\"summonerName\":\"HornyTitan\",\"bot\":false,\"summonerId\":\"Cu94y8E8NWLuvOOIlAMyLOcDlDhYj4b8YVHIDk9Zid-pHg\",\"gameCustomizationObjects\":[],\"perks\":{\"perkIds\":[8128,8126,8138,8135,8234,8275,5008,5008,5002],\"perkStyle\":8100,\"perkSubStyle\":8200}},{\"teamId\":100,\"spell1Id\":4,\"spell2Id\":6,\"championId\":119,\"profileIconId\":5813,\"summonerName\":\"M?R?h?n?k\",\"bot\":false,\"summonerId\":\"d1PY7GR8d19ddIfsgf5J69EreVuN2bB-ngX09sM96V5ie2k\",\"gameCustomizationObjects\":[],\"perks\":{\"perkIds\":[8008,8009,9103,8014,8139,8135,5005,5008,5002],\"perkStyle\":8000,\"perkSubStyle\":8100}},{\"teamId\":200,\"spell1Id\":32,\"spell2Id\":4,\"championId\":80,\"profileIconId\":5769,\"summonerName\":\"LightWin\",\"bot\":false,\"summonerId\":\"yjHnPKitY4h3wxBdMo9Fif8aC_OHtS0Bgf7AH79ngqZO\",\"gameCustomizationObjects\":[],\"perks\":{\"perkIds\":[8128,8126,8138,8135,9111,8014,5008,5008,5002],\"perkStyle\":8100,\"perkSubStyle\":8000}},{\"teamId\":200,\"spell1Id\":4,\"spell2Id\":21,\"championId\":203,\"profileIconId\":5430,\"summonerName\":\"Siicolen\",\"bot\":false,\"summonerId\":\"-Hu1XAVQZUT2ORnzXX2TGyF_biP3aq7Fhw9fISg3LMr1-CI50o3VpTwb4w\",\"gameCustomizationObjects\":[],\"perks\":{\"perkIds\":[8008,9111,9104,8014,8143,8135,5005,5008,5002],\"perkStyle\":8000,\"perkSubStyle\":8100}},{\"teamId\":200,\"spell1Id\":4,\"spell2Id\":32,\"championId\":157,\"profileIconId\":654,\"summonerName\":\"ТАТАРСТАН\",\"bot\":false,\"summonerId\":\"jJ4igbzuruQnkYs5Nr3fvzFpb_6GzrGTJ68hPuC2c8Ur\",\"gameCustomizationObjects\":[],\"perks\":{\"perkIds\":[8008,9111,9104,8014,8139,8135,5005,5008,5002],\"perkStyle\":8000,\"perkSubStyle\":8100}},{\"teamId\":200,\"spell1Id\":32,\"spell2Id\":4,\"championId\":121,\"profileIconId\":1666,\"summonerName\":\"GameIsLife\",\"bot\":false,\"summonerId\":\"r7j4EFKGvemxmKQ-u7k3j4aVQc3R9zQgrgptk9d4tsm4\",\"gameCustomizationObjects\":[],\"perks\":{\"perkIds\":[8128,8143,8138,8135,8014,8009,5008,5008,5002],\"perkStyle\":8100,\"perkSubStyle\":8000}},{\"teamId\":200,\"spell1Id\":4,\"spell2Id\":32,\"championId\":117,\"profileIconId\":4764,\"summonerName\":\"ShelterSiren\",\"bot\":false,\"summonerId\":\"JyrLjiNPeZaAduVLVFmlNN3rosp4jnQyrfLDb9ElQZsi5Ew\",\"gameCustomizationObjects\":[],\"perks\":{\"perkIds\":[8214,8226,8210,8237,8009,8014,5008,5008,5002],\"perkStyle\":8200,\"perkSubStyle\":8000}}],\"observers\":{\"encryptionKey\":\"W+tJ7RvaWU6ej73Oa9zDUfv1jDHDF+zp\"},\"platformId\":\"RU\",\"bannedChampions\":[],\"gameStartTime\":0,\"gameLength\":0}{\"gameId\":448229997,\"mapId\":12,\"gameMode\":\"ARAM\",\"gameType\":\"MATCHED_GAME\",\"gameQueueConfigId\":450,\"participants\":[{\"teamId\":100,\"spell1Id\":4,\"spell2Id\":3,\"championId\":147,\"profileIconId\":4961,\"summonerName\":\"PrettyWolfy\",\"bot\":false,\"summonerId\":\"fxaBRsMljLKKBtTPkT4k90S_TEiK1kE_Hm-Dz2a0KjRG82k\",\"gameCustomizationObjects\":[],\"perks\":{\"perkIds\":[8229,8226,8210,8237,8014,8009,5008,5008,5002],\"perkStyle\":8200,\"perkSubStyle\":8000}},{\"teamId\":100,\"spell1Id\":6,\"spell2Id\":4,\"championId\":33,\"profileIconId\":713,\"summonerName\":\"Она плавает в п4\",\"bot\":false,\"summonerId\":\"5YzudbSnKxhL7a769YqSdyFQyf6vtYAZv5P11uNsUO-1Mg\",\"gameCustomizationObjects\":[],\"perks\":{\"perkIds\":[8439,8463,8429,8451,8126,8106,5005,5002,5002],\"perkStyle\":8400,\"perkSubStyle\":8100}},{\"teamId\":100,\"spell1Id\":3,\"spell2Id\":4,\"championId\":202,\"profileIconId\":5611,\"summonerName\":\"KAPUSHA\",\"bot\":false,\"summonerId\":\"esUMzmLrzB5l45vbR9frN66QIbttzo_WOVd9lq18lO4Oog\",\"gameCustomizationObjects\":[],\"perks\":{\"perkIds\":[8128,8139,8138,8135,9103,8009,5008,5008,5002],\"perkStyle\":8100,\"perkSubStyle\":8000}},{\"teamId\":100,\"spell1Id\":6,\"spell2Id\":4,\"championId\":876,\"profileIconId\":5859,\"summonerName\":\"HornyTitan\",\"bot\":false,\"summonerId\":\"Cu94y8E8NWLuvOOIlAMyLOcDlDhYj4b8YVHIDk9Zid-pHg\",\"gameCustomizationObjects\":[],\"perks\":{\"perkIds\":[8128,8126,8138,8135,8234,8275,5008,5008,5002],\"perkStyle\":8100,\"perkSubStyle\":8200}},{\"teamId\":100,\"spell1Id\":4,\"spell2Id\":6,\"championId\":119,\"profileIconId\":5813,\"summonerName\":\"M?R?h?n?k\",\"bot\":false,\"summonerId\":\"d1PY7GR8d19ddIfsgf5J69EreVuN2bB-ngX09sM96V5ie2k\",\"gameCustomizationObjects\":[],\"perks\":{\"perkIds\":[8008,8009,9103,8014,8139,8135,5005,5008,5002],\"perkStyle\":8000,\"perkSubStyle\":8100}},{\"teamId\":200,\"spell1Id\":32,\"spell2Id\":4,\"championId\":80,\"profileIconId\":5769,\"summonerName\":\"LightWin\",\"bot\":false,\"summonerId\":\"yjHnPKitY4h3wxBdMo9Fif8aC_OHtS0Bgf7AH79ngqZO\",\"gameCustomizationObjects\":[],\"perks\":{\"perkIds\":[8128,8126,8138,8135,9111,8014,5008,5008,5002],\"perkStyle\":8100,\"perkSubStyle\":8000}},{\"teamId\":200,\"spell1Id\":4,\"spell2Id\":21,\"championId\":203,\"profileIconId\":5430,\"summonerName\":\"Siicolen\",\"bot\":false,\"summonerId\":\"-Hu1XAVQZUT2ORnzXX2TGyF_biP3aq7Fhw9fISg3LMr1-CI50o3VpTwb4w\",\"gameCustomizationObjects\":[],\"perks\":{\"perkIds\":[8008,9111,9104,8014,8143,8135,5005,5008,5002],\"perkStyle\":8000,\"perkSubStyle\":8100}},{\"teamId\":200,\"spell1Id\":4,\"spell2Id\":32,\"championId\":157,\"profileIconId\":654,\"summonerName\":\"ТАТАРСТАН\",\"bot\":false,\"summonerId\":\"jJ4igbzuruQnkYs5Nr3fvzFpb_6GzrGTJ68hPuC2c8Ur\",\"gameCustomizationObjects\":[],\"perks\":{\"perkIds\":[8008,9111,9104,8014,8139,8135,5005,5008,5002],\"perkStyle\":8000,\"perkSubStyle\":8100}},{\"teamId\":200,\"spell1Id\":32,\"spell2Id\":4,\"championId\":121,\"profileIconId\":1666,\"summonerName\":\"GameIsLife\",\"bot\":false,\"summonerId\":\"r7j4EFKGvemxmKQ-u7k3j4aVQc3R9zQgrgptk9d4tsm4\",\"gameCustomizationObjects\":[],\"perks\":{\"perkIds\":[8128,8143,8138,8135,8014,8009,5008,5008,5002],\"perkStyle\":8100,\"perkSubStyle\":8000}},{\"teamId\":200,\"spell1Id\":4,\"spell2Id\":32,\"championId\":117,\"profileIconId\":4764,\"summonerName\":\"ShelterSiren\",\"bot\":false,\"summonerId\":\"JyrLjiNPeZaAduVLVFmlNN3rosp4jnQyrfLDb9ElQZsi5Ew\",\"gameCustomizationObjects\":[],\"perks\":{\"perkIds\":[8214,8226,8210,8237,8009,8014,5008,5008,5002],\"perkStyle\":8200,\"perkSubStyle\":8000}}],\"observers\":{\"encryptionKey\":\"W+tJ7RvaWU6ej73Oa9zDUfv1jDHDF+zp\"},\"platformId\":\"RU\",\"bannedChampions\":[],\"gameStartTime\":0,\"gameLength\":0}\n";
    }
}