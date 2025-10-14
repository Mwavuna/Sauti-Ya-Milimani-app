package com.example.sautiyamilimani_test1.data.local.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.sautiyamilimani_test1.data.local.converters.MemberTypeConverters
import com.example.sautiyamilimani_test1.data.local.daos.MemberDao
import com.example.sautiyamilimani_test1.data.local.entities.MemberEntity
import com.example.sautiyamilimani_test1.domain.model.Member

@Database(entities = [MemberEntity::class], version = 1, exportSchema = false)
@TypeConverters(MemberTypeConverters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun memberDao(): MemberDao
}