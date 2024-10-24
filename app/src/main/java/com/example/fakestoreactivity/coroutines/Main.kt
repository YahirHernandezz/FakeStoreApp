package com.example.fakestoreactivity.coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(){
    //Ejecutar codigo asicronico
//    GlobalScope.launch {
//        println("Hola desde launch")
//        delay(2000)
//        println("Termino de traerse los datos")
//    }
    //cLaunch()
    cAsync()
}

fun cLaunch(){
    runBlocking {
        launch {
            //println("Trayendo informacion de una API")
            println("Mi super aplicacion")
            val data = consultaBaseDeDatos()
            println(data)
        }
    }
}

//las corrutinas necesitan llamar a donde se ejecutan (Scope)
fun cAsync(){
    runBlocking {
        val result =async {
            println("Consultando datos")
            delay(2000)
            20
        }
        println(result.await())
    }
}

//funcion asincrona/suspendida
suspend fun consultaBaseDeDatos() : String{
    println("Consultando base de datos")
    delay(2000)
    return "Datos traidos"
}
