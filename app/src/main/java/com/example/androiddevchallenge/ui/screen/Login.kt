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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.BloomPasswordTextField
import com.example.androiddevchallenge.ui.theme.BloomSecondaryButton
import com.example.androiddevchallenge.ui.theme.BloomTextField
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.typography

@Composable
fun LoginScreen(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    onLoginClicked: () -> Unit = {},
    onTermsOfUseClicked: () -> Unit = {},
    onPrivacyPolicyClicked: () -> Unit = {}
) {
    Surface(color = MaterialTheme.colors.background) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(start = 16.dp, end = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            Text(
                style = MaterialTheme.typography.h1,
                text = "Log in with email",
                modifier = Modifier.paddingFromBaseline(top = 184.dp)
            )

            BloomTextField(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                hint = "Email address"
            )

            BloomPasswordTextField(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth(),
                hint = "Password (8+ characters)"
            )

            // unfortunately, couldn't find a way to set textAlign in ClickableText
            // it seems like textAlign param is only available in Text
            val annotatedText1 = buildAnnotatedString {
                append("By clicking below, you agree to our ")
                pushStringAnnotation(tag = "link", annotation = "tnc")
                pushStyle(SpanStyle(textDecoration = TextDecoration.Underline))
                append("Terms of Use")
                pop()
                append(" and consent")
            }

            val annotatedText2 = buildAnnotatedString {
                append(" to our ")
                pushStringAnnotation(tag = "link", annotation = "pp")
                pushStyle(SpanStyle(textDecoration = TextDecoration.Underline))
                append("Privacy Policy")
                pop()
                append(".")
            }

            ClickableText(
                modifier = Modifier
                    .wrapContentWidth()
                    .paddingFromBaseline(top = 24.dp),
                text = annotatedText1,
                style = MaterialTheme.typography.body2,
                onClick = { offset ->
                    // We check if there is an *URL* annotation attached to the text
                    // at the clicked position
                    annotatedText1.getStringAnnotations(tag = "link", start = offset, end = offset)
                        .firstOrNull()?.let { annotation ->
                            // If yes, we log its value
                            if (annotation.item.equals("tnc")) {
                                onTermsOfUseClicked()
                            } else if (annotation.item.equals("pp")) {
                                onPrivacyPolicyClicked()
                            }
                        }
                }
            )

            ClickableText(
                modifier = Modifier
                    .wrapContentWidth()
                    .paddingFromBaseline(bottom = 16.dp),
                text = annotatedText2,
                style = MaterialTheme.typography.body2,
                onClick = { offset ->
                    // We check if there is an *URL* annotation attached to the text
                    // at the clicked position
                    annotatedText1.getStringAnnotations(tag = "link", start = offset, end = offset)
                        .firstOrNull()?.let { annotation ->
                            // If yes, we log its value
                            if (annotation.item.equals("tnc")) {
                                onTermsOfUseClicked()
                            } else if (annotation.item.equals("pp")) {
                                onPrivacyPolicyClicked()
                            }
                        }
                }
            )

            BloomSecondaryButton(
                isDarkTheme = isDarkTheme,
                text = "Log in",
                onClick = onLoginClicked
            )
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LoginScreenLightPreview() {
    MyTheme {
        LoginScreen(isDarkTheme = false)
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun LoginScreenDarkPreview() {
    MyTheme(darkTheme = true) {
        LoginScreen(isDarkTheme = true)
    }
}
