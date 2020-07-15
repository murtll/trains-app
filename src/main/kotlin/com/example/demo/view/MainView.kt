package com.example.demo.view

import com.example.demo.view.timetable.filterByDest
import com.example.demo.view.timetable.filterByTime
import com.example.demo.view.timetable.sortByAlph
import com.google.gson.Gson
import javafx.beans.property.SimpleStringProperty
import javafx.scene.control.Alert
import ru.trains.timetable.Train
import tornadofx.*
import java.io.File
import java.time.LocalTime

val gson = Gson()

class MainView : View("Trains timetable") {
    private val outputRasp = mutableListOf<Train>().observable()

    private val inputTime = SimpleStringProperty()
    private val newRoute = SimpleStringProperty()
    private val inputDest = SimpleStringProperty()
    private val removeNumber = SimpleStringProperty()
    override val root = form {

        fieldset {

            field("New route: ") {
                textfield(newRoute) {
                    action {
                        try {

                            val trainList = outputRasp.map { it }.toMutableList()
                            val time = newRoute.value.split(" ").last().split(":")
                            trainList += Train(newRoute.value.split(" ").first(),
                                    newRoute.value.split(" ")[1].toInt(),
                                    LocalTime.of(time.first().toInt(), time.last().toInt()))
                            val jsonString = gson.toJson(trainList)
                            File("C:\\Users\\asus\\Exercism\\kotlin\\TrainsApp\\src\\main\\kotlin\\com\\example\\demo\\view\\timetable\\timetable.json").writeText(jsonString)

                            outputRasp.clear()
                            val jsonList = File("C:\\Users\\asus\\Exercism\\kotlin\\TrainsApp\\src\\main\\kotlin\\com\\example\\demo\\view\\timetable\\timetable.json").readText()
                            val jsonTimetable = gson.fromJson(jsonList, List::class.java)
                            jsonTimetable.forEach {
                                outputRasp += gson.fromJson(it.toString(), Train::class.java)
                            }

                            newRoute.value = ""
                            outputRasp.sortByAlph()
                        }
                        catch (e: Exception){
                            alert(Alert.AlertType.ERROR, "Wrong input format! Try again.")
                        }
                    }
                }

                button("Add") {
                    action {
                        try {

                            val trainList = outputRasp.map { it }.toMutableList()
                                val time = newRoute.value.split(" ").last().split(":")
                                trainList += Train(newRoute.value.split(" ").first(),
                                        newRoute.value.split(" ")[1].toInt(),
                                        LocalTime.of(time.first().toInt(), time.last().toInt()))
                            val jsonString = gson.toJson(trainList)
                            File("C:\\Users\\asus\\Exercism\\kotlin\\TrainsApp\\src\\main\\kotlin\\com\\example\\demo\\view\\timetable\\timetable.json").writeText(jsonString)

                            outputRasp.clear()
                            val jsonList = File("C:\\Users\\asus\\Exercism\\kotlin\\TrainsApp\\src\\main\\kotlin\\com\\example\\demo\\view\\timetable\\timetable.json").readText()
                            val jsonTimetable = gson.fromJson(jsonList, List::class.java)
                            jsonTimetable.forEach {
                                outputRasp += gson.fromJson(it.toString(), Train::class.java)
                            }

                            newRoute.value = ""
                            outputRasp.sortByAlph()
                        }
                        catch (e: Exception){
                            alert(Alert.AlertType.ERROR, "Wrong input format! Try again.")
                        }
                    }
                }

                button("Show all routes"){
                    action {
                        try{
                            val jsonList = File("C:\\Users\\asus\\Exercism\\kotlin\\TrainsApp\\src\\main\\kotlin\\com\\example\\demo\\view\\timetable\\timetable.json").readText()
                            val jsonTimetable = gson.fromJson(jsonList, List::class.java)
                            outputRasp.clear()
                            jsonTimetable.forEach {
                                outputRasp += gson.fromJson(it.toString(), Train::class.java)
                            }
                            outputRasp.sortByAlph()
                        } catch (e: Exception) {
                            alert(Alert.AlertType.ERROR, "Can't find file...")
                        }
                    }
                }
            }

            field("Time:") {
                textfield(inputTime){
                    action{
                        try {
                            val jsonList = File("C:\\Users\\asus\\Exercism\\kotlin\\TrainsApp\\src\\main\\kotlin\\com\\example\\demo\\view\\timetable\\timetable.json").readText()
                            val jsonTimetable = gson.fromJson(jsonList, List::class.java)
                            outputRasp.clear()
                            jsonTimetable.forEach {
                                outputRasp += gson.fromJson(it.toString(), Train::class.java)
                            }
                            outputRasp.sortByAlph()
                            outputRasp.filterByTime(inputTime.value)
                        } catch (e: Exception) {
                            alert(Alert.AlertType.ERROR, "Wrong time format! Try again.")
                        }
                    }
                }

                button("Filter by time") {
                    action {
                        try {
                            val jsonList = File("C:\\Users\\asus\\Exercism\\kotlin\\TrainsApp\\src\\main\\kotlin\\com\\example\\demo\\view\\timetable\\timetable.json").readText()
                            val jsonTimetable = gson.fromJson(jsonList, List::class.java)
                            outputRasp.clear()
                            jsonTimetable.forEach {
                                outputRasp += gson.fromJson(it.toString(), Train::class.java)
                            }
                            outputRasp.sortByAlph()
                            outputRasp.filterByTime(inputTime.value)
                        } catch (e: Exception) {
                            alert(Alert.AlertType.ERROR, "Wrong time format! Try again.")
                        }
                    }
                }
            }

            field("Destination:") {
                textfield(inputDest) {
                    action {
                        try {
                            val jsonList = File("C:\\Users\\asus\\Exercism\\kotlin\\TrainsApp\\src\\main\\kotlin\\com\\example\\demo\\view\\timetable\\timetable.json").readText()
                            val jsonTimetable = gson.fromJson(jsonList, List::class.java)
                            outputRasp.clear()
                            jsonTimetable.forEach {
                                outputRasp += gson.fromJson(it.toString(), Train::class.java)
                            }
                            outputRasp.sortByAlph()
                            outputRasp.filterByDest(inputDest.value)
                        } catch (e: Exception) {
                            alert(Alert.AlertType.ERROR, "Wrong city name! Try again.")
                        }
                    }
                }

                button("Filter by destination") {
                    action {
                        try {
                            val jsonList = File("C:\\Users\\asus\\Exercism\\kotlin\\TrainsApp\\src\\main\\kotlin\\com\\example\\demo\\view\\timetable\\timetable.json").readText()
                            val jsonTimetable = gson.fromJson(jsonList, List::class.java)
                            outputRasp.clear()
                            jsonTimetable.forEach {
                                outputRasp += gson.fromJson(it.toString(), Train::class.java)
                            }
                            outputRasp.sortByAlph()
                            outputRasp.filterByDest(inputDest.value)
                        } catch (e: Exception) {
                            alert(Alert.AlertType.ERROR, "Wrong city name! Try again.")
                        }
                    }
                }
            }

            field("Remove train:") {
                textfield(removeNumber){
                    action {
                        try {
                            outputRasp.removeIf { it.numr == removeNumber.value.toInt() }
                            removeNumber.value = ""

                            val jsonString = gson.toJson(outputRasp)
                            File("C:\\Users\\asus\\Exercism\\kotlin\\TrainsApp\\src\\main\\kotlin\\com\\example\\demo\\view\\timetable\\timetable.json").writeText(jsonString)
                        } catch (e: Exception) {
                            alert(Alert.AlertType.ERROR, "You should enter correct train number!")
                        }
                    }
                }

                button("Remove") {
                    action {
                        try {
                            outputRasp.removeIf { it.numr == removeNumber.value.toInt() }
                            removeNumber.value = ""

                            val jsonString = gson.toJson(outputRasp)
                            File("C:\\Users\\asus\\Exercism\\kotlin\\TrainsApp\\src\\main\\kotlin\\com\\example\\demo\\view\\timetable\\timetable.json").writeText(jsonString)
                        } catch (e: Exception) {
                            alert(Alert.AlertType.ERROR, "You should enter correct train number!")
                        }
                    }
                }

                button("Remove all") {
                    action {
                        outputRasp.clear()
                        val jsonString = gson.toJson(outputRasp)
                        File("C:\\Users\\asus\\Exercism\\kotlin\\TrainsApp\\src\\main\\kotlin\\com\\example\\demo\\view\\timetable\\timetable.json").writeText(jsonString)
                    }
                }
            }

        }
            tableview(outputRasp) {
                readonlyColumn("Destination", Train::nazn)
                readonlyColumn("Number", Train::numr)
                readonlyColumn("Time", Train::time)
            }
    }

    private fun writeToTimetable(inputValue: String){
        val time = inputValue.split(" ").last().split(":")
        outputRasp += Train(inputValue.split(" ").first(),
                inputValue.split(" ")[1].toInt(),
                LocalTime.of(time.first().toInt(), time.last().toInt()))
    }
}
