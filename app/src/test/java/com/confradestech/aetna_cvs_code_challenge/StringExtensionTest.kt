package com.confradestech.aetna_cvs_code_challenge

import com.confradestech.aetna_cvs_code_challenge.commom.utils.extensions.toUserFriendlyDate
import org.junit.Assert.assertEquals
import org.junit.Test

class StringExtensionTest {

    @Test
    fun testToUserFriendlyDate() {
        val isoDateString = "2024-05-30T22:05:40Z"
        val expectedDateString = "May 30, 2024 10:05 PM"

        val result = isoDateString.toUserFriendlyDate()
        assertEquals(expectedDateString, result)
    }

    @Test
    fun testToUserFriendlyDate_withNoTime() {
        val isoDateString = "2024-05-30T00:00:00Z"
        val expectedDateString = "May 30, 2024 12:00 AM"

        val result = isoDateString.toUserFriendlyDate()
        assertEquals(expectedDateString, result)
    }

    @Test
    fun testToUserFriendlyDate_withDifferentDate() {
        val isoDateString = "2023-12-25T13:45:30Z"
        val expectedDateString = "Dec 25, 2023 1:45 PM"

        val result = isoDateString.toUserFriendlyDate()
        assertEquals(expectedDateString, result)
    }
}