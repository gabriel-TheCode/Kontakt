package com.gabrielthecode.kontakt.application

import android.app.Application
import android.content.Context
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ProcessLifecycleOwner
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class KontaktApp : Application(), LifecycleObserver {
	private lateinit var mContext: Context
	override fun onCreate() {
		super.onCreate()
		mContext = applicationContext

		ProcessLifecycleOwner.get().lifecycle.addObserver(this)
	}
}
