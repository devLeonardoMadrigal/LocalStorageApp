package com.example.localstorageapp.composables

import android.widget.CheckBox
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.localstorageapp.R
import com.example.localstorageapp.viewmodel.ShoppingViewModel

@Composable
fun ShoppingScreen(viewModel: ShoppingViewModel = hiltViewModel()){

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = "",
                onValueChange = {},
                label = {Text("Name")}
            )
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = "",
                onValueChange = {},
                label = {Text("Price")}
            )
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = "",
                onValueChange = {},
                label = {Text("Quantity")}
            )
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = {
                    viewModel.addItem(
                        name = "1",
                        quantity = "2",
                        price = "3"

                    )
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add item")
            }
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            LazyColumn(modifier = Modifier.fillMaxWidth()
            ) {
                items(viewModel.items.value) {item ->
                   Row(modifier = Modifier.fillMaxWidth()) {
                       Text("Name:${item.name}")
                       Spacer(modifier = Modifier.width(10.dp))
                       Text("Name:${item.price}")
                       Spacer(modifier = Modifier.width(10.dp))
                       Text("Name:${item.quantity}")
                       Spacer(modifier = Modifier.width(10.dp))
                       Text("Is bought?")
                       Checkbox(checked = item.isBought, onCheckedChange = {})
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
                }

            }
        }
    }
}