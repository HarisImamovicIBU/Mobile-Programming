package com.example.brainscript

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp
import com.example.brainscript.database.AppDatabase
import com.example.brainscript.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class CategoryPlanner: Application() {
    @Inject
    lateinit var database: AppDatabase

    override fun onCreate() {
        super.onCreate()

        CoroutineScope(Dispatchers.IO).launch {
            //Test you can run here
            database.userDao().getUserById(1)
            Log.d("DatabaseTest", "Database initialized")
        }
    }
}