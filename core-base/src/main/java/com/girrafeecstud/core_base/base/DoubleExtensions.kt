/* Created by Girrafeec */

package com.girrafeecstud.core_base.base

fun Double.roundTo(numDecimalPlaces: Int): Double =
    "%.${numDecimalPlaces}f".format(this).toDouble()