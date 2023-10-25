package com.argahutama.compose.common.theme.util

import java.text.SimpleDateFormat
import java.util.Locale

fun String.formatDate(
    from: String,
    to: String,
) = try {
    val parsedDate = SimpleDateFormat(from, Locale.getDefault()).parse(this)
    SimpleDateFormat(to, Locale.getDefault()).format(parsedDate!!)
} catch (e: Exception) {
    ""
}