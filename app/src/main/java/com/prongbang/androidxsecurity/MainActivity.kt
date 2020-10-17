package com.prongbang.androidxsecurity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

	private val preferencesUtility by lazy { PreferencesUtility(this) }

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		initLoad()
	}

	private fun initLoad() {
		preferencesUtility.set(mapOf("author" to "prongbang"))
		resultText.text = preferencesUtility.get("author")
	}
}