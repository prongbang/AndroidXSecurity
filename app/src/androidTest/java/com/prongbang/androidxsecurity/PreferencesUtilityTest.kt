package com.prongbang.androidxsecurity

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PreferencesUtilityTest {

	private val context by lazy { InstrumentationRegistry.getInstrumentation().targetContext }
	private val preferencesUtility by lazy { PreferencesUtility(context) }

	@Test
	fun setAuthor_ShouldGetAndDecrypt_WhenEncryptData() {
		val expected = "prongbang"
		preferencesUtility.set(mapOf("author" to "prongbang"))

		val actual = preferencesUtility.get("author")

		assertEquals(expected, actual)
	}

}