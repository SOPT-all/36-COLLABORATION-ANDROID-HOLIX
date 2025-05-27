package org.sopt.holix.presentation.club_detail_home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.holix.core.util.UiState
import org.sopt.holix.core.util.handleError
import org.sopt.holix.domain.repository.ClubRepository
import org.sopt.holix.domain.repository.DummyRepository
import javax.inject.Inject

@HiltViewModel
class ClubDetailHomeViewModel @Inject constructor(
    private val clubRepository: ClubRepository
): ViewModel() {
    private val _state = MutableStateFlow(ClubDetailHomeState())
    val state: StateFlow<ClubDetailHomeState>
        get() = _state.asStateFlow()

    private val _sideEffect = MutableSharedFlow<ClubDetailHomeSideEffect>()
    val sideEffect: SharedFlow<ClubDetailHomeSideEffect>
        get() = _sideEffect

    fun getClubDetail(clubId: Long) = viewModelScope.launch {
        clubRepository.getClubDetail(clubId)
            .onSuccess {
                _state.value = _state.value.copy(
                    uiState = UiState.Success(it)
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
        _sideEffect.emit(ClubDetailHomeSideEffect.ShowSnackBar(message))
    }

    fun navigateUp() = viewModelScope.launch {
        _sideEffect.emit(ClubDetailHomeSideEffect.NavigateUp)
    }

}