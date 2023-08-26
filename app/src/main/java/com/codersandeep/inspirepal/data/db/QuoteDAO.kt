package com.codersandeep.inspirepal.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.codersandeep.inspirepal.data.db.entity.QuoteEntity
import com.codersandeep.inspirepal.data.models.quote.Quote

@Dao
interface QuoteDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuote(quote: QuoteEntity)

    @Query("SELECT * FROM quotes")
    fun getQuotes():LiveData<List<QuoteEntity>>

    @Delete
    suspend fun deleteQuote(quote: QuoteEntity)
}