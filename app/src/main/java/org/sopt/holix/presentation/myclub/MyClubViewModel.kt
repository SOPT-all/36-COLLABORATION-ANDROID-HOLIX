package org.sopt.holix.presentation.myclub

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.holix.R
import org.sopt.holix.core.util.UiState
import org.sopt.holix.domain.model.MyClubEntity
import org.sopt.holix.domain.model.RecommendClubEntity
import javax.inject.Inject

@HiltViewModel
class MyClubViewModel @Inject constructor(

): ViewModel() {
    private val _state = MutableStateFlow(MyClubState())
    val state: StateFlow<MyClubState>
        get() = _state.asStateFlow()

    private val _sideEffect = MutableSharedFlow<MyClubSideEffect>()
    val sideEffect: MutableSharedFlow<MyClubSideEffect>
        get() = _sideEffect

    fun getDummyMyClubList() = viewModelScope.launch {
        _state.value = _state.value.copy(
            UiState.Success(
                data = persistentListOf(
                    MyClubEntity(
                        clubId = 1,
                        title = "디자이너로서 성공하고 싶은 사람들이 모인 방",
                        participants = "멤버 130명 / 500명",
                        url = "https://picsum.photos/360",
                        isPinned = true
                    ),
                    MyClubEntity(
                        clubId = 2,
                        title = "AI 도구 활용한 영상 만들기 질문 답변방",
                        participants = "멤버 130명 / 500명",
                        url = "https://picsum.photos/360",
                        isPinned = false
                    ),
                    MyClubEntity(
                        clubId = 3,
                        title = "손글씨를 사랑하는 사람들의 모임",
                        participants = "멤버 130명 / 500명",
                        url = "https://picsum.photos/360",
                        isPinned = true
                    ),
                    MyClubEntity(
                        clubId = 4,
                        title = "UX Writing 실무 파헤치기",
                        participants = "멤버 998명 / 1,000명",
                        url = "https://picsum.photos/360",
                        isPinned = false
                    )
                )
            )
        )
    }
    fun getRecommendClubList() = viewModelScope.launch {
        _state.value = _state.value.copy(
            recommendUiState = persistentListOf(
                RecommendClubEntity(
                    title = "UI/UX 원칙&UX 분석 Case Study - 매일 업로드",
                    clubMaster = "전민수",
                    participants = "멤버 975명 / 1,000명",
                    isRecruiting = true,
                    imageRes = R.drawable.img_my_recommmend_1_and
                ),
                RecommendClubEntity(
                    title = "데일리 디자인 정보 공유 세션",
                    clubMaster = "이선주",
                    participants = "멤버 847명 / 1,500명",
                    isRecruiting = true,
                    imageRes = R.drawable.img_my_recommmend_2_and
                ),
                RecommendClubEntity(
                    title = "피그마 학습방 : 서비스 기획자와 함께해요!",
                    clubMaster = "기획자 김로리",
                    participants = "멤버 1,000명 / 1,000명",
                    isRecruiting = true,
                    imageRes = R.drawable.img_my_recommmend_3_and
                )
            )
        )
    }

    fun navigateToClubDetailHome(clubId: Long) = viewModelScope.launch {
        _sideEffect.emit(MyClubSideEffect.NavigateClubDetailHome(clubId))
    }

    fun onTabSelected(selectedTab: Int) {
        _state.value = _state.value.copy(
            selectedTab = selectedTab
        )
    }

    fun showSnackBar(message: String) = viewModelScope.launch {
        _sideEffect.emit(MyClubSideEffect.ShowSnackBar(message))
    }

    fun navigateUp() = viewModelScope.launch {
        _sideEffect.emit(MyClubSideEffect.NavigateUp)
    }

}