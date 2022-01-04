package com.davidg.munro.utils

import androidx.annotation.VisibleForTesting
import java.lang.Double.parseDouble

class NumberUtils {
    

    companion object {

        var numRegex = ".*[0-9].*"
        var alphaRegex = ".*[A-Z].*"

        @VisibleForTesting
        fun isNumeric(s: String) : Boolean{
            var numeric = true
            try {
                parseDouble(s)
            } catch (e: NumberFormatException) {
                numeric = false
            }
                return numeric
        }


        @VisibleForTesting
        fun containsLettersNumbers(s: String): Boolean {
            return s.matches(numRegex.toRegex()) && s.matches(alphaRegex.toRegex())
        }
    }
}