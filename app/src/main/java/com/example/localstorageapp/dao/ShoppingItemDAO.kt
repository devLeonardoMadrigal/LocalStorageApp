package com.example.localstorageapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.localstorageapp.entities.ShoppingItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingItemDAO {
    //We write the queries here (insert, update, etc.)
    //CRUD: Create, Read, Update, Delete

    @Insert(onConflict = OnConflictStrategy.REPLACE)//If same value is added, it will be replaced
    suspend fun insertItem(item: ShoppingItem)// suspend function because we don't want to use the Main thread; we'll use coroutines for asycronous operations

    @Query("SELECT * FROM shopping_items")
    fun getAllItems(): Flow<List<ShoppingItem>>
    //Why we using Flow instead of suspend fun?
    //

    @Update
    suspend fun updateItem(item : ShoppingItem)

    @Delete
    suspend fun deleteItem(item : ShoppingItem)


}