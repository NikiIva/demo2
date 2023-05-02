import java.lang.RuntimeException
import java.util.regex.Matcher
import java.util.regex.Pattern

var port : String = ""
var token : String = ""
object Example {
    @JvmStatic
    fun main(args: Array<String>) {
        val data = GetAuthData().getData()
        print(data)
//        if ("Mac OS X" == SystemUtils.OS_NAME) {
//            println(SystemUtils.OS_NAME)
//            val rt = Runtime.getRuntime()
//            val proc = rt.exec("ps -A")
//            val stdInput = BufferedReader(InputStreamReader(proc.inputStream))
//            val collect = stdInput.lines()
//                .filter { it.contains("LeagueClientUx") }
//                .collect(Collectors.toList())
//
//            port = getPort(collect)
//            token = getToken(collect)
//            println(port)
//            println(token)
//        }
//        //todo windows
    }


    private fun getPort(collect : List<String>) : String {
        val pattern: Pattern = Pattern.compile("--app-port=([0-9]*)")
        for (s in collect) {
            val matcher: Matcher = pattern.matcher(s)
            if (matcher.find()) {
                return matcher.group(1)
            }
        }
        throw RuntimeException("Не удалось установить порт")
    }

    private fun getToken(collect : List<String>) : String {
        val pattern: Pattern = Pattern.compile("--remoting-auth-token=([\\w-]*)")
        for (s in collect) {
            val matcher: Matcher = pattern.matcher(s)
            if (matcher.find()) {
                return matcher.group(1)
            }
        }
        throw RuntimeException("Не удалось установить токен")
    }
}