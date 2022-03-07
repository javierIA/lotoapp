package com.example.lotoapp.ui

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.airbnb.lottie.LottieAnimationView
import com.example.lotoapp.InterferenceOB
import com.example.lotoapp.MainActivity
import com.example.lotoapp.R
import com.example.lotoapp.db.AppDatabase
import com.example.lotoapp.db.Logs
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class Results : AppCompatActivity() {
    private lateinit var btn: Button
    private lateinit var animation: LottieAnimationView
    private lateinit var txt1: TextView
    private lateinit var txt2: TextView
    private lateinit var database: AppDatabase
    private val logs: ArrayList<Logs> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_results)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
            actionBar.setCustomView(R.layout.toolbar)

        }


        database = Room.databaseBuilder(
            application, AppDatabase::class.java, AppDatabase.DATABASE_NAME
        )
            .allowMainThreadQueries()
            .build()
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        btn = findViewById(R.id.backresults)
        animation = findViewById(R.id.resultsanim)
        animation.scale = 2.5f
        txt1 = findViewById(R.id.txterror)
        txt2 = findViewById(R.id.txterror2)
        val extras = intent.extras
        if (extras != null) {
            val result = extras.getString("detection")
            val id = extras.getString("id")
            val name = extras.getString("name")
            var number = extras.getInt("number")
            number--

            if (number >=1 ) {
                savedb(id.toString(), name.toString(), number.toString(), result.toString())

                btn.text = "Finish to $number"
                val intent = Intent(this, InterferenceOB::class.java)
                intent.putExtra("number", number)
                intent.putExtra("id", id)
                intent.putExtra("name", name)

                btn.setOnClickListener() {
                    this.finish()
                    startActivity(intent)
                }

            } else {
                btn.text = "Finish"

                btn.setOnClickListener {
                    val intent = Intent(this, MainActivity::class.java)
                    this.finish()
                    startActivity(intent)
                }
            }
            checkStatus()
        } else {
            Toast.makeText(this, "No hay resultados", Toast.LENGTH_LONG).show()
            btn.text = getString(R.string.back)
            btn.setOnClickListener {
                this.finish()
            }
        }

    }


    private fun checkStatus() {

        animation.setAnimation(R.raw.success)
        txt1.text = getString(R.string.success)
        txt2.text = getString(R.string.sure)


    }
    private fun savedb(id: String, name: String, number: String, result: String) {
        val c: Calendar = Calendar.getInstance()
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val strDate: String = sdf.format(c.getTime())
        database.logsDao.insert(Logs(user = id.toString(), date =strDate , detection =number.toString(), result = result.toString()))
}
}
