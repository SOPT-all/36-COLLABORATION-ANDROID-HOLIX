package org.sopt.holix.presentation.home

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.persistentListOf
import org.sopt.holix.presentation.home.dummyData.dummyStudyList1
import org.sopt.holix.presentation.home.dummyData.dummyStudyList2
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    private val _uiState = mutableStateOf(HomeUiState())
    val uiState: State<HomeUiState> = _uiState

    init {
        loadDummyData()
    }

    private fun loadDummyData() {
        _uiState.value = _uiState.value.copy(
            sections = persistentListOf(
                dummyStudyList1,
                dummyStudyList2
            )
        )
    }

    fun onTabSelected(index: Int) {
        _uiState.value = _uiState.value.copy(selectedTab = index)
    }

    fun onSearchChanged(newValue: String) {
        _uiState.value = _uiState.value.copy(search = newValue)
    }
}