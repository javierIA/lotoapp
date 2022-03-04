package com.example.lotoapp.db

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Created by SAUL on 03/10/2020.
 */
@Database(entities = [Logs::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract val logsDao: LogsDao

    companion object {
        const val DATABASE_NAME = "db-loto"
    }
}