package com.example.lotoapp.activitys

import android.R.attr.data
import android.R.attr.path
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.example.lotoapp.InterferenceOB
import com.example.lotoapp.MainActivity
import com.example.lotoapp.R
import java.io.IOException
import java.io.OutputStreamWriter


class Results : AppCompatActivity() {
    private lateinit var btn : Button
    private lateinit var animation: LottieAnimationView
    private lateinit var txt1: TextView
    private lateinit var txt2: TextView


    @SuppressLint("SetTextI18n", "SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
            actionBar.setCustomView(R.layout.toolbar)

        }
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        btn = findViewById(R.id.backresults)
        animation= findViewById(R.id.resultsanim)
        animation.scale = 2.5f
        txt1= findViewById(R.id.txterror)
        txt2= findViewById(R.id.txterror2)
        val extras = intent.extras
        if (extras != null) {
            val result = extras.getString("detection")
            val id = extras.getString("id")
            val name = extras.getString("name")
            var number = extras.getInt("number")

            if (number > 1) {
                number -=1
                btn.text = "Finish $number"
                val intent = Intent(this, InterferenceOB::class.java)
                intent.putExtra("number", number)
                intent.putExtra("id", id)
                intent.putExtra("name", name)
                btn.setOnClickListener() {
                    startActivity(intent)
                    this.finish()
                }
                saveFile(id+".txt", CreateStringTosave(result.toString(), id.toString(), name.toString()))
                Toast.makeText(this, "Saved whit yout ${id.toString()}", Toast.LENGTH_SHORT).show()

            }
            else{
                btn.text = "Finalizar"

                btn.setOnClickListener{
                   val intent = Intent(this, MainActivity::class.java)
                   startActivity(intent)
                   this.finish()
               }
            }
            checkStatus()
        }
        else{
            Toast.makeText(this, "No hay resultados", Toast.LENGTH_LONG).show()
            btn.text = getString(R.string.back)
            btn.setOnClickListener {
                this.finish()
            }
        }

    }

    private fun CreateStringTosave(result: String,id :String,number:String): String {
        val timeStamp = System.currentTimeMillis()
        val string = "Resultado: $result"+"ID: $id"+"Número: $number"+"Fecha: $timeStamp"
        return string
    }

    private fun checkStatus() {

            animation.setAnimation(R.raw.success)
            txt1.text = getString(R.string.success)
            txt2.text = getString(R.string.sure)


    }
    private fun saveFile(content: String, filepath: String) {
        try {
            val outputStreamWriter =
                OutputStreamWriter(this.openFileOutput(filepath, Context.MODE_PRIVATE))
            outputStreamWriter.write(data)
            outputStreamWriter.close()
        } catch (e: IOException) {
            Log.e("Exception", "File write failed: $e")
        }
    }

}