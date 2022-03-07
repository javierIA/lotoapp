package com.example.lotoapp.ui

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lotoapp.InterferenceOB
import com.example.lotoapp.R
import com.google.zxing.integration.android.IntentIntegrator

class QRscanner : AppCompatActivity() {
    private lateinit var btnqr: Button
    private lateinit var id: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val actionBar = supportActionBar
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        // showing the back button in action bar
        if (actionBar != null) {
            actionBar.displayOptions = androidx.appcompat.app.ActionBar.DISPLAY_SHOW_CUSTOM
            actionBar.setCustomView(R.layout.toolbar)

        }
        setContentView(R.layout.activity_qrscanner)
        btnqr = findViewById(R.id.qrbtn)
        btnqr.setOnClickListener {
            initScanner()
        }
        val extras = intent.extras

        if (extras != null) {
            id = extras.getString("id").toString()
        }
    }

    private fun initScanner() {
        val integrator = IntentIntegrator(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        integrator.setPrompt("Scan ticket of workstation")
        integrator.setBeepEnabled(true)
        integrator.initiateScan()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "Cancelado", Toast.LENGTH_LONG).show()
            } else {
                val intent = Intent(this, InterferenceOB::class.java)
                intent.putExtra("id", id)
                intent.putExtra("name", result.contents)
                intent.putExtra("number", 3)
                startActivity(intent)
                this.finish()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}