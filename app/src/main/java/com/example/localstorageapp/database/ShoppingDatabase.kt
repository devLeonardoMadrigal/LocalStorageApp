package com.example.localstorageapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.localstorageapp.dao.ShoppingItemDAO
import com.example.localstorageapp.entities.ShoppingItem

@Database(entities = [ShoppingItem::class], version = 1)
abstract class ShoppingDatabase : RoomDatabase() {

    abstract fun shoppingItemDao() : ShoppingItemDAO

}