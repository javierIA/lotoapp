package com.example.lotoapp.activitys

import android.os.Bundle
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.HandlerCompat.postDelayed
import com.example.lotoapp.R
import com.raycoarana.codeinputview.CodeInputView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.os.Handler as Handler

class Clockcheck : AppCompatActivity() {
    private lateinit var codeInputView: CodeInputView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clockcheck)
        codeInputView= findViewById(R.id.codeclock)
        codeInputView.addOnCompleteListener { code ->
            Handler(Looper.getMainLooper()).postDelayed({
                codeInputView.setEditable(true)
                if(code=="1234"){
                    Toast.makeText(this,"Correcto",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this,"Incorrecto",Toast.LENGTH_SHORT).show()
                    codeInputView.error = "Your code is incorrect"

                }
            },500)
        }
    }
}