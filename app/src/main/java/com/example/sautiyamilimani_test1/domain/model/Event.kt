package com.example.sautiyamilimani_test1.domain.model

data class Event(
    val title:String,
    val type:EventType,
    val location:String,
    val date:String,
    val startTime:String,
    val endTime:String,
    val organizer:String,
    val description:String,
)

enum class EventType{
    FIRST_SUNDAY_SERVICE,
    MAIN_SUNDAY_SERVICE,
    SATURDAY_PRACTICE,
    MEETING,
    CLEANING,
    PRAYER_KESHA,
    WORSHIP_EXPERIENCE,
    PRACTICE,
    SERVICE,
    SEMINAR,
    PREWEDDING,
    FUNDRAISING,
    WEDDING,
    GET_TOGETHER,
    OTHER
}


