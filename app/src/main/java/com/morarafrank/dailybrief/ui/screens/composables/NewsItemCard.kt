package com.morarafrank.dailybrief.ui.screens.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.R
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.morarafrank.dailybrief.domain.model.Article
import com.morarafrank.dailybrief.ui.theme.Typography
import com.morarafrank.dailybrief.utils.UiUtils

@Composable
fun NewsItemCard(
    modifier: Modifier = Modifier,
    newsArticle: Article,
    onClick: (articleId: String) -> Unit
) {

    val context = LocalContext.current

//    Card(
//        modifier = modifier
//            .fillMaxWidth()
//            .padding(4.dp)
//            .wrapContentSize(),
//
//    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp)
                .wrapContentSize()
                .clickable{
                    onClick(newsArticle.url ?: "")
                },
            horizontalArrangement = Arrangement.Start
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(newsArticle.urlToImage)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = modifier
                    .size(80.dp)
                    .padding(2.dp),
                contentScale = ContentScale.Fit,
                placeholder = painterResource(com.morarafrank.dailybrief.R.drawable.placeholder_image),
                error = painterResource(com.morarafrank.dailybrief.R.drawable.broken_image)
            )


            Column(
                modifier = modifier.fillMaxWidth().padding(4.dp),
                verticalArrangement = Arrangement.spacedBy(
                    4.dp, Alignment.CenterVertically
                ),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = newsArticle.title ?: "No Title",
                    style = Typography.bodyMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = newsArticle.description ?: "No Description",
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = Typography.bodySmall
                )

                Text(
                    text = newsArticle.source?.name ?: "No Description",
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = Typography.bodySmall.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )


                Row(
                    modifier= modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "By ${newsArticle.author ?:" Unknown"}",
                        style = Typography.bodySmall.copy(
                            fontSize = 10.sp
                        )
                    )
                    Text(
                        text = UiUtils.formatLastUpdated(newsArticle.publishedAt),
                        style = Typography.bodySmall.copy(
                            fontSize = 10.sp
                        )
                    )
                }
            }
        }
//    }

}

//@Preview
//@Composable
//private fun PrevArticleCard() {
//    NewsItemCard(
//        newsArticle = Article(
//            title = "Sample News Title",
//            description = "This is a sample description for the news article.",
//            url = "https://example.com/news/sample-news-title",
//            urlToImage = "https://example.com/images/sample-news-image.jpg",
//            publishedAt = "2024-06-01T12:00:00Z",
//            source = null,
//            author = "Sample Source"
//        )
//    )
//}