package me.cniekirk.traintimes.ui.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun M3TextField(
    modifier: Modifier = Modifier,
    onValueChanged: (String) -> Unit,
    leadingIcon: ImageVector? = null,
) {
    var textValue by remember { mutableStateOf(TextFieldValue()) }
    Surface(
        shape = RoundedCornerShape(60.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .height(50.dp)
                .padding(start = 16.dp, end = 12.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                leadingIcon?.let { Icon(it, contentDescription = null) }
                BasicTextField(
                    value = textValue,
                    onValueChange = { value ->
                        textValue = value
                        onValueChanged(textValue.text)
                    },
                    modifier = Modifier.padding(start = 8.dp),
                    singleLine = true
                )
            }
        }
    }
}

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
@Composable
fun TextFieldPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        M3TextField(
            onValueChanged = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 36.dp, start = 16.dp, end = 16.dp)
            ,
            leadingIcon = Icons.Default.Search
        )
    }
}