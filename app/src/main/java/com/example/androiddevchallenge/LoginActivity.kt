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

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.androiddevchallenge.ui.screen.LoginScreen
import com.example.androiddevchallenge.ui.theme.MyTheme

class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                LoginScreen(
                    onLoginClicked = {
                        startActivity(Intent(this, MainActivity::class.java))
                    },
                    onTermsOfUseClicked = {
                        Toast.makeText(this, "Terms of Use clicked.", Toast.LENGTH_SHORT).show()
                    },
                    onPrivacyPolicyClicked = {
                        Toast.makeText(this, "Privacy Policy clicked.", Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LoginScreenLightPreview() {
    MyTheme(darkTheme = false) {
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
