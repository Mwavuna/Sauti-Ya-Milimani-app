package com.example.sautiyamilimani_test1.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sautiyamilimani_test1.domain.model.Role
import com.example.sautiyamilimani_test1.domain.model.Status

@Entity(tableName = "members")
data class MemberEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int?=0,
    val fullName:String="",
    val email:String?="",
    val phone1:String?="",
    val phone2:String?="",
    val status:Status= Status.PENDING,
    val role:Role=Role.MEMBER,
    val attendanceRate: Float?=0.00f,
    val addedBy:String="",
    val editedBy:String?=null,
    val deletedBy:String?=null,
    val timestamp: Long = System.currentTimeMillis()

)
