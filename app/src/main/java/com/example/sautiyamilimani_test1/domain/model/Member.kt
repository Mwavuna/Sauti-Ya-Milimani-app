package com.example.sautiyamilimani_test1.domain.model

data class Member(
    val id: Int? = null,
    val fullName:String="",
    val email:String?="",
    val phone1:String?="",
    val phone2:String?="",
    val status:Status= Status.PENDING,
    val role:Role,
    val attendanceRate: Float?=0.00f,
    val addedBy:String,
    val editedBy:String?=null,
    val deletedBy:String?=null,
    val timestamp: Long = System.currentTimeMillis()
)

enum class Status{
    ACTIVE,
    INACTIVE,
    PENDING
}

enum class  Role{
    MEMBER,
    LEADER
}
