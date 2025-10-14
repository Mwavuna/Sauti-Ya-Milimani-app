package com.example.sautiyamilimani_test1.data.local.converters

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.sautiyamilimani_test1.domain.model.Role
import com.example.sautiyamilimani_test1.domain.model.Status

class MemberTypeConverters {
    @TypeConverter
    fun fromStatus(status: Status):String=status.name

    @TypeConverter
    fun toStatus(value:String): Status=Status.valueOf(value)

    @TypeConverter
    fun fromRole(role: Role):String=role.name

    @TypeConverter
    fun toRole(value: String):Role=Role.valueOf(value)

}