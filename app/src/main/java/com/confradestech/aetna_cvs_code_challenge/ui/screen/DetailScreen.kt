package com.confradestech.aetna_cvs_code_challenge.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.confradestech.aetna_cvs_code_challenge.R
import com.confradestech.aetna_cvs_code_challenge.commom.previewAssets.flickrItemPreviewAsset
import com.confradestech.aetna_cvs_code_challenge.commom.theme.AetnaCvsCodeChallengeTheme
import com.confradestech.aetna_cvs_code_challenge.commom.ui.BodyTextComponent
import com.confradestech.aetna_cvs_code_challenge.commom.utils.PreviewProfileScreens
import com.confradestech.aetna_cvs_code_challenge.commom.utils.extensions.toUserFriendlyDate
import com.confradestech.aetna_cvs_code_challenge.domain.model.FlickrItem

@Composable
fun DetailScreen(
    flickrItem: FlickrItem
) {
    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        BuildImage(mediaLink = flickrItem.flickrItemMedia.mediaLink)
        Spacer(modifier = Modifier.height(12.dp))
        BuildTextElements(flickrItem = flickrItem)
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun BuildImage(mediaLink: String) {
    GlideImage(
        model = mediaLink,
        modifier = Modifier
            .size(500.dp),
        contentDescription = stringResource(R.string.flickr_media_item_description),
        contentScale =  ContentScale.Fit,
        alignment = Alignment.CenterStart
    ) { requestBuilder ->
        try {
            requestBuilder
                .transition(DrawableTransitionOptions.withCrossFade())
                .fitCenter()
                .load(mediaLink)
                .error(R.drawable.baseline_image_not_supported_24)
        } catch (error: Throwable) {
            requestBuilder
                .transition(DrawableTransitionOptions.withCrossFade())
                .fitCenter()
                .load(mediaLink)
                .error(R.drawable.baseline_image_not_supported_24)
        }
    }
}

@Composable
private fun BuildTextElements(flickrItem: FlickrItem) {

    val title = stringResource(R.string.details_title).replace(
        "#value",
        flickrItem.title
    )
    val description = stringResource(R.string.details_description).replace(
        "#value",
        flickrItem.description
    )
    val author = stringResource(R.string.details_author).replace(
        "#value",
        flickrItem.author
    )
    val published = stringResource(R.string.details_published).replace(
        "#value",
        flickrItem.published.toUserFriendlyDate()
    )

    BodyTextComponent(
        text = title,
        textAlign = TextAlign.Start
    )
    Spacer(modifier = Modifier.height(5.dp))
    BodyTextComponent(
        text = description,
        textAlign = TextAlign.Start
    )
    Spacer(modifier = Modifier.height(5.dp))
    BodyTextComponent(
        text = author,
        textAlign = TextAlign.Start
    )
    Spacer(modifier = Modifier.height(5.dp))
    BodyTextComponent(
        text =published,
        textAlign = TextAlign.Start
    )
}

@PreviewProfileScreens
@Composable
fun DetailScreenPreview() {
    AetnaCvsCodeChallengeTheme {
        DetailScreen(
            flickrItem = flickrItemPreviewAsset
        )
    }
}