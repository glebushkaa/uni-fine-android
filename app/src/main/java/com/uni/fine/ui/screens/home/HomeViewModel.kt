package com.uni.fine.ui.screens.home

import com.uni.fine.domain.SessionState
import com.uni.fine.domain.repository.CheckRepository
import com.uni.fine.ui.core.StateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val checkRepository: CheckRepository,
    private val sessionState: SessionState
) : StateViewModel<HomeState>(HomeState()) {

    private val _sideEffect = MutableSharedFlow<HomeSideEffect>(extraBufferCapacity = 1)
    val sideEffect = _sideEffect.asSharedFlow()

    init {
        collectChecks()
        loadChecks()
    }

    fun sendAction(action: HomeAction) {
        when (action) {
            HomeAction.CreateCheckClicked -> _sideEffect.tryEmit(HomeSideEffect.CreateCheck)
            HomeAction.LogOutClicked -> logOut()
        }
    }

    private fun logOut() {
        launch { sessionState.logOut() }
    }

    private fun collectChecks() {
        launch {
            checkRepository.getChecks().collect { result ->
                mutableState.update {
                    it.copy(checks = result.toImmutableList())
                }
            }
        }
    }

    private fun loadChecks() {
        launch {
            checkRepository.requestChecksUpdate()
        }
    }
}