package com.divao.pokedapp.presentation.common

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.divao.pokedapp.R
import com.divao.pokedapp.common.DisposableHolder
import com.divao.pokedapp.common.DisposableHolderDelegate
import com.divao.pokedapp.presentation.common.custom.ProgressDialog
import com.divao.pokedapp.presentation.common.navigation.FlowContainerFragment
import com.jakewharton.rxbinding2.support.v7.widget.navigationClicks
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.PublishSubject

abstract class SceneFragment : Fragment(), SceneView, ExitHandler, DisposableHolder by DisposableHolderDelegate() {
    private val progressDialog: ProgressDialog by lazy {
        val dialog = ProgressDialog(activity as FragmentActivity)
        dialog.onBackButtonPressedProgressDialog.subscribe { onBackPressed() }.addTo(disposables)
        return@lazy dialog
    }

    val parentFlowContainerFragment: FlowContainerFragment?
        get() = parentFragment as? FlowContainerFragment

    val activity: EntryPointActivity?
        get() = getActivity() as? EntryPointActivity

    override val onViewCreated: PublishSubject<Unit> = PublishSubject.create<Unit>()
    final override val onViewLoaded: PublishSubject<Unit> = PublishSubject.create<Unit>()
    final override val onViewResumed: PublishSubject<Unit> = PublishSubject.create<Unit>()
    override val onViewPaused: PublishSubject<Unit> = PublishSubject.create<Unit>()
    override val onViewDestroyed: PublishSubject<Unit> = PublishSubject.create<Unit>()

    private val genericAlertDialog: AlertDialog by lazy { AlertDialog.Builder(context).setPositiveButton(context?.getString(R.string.ok)) { dialog, _ -> dialog.dismiss() }.create() }

    init {
        onViewResumed.take(1).subscribe(onViewLoaded)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewCreated.onNext(Unit)
    }

    override fun onResume() {
        super.onResume()
        onViewResumed.onNext(Unit)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            onViewResumed.onNext(Unit)
        } else {
            onViewPaused.onNext(Unit)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        dismissLoading()
        onViewDestroyed.onNext(Unit)
        disposeAll()
    }

    override fun displayLoading() {
        if (isAdded) {
            progressDialog.show()
        }
    }

    override fun dismissLoading() {
        progressDialog.dismiss()
    }

    fun showAlertDialogWithMessage(message: String) {
        genericAlertDialog.setMessage(message)
        genericAlertDialog.show()
    }

    override fun displayBlockingGenericError() {
        showAlertDialogWithMessage(getString(R.string.generic_error_message))
    }

    override fun displayNonBlockingGenericError() {
        showAlertDialogWithMessage(getString(R.string.generic_error_message))
    }

    override fun displayBlockingNoInternetError() {
        showAlertDialogWithMessage(getString(R.string.no_internet_error_message))
    }

    override fun displayNonBlockingNoInternetError() {
        showAlertDialogWithMessage(getString(R.string.no_internet_error_message))
    }

    override fun onBackPressed() {
        if (parentFlowContainerFragment != null) {
            parentFlowContainerFragment?.popScreen()
        } else {
            activity?.popScreen()
        }
    }

    fun setupToolbar(toolbar: Toolbar, isModal: Boolean = false, showHomeAsUpIndicator: Boolean = true) {
        toolbar.navigationClicks().subscribe { onBackPressed() }.addTo(disposables)
        if (showHomeAsUpIndicator) {
            toolbar.navigationIcon = if (!isModal)
                context?.let { ContextCompat.getDrawable(it, R.drawable.ic_back) }
            else
                context?.let {
                    ContextCompat.getDrawable(it, R.drawable.ic_close)
                }
        }
    }
}