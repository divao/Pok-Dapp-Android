package com.divao.pokedapp.presentation.common.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.divao.pokedapp.R
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command

class PushNavigator constructor(fragmentActivity: FragmentActivity, fm: FragmentManager, containerId: Int) : SupportAppNavigator(fragmentActivity, fm, containerId) {

    override fun setupFragmentTransaction(command: Command?, currentFragment: Fragment?, nextFragment: Fragment?, fragmentTransaction: FragmentTransaction?) {
        if (currentFragment != null)
            fragmentTransaction?.setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )
    }
}
