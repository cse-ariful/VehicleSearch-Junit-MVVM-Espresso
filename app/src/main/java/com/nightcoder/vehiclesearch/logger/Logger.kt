package com.nightcoder.vehiclesearch.logger

interface Logger {
    fun info(msg: String): Unit
    fun error(message:String?,exception: java.lang.Exception?)

}