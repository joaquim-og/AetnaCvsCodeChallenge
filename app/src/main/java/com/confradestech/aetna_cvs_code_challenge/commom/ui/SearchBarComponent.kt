package com.confradestech.aetna_cvs_code_challenge.commom.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.confradestech.aetna_cvs_code_challenge.R
import com.confradestech.aetna_cvs_code_challenge.commom.theme.AetnaCvsCodeChallengeTheme
import com.confradestech.aetna_cvs_code_challenge.commom.utils.PreviewProfileScreens
import com.confradestech.aetna_cvs_code_challenge.commom.utils.extensions.hasInternet

@Composable
fun SearchBarComponent(
    onSearch: (String) -> Unit
) {
    var searchText by rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = searchText,
        onValueChange = {
            searchText = it
            if (searchText.isNotEmpty() && context.hasInternet()) {
                onSearch(searchText)
            }
            if (context.hasInternet().not()){
                showToastError(context)
            }
        },
        placeholder = {
            Text(text = stringResource(R.string.search_bar_placeholder))
        },
        singleLine = false,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(R.string.search_bar_content_description)
            )
        },
        trailingIcon = {
            Icon(
                modifier = Modifier.clickable {
                    keyboardController?.hide()
                    if (searchText.isNotEmpty() && context.hasInternet()) {
                        onSearch(searchText)
                        searchText = ""
                    }
                    if (context.hasInternet().not()){
                        showToastError(context)
                    }
                },
                imageVector = Icons.AutoMirrored.Filled.Send,
                contentDescription = stringResource(R.string.search_bar_content_send_description)
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Send
        ),
        keyboardActions = KeyboardActions(
            onSend = {
                keyboardController?.hide()
                if (searchText.isNotEmpty() && context.hasInternet()) {
                    onSearch(searchText)
                    searchText = ""
                }
                if (context.hasInternet().not()){
                    showToastError(context)
                }
            }
        )
    )
}

private fun showToastError(context: Context) {
    Toast.makeText(context, R.string.no_internet_connection, Toast.LENGTH_SHORT).show()
}

@Composable
@PreviewProfileScreens
fun SearchBarPreview() {
    AetnaCvsCodeChallengeTheme {
        SearchBarComponent(
            onSearch = { /*No-op for previews */ }
        )
    }
}