package com.jay.theshoppingapp.core.logger

import android.util.Log
import android.util.Log.ASSERT
import android.util.Log.DEBUG
import android.util.Log.ERROR
import android.util.Log.INFO
import android.util.Log.VERBOSE
import android.util.Log.WARN
import com.jay.theshoppingapp.BuildConfig

object AppLoggerImpl: AppLogger {
    override fun log(type: Int, tag: String, message: String) {
        if(BuildConfig.DEBUG) {
            when(type) {
                ASSERT -> Log.v(tag, message)
                DEBUG -> Log.d(tag, message)
                ERROR -> Log.e(tag, message)
                INFO -> Log.i(tag, message)
                VERBOSE -> Log.v(tag, message)
                WARN -> Log.w(tag, message)
            }
        }
    }
}