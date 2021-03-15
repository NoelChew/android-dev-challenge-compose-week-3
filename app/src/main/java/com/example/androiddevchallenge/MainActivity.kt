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
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha.medium
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.dummyThemes
import com.example.androiddevchallenge.ui.screen.HomeScreen
import com.example.androiddevchallenge.ui.screen.UndefinedTab
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme() {
                MyApp()
            }
        }
    }
}

private enum class BloomTabs(
    val title: String,
    val icon: ImageVector
) {
    Home("Home", Icons.Default.Home),
    Favorites("Favorites", Icons.Default.FavoriteBorder),
    Profile("Profile", Icons.Default.AccountCircle),
    Cart("Cart", Icons.Default.ShoppingCart)
}

// Start building your app here!
@Composable
fun MyApp() {
//    Surface(color = MaterialTheme.colors.background) {
//        Text(text = "Ready... Set... GO!")
//    }
    val (selectedTab, setSelectedTab) = remember { mutableStateOf(BloomTabs.Home) }
    val tabs = BloomTabs.values()

    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        topBar = {},
        floatingActionButton = {},
        bottomBar = {
            BottomNavigation(
                modifier = Modifier.height(56.dp),
                elevation = 16.dp,
                backgroundColor = MaterialTheme.colors.primary
            ) {
                tabs.forEach { navItem ->
                    BottomNavigationItem(
                        selected = selectedTab == navItem,
                        selectedContentColor = MaterialTheme.colors.onBackground,
                        unselectedContentColor = MaterialTheme.colors.onBackground.copy(alpha = medium),
                        label = { Text(navItem.title) },
                        icon = {
                            Icon(
                                imageVector = navItem.icon,
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        onClick = {
                            setSelectedTab(navItem)
                        }
                    )
                }
            }
        }
    ) {
        when (selectedTab) {
            BloomTabs.Home ->
                HomeScreen(bloomThemes = dummyThemes)
            BloomTabs.Favorites ->
                UndefinedTab("Favorites")
            BloomTabs.Profile ->
                UndefinedTab("Profile")
            BloomTabs.Cart ->
                UndefinedTab("Cart")
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}
