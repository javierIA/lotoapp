package com.example.lotoapp.activity

import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lotoapp.R
import com.raycoarana.codeinputview.CodeInputView


class Clockcheck : AppCompatActivity() {
    private lateinit var codeInputView: CodeInputView
    private val mHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clockcheck)
        codeInputView= findViewById(R.id.codeclock)
        codeInputView.addOnCompleteListener { code ->
                mHandler.postDelayed(Runnable { //Make the input enable again so the user can change it
                codeInputView.setEditable(true)
                    if (code=="1234")
                        Toast.makeText(this, "Jalo", Toast.LENGTH_SHORT).show()
                    // TODO: Add next activity 
                    else
                        codeInputView.error = "Your code is incorrect"
            }, 1000)
        }
    }
}