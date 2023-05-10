import org.apache.commons.lang3.SystemUtils
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.RuntimeException
import java.util.regex.Matcher
import java.util.regex.Pattern
import java.util.stream.Collectors

object GetAuthData {
    val data: Map<String, String>
        get(){
            if ("Mac OS X" == SystemUtils.OS_NAME) {
                println(SystemUtils.OS_NAME)
                val rt = Runtime.getRuntime()
                val proc = rt.exec("ps -A")
                val stdInput = BufferedReader(InputStreamReader(proc.inputStream))
                val collect = stdInput.lines()
                    .filter { it.contains("LeagueClientUx") }
                    .collect(Collectors.toList())

                val port = getPort(collect)
                val token = getToken(collect)
                val map = mapOf(Pair("port", port), Pair("token", token))
                return map
            }
            throw RuntimeException("Не удалось получить данные для авторизации")
            //todo windows
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