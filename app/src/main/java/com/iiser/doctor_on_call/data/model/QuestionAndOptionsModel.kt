package com.iiser.doctor_on_call.data.model

data class QuestionAndOptionsModel(
    val question: String,
    val options:List<String>,
    // added newly
    val bodyRegion: String
)