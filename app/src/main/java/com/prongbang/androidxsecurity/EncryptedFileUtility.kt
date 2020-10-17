package com.prongbang.androidxsecurity

import android.content.Context
import androidx.security.crypto.EncryptedFile
import androidx.security.crypto.MasterKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.ByteArrayOutputStream
import java.io.File
import java.nio.charset.StandardCharsets

class EncryptedFileUtility(context: Context) {
	private val sharedPrefsFile = "app_info_prefs"
	private val keyAlias = MasterKey.Builder(context)
			.setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
			.build()
	private val file = File(context.filesDir, "confidential_info.txt")
	private val encryptedFile: EncryptedFile = EncryptedFile.Builder(
			context,
			file,
			keyAlias,
			EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
	).build()

	fun set(map: Map<String, Any>) {
		val json = Gson().toJson(map)
		val fileContent = json.toByteArray(StandardCharsets.UTF_8)
		encryptedFile.openFileOutput()
				.apply {
					write(fileContent)
					flush()
					close()
				}
	}

	fun get(key: String): String {
		val inputStream = encryptedFile.openFileInput()
		val byteArrayOutputStream = ByteArrayOutputStream()
		var nextByte: Int = inputStream.read()
		while (nextByte != -1) {
			byteArrayOutputStream.write(nextByte)
			nextByte = inputStream.read()
		}
		val plaintext: ByteArray = byteArrayOutputStream.toByteArray()
		val type = object : TypeToken<Map<String, String>>() {}.type
		val map: Map<String, String> = Gson().fromJson(String(plaintext, Charsets.UTF_8), type)
		return map[key] ?: ""
	}
}