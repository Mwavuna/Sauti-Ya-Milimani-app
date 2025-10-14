package com.example.sautiyamilimani_test1.domain.model

data class Minute(
    var title: String,
    var date: String,
    var type: MinuteEvent,
    var attendees: Int,
    var recorder: String,
    var summary: String,
    var notes: String,
    var actions: String,
    var published: Boolean? = false
)

enum class MinuteEvent {
    NORMAL_SUNDAY_MEETING,
    CUSTOM
}