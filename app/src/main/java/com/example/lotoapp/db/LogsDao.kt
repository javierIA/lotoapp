package com.example.lotoapp.db

import androidx.room.*

@Dao
interface LogsDao {
    @Query("SELECT * FROM logs")
    fun  getAll(): List<Logs>

    @Query("SELECT * FROM logs WHERE id = :id")
     fun getById(id: Int): Logs

    @Query("SELECT * FROM logs WHERE user = :user")
     fun getUser(user: String): List<Logs>
    @Update
     fun update(logs: Logs)
    @Delete
     fun delete(logs: Logs)
    @Insert
     fun insert(logs: Logs)
     @Query("DELETE FROM logs")
     fun deleteAll()
}