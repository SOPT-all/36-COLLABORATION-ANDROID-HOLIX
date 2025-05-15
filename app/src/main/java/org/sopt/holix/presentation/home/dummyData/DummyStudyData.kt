package org.sopt.holix.presentation.home.dummyData

import org.sopt.holix.presentation.home.model.StudyUiModel
import org.sopt.holix.presentation.home.model.TagUiModel

val dummyStudyList1 = listOf(
    StudyUiModel(
        studyId = 1,
        studyTitle = "위클리 UX / UI 분석 챌린지",
        studyLeader = "레오",
        price = "매월 14,900원",
        imageUrl = "",
        tags = listOf(
            TagUiModel("챌린지", "blue"),
            TagUiModel("커뮤니티", "black")
        ),
        category = "활발한 스터디에 참여해보세요 🔥"
    ),
    StudyUiModel(
        studyId = 2,
        studyTitle = "매주 재무제표 50가지 공부하기",
        studyLeader = "재무제표 읽는 남자",
        price = "매월 23,900원",
        imageUrl = "",
        tags = listOf(
            TagUiModel("온라인", "blue")
        ),
        category = "활발한 스터디에 참여해보세요 🔥"
    )
)

val dummyStudyList2 = listOf(
    StudyUiModel(
        studyId = 3,
        studyTitle = "최정문 북클럽 - 과학으로 가는 문(과학, 수학, 공학, SF 독서클럽)",
        studyLeader = "최정문",
        price = "매월 14,900원",
        imageUrl = "",
        tags = listOf(
            TagUiModel(name = "온라인", color = "blue"),
            TagUiModel(name = "커뮤니티", color = "gray")
        ),
        category = "북클럽과 세미나로 인사이트 충전 💡"
    ),
    StudyUiModel(
        studyId = 4,
        studyTitle = "🚀 스타트업 로켓 성장",
        studyLeader = "최인철 / Jordan",
        price = "매월 100,000원",
        imageUrl = "",
        tags = listOf(
            TagUiModel(name = "커뮤니티", color = "gray")
        ),
        category = "북클럽과 세미나로 인사이트 충전 💡"
    )
)

