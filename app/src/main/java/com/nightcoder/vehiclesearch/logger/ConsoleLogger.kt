package com.nightcoder.vehiclesearch.logger

import android.util.Log
import java.lang.Exception

class ConsoleLogger :Logger{
    override fun info(msg: String) {
        Log.d("CONSOLE_LOG", msg)
    }

    override fun error(message: String?, exception: Exception?) {
        Log.e("CONSOLE_LOG", message, exception)
    }
}