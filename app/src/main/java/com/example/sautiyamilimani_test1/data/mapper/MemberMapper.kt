package com.example.sautiyamilimani_test1.data.mapper

import com.example.sautiyamilimani_test1.data.local.entities.MemberEntity
import com.example.sautiyamilimani_test1.domain.model.Member

fun MemberEntity.toDomain(): Member {
    return Member(
        id = id,
        fullName = fullName,
        email = email,
        phone1 = phone1,
        phone2 = phone2,
        status = status,
        role = role,
        attendanceRate = attendanceRate,
        addedBy = addedBy,
        editedBy = editedBy,
        deletedBy = deletedBy,
        timestamp = timestamp
    )
}

fun Member.toEntity(): MemberEntity {
    return MemberEntity(
        id = id,
        fullName = fullName,
        email = email,
        phone1 = phone1,
        phone2 = phone2,
        status = status,
        role = role,
        attendanceRate = attendanceRate,
        addedBy = addedBy,
        editedBy = editedBy,
        deletedBy = deletedBy,
        timestamp = timestamp
    )
}
