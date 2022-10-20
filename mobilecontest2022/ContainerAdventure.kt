package yandex.mobilecontest2022

import java.io.*


// Рабочая
fun median(list: List<Int>) = list.sorted().let {
    if (it.size % 2 == 0) {
        //        (it[it.size / 2] + it[(it.size - 1) / 2]) / 2
        it[it.size / 2 - 1]
    } else
        it[it.size / 2]
}

fun containerAdventure() {
    val input = BufferedReader(FileReader("src/main/resources/input.txt"))
    val output = BufferedWriter(FileWriter("src/main/resources/output.txt"))
    val list = mutableListOf<Int>()

    var lineCounter = 0

    input.forEachLine { string ->
        when (lineCounter) {
            0 -> {}
            else -> {
                val params = string.split(" ").toMutableList().apply {
                    removeFirst()
                }.map {
                    it.toInt()
                }
                params.maxOrNull()?.let { list.add(it) }
            }
        }
        lineCounter += 1
    }
    output.write(median(list).toString())
    output.flush()
}