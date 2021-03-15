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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.androiddevchallenge.data.BloomItem
import com.example.androiddevchallenge.data.BloomTheme
import com.example.androiddevchallenge.data.dummyThemes

@Composable
fun BloomSearchTextField(modifier: Modifier = Modifier) {
    var query by remember { mutableStateOf("") }

    OutlinedTextField(
        value = query,
        onValueChange = { query = it },
        modifier = modifier.fillMaxWidth(),
        maxLines = 1,
        textStyle = MaterialTheme.typography.body1,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                modifier = Modifier
                    .padding(0.dp)
                    .size(18.dp)
            )
        },
        placeholder = {
            Text(
                text = "Search",
                modifier = Modifier
                    .padding(start = 0.dp)
                    .wrapContentWidth(),
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Start,
                maxLines = 1
            )
        }
    )
}

@Composable
fun BloomThemeCard(bloomTheme: BloomTheme) {
    Row(
        modifier = Modifier
            .size(144.dp)
    ) {
        BloomCard(
            modifier = Modifier.size(136.dp),
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                ) {
                    BloomImage(
                        imageUrl = bloomTheme.imageUrl,
                        modifier = Modifier
                            .width(136.dp)
                            .height(96.dp),
                        shape = RoundedCornerShape(
                            topStart = 4.dp,
                            topEnd = 4.dp,
                            bottomStart = 0.dp,
                            bottomEnd = 0.dp
                        )
                    )
                    Box(
                        modifier = Modifier
                            .width(136.dp)
                            .height(40.dp)
                            .background(MaterialTheme.colors.onSecondary)
                    ) {
                        Text(
                            text = bloomTheme.title,
                            style = MaterialTheme.typography.h2,
                            modifier = Modifier
                                .padding(start = 8.dp, end = 8.dp)
                                .paddingFromBaseline(top = 24.dp)
                                .fillMaxWidth()
                                .wrapContentHeight()
                        )
                    }
                }
            }
        )
        Spacer(
            modifier = Modifier
                .height(1.dp)
                .width(8.dp)
        )
    }
}

@Composable
fun BloomItemRow(bloomItem: BloomItem) {
    var checkBoxState by remember { mutableStateOf(bloomItem.isChecked) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
    ) {
        BloomImage(
            imageUrl = bloomItem.imageUrl,
            modifier = Modifier
                .size(64.dp)
        )
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
        ) {
            val (checkBox, textTitle, textDescription, divider) = createRefs()

            Text(
                modifier = Modifier
                    .constrainAs(textTitle) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        width = Dimension.fillToConstraints
                    }
                    .padding(horizontal = 8.dp)
                    .paddingFromBaseline(top = 24.dp)
                    .wrapContentHeight(),
                style = MaterialTheme.typography.h2,
                maxLines = 1,
                text = bloomItem.title
            )

            Text(
                text = bloomItem.description,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .paddingFromBaseline(bottom = 24.dp)
                    .constrainAs(textDescription) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                        width = Dimension.fillToConstraints
                    }
                    .wrapContentHeight(),
                style = MaterialTheme.typography.body1
            )

            Checkbox(
                checked = checkBoxState,
                onCheckedChange = { checkBoxState = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = MaterialTheme.colors.secondaryVariant,
                    checkmarkColor = MaterialTheme.colors.background
                ),
                modifier = Modifier
                    .constrainAs(checkBox) {
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom, margin = 24.dp)
                    }
                    .wrapContentHeight()
                    .wrapContentWidth()
            )

            Divider(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .constrainAs(divider) {
                        start.linkTo(parent.start)
                        end.linkTo(checkBox.end)
                        bottom.linkTo(parent.bottom)
                        width = Dimension.fillToConstraints
                    },
                thickness = 2.dp
            )
        }
    }
}

@Preview
@Composable
fun TestPreview() {
    MyTheme(darkTheme = true) {
        Surface() {
//            BloomSearchTextField(Modifier.fillMaxWidth())

            LazyRow(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                dummyThemes.forEach {
                    item {
                        BloomThemeCard(bloomTheme = it)
                        Spacer(
                            modifier = Modifier
                                .height(1.dp)
                                .width(8.dp)
                        )
                    }
                }
            }

//            LazyColumn(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .fillMaxHeight()
//            ) {
//                dummyItems.forEach {
//                    item {
//                        BloomItemRow(bloomItem = it)
//                        Spacer(
//                            modifier = Modifier
//                                .height(8.dp)
//                                .width(1.dp)
//                        )
//                    }
//                }
//            }
        }
    }
}
