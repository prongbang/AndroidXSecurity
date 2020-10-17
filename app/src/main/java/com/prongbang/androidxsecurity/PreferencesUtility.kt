package com.prongbang.androidxsecurity

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class PreferencesUtility(context: Context) {
	private val sharedPrefsFile = "app_info_prefs"
	private val keyAlias = MasterKey.Builder(context)
			.setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
			.build()
	private val sharedPreferences: SharedPreferences = EncryptedSharedPreferences.create(
			context,
			sharedPrefsFile,
			keyAlias,
			EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
			EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
	)

	fun set(map: Map<String, Any>) {
		with(sharedPreferences.edit()) {
			for (m in map) {
				putString(m.key, "${m.value}")
			}
			apply()
		}
	}

	fun get(key: String): String = sharedPreferences.getString(key, "") ?: ""
}