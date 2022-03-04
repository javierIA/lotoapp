package com.example.lotoapp

import android.Manifest
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.loto.ManagePermissions
import com.example.lotoapp.R.*
import com.example.lotoapp.activitys.Clockcheck
import com.example.lotoapp.db.AppDatabase
import kotlinx.android.synthetic.main.activity_main.*
import com.example.lotoapp.activitys.Logs
class MainActivity : AppCompatActivity() {
    private val permissionsRequestCode = 123
    private lateinit var managePermissions: ManagePermissions
    private lateinit var btn: Button
    private lateinit var database: AppDatabase
    private lateinit var img: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)


        val list = listOf<String>(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        // Initialize a new instance of ManagePermissions class
        managePermissions = ManagePermissions(this, list, permissionsRequestCode)
        managePermissions.checkPermissions()
        val actionBar = supportActionBar

        // showing the back button in action bar
        if (actionBar != null) {
            actionBar.displayOptions = androidx.appcompat.app.ActionBar.DISPLAY_SHOW_CUSTOM
            actionBar.setCustomView(layout.toolbar)

        }
        img = findViewById(id.logo)
        btn = findViewById(id.mainNextButton)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        btn.setOnClickListener {
            val intent = Intent(this, Clockcheck::class.java)
            startActivity(intent)
        }
        logo.setOnLongClickListener {
            Toast.makeText(this, "Приложение разработано в рамках курса по программированию на Яндекс.Деньги", Toast.LENGTH_LONG).show()
            val intent = Intent(this, Logs::class.java)
            startActivity(intent)
            true
        }
        database = Room.databaseBuilder(
            application, AppDatabase::class.java, AppDatabase.DATABASE_NAME
        )
            .allowMainThreadQueries()
            .build()



        val titles = database.logsDao.getAll()
        titles.forEach { title ->
            print("${title.id}\n")

        }

    }

}

