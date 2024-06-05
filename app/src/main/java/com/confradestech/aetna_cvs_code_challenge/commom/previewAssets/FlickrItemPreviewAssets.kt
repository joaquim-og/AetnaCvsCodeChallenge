package com.confradestech.aetna_cvs_code_challenge.commom.previewAssets

import com.confradestech.aetna_cvs_code_challenge.domain.model.FlickrItem
import com.confradestech.aetna_cvs_code_challenge.domain.model.FlickrItemMedia

val flickrItemMediaPreviewAsset = FlickrItemMedia(
    mediaLink = "title"
)

val flickrItemPreviewAsset = FlickrItem(
    author = "author",
    authorId = "authorId",
    dateTaken = "dateTaken",
    description = "description",
    link = "link",
    flickrItemMedia = flickrItemMediaPreviewAsset,
    published = "published",
    tags = "tags",
    title = "title"
)