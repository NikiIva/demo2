import androidx.compose.runtime.*
import kotlinx.coroutines.delay


object SessionUpdate {

    fun updateSession(
        uiRows : MutableState<ArrayList<UIRow>>,
        summonerName : MutableList<String>,
        accountId : MutableList<String>,
        championInfoName : MutableList<String>,
        championInfoKey : MutableList<String>,
        damageDealt : MutableList<String>,
        damageReceived : MutableList<String>,
        abilityHaste : MutableList<String>,
        attackSpeed : MutableList<String>,
        shield : MutableList<String>,
        healing : MutableList<String>,
        tenacity : MutableList<String>,
        energy : MutableList<String>,
        ddragonChampionInfoName : MutableList<String>,
        extra : MutableList<String>,
        q : MutableState<Int>
    ) {
        var newSession = "null"
        q.value++
        if (q.value % 2 == 0) {
            newSession = ClientRESTs.mockSession1()
        } else {
            newSession = ClientRESTs.mockSession()
        }
        uiRows.value = Start.run(newSession)
//                val newSession = ClientRESTs.getSession()
//                    val session = ClientRESTs.mockSession1()
        val run = uiRows.value

        for (i in 0..14) {
            summonerName[i] = run[i].summonerInfo?.summonerName ?: ""
            accountId[i] = run[i].summonerInfo?.accountId ?: ""
            championInfoName[i] = run[i].championInfo?.name ?: ""
            championInfoKey[i] = run[i].championInfo?.key ?: ""
            damageDealt[i] = run[i].championInfo?.balance?.damageDealt ?: ""
            damageReceived[i] = run[i].championInfo?.balance?.damageReceived ?: ""
            abilityHaste[i] = run[i].championInfo?.balance?.abilityHaste ?: ""
            attackSpeed[i] = run[i].championInfo?.balance?.attackSpeed ?: ""
            shield[i] = run[i].championInfo?.balance?.shield ?: ""
            healing[i] = run[i].championInfo?.balance?.healing ?: ""
            tenacity[i] = run[i].championInfo?.balance?.tenacity ?: ""
            energy[i] = run[i].championInfo?.balance?.energy ?: ""
            ddragonChampionInfoName[i] = run[i].championInfo?.balance?.ddragonChampionName ?: "0.0"
            extra[i] = run[i].championInfo?.balance?.extra ?: ""
        }
    }
}