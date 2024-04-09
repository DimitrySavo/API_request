package com.example.api_request.ui.elements

import android.content.res.Resources.Theme
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.api_request.ui.theme.*


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RequestNameField(
    modifier: Modifier = Modifier,
    methods: List<String> = listOf("GET", "SET", "UPDATE")
) {
    Box (modifier = modifier.then(
        Modifier
            .fillMaxWidth()
            .padding(10.dp))) {
        Row () {
            OutlinedTextField(
                value = "",
                singleLine = true,
                placeholder = {Text(
                    text = "Введите название запроса",
                    style = TextStyle(
                        fontSize = 18.sp,
                        color = PlaceholderGrey)
                )},
                onValueChange = {
            })
            DropdownMenu(elements = methods)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenu(elements: List<String>) {
    var selectedText by remember {
        mutableStateOf("Метод")
    }

    var isExpanded by remember {
        mutableStateOf(false)
    }

    ExposedDropdownMenuBox(expanded = isExpanded,
        onExpandedChange = {isExpanded = !isExpanded}
    ){
        TextField(
            modifier = Modifier.menuAnchor(),
            value = selectedText,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)})

        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }) {
                elements.forEachIndexed { index, s ->
                    DropdownMenuItem(text = {
                        Text(text = s) },
                        onClick = { selectedText = elements[index]
                        isExpanded = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                        )
                }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun Preview() {
    RequestNameField()
}