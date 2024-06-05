package com.confradestech.aetna_cvs_code_challenge.commom.utils.extensions

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun String.toUserFriendlyDate(): String {
    val inputFormatter = DateTimeFormatter.ISO_ZONED_DATE_TIME
    val zonedDateTime = ZonedDateTime.parse(this, inputFormatter)
    val outputFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy h:mm a", Locale.US)
    return zonedDateTime.format(outputFormatter)
}