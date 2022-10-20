package yandex.mobilecontest2022

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter


// Недоработана
fun softpads() {
    val input = BufferedReader(FileReader("src/main/resources/input.txt"))
    val output = BufferedWriter(FileWriter("src/main/resources/output.txt"))

    var h = -1
    var padsCount = -1

    var lineCounter = 1
    var h1 = 0
    val h2 = IntArray(2)

    input.forEachLine { string ->
        when (lineCounter) {
            1 -> {
                val params = string.split(" ").map { it.toInt() }
                h = params[0]
                padsCount = params[1]
            }
            else -> {
                val params = string.split(" ").map { it.toInt() }
                params.forEachIndexed { i, el ->
                    when {
                        h == 1 -> {
                            h1 += el
                        }
                        h == 2 -> {
                            if (i + 1 != padsCount) {
                                h2[0] += el
                            } else {
                                h2[1] += el
                            }
                        }
                        (h >= 3) || (h == 0) -> {
                            return@forEachIndexed
                        }
                    }
                }
            }
        }
        lineCounter += 1
    }

    when {
        h == 1 -> {
            println(h1)
        }
        h == 2 -> {
            val out = if (h2[0] < h2[1]) h2[0] else h2[1]
            println(out)
        }
        (h >= 3) || (h == 0) -> {
            println(0)
        }
    }
    output.flush()
}