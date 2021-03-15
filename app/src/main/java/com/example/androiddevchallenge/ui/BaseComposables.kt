/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.BottomNavigation
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Snackbar
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun BloomButton(
    text: String,
    colors: ButtonColors,
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.medium,
    textColor: Color = Color.Unspecified,
    onClick: () -> Unit
) {
    TextButton(
        shape = shape,
        colors = colors,
        modifier = modifier,
        onClick = onClick
    ) {
        Text(
            text = text,
            color = textColor
        )
    }
}

@Composable
fun BloomPrimaryButton(
    modifier: Modifier = Modifier,
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    text: String,
    shape: Shape = MaterialTheme.shapes.medium,
    onClick: () -> Unit
) {
    BloomButton(
        text = text,
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        shape = shape,
        textColor = if (isDarkTheme) white else white,
        onClick = onClick
    )
}

@Composable
fun BloomSecondaryButton(
    modifier: Modifier = Modifier,
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    text: String,
    shape: Shape = MaterialTheme.shapes.medium,
    onClick: () -> Unit
) {
    BloomButton(
        text = text,
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = MaterialTheme.colors.secondary,
            contentColor = MaterialTheme.colors.onSecondary
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        shape = shape,
        textColor = if (isDarkTheme) gray else white,
        onClick = onClick
    )
}

@Composable
fun BloomTextField(modifier: Modifier = Modifier, hint: String) {
    var text by remember { mutableStateOf(TextFieldValue("")) }

    OutlinedTextField(
        modifier = modifier
            .height(56.dp),
        placeholder = {
            Text(
                hint,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                style = MaterialTheme.typography.body1
            )
        },
        textStyle = MaterialTheme.typography.body1,
        value = text,
        onValueChange = {
            text = it
        }
    )
}

@Composable
fun BloomPasswordTextField(modifier: Modifier = Modifier, hint: String) {
    var text by remember { mutableStateOf(TextFieldValue("")) }

    OutlinedTextField(
        modifier = modifier
            .height(56.dp),
        placeholder = {
            Text(
                hint,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                style = MaterialTheme.typography.body1
            )
        },
        textStyle = MaterialTheme.typography.body1,
        value = text,
        onValueChange = {
            text = it
        },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
}

@Composable
fun BloomImage(modifier: Modifier = Modifier, imageUrl: String, shape: Shape = MaterialTheme.shapes.small) {
    Surface(
        modifier = modifier
            .clip(shape)
    ) {
        CoilImage(
            data = imageUrl,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            contentDescription = "",
        )
    }
}

@Composable
fun BloomCard(modifier: Modifier = Modifier, content: @Composable () -> Unit = {}) {
    Card(
        modifier = modifier,
        elevation = 1.dp,
        shape = MaterialTheme.shapes.small
    ) {
        content()
    }
}

@Composable
fun BloomSnackbar(modifier: Modifier = Modifier, content: @Composable () -> Unit = {}) {
    Snackbar(
        modifier = modifier,
        elevation = 2.dp,
        shape = MaterialTheme.shapes.small
    ) {
        content()
    }
}

@Composable
fun BloomBottomNavigation(modifier: Modifier = Modifier, content: @Composable () -> Unit = {}) {
    BottomNavigation(
        modifier = modifier,
        elevation = 16.dp
    ) {
        content()
    }
}

@Preview
@Composable
fun PreviewBloomButton() {
    MyTheme {
//        BloomImage(
//            modifier = Modifier
//                .height(64.dp)
//                .width(64.dp),
//            imageUrl = "https://images.pexels.com/photos/4751978/pexels-photo-4751978.jpeg?auto=compress&cs=tinysrgb&dpr=3&h=750&w=1260"
//        )
//        BloomSecondaryButton(text = "Test 123", modifier = Modifier, onClick = {
//
//        })
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            color = MaterialTheme.colors.background
        ) {
            BloomPasswordTextField(
                modifier = Modifier.fillMaxWidth(),
                hint = "password here"
            )
        }
    }
}
