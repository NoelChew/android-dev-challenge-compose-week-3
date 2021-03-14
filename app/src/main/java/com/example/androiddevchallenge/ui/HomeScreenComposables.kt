package com.example.androiddevchallenge.ui.theme

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.BloomItem
import com.example.androiddevchallenge.data.BloomTheme
import com.example.androiddevchallenge.data.dummyItems
import com.example.androiddevchallenge.data.dummyThemes
import dev.chrisbanes.accompanist.coil.CoilImage

import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet

@Composable
fun BloomSearchTextField(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf(TextFieldValue("")) }

    OutlinedTextField(
        modifier = modifier
            .height(56.dp),
        placeholder = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    modifier = Modifier
                        .size(18.dp)
                        .padding(top = 4.dp)
                )
                Text(
                    "Search",
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    style = MaterialTheme.typography.body1
                )
            }
        },
        textStyle = MaterialTheme.typography.body1,
        value = text,
        onValueChange = {
            text = it
        }
    )
}

@Composable
fun BloomThemeCard(bloomTheme: BloomTheme) {
    Row(
        modifier = Modifier.size(144.dp)
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
                        imageUrl = bloomTheme.imageUrl, modifier = Modifier
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
                    ) {
                        Surface(
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxHeight()
                        ) {
                            Text(
                                text = bloomTheme.title,
                                style = MaterialTheme.typography.h2,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                                    .padding(start = 8.dp, end = 8.dp)
                                    .paddingFromBaseline(top = 12.dp)
                            )
                        }

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
            imageUrl = bloomItem.imageUrl, modifier = Modifier
                .size(64.dp)
        )
        ConstraintLayout(
            modifier = Modifier.fillMaxHeight()
        ) {
            val (checkBox, textTitle, textDescription, divider) = createRefs()
            Checkbox(
                checked = checkBoxState,
                onCheckedChange = { checkBoxState = it },
                modifier = Modifier
                    .wrapContentHeight()
                    .wrapContentWidth()
                    .padding(bottom = 24.dp)
                    .constrainAs(checkBox) {
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    })

            Text(
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(start = 16.dp, end = 16.dp)
                    .constrainAs(textTitle) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        end.linkTo(checkBox.start)
                    }
                    .fillMaxWidth()
                    .paddingFromBaseline(top = 24.dp),
                style = MaterialTheme.typography.h2,
                maxLines = 1,
                text = bloomItem.title
            )
            Text(
                text = bloomItem.description,
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(start = 16.dp, end = 16.dp)
                    .constrainAs(textDescription) {
                        start.linkTo(parent.start)
                        end.linkTo(checkBox.start)
                        top.linkTo(textTitle.bottom)
                    }
                    .fillMaxWidth(),
                style = MaterialTheme.typography.body1
            )


            Divider(
                modifier = Modifier
                    .constrainAs(divider) {
                        start.linkTo(parent.start, margin = 8.dp)
                        end.linkTo(checkBox.end)
                        bottom.linkTo(parent.bottom)
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

//            LazyRow(
//                modifier = Modifier
//                    .padding(top = 16.dp)
//                    .fillMaxWidth()
//                    .wrapContentHeight()
//            ) {
//                dummyThemes.forEach {
//                    item {
//                        BloomThemeCard(bloomTheme = it)
//                        Spacer(
//                            modifier = Modifier
//                                .height(1.dp)
//                                .width(8.dp)
//                        )
//                    }
//                }
//            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                dummyItems.forEach {
                    item {
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

    }
}