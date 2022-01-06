package com.davidg.munro.main

import android.content.Context
import com.davidg.munro.data.MunroItemDAO
import com.davidg.munro.data.MunroItem
import com.davidg.munro.data.SortDirection
import com.davidg.munro.utils.TestUtils
import com.davidg.munro.utils.isGreaterThanOrEqual
import com.davidg.munro.utils.isLessThanOrEqual
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.RuntimeException

class Munro {

    companion object {

        private var munroItems = MunroItemDAO.munroItemsList

        /*  Opens CSV file and loads the data */

        @JvmStatic
        fun openCsvFile(context: Context, fileName: String) {
            MunroItemDAO.resetData()
            try {
                val minput = InputStreamReader(context.assets.open(fileName))
                val reader = BufferedReader(minput)

                var line: String?

                while (reader.readLine().also { line = it } != null) {
                    val row: List<String> = line!!.split(",")
                    val item = MunroItem();
                    item.name = row[6]
                    if (TestUtils.isNumeric(row[10])) {
                        item.heightInMeters = row[10].toDouble().also { item.heightInMeters = it }
                    }
                    item.gridReference = row[14]
                    item.hillCategory = row[28]
                    MunroItemDAO.addMunroItem(item)
                }
                MunroItemDAO.removeUnwantedItems()
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
        }

        /* Returns all munro items in the list */

        @JvmStatic
        fun getAllMunros(): List<MunroItem> {
            checkInitialization()
            return munroItems;
        }

        /* Returns munros by name */

        @JvmStatic
        fun munrosByName(name: String): List<MunroItem> {
            checkInitialization()
            return munroItems.filter { it.name.equals(name) }
        }


        /* Returns all munros by category search and Top by default */

        @JvmStatic
        fun munrosByHillCategory(category: String): List<MunroItem> {
            checkInitialization()
            val munrosByCategory = munroItems.filter { it.hillCategory.equals(category) }
            if (munrosByCategory.isEmpty().not()) {
                return munrosByCategory
            }
            return munroItems.filter { it.hillCategory.equals("TOP") }
        }

        /* Returns all munros by name in alphabetical order either by desc or asc */

        @JvmStatic
        fun munrosSortedByName(sortDirection: SortDirection): List<MunroItem> {
            checkInitialization()
            val itemsByName = munroItems.sortedWith(
                compareBy(String.CASE_INSENSITIVE_ORDER,
                    { it.name.toString() })
            )

            if (sortDirection == SortDirection.Ascending) {
                return itemsByName
            }
            return itemsByName.reversed()
        }

        /* Returns all munros by height in alphabetical order either by desc or asc */

        @JvmStatic
        fun munrosSortByHeight(sortDirection: SortDirection): List<MunroItem> {
            checkInitialization()
            if (sortDirection == SortDirection.Descending) {
                return munroItems.sortedByDescending { it.heightInMeters }
            }
            return munroItems.sortedByDescending { it.heightInMeters }.reversed()
        }


        /* Returns the top munros. For example user can request the top 10 */

        @JvmStatic
        fun munrosHighest(limit: Int): List<MunroItem> {
            checkInitialization()
            try {
                if (limit > munroItems.count()) {
                    throw RuntimeException("\"Munro\", \"Limit should not exceed the count size: ${munroItems.size}\"")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            val munroItems = munroItems.sortedByDescending { it.heightInMeters }
            return munroItems.take(limit)
        }


        /* Returns all munros by grid reference */

        @JvmStatic
        fun munrosByGridReference(gridReference: String): List<MunroItem> {
            checkInitialization()
            return munroItems.filter { it.gridReference.equals(gridReference) }
        }

        /* Returns all munros by max height */

        @JvmStatic
        fun munrosMaxHeight(maxHeight: Int): List<MunroItem> {
            checkInitialization()
            return munroItems.filter { it.heightInMeters isLessThanOrEqual (maxHeight.toDouble()) == true }
        }

        /* Returns all munros by Min height */

        @JvmStatic
        fun munrosMinHeight(maxHeight: Int): List<MunroItem> {
            checkInitialization()
            return munroItems.filter { it.heightInMeters isGreaterThanOrEqual (maxHeight.toDouble()) == true }
        }


        /* Returns all munros between a minimum and maximum height */

        @JvmStatic
        fun munrosBetweenHeights(minHeight: Int, maxHeight: Int): List<MunroItem> {
            checkInitialization()
            try {
                if (maxHeight < minHeight) {
                    throw RuntimeException("MunroApplication Max height must be greater than min height")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return munroItems.filter {
                it.heightInMeters isGreaterThanOrEqual (minHeight.toDouble()) == true
                        && it.heightInMeters isLessThanOrEqual (maxHeight.toDouble()) == true
            }
        }

        /* Returns all munros between a minimum and maximum height with option to order by DESC or ASC */

        @JvmStatic
        fun munrosBetweenHeights(
            minHeight: Int,
            maxHeight: Int,
            sortDirection: SortDirection
        ): List<MunroItem> {
            checkInitialization()
            try {
                if (maxHeight < minHeight) {
                    throw RuntimeException("MunroApplication Max height must be greater than min height")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            val items = munroItems.filter {
                it.heightInMeters isGreaterThanOrEqual (minHeight.toDouble()) == true
                        && it.heightInMeters isLessThanOrEqual (maxHeight.toDouble()) == true
            }

            if (sortDirection == SortDirection.Descending) {
                return items.sortedByDescending { it.heightInMeters }
            }

            return items.sortedBy { it.heightInMeters }
        }


        /* Returns all munros by Height with the option to sort by name alphabetically if heights are the same */

        @JvmStatic
        fun munrosSortByHeight(
            sortByNameDesc: Boolean
        ): List<MunroItem> {
            checkInitialization()
            if (sortByNameDesc) {
                return munroItems
                    .sortedWith(compareByDescending<MunroItem> { it.heightInMeters }
                        .thenByDescending { it.name })
            }
            return munroItems.sortedByDescending { it.heightInMeters }
        }


        private fun checkInitialization() {
            if (munroItems.isEmpty()) {
                try {
                    throw RuntimeException("Munro: Not initialized")
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}