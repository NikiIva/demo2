package cache

import ddragon.AllChampions
import pastebin.GetAllChampionsService

object Cache {
    private var allChampions : AllChampions? = null

    fun getAllChampions(): AllChampions? {
        if (allChampions == null){
            allChampions = GetAllChampionsService.allChampions
        }
        return allChampions
    }
}