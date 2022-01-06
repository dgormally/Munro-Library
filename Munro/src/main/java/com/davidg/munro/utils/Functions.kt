package com.davidg.munro.utils


infix fun <T : Comparable<T>> T?.isGreaterThanOrEqual(other: T?): Boolean? =
    if (this != null && other != null) this >= other else null

infix fun <T : Comparable<T>> T?.isLessThanOrEqual(other: T?): Boolean? =
    if (this != null && other != null) this <= other else null