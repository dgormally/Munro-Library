package com.davidg.munro.utils

import androidx.annotation.VisibleForTesting
import java.lang.Double.parseDouble

class TestUtils {
    

    companion object {

        var numRegex = ".*[0-9].*"
        var alphaRegex = ".*[A-Z].*"

        fun isNumeric(s: String) : Boolean{
            var numeric = true
            try {
                parseDouble(s)
            } catch (e: NumberFormatException) {
                numeric = false
            }
                return numeric
        }


        fun containsLettersNumbers(s: String): Boolean {
            return s.matches(numRegex.toRegex()) && s.matches(alphaRegex.toRegex())
        }


        fun isAlphabetical(stringsArray: List<String>): Boolean {
            if (stringsArray.size < 2) return true
            return (1 until stringsArray.size).none { stringsArray[it] <= stringsArray[it - 1] }
        }
    }
}