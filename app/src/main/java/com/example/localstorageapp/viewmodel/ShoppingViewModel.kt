package com.example.localstorageapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.localstorageapp.entities.ShoppingItem
import com.example.localstorageapp.repository.ShoppingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingViewModel @Inject constructor(private val repository: ShoppingRepository) : ViewModel() {

    private val _items = MutableStateFlow<List<ShoppingItem>>(emptyList())

    val items : StateFlow<List<ShoppingItem>> = _items.asStateFlow()

    init {
        readAllItems()
    }

    fun addItem(name: String, price: String, quantity: String){
        viewModelScope.launch {
            val priceNum = price.toDoubleOrNull() ?: 0.0
            val qtyNum = quantity.toIntOrNull() ?: 0
            repository.insertItem(
                ShoppingItem(
                    name = name,
                    price = priceNum,
                    quantity = qtyNum
                )
            )
        }
    }

    fun deleteItem(item: ShoppingItem){
        viewModelScope.launch {
            repository.deleteItem(item)
        }
    }

    fun toggleBought(item: ShoppingItem){
        viewModelScope.launch {
            repository.updateItem(item.copy(isBought = !item.isBought))
        }
    }

    fun readAllItems(){
        viewModelScope.launch {
            repository.getAllItems().collect {
                _items.value = it
            }
        }
    }

}