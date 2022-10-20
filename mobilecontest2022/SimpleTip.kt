package yandex.mobilecontest2022

import java.io.BufferedReader
import java.io.InputStreamReader


// Рабочая
fun simpleTip() {
    val input = BufferedReader(InputStreamReader(System.`in`))
    val map = mutableMapOf<Char, Int>()

    var lineCounter = 0
    var limit = -1

    input.forEachLine { string ->
        when (lineCounter) {
            0 -> {
                limit = string.toInt()
            }
            limit -> {
                val char = string[0]
                map[char] = (map[char] ?: 0) + 1
                println(map.maxByOrNull { it.value }?.key)
            }
            else -> {
                val char = string[0]
                map[char] = (map[char] ?: 0) + 1
            }
        }
        lineCounter += 1
    }
}