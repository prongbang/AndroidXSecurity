package com.prongbang.androidxsecurity

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EncryptedFileUtilityTest {

	private val context by lazy { InstrumentationRegistry.getInstrumentation().targetContext }
	private val encryptedFileUtility by lazy { EncryptedFileUtility(context) }

	@Test
	fun setAuthor_ShouldGetAndDecrypt_WhenEncryptData() {
		val expected = "prongbang"
		encryptedFileUtility.set(mapOf("author" to "prongbang"))

		val actual = encryptedFileUtility.get("author")

		assertEquals(expected, actual)
	}

}