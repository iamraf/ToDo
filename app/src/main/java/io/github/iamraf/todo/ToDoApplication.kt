package io.github.iamraf.todo

import android.annotation.SuppressLint
import android.app.Application
import androidx.fragment.app.FragmentManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ToDoApplication : Application() {
    @SuppressLint("UnsafeExperimentalUsageWarning")
    override fun onCreate() {
        super.onCreate()
        FragmentManager.enableNewStateManager(false)
    }
}