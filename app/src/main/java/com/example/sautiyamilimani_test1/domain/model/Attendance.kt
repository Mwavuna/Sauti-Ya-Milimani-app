package com.example.sautiyamilimani_test1.domain.model

data class Attendance(
    val event:AttendedEvent,
    val date:String,
    val attendees:List<Member>
)

enum class AttendedEvent{
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
    WEDDING,
    GET_TOGETHER,
    OTHER
}


