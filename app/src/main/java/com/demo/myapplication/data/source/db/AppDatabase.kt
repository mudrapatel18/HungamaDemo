package com.demo.myapplication.data.source.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.demo.myapplication.domain.model.TabModel

@Database(entities = [TabModel::class], version = AppDatabase.VERSION)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val DB_NAME = "hungama.db"
        const val VERSION = 1
    }
    abstract fun tabDao(): TabDao
}