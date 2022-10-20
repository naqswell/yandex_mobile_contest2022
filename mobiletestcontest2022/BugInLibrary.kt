package yandex.mobiletestcontest2022

import java.io.*

// Решено
fun bugInLibrary() {
    val input = BufferedReader(InputStreamReader(System.`in`))
    val strings = arrayOfNulls<String>(10000)

    input.forEachLine { string ->
        var digit = ""

        var i = 0
        for (char in string) {
            if (char.isDigit()) {
                digit += char
            }
            i += 1
        }

        strings[digit.toInt() - 1] = string.filter { !it.isDigit() }
    }

    for (str in strings) {
        if (str != null)
            println(str)
    }
}