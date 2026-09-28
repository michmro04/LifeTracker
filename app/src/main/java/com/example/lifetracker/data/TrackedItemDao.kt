package com.example.lifetracker.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackedItemDao{

    @Query("SELECT * FROM tracked_items")
    fun getAllItems(): Flow<List<TrackedItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: TrackedItemEntity)

    @Query("DELETE FROM tracked_items WHERE id = :itemId")
    suspend fun deleteItemById(itemId: String)
}