package com.example.androiddevchallenge.data

import androidx.compose.runtime.Immutable

@Immutable
data class BloomTheme(val title: String, val imageUrl: String)

@Immutable
data class BloomItem(val title: String, val description: String, val imageUrl: String, var isChecked: Boolean = false)