package com.example.lotoapp.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.room.Room
import com.example.lotoapp.R
import com.example.lotoapp.db.AppDatabase
import com.example.lotoapp.helpers.SaveImg
import com.example.lotoapp.helpers.SaveTxt
import java.text.SimpleDateFormat
import java.util.*


class Logs : AppCompatActivity() {
    private lateinit var  btn_logs: Button
    private lateinit var  btn_clear: Button

    private lateinit var  txt: TextView
    private lateinit var database: AppDatabase
    private lateinit var  SaveTxt: SaveTxt
    private  var list : MutableList<String> = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logs)
        val actionBar = supportActionBar
        SaveTxt = SaveTxt(this)

        // showing the back button in action bar
        if (actionBar != null) {
            actionBar.displayOptions = androidx.appcompat.app.ActionBar.DISPLAY_SHOW_CUSTOM
            actionBar.setCustomView(R.layout.toolbar)

        }
        txt = findViewById(R.id.logstxt)
        btn_logs = findViewById(R.id.btnlogs)
        btn_clear = findViewById(R.id.btnclear)
        btn_logs.setOnClickListener() {
            saveLogs()
            Toast.makeText(this, "Logs saved", Toast.LENGTH_SHORT).show()
        }
        fetchLogs()
        btn_clear.setOnClickListener() {
            clearLogs()
            Toast.makeText(this, "Logs cleared", Toast.LENGTH_SHORT).show()
        }
    }
    private fun fetchLogs() {

        database = Room.databaseBuilder(
            application, AppDatabase::class.java, AppDatabase.DATABASE_NAME
        ).allowMainThreadQueries().build()



        val logs = database.logsDao.getAll()
        logs.forEach { log ->
            txt.append(
                log.toString() + "\n"
            )

        }
    }
        private fun saveLogs() {
        database = Room.databaseBuilder(
            application, AppDatabase::class.java, AppDatabase.DATABASE_NAME
        ).allowMainThreadQueries().build()


        val logs = database.logsDao.getAll()

        logs.forEach { log ->
            list.add(log.toString()+"\n")
        }
        val c: Calendar = Calendar.getInstance()
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val strDate: String = sdf.format(c.getTime())

        SaveTxt.saveTxt( strDate,list)
        list.clear()

    }
    private fun clearLogs() {
        database = Room.databaseBuilder(
            application, AppDatabase::class.java, AppDatabase.DATABASE_NAME
        ).allowMainThreadQueries().build()
        database.logsDao.deleteAll()
}
}