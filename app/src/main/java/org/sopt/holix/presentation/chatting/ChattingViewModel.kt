package org.sopt.holix.presentation.chatting

import android.util.Log
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
import org.sopt.holix.data.dto.request.ChattingRequestDto
import org.sopt.holix.domain.repository.ChattingRepository
import javax.inject.Inject

@HiltViewModel
class ChattingViewModel @Inject constructor(
    private val chattingRepository: ChattingRepository
): ViewModel() {
    private val _state = MutableStateFlow(ChattingState())
    val state: StateFlow<ChattingState>
        get() = _state.asStateFlow()

    private val _sideEffect = MutableSharedFlow<ChattingSideEffect>()
    val sideEffect: MutableSharedFlow<ChattingSideEffect>
        get() = _sideEffect

    fun getChattingList(clubId: Long) = viewModelScope.launch {
        chattingRepository.getChattingList(clubId = clubId)
            .onSuccess {
                val chattingList = it.chattingList.map { chatting ->
                    chatting.toDomainModel()
                }
                Log.d("chattingList", chattingList.toString())
                _state.value = _state.value.copy(
                    uiState = UiState.Success(chattingList.toPersistentList())
                )
            }.onFailure { throwable ->
                val errorMessage = handleError(throwable)
                _state.value = _state.value.copy(
                    uiState = UiState.Failure(errorMessage)
                )
                Log.e("getChattingList", "getChattingList: $errorMessage")
                showSnackBar(errorMessage)
            }
    }

    fun postChatting(clubId: Long, chattingRequestDto: ChattingRequestDto) = viewModelScope.launch {
        chattingRepository.postChatting(clubId, chattingRequestDto)
            .onSuccess {
                _state.value = _state.value.copy(
                    chattingState = UiState.Success(it.message)
                )
            }
            .onFailure { throwable ->
                val errorMessage = handleError(throwable)
                _state.value = _state.value.copy(
                    chattingState = UiState.Failure(errorMessage)
                )
                Log.e("postChatting", "postChatting: $errorMessage")
                showSnackBar(errorMessage)
            }
    }

    fun fetchText(chat: String) = viewModelScope.launch {
        _state.value = _state.value.copy(
            chattingText = chat
        )
    }

    fun sendChatting() = viewModelScope.launch {
        _state.value = _state.value.copy(
            isSendChatting = !state.value.isSendChatting
        )
    }

    fun showSnackBar(message: String) = viewModelScope.launch {
        _sideEffect.emit(ChattingSideEffect.ShowSnackBar(message))
    }

    fun navigateUp() = viewModelScope.launch {
        _sideEffect.emit(ChattingSideEffect.NavigateUp)
    }

    fun navigateNext() = viewModelScope.launch {
        _sideEffect.emit(ChattingSideEffect.NavigateNext)
    }
}