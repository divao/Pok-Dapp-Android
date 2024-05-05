package com.divao.pokedapp.presentation.common

import com.divao.pokedapp.presentation.common.navigation.MainContentScreen
import com.divao.pokedapp.presentation.common.navigation.Screen

class MainActivity : EntryPointActivity() {
    override val initialScreen: Screen = MainContentScreen()
}