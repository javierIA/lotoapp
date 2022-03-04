package com.example.lotoapp.db

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "logs")
data class Logs (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "user")
    @NonNull
    val user: String,
    @ColumnInfo(name = "date")
    @NonNull
    val date: String,
    @NonNull
    @ColumnInfo(name = "result")
    val result: String,
    @NonNull
    @ColumnInfo(name = "detection")
    val detection: String
    )
{
    override fun toString(): String {
        return "id=$id, user='$user', date='$date', result='$result', detection='$detection'"
    }
}

