package com.uni.fine.ui.screens.auth

sealed interface AuthSideEffect {
    data object Back : AuthSideEffect
    data object Home: AuthSideEffect
}