package com.example.lotoapp.helpers

import android.app.Activity
import android.content.ContentValues
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

class SaveTxt (private val activity: Activity) {
    fun saveTxt(name: String, content: MutableList<String>) {
        val filename = "$name.txt"

        //Output stream
        var fos: OutputStream? = null

        //For devices running android >= Q
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            //getting the contentResolver
            activity.contentResolver?.also { resolver ->

                //Content resolver will process the contentvalues
                val contentValues = ContentValues().apply {

                    //putting file information in content values
                    put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                    put(MediaStore.MediaColumns.MIME_TYPE, "text/plain")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOCUMENTS)
                }

                //Inserting the contentValues to contentResolver and getting the Uri
                val fileUri: Uri? =
                    resolver.insert(MediaStore.Files.getContentUri("external"), contentValues)

                //Opening an outputstream with the Uri that we got
                fos = fileUri?.let { resolver.openOutputStream(it,"wa") }
            }
        } else {

            val txtDir =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
            val txt = File(txtDir, filename)
            fos = FileOutputStream(txt)

        }
        //Writing the content to the file if exits update it

        fos?.use {
            fos!!.write(content.joinToString().toByteArray())
            fos!!.flush()

            fos!!.close()
        }

    }
}