package com.codersandeep.inspirepal.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quotes")
data class QuoteEntity(
    @PrimaryKey()
    val id : String,
    val content : String,
    val author : String
)
