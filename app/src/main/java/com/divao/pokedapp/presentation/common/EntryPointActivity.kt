package com.divao.pokedapp.presentation.common

import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.divao.pokedapp.R
import com.divao.pokedapp.common.PokeDappApplication
import com.divao.pokedapp.presentation.common.navigation.PresentNavigator
import com.divao.pokedapp.presentation.common.navigation.Screen
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Inject

abstract class EntryPointActivity : AppCompatActivity() {
    val component: EntryPointComponent by lazy {
        DaggerEntryPointComponent.factory()
            .create(this, supportFragmentManager, R.id.container, (application as PokeDappApplication).component)
    }

    @Inject
    lateinit var cicerone: Cicerone<Router>

    @Inject
    lateinit var navigator: PresentNavigator

    abstract val initialScreen: Screen

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up)
        setContentView(R.layout.activity_main)
        component.inject(this)

        // Única forma de avisar um fragment que ouve alteração na Back Stack
        supportFragmentManager.addOnBackStackChangedListener {
            val backStackEntryCount = supportFragmentManager.backStackEntryCount
            supportFragmentManager.fragments[backStackEntryCount + 1].userVisibleHint = true
        }

        if (savedInstanceState == null) {
            cicerone.router.replaceScreen(initialScreen)
        }
    }

    override fun onResume() {
        super.onResume()
        cicerone.navigatorHolder.setNavigator(navigator)
    }


    override fun onPause() {
        cicerone.navigatorHolder.removeNavigator()
        super.onPause()
        if (isFinishing) {
            overridePendingTransition(R.anim.slide_out_down, R.anim.slide_in_down)
        }
    }


    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        hideKeyboardIfNecessaryOnMotionEvent(this, currentFocus, ev)
        return super.dispatchTouchEvent(ev)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val childFragment = supportFragmentManager.findFragmentById(R.id.container) as? ExitHandler
        if ((childFragment as? Fragment) == initialScreen) {
            finish()
        } else {
            childFragment?.onBackPressed()
        }
    }

    fun presentScreen(screen: Screen) {
        cicerone.router.navigateTo(screen)
    }

    fun popScreen() {
        cicerone.router.exit()
    }
}