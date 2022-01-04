package com.davidg.munro

import com.davidg.munro.utils.NumberUtils
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MunroUnitTest {

    @Test
    fun check_if_gridReference_contains_numbers_and_letters(){
        val s = NumberUtils.containsLettersNumbers("GYG3222")
        assertEquals(s, true)
    }

    @Test
    fun check_if_gridReference_not_contains_numbers_and_letters(){
        val s = NumberUtils.containsLettersNumbers("GYG")
        assertEquals(s, false)
    }
}