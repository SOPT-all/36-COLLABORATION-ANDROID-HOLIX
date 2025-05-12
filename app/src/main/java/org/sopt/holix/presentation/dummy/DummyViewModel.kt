package org.sopt.holix.presentation.dummy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.holix.core.util.UiState
import org.sopt.holix.core.util.handleError
import org.sopt.holix.domain.repository.DummyRepository
import javax.inject.Inject

@HiltViewModel
class DummyViewModel @Inject constructor(
    private val dummyRepository: DummyRepository
): ViewModel() {
    private val _state = MutableStateFlow(DummyState())
    val state: StateFlow<DummyState>
        get() = _state.asStateFlow()

    private val _sideEffect = MutableSharedFlow<DummySideEffect>()
    val sideEffect: MutableSharedFlow<DummySideEffect>
        get() = _sideEffect

    fun getDummyList() = viewModelScope.launch {
        dummyRepository.getDummyList()
            .onSuccess {
                _state.value = _state.value.copy(
                    uiState = UiState.Success(it.toPersistentList())
                )
            }.onFailure { throwable ->
                val errorMessage = handleError(throwable)
                _state.value = _state.value.copy(
                    uiState = UiState.Failure(errorMessage)
                )
                showSnackBar(errorMessage)
            }
    }

    fun showSnackBar(message: String) = viewModelScope.launch {
        _sideEffect.emit(DummySideEffect.ShowSnackBar(message))
    }

    fun navigateUp() = viewModelScope.launch {
        _sideEffect.emit(DummySideEffect.NavigateUp)
    }

    fun navigateNext() = viewModelScope.launch {
        _sideEffect.emit(DummySideEffect.NavigateNext)
    }

}