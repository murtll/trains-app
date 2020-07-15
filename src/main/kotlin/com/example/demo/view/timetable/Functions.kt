package com.example.demo.view.timetable

import ru.trains.timetable.Train
import java.time.LocalTime

fun MutableList<Train>.sortByAlph() = sortBy{it.nazn}

fun MutableList<Train>.filterByTime(requiredTime: String) {
    val dataRequiredTime = requiredTime.split(":").map { it.toInt() }
    val sumRequiredTime = dataRequiredTime[0] * 60 + dataRequiredTime[1]
    val tmp = this.filter { it.timeSum > sumRequiredTime }
    this.clear()
    this.addAll(tmp)
}

fun randomTime(): LocalTime = LocalTime.of((0..23).random(), (0..59).random())

fun MutableList<Train>.filterByDest(requiredCity: String) {
    val tmp = this.filter { it.nazn == requiredCity.capitalize() }
    this.clear()
    this.addAll(tmp)
}

fun <T> Iterable<T>.random(): T = shuffled().first()