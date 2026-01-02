package com.morarafrank.dailybrief.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.morarafrank.dailybrief.R
import com.morarafrank.dailybrief.ui.NewsViewModel
import com.morarafrank.dailybrief.ui.screens.composables.FetchingNewsError
import com.morarafrank.dailybrief.ui.screens.composables.HeadlinesItemCard
import com.morarafrank.dailybrief.ui.screens.composables.LatestNewsUi
import com.morarafrank.dailybrief.ui.screens.composables.LoadingNews
import com.morarafrank.dailybrief.ui.screens.composables.NewsItemCard
import com.morarafrank.dailybrief.ui.theme.Typography
import com.morarafrank.dailybrief.utils.NewsUiState
import kotlin.math.sign

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToHeadlinesScreen: () -> Unit,
    navigateToLatestNewsScreen: () -> Unit,
    navigateToSingleArticle: (articleUrl: String) -> Unit,
    viewModel: NewsViewModel = hiltViewModel()
) {

    val newsUiState by viewModel.newsUiState.collectAsStateWithLifecycle()
    val headlinesUiState by viewModel.headlinesUiState.collectAsStateWithLifecycle()
    val latestNewsUiState by viewModel.newsUiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getAllNews("Breaking News")
        viewModel.getTopHeadlines()
    }

    var searchClicked by remember { mutableStateOf(false) }
    var searchText by remember{mutableStateOf("")}


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "DailyBrief",
                    )
                },
                modifier = modifier,
                actions = {
                    IconButton(
                        onClick = {
                            searchClicked = !searchClicked
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_search),
                            contentDescription = "Search"
                        )
                    }
                }
            )
        },
        content = {

            Column(
                modifier = modifier
                    .padding(it)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top)
            ) {

                AnimatedVisibility(visible = searchClicked) {
                       OutlinedTextField(
                           value = searchText,
                           onValueChange = {
                               searchText = it
                           },
                           modifier = modifier
                               .fillMaxWidth()
                               .padding(8.dp),
                           placeholder = {
                               Text(
                                   text = "Search News Articles",
                                   style = Typography.bodyMedium
                               )
                           },
                           trailingIcon = {
                               AnimatedVisibility(
                                   visible = searchText.isNotEmpty()
                               ) {
                                   IconButton(
                                       onClick = {
                                           searchText = ""
                                       },
                                   ) {
                                       Icon(
                                           painter = painterResource(R.drawable.ic_close),
                                           contentDescription = null
                                       )
                                   }
                               }
                           }
                       )
                   }

                Text(
                    text = "Top Headlines",
                    fontWeight = FontWeight.Bold,
                    modifier = modifier,
                    style=Typography.bodyLarge
                )

                when(headlinesUiState){
                    is NewsUiState.Idle -> {}
                    is NewsUiState.Loading -> {
                        LoadingNews(
                            modifier = modifier.fillMaxWidth().padding(it),
                            size = 100.dp,
                            title = "Loading Headlines...",
                            animation = R.raw.loading_anim0
                        )
                    }
                    is NewsUiState.Error -> {
                        val message = (headlinesUiState as NewsUiState.Error).message

                        FetchingNewsError(
                            modifier = modifier.fillMaxWidth().padding(it),
                            error = "No Headlines Available: $message",
                            onRetry = {
                                // Retry fetching headlines
                            }
                        )
                    }
                    is NewsUiState.Success -> {
                        val headLines = (headlinesUiState as NewsUiState.Success).data

                        LazyRow(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(start = 8.dp, end = 8.dp)
                        ) {
                            items(headLines) { headline ->
                                HeadlinesItemCard(
                                    newsArticle = headline,
                                    onClick = {
                                        viewModel.selectNewsArticle(headline.url ?: "")
                                        navigateToSingleArticle(it)
                                    }
                                )
                            }
                        }
                    }
                }

                when(newsUiState){
                    is NewsUiState.Loading -> {
                        LoadingNews(
                            modifier = modifier.padding(it),
                            size = 200.dp,
                            title = "Loading News...",
                            animation = R.raw.loading_anim0
                        )
                    }

                    is NewsUiState.Error -> {
                        val message = (newsUiState as NewsUiState.Error).message

                        FetchingNewsError(
                            modifier = modifier.fillMaxSize().padding(it),
                            error = "No News Available: $message",
                            onRetry = {
                                viewModel.getAllNews("bitcoin")
                            }
                        )
                    }
                    is NewsUiState.Idle -> {
                        // Initial state, could show a placeholder or nothing
                        Text(
                            "Idle State",
                            modifier = modifier.fillMaxSize().padding(it),
                            style = Typography.bodyLarge
                        )
                    }
                    is NewsUiState.Success -> {
                        val articles = (newsUiState as NewsUiState.Success).data

                        LazyColumn(
                            modifier = modifier.fillMaxSize()
                                .padding(8.dp)
                        ) {

                            item {
                                Text(
                                    text = "Latest News",
                                    fontWeight = FontWeight.Bold,
                                    modifier = modifier
                                        .padding(4.dp),
                                    style=Typography.bodyLarge
                                )
                            }


                            items(articles) { article ->
                                NewsItemCard(
                                    newsArticle = article,
                                    onClick = {
                                        viewModel.selectNewsArticle(article.url ?: "")
//                                    onClickArticle(it)
                                        navigateToSingleArticle(it)
                                    }
                                )
                            }
                        }
                    }
                }


            }
        }
    )
}

