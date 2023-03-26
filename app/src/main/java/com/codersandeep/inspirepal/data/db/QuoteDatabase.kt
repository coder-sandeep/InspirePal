package com.codersandeep.inspirepal.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.codersandeep.inspirepal.data.db.entity.QuoteEntity

@Database(entities = [QuoteEntity::class], version = 1)
abstract class QuoteDatabase:RoomDatabase(){
    abstract fun quoteDao():QuoteDAO
}