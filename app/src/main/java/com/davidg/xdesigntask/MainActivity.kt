package com.davidg.xdesigntask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.davidg.munro.data.SortDirection
import com.davidg.munro.main.Munro.Companion.munrosBetweenHeights
import com.davidg.munro.main.Munro.Companion.munrosByName
import com.davidg.munro.main.Munro.Companion.munrosMinHeight
import com.davidg.munro.main.Munro.Companion.munrosSortByHeight
import com.davidg.munro.main.Munro.Companion.openCsvFile

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openCsvFile(this, "munrotab_v6.2.csv")


        val minHeightItems = munrosMinHeight(900)
        minHeightItems.forEach {it->
            Log.d("TAG", " Min heights  are: $it")
        }

        val list = munrosByName("Ben")
        list?.forEach { it ->
            Log.d("TAG", " Objects with name Ben are: $it")
        }


        val items = munrosBetweenHeights(900, 950, SortDirection.Descending)
        items.forEach {it->
            Log.d("TAG", " Objects between heights 900 and 950 are: $it")
        }
    }

}
