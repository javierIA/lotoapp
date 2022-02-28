package com.example.lotoapp.activitys

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.example.lotoapp.R
import java.util.*

class Results : AppCompatActivity() {
    private lateinit var btn : Button
    private lateinit var animation: LottieAnimationView
    private lateinit var txt1: TextView
    private lateinit var txt2: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)
        val actionBar = supportActionBar
        val random = Random()
        if (actionBar != null) {
            actionBar.displayOptions = androidx.appcompat.app.ActionBar.DISPLAY_SHOW_CUSTOM
            actionBar.setCustomView(R.layout.toolbar)

        }
        animation= findViewById(R.id.resultsanim)
        txt1= findViewById(R.id.txterror)
        txt2= findViewById(R.id.txterror2)
        oncheckStatus(random.nextBoolean())

    }
    private fun oncheckStatus(status: Boolean) {
        if(status)
        {
            animation.setAnimation(R.raw.success)
            txt1.text = getString(R.string.success);
            txt2.text = getString(R.string.sure)




        }

    }
}