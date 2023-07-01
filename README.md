Известные баги: не удается одновременно закачать иконки и потом получить к ним доступ из файла ресурсов
Не уверен обновилось раз, или не обновилось совсем в лобби

https://stackoverflow.com/questions/68909340/how-to-show-snackbar-with-a-button-onclick-in-jetpack-compose - snackbar

Для запуска надо добавить 
>> -Djavax.net.ssl.trustStore=cacerts -Djavax.net.ssl.trustStorePassword=changeit
>


Для получения id, accountId, puuid:
>>https://developer.riotgames.com/apis#summoner-v4/GET_getBySummonerName
> 
> HeaderParam
> 
> aram helper
> 
> summonerName
> 

далее с этим айдишником 
>> https://developer.riotgames.com/apis#spectator-v4/GET_getCurrentGameInfoBySummoner
> encryptedId = id из предыдущего запроса