package com.davidg.munro

import com.davidg.munro.data.MunroItem
import com.davidg.munro.main.Munro
import com.davidg.munro.utils.TestUtils
import com.davidg.munro.utils.isGreaterThanOrEqual
import com.davidg.munro.utils.isLessThanOrEqual
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MunroUnitTest {


    @Test
    fun check_if_gridReference_contains_numbers_and_letters() {
        val s = TestUtils.containsLettersNumbers("GYG3222")
        assertEquals(s, true)
    }

    @Test
    fun check_if_gridReference_not_contains_numbers_and_letters() {
        val s = TestUtils.containsLettersNumbers("GYG")
        assertEquals(s, false)
    }

    @Test
    fun test_isAlphabetical_utils_method() {
        //Test Non Ordered
        val nonOrdered: ArrayList<String> = ArrayList()
        nonOrdered.add("Z")
        nonOrdered.add("D")
        nonOrdered.add("Y")
        nonOrdered.add("H")

        assertEquals(TestUtils.isAlphabetical(nonOrdered), false)

        //Test  Ordered
        val ordered: ArrayList<String> = ArrayList()
        nonOrdered.add("A")
        nonOrdered.add("B")
        nonOrdered.add("C")
        nonOrdered.add("D")

        assertEquals(TestUtils.isAlphabetical(ordered), true)
    }

    @Test
    fun check_if_names_are_in_sorted_order_ascending() {
        val mylist: ArrayList<String> = ArrayList()
        mylist.add("Aaa")
        mylist.add("bb")
        mylist.add("cc")
        mylist.add("dd")
        val item = mylist.sortedWith(
            compareBy(String.CASE_INSENSITIVE_ORDER,
                { it })
        )
        assertEquals(TestUtils.isAlphabetical(item), true)
    }


    @Test
    fun check_if_names_are_in_sorted_order_desc() {
        val munroItems: ArrayList<String> = ArrayList()
        munroItems.add("Aaa")
        munroItems.add("cc")
        munroItems.add("dd")
        munroItems.add("bb")
        val item = munroItems.sortedWith(
            compareBy(String.CASE_INSENSITIVE_ORDER,
                { it })
        )
        val reversedItems = item.reversed()
        assertEquals(TestUtils.isAlphabetical(reversedItems), false)
    }

    @Test
    fun check_limit_count_of_top_2_munros() {
        val munroItems: ArrayList<MunroItem> = ArrayList()
        munroItems.add(
            MunroItem(
                "David",
                46637.0, "GYD37373", "TOP"
            )
        )
        munroItems.add(
            MunroItem(
                "James",
                500.0, "GYD35373", "MUN"
            )
        )
        munroItems.add(
            MunroItem(
                "David",
                46.0, "GYD37373", "TOP"
            )
        )
        munroItems.add(
            MunroItem(
                "Peter",
                5577.0, "GYD37373", "TOP"
            )
        )
        munroItems.add(
            MunroItem(
                "Peter",
                233.0, "GYD37370", "TOP"
            )
        )

        val list = munroItems.sortedByDescending { it.heightInMeters }
        val limitedList = list.take(2)

        assertEquals(limitedList.count(), 2)

        assertEquals(list[0].heightInMeters, 46637.0)

        assertEquals(list[1].heightInMeters, 5577.0)
    }

}