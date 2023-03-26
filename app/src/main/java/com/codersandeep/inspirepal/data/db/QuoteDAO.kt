package com.codersandeep.inspirepal.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.codersandeep.inspirepal.data.db.entity.QuoteEntity
import com.codersandeep.inspirepal.data.models.quote.Quote

@Dao
interface QuoteDAO {
    @Insert
    suspend fun insertQuote(quote: QuoteEntity)

    @Query("SELECT * FROM quotes")
    fun getQuotes():LiveData<List<QuoteEntity>>
}