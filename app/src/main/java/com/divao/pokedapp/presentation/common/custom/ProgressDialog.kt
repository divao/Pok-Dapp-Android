package com.divao.pokedapp.presentation.common.custom

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import com.divao.pokedapp.R
import io.reactivex.subjects.PublishSubject

class ProgressDialog(activity: Activity) : ImmersiveDialog(activity) {

    var onBackButtonPressedProgressDialog: PublishSubject<Unit> = PublishSubject.create()

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setCanceledOnTouchOutside(false)
        setContentView(R.layout.dialog_progress)
        setCancelable(false)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        onBackButtonPressedProgressDialog.onNext(Unit)
    }

    override fun show() {
        if (!isShowing)
            super.show()
    }
}