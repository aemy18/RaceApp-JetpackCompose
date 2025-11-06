package com.practical.task.core.extension

import android.util.Log
import com.practical.task.BuildConfig

fun String.logd(tag: String = "TestLogger") {
    if (BuildConfig.DEBUG) Log.d(tag, this)
}

fun String.loge(tag: String = "TestLogger") {
    if (BuildConfig.DEBUG) Log.e(tag, this)
}

fun String.logdv(tag: String = "TestLogger") {
    if (BuildConfig.DEBUG) Log.v(tag, this)
}

fun String.logi(tag: String = "TestLogger") {
    if (BuildConfig.DEBUG) Log.i(tag, this)
}

fun String.logw(tag: String = "TestLogger") {
    if (BuildConfig.DEBUG) Log.w(tag, this)
}
