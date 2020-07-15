package ru.trains.timetable

import com.example.demo.view.timetable.random
import com.example.demo.view.timetable.randomTime
import java.time.LocalTime

data class Train(private val _nazn: String, val numr: Int = (1000..9999).random(), val time: LocalTime = randomTime()){
    val nazn = _nazn.capitalize()
    @Transient
    private val hours = time.hour
    @Transient
    private val minutes = time.minute
    val timeSum = hours * 60 + minutes

    init {
        require(numr.toString().length == 4){"The train number are wrong!"}
    }
}
