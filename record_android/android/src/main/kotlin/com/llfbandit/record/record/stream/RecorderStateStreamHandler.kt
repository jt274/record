package com.llfbandit.record.record.stream

import android.os.Handler
import android.os.Looper
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.EventChannel.EventSink

class RecorderStateStreamHandler : EventChannel.StreamHandler {
    // Event producer
    private var eventSink: EventSink? = null

    private val uiThreadHandler = Handler(Looper.getMainLooper())

    override fun onListen(arguments: Any?, events: EventSink?) {
        this.eventSink = events
    }

    override fun onCancel(arguments: Any?) {
        eventSink = null
    }

    fun sendStateEvent(state: Int) {
        uiThreadHandler.post {
            eventSink?.success(state)
        }
    }

    fun sendStateErrorEvent(ex: Exception) {
        uiThreadHandler.post {
            eventSink?.error("-1", ex.message, ex)
        }
    }
}