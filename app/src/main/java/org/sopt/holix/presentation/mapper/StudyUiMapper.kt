package org.sopt.holix.presentation.mapper

import org.sopt.holix.domain.model.Study
import org.sopt.holix.domain.model.StudyData
import org.sopt.holix.domain.model.Tag
import org.sopt.holix.presentation.home.model.StudyDataUiModel
import org.sopt.holix.presentation.home.model.StudyUiModel
import org.sopt.holix.presentation.home.model.TagUiModel


fun Study.toUiModel(): StudyUiModel = StudyUiModel(
    studyId = id,
    studyTitle = title,
    studyLeader = leader,
    price = price,
    imageUrl = imageUrl,
    tags = tags.map { it.toUiModel() },
    category = category
)

fun Tag.toUiModel(): TagUiModel = TagUiModel(
    name = name,
    color = color
)

fun StudyData.toUiModel(): StudyDataUiModel = StudyDataUiModel(
    passionateStudies = passionateStudies.map { it.toUiModel() },
    insightStudies = insightStudies.map { it.toUiModel() },
    newStudies = newStudies.map { it.toUiModel() },
    recommendStudies = recommendStudies.map { it.toUiModel() },
    freeStudies = freeStudies.map { it.toUiModel() }
)
