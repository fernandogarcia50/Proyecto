package com.example.proyecto

import android.app.Application
import com.example.proyecto.database.DataBaseManager

open class MyAplication : Application() {
    override fun onCreate() {
        DataBaseManager.instance.initializeDb(applicationContext)
        super.onCreate()
    }
}