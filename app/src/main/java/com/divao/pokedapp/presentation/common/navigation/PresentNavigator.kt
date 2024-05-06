package com.divao.pokedapp.presentation.common.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.divao.pokedapp.R
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward
import ru.terrakok.cicerone.commands.Replace

class PresentNavigator constructor(fragmentActivity: FragmentActivity, private val fm: FragmentManager, private val containerId: Int) : SupportAppNavigator(fragmentActivity, fm, containerId) {

    override fun setupFragmentTransaction(command: Command?, currentFragment: Fragment?, nextFragment: Fragment?, fragmentTransaction: FragmentTransaction?) {
        if (currentFragment != null) {
            fragmentTransaction?.setCustomAnimations(
                R.anim.slide_in_up,
                R.anim.slide_out_up,
                R.anim.slide_out_down,
                R.anim.slide_in_down
            )
        }
    }

    override fun fragmentReplace(command: Replace) {
        (command.screen as? SupportAppScreen)?.let { showFragmentHidingOthers(false, command, it) }
    }

    override fun fragmentForward(command: Forward) {
        (command.screen as? SupportAppScreen)?.let { showFragmentHidingOthers(true, command, it) }
    }

    private fun showFragmentHidingOthers(shouldAddToBackStack: Boolean, command: Command, screen: SupportAppScreen) {
        val fragment = createFragment(screen)

        with(fm.beginSetupTransaction(command, fragment)) {
            fm.fragments.filter { it != fragment }.forEach {
                hide(it)
            }

            fragment.let {
                if (it.isAdded) {
                    show(it)
                } else add(containerId, it, screen.screenKey)
            }

            if (shouldAddToBackStack) {
                addToBackStack(screen.screenKey)
            }
            commit()
        }
    }

    private fun FragmentManager.beginSetupTransaction(command: Command, fragment: Fragment): FragmentTransaction {
        val fragmentTransaction = this.beginTransaction()

        setupFragmentTransaction(
            command,
            fm.findFragmentById(containerId),
            fragment,
            fragmentTransaction
        )

        return fragmentTransaction
    }
}
