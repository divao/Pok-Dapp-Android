package com.divao.pokedapp.presentation.common.custom

import android.app.Activity
import android.app.Dialog
import android.view.WindowManager

open class ImmersiveDialog(private val activity: Activity) : Dialog(activity) {
    override fun show() {
        window.let {
            window?.setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)
            window?.decorView?.systemUiVisibility = activity.window.decorView.systemUiVisibility
        }

        super.show()
        window.let { window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE) }
    }
}