package yandex.mobiletestcontest2022

import java.io.BufferedReader
import java.io.InputStreamReader
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import kotlin.math.roundToInt
import kotlin.properties.Delegates

enum class SystemResponses(val char: Char) {
    BEFORE_COUNTER('?'),
    OUT_OF_LIMIT('!')
}

enum class DateDirection {
    INCREASE,
    DECREASE
}

// ****Недоработана*****************************************************************************************************
fun findDay() {

//    val rawLeftDate = "01.01.1970".replace('.', '-')
//    val rawRightDate = "31.12.2020".replace('.', '-')

    val rawLeftDate = "01.12.2020".replace('.', '-')
    val rawRightDate = "31.12.2020".replace('.', '-')

    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    var leftBorder = LocalDate.parse(rawLeftDate, formatter)
    var rightBorder = LocalDate.parse(rawRightDate, formatter)

    var date = rightBorder
    var prevDate = date

    fun divideDatesAndGetDate(dateDirection: DateDirection): LocalDate {
//        var period = Period.between(leftDate, rightDate)

        val getHalve: (periodInDays: Long) -> Long = {
            if ((it / 2) > 0) {
                it / 2
            } else {
                1
            }
        }


        return when (dateDirection) {
            DateDirection.INCREASE -> {
                val periodInDays = ChronoUnit.DAYS.between(date, rightBorder)
                leftBorder = date
                println("date ${date.format(formatter)} rightBorder ${rightBorder.format(formatter)}")
                val halve = getHalve(periodInDays)
                println("plus days $halve")
                date = date.plusDays(halve)
                date
            }
            DateDirection.DECREASE -> {
                val periodInDays = ChronoUnit.DAYS.between(leftBorder, date)
                rightBorder = date
                println("leftBorder ${leftBorder.format(formatter)} date ${date.format(formatter)}")
                val halve = getHalve(periodInDays)
                println("minus days $halve")
                date = date.minusDays(halve)
                date
            }
        }
    }


    fun getRemainingDate(): LocalDate {
        println("leftBorder ${leftBorder.format(formatter)} rightBorder ${rightBorder.format(formatter)}")
        while (date.isAfter(leftBorder)) {
            prevDate = date
            date = date.minusDays(1)
            return date
        }
        return date
    }

    fun ask(date: LocalDate) {
        println("? " + date.format(formatter).replace('-', '.'))
        System.out.flush()
    }

    fun answer(date: LocalDate) {
        println("! " + date.format(formatter).replace('-', '.'))
        System.out.flush()
    }


    val input = BufferedReader(InputStreamReader(System.`in`))
    var lineCounter = 0
    var limit: Int by Delegates.notNull()
    var listSize: Int? = null

    var isRangeFound = false

    var oldBeforeCounter: Int? = null

    input.forEachLine { line ->

        when {
            (lineCounter) == 0 -> {
                limit = line.toInt()
                ask(rightBorder)
            }
            lineCounter > limit -> {
                return@forEachLine
            }
            else -> {
                when (line[0]) {
                    SystemResponses.BEFORE_COUNTER.char -> {
                        val beforeCounter = line.filter { lineIt -> lineIt.isDigit() }.toInt()
                        if (listSize == null)
                            listSize = beforeCounter
                        when (isRangeFound) {
                            true -> {
                                if (oldBeforeCounter != beforeCounter)
                                    answer(prevDate)
                                else {
                                    ask(getRemainingDate())
                                    oldBeforeCounter = beforeCounter
                                }
                            }
                            false -> {
                                val central = (listSize!!.toDouble() / 2.0).roundToInt()
                                when {
                                    central < beforeCounter -> {
                                        //decrease
                                        println("decrease $central < $beforeCounter")
                                        println("date ${date.format(formatter).replace('-', '.')}")
                                        ask(divideDatesAndGetDate(DateDirection.DECREASE))
                                    }
                                    central > beforeCounter -> {
                                        //increase
                                        println("increase $central > $beforeCounter")
                                        println("date ${date.format(formatter).replace('-', '.')}")
                                        ask(divideDatesAndGetDate(DateDirection.INCREASE))
                                    }
                                    central == beforeCounter -> {
                                        isRangeFound = true
                                        println("equal")
                                        println("date ${date.format(formatter).replace('-', '.')}")
                                        ask(getRemainingDate())
                                        oldBeforeCounter = beforeCounter
                                    }
                                }
                            }
                        }

                    }

                    SystemResponses.OUT_OF_LIMIT.char -> {
                        /*finish */
                        return@forEachLine
                    }
                }
            }
        }
        lineCounter += 1

    }
}
