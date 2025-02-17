package com.andresual.nexmedisassesment.util

import android.graphics.Color

fun Int.isDarkColor(): Boolean {
    val darkness = 1 - (0.299 * Color.red(this) + 0.587 * Color.green(this) + 0.114 * Color.blue(this)) / 255
    return darkness >= 0.5
}

fun Int.setTintColor(reverse: Boolean = false): Int = if (this.isDarkColor() xor reverse) Color.WHITE else Color.BLACK