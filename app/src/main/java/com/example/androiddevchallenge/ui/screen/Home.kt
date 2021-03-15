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
package com.example.androiddevchallenge.ui.screen

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.androiddevchallenge.data.BloomTheme
import com.example.androiddevchallenge.data.dummyItems
import com.example.androiddevchallenge.data.dummyThemes
import com.example.androiddevchallenge.ui.theme.BloomItemRow
import com.example.androiddevchallenge.ui.theme.BloomSearchTextField
import com.example.androiddevchallenge.ui.theme.BloomThemeCard
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.typography

@Composable
fun HomeScreen(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    bloomThemes: List<BloomTheme>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
    ) {
        BloomSearchTextField(
            modifier = Modifier
                .padding(top = 40.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth()
        )

        Text(
            text = "Browse themes",
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .paddingFromBaseline(top = 32.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            style = MaterialTheme.typography.h1
        )

        LazyRow(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            contentPadding = PaddingValues(start = 16.dp)
        ) {
            bloomThemes.forEach {
                item {
                    BloomThemeCard(bloomTheme = it)
                }
            }
        }

        ConstraintLayout(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            val (label, icon) = createRefs()
            Icon(
                imageVector = Icons.Default.FilterList,
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 4.dp)
                    .constrainAs(icon) {
                        bottom.linkTo(parent.bottom)
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    }
                    .size(24.dp)
            )

            Text(
                text = "Design your home garden",
                modifier = Modifier
                    .wrapContentHeight()
                    .constrainAs(label) {
                        start.linkTo(parent.start)
                        end.linkTo(icon.start)
                        width = Dimension.fillToConstraints
                    }
                    .paddingFromBaseline(top = 32.dp, bottom = 16.dp),
                style = MaterialTheme.typography.h1
            )
        }

        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            dummyItems.forEach {
                BloomItemRow(bloomItem = it)
                Spacer(
                    modifier = Modifier
                        .height(8.dp)
                        .width(1.dp)
                )
            }
            Spacer(
                modifier = Modifier
                    .height(96.dp)
                    .width(1.dp)
            )
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun HomeScreenLightPreview() {
    MyTheme {
        HomeScreen(isDarkTheme = false, dummyThemes)
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun HomeScreenDarkPreview() {
    MyTheme(darkTheme = true) {
        HomeScreen(isDarkTheme = true, dummyThemes)
    }
}
