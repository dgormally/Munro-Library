package com.davidg.xdesigntask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.davidg.munro.main.Munro.Companion.munrosMinHeight
import com.davidg.munro.main.Munro.Companion.openCsvFile

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openCsvFile(this, "munrotab_v6.2.csv")
        val list = munrosMinHeight(900)
        list.forEach {
            Log.d("DAVID", "David names are ${it.name}")
        }
    }

}
