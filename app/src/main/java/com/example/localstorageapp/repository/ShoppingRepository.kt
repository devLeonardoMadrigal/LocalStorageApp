package com.example.localstorageapp.repository

import com.example.localstorageapp.dao.ShoppingItemDAO
import com.example.localstorageapp.entities.ShoppingItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ShoppingRepository @Inject constructor(private val dao: ShoppingItemDAO) {

    fun getAllItems() : Flow<List<ShoppingItem>> = dao.getAllItems()
    suspend fun insertItem(item: ShoppingItem) = dao.insertItem(item)
    suspend fun updateItem(item: ShoppingItem) = dao.updateItem(item)
    suspend fun deleteItem(item: ShoppingItem) = dao.deleteItem(item)

}