import org.apache.commons.lang3.SystemUtils
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.*


object Example {
    @JvmStatic
    fun main(args: Array<String>) {
        println(SystemUtils.OS_NAME)
        val cmd = "ps -A" // | grep LeagueClientUx"
        val rt = Runtime.getRuntime()
        val proc = rt.exec(cmd)

        val stdInput = BufferedReader(InputStreamReader(proc.inputStream))

        val stdError = BufferedReader(InputStreamReader(proc.errorStream))

// Read the output from the command

// Read the output from the command
        println("Here is the standard output of the command:\n")
        var s: String?
        while (stdInput.readLine().also { s = it } != null) {
            println(s)
            println()
        }

// Read any errors from the attempted command

// Read any errors from the attempted command
        println("Here is the standard error of the command (if any):\n")
        while (stdError.readLine().also { s = it } != null) {
            println(s)
        }
    }

    fun execCmd(cmd: String?): String? {
        var result: String? = null
        try {
            Runtime.getRuntime().exec(cmd).inputStream.use { inputStream ->
                Scanner(inputStream).useDelimiter("\\A").use { s ->
                    result = if (s.hasNext()) s.next() else null
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return result
    }
}