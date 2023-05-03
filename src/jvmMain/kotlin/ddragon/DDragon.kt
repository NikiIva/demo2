package ddragon

data class AllChampions(
    var type: String?,
    var format: String?,
    var varsion: String?,
    var champions: ArrayList<Champion>
)

data class Champion(
    var version: String?,
    var id: String?,
    var key: String?,
    var name: String?,
    var title: String?,
    var blurb: String?,
    var info: ChampionInfo?,
    var image: ChampionImage?,
    var tags: List<String>?,
    var partype: String?,
    var stats: ChampionStats?
)

data class ChampionInfo(
    var attack: Int,
    var defense: Int,
    var magic: Int,
    var difficulty: Int
)

data class ChampionImage(
    var full: String?,
    var sprite: String?,
    var group: String?,
    var x: Int,
    var y: Int,
    var w: Int,
    var h: Int
)

data class ChampionStats(
    var hp: Double?,
    var hpperlevel: Double?,
    var mp: Double?,
    var mpperlevel: Double?,
    var movespeed: Double?,
    var armor: Double?,
    var armorperlevel: Double?,
    var spellblock: Double?,
    var spellblockperlevel: Double?,
    var attackrange: Double?,
    var hpregen: Double?,
    var hpregenperlevel: Double?,
    var mpregen: Double?,
    var mpregenperlevel: Double?,
    var crit: Double?,
    var critperlevel: Double?,
    var attackdamage: Double?,
    var attackdamageperlevel: Double?,
    var attackspeedperlevel: Double?,
    var attackspeed: Double?
)

//detailedInfo

data class ChampionDetailedInfo(var id: String?,
                                var key: String?,
                                var name: String?,
                                var title: String?,
                                var image: ChampionImage?,
                                var skins: List<Skin>?,
                                var lore: String?,
                                var blurb: String?,
                                var allytips: List<String>?,
                                var enemytips: List<String>?,
                                var tags: List<String>?,
                                var partype: String?,
                                var info: ChampionInfo?,
                                var stats: ChampionStats?,
                                var spells: List<Spells>?,
                                var passive: Passive?)

data class Spells(var id: String? ,
                  var name: String? ,
                  var description: String?,
                  var tooltip: String? ,
                  var leveltip: Leveltip? ,
                  var maxrank: Int? ,
                  var cooldown: Int? ,
                  var cooldownBurn: String? ,
                  var cost: List<Int>?,
                  var costBurn: String? ,
                  var datavalues: Any? ,
                  var effect: List<Int>? ,
                  var effectBurn: List<String>? ,
                  var vars: Any? ,
                  var costType: String? ,
                  var maxammo: String? ,
                  var range: Int? ,
                  var rangeBurn: String? ,
                  var image: ChampionImage?,
                  var resource: String? )

data class Leveltip(var label: List<String>?,
                    var effect: List<String>?)

data class Skin(var id: String?,
                var num: Int?,
                var name: String?,
                var chromas: Boolean?)

data class Passive(var name: String?,
                   var description: String?,
                   var image: ChampionImage?)