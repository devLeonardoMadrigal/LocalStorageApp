package com.example.localstorageapp.composables

import android.widget.CheckBox
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.localstorageapp.R
import com.example.localstorageapp.entities.ShoppingItem
import com.example.localstorageapp.viewmodel.ShoppingViewModel

@Composable
fun ShoppingScreen(viewModel: ShoppingViewModel = hiltViewModel()){

    var nameField by remember { mutableStateOf("") }
    var priceField by remember { mutableStateOf("") }
    var quantityField by remember { mutableStateOf("") }
    //val isItemChecked by remember { mutableStateOf(viewModel) }

    val currentItems by viewModel.items.collectAsStateWithLifecycle()

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = nameField,
                onValueChange = {newText -> nameField = newText},
                label = {Text("Name")}
            )
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = priceField,
                onValueChange = {newText -> priceField = newText},
                label = {Text("Price")}
            )
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = quantityField,
                onValueChange = {newText -> quantityField = newText},
                label = {Text("Quantity")}
            )
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)) {
            Button(
                onClick = {
                    viewModel.addItem(
                        name = nameField,
                        quantity = quantityField,
                        price = priceField
                    )
                    nameField= ""
                    quantityField=""
                    priceField = ""

                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add item")
            }
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            LazyColumn(modifier = Modifier.fillMaxWidth()
            ) {
                items(currentItems) {item ->
                   Row(modifier = Modifier.fillMaxWidth()) {
                       var isChecked by remember { mutableStateOf(item.isBought) }

                       Text("Name:${item.name}")
                       Spacer(modifier = Modifier.width(10.dp))
                       Text("Price:${item.price}")
                       Spacer(modifier = Modifier.width(10.dp))
                       Text("Quantity:${item.quantity}")
                       Spacer(modifier = Modifier.width(10.dp))
                       Text("Is bought?")
                       Checkbox(checked = isChecked, onCheckedChange = {
                           checkBoxState -> isChecked = checkBoxState
                       })
                       Spacer(modifier = Modifier.width(10.dp))
                       IconButton(onClick = {
                           viewModel.deleteItem(item)
                       },) {
                           Icon(
                               Icons.Filled.Close,
                               contentDescription = "Delete item",
                           )

                       }
                   }
                    ShoppingItemRow(
                        item,
                        {viewModel.toggleBought(item)},
                        {viewModel.deleteItem(item)}
                    )
                }

            }
        }
    }
}

@Composable
fun ShoppingItemRow(item: ShoppingItem, onToggle: () -> Unit, onDelete: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth().heightIn(max=300.dp).padding(vertical = 4.dp)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Column() {
                Text("Name: ${item.name}")
                Text("Price: ${item.price}")
            }
            Column() {
                Checkbox(checked = item.isBought, {onToggle()})
                IconButton(onClick = {
                    onDelete()
                },) {
                    Icon(
                        Icons.Filled.Close,
                        contentDescription = "Delete item",
                    )

                }
            }
        }



    }

}