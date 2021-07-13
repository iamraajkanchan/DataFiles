package com.example.datafilesexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private var FILE_NAME: String = "example.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bt_save.setOnClickListener {
            var text = ed_textContainer.text.toString()
            var fos: FileOutputStream? = null
            try {
                fos = openFileOutput(FILE_NAME, MODE_APPEND)
                fos!!.write(text.toByteArray())
                fos.write("\n".toByteArray())
                ed_textContainer.setText("")
                Toast.makeText(
                    this, "Saved to $filesDir/$FILE_NAME", Toast.LENGTH_SHORT
                ).show()
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                if (fos != null) {
                    fos?.close()
                }
            }
        }

        bt_load.setOnClickListener {
            var fis: FileInputStream? = null
            try {
                fis = openFileInput(FILE_NAME)
                val isr = InputStreamReader(fis)
                val br = BufferedReader(isr)
                val sb = StringBuilder()
                val lineList = mutableListOf<String>()
                br.useLines { lines -> lines.forEach { lineList.add(it) } }
                lineList.forEach { sb.append(it).append("\n") }
                ed_textContainer.setText(sb.toString())
            } catch (e: Exception) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                fis?.close()
            }
        }
    }
}