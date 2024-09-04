package com.wefit.model

import kotlinx.serialization.Serializable

@Serializable
data class Exercise(
    val id: Int,
    val name: String,
    val description: String,
    val img_s3: String,
)