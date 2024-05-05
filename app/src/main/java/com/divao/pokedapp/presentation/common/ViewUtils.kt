package com.divao.pokedapp.presentation.common

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

fun hideKeyboardIfNecessaryOnMotionEvent(activity: Activity, currentlyFocusedView: View?, motionEvent: MotionEvent) {
    if (motionEvent.action == MotionEvent.ACTION_DOWN) {
        val view = currentlyFocusedView
        if (view != null && view is EditText) {
            val r = Rect()
            view.getGlobalVisibleRect(r)
            val rawX = motionEvent.rawX.toInt()
            val rawY = motionEvent.rawY.toInt()
            if (!r.contains(rawX, rawY)) {
                view.clearFocus()
                (activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(currentlyFocusedView.windowToken, 0)
            }
        }
    }
}