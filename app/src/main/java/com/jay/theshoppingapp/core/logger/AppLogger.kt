package com.jay.theshoppingapp.core.logger

interface AppLogger {
    fun log(type: Int, tag: String, message: String)
}