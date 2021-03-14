package com.example.androiddevchallenge.ui.screen

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import coil.size.Scale
import com.example.androiddevchallenge.MyApp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.BloomTheme
import com.example.androiddevchallenge.data.dummyItems
import com.example.androiddevchallenge.data.dummyThemes
import com.example.androiddevchallenge.ui.theme.BloomItemRow
import com.example.androiddevchallenge.ui.theme.BloomPrimaryButton
import com.example.androiddevchallenge.ui.theme.BloomSearchTextField
import com.example.androiddevchallenge.ui.theme.BloomSecondaryButton
import com.example.androiddevchallenge.ui.theme.BloomThemeCard
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.pink900
import com.example.androiddevchallenge.ui.theme.typography
import com.example.androiddevchallenge.ui.theme.white
import dev.chrisbanes.accompanist.coil.CoilImage

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
            .padding(start = 16.dp, end = 16.dp)
    ) {
        BloomSearchTextField(
            modifier = Modifier
                .padding(top = 40.dp)
                .fillMaxWidth()
        )

        Text(
            text = "Browse themes",
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .paddingFromBaseline(32.dp),
            style = MaterialTheme.typography.h1
        )

        LazyRow(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            bloomThemes.forEach {
                item {
                    BloomThemeCard(bloomTheme = it)
                }
            }
        }

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth()
        ) {
            val (label, icon) = createRefs()
            Icon(
                imageVector = Icons.Default.FilterList,
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .constrainAs(icon) {
                        end.linkTo(parent.end)
                    })

            Text(
                text = "Design your home garden",
                modifier = Modifier
                    .wrapContentHeight()
                    .constrainAs(label) {
                        start.linkTo(parent.start)
                        end.linkTo(icon.start)
                    }
                    .paddingFromBaseline(top = 32.dp, bottom = 16.dp),
                style = MaterialTheme.typography.h1
            )
        }

        Column(
            modifier = Modifier
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