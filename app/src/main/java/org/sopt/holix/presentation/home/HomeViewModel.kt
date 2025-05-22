package org.sopt.holix.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.holix.core.util.UiState
import org.sopt.holix.core.util.handleError
import org.sopt.holix.domain.repository.HomeRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState>
        get() = _state.asStateFlow()

    private val _sideEffect = MutableSharedFlow<HomeSideEffect>()
    val sideEffect: SharedFlow<HomeSideEffect>
        get() = _sideEffect

    fun getHomeStudyData() = viewModelScope.launch {
        runCatching { homeRepository.getHomeData() }
            .onSuccess { studyData ->
                _state.value = _state.value.copy(
                    uiState = UiState.Success(
                        persistentListOf(
                            studyData.passionateStudies,
                            studyData.insightStudies,
                            studyData.newStudies,
                            studyData.recommendedStudies,
                            studyData.freeStudies
                        )
                    )
                )
                _sideEffect.emit(HomeSideEffect.ShowSnackBar("성공..."))
            }.onFailure { throwable ->
                val errorMessage = handleError(throwable)
                _state.value = _state.value.copy(
                    uiState = UiState.Failure(errorMessage)
                )
                _sideEffect.emit(HomeSideEffect.ShowSnackBar("실패..."))
            }
    }

    fun onTabSelected(index: Int) {
        _state.value = _state.value.copy(selectedTab = index)
    }

    fun onSearchChanged(newValue: String) {
        _state.value = _state.value.copy(search = newValue)
    }


}