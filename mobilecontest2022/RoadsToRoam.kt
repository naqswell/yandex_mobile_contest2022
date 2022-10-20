package yandex.mobilecontest2022

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter


// Недоработана
fun roadsToRoam() {
    val input = BufferedReader(FileReader("src/main/resources/input.txt"))
    val output = BufferedWriter(FileWriter("src/main/resources/output.txt"))

    val map = mutableMapOf<Int, MutableList<Int>>()

    var lineCounter = 0
    var citiesCounter = -1
    var roadsCounter = -1

    input.forEachLine { string ->
        when (lineCounter) {
            0 -> {
                val params = string.split(" ").map { it.toInt() }
                citiesCounter = params[0]
                roadsCounter = params[1]
            }
            else -> {
                val params = string.split(" ").map { it.toInt() }
                val city1 = params[0]
                val city2 = params[1]

                map[city2]?.add(city1) ?: map.put(city2, mutableListOf(city1))
            }
        }
        lineCounter += 1
    }

    val maxKey = map.maxByOrNull { it.value.size }?.key
    val listOfMaxKey = map[maxKey]
    val flag = (listOfMaxKey?.toSet()?.size == (citiesCounter - 1))
    if (flag) {
        output.write(maxKey.toString())
    } else {
        output.write("-1")
    }
    output.flush()
}