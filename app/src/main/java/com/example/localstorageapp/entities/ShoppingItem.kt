package com.example.localstorageapp.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_items")//table name
data class ShoppingItem(

    //columns
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    @ColumnInfo("name")
    val name: String,
    val price: Double,
    val quantity: Int,
    val isBought: Boolean = false
)
