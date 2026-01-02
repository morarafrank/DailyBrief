package com.morarafrank.dailybrief.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.morarafrank.dailybrief.R
import com.morarafrank.dailybrief.ui.NewsViewModel
import com.morarafrank.dailybrief.ui.screens.composables.FetchingNewsError
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
    navigateToSingleArticle: (String) -> Unit,
    viewModel: NewsViewModel = hiltViewModel()
) {

    val newsUiState by viewModel.newsUiState.collectAsStateWithLifecycle()
    val headlinesUiState by viewModel.sourcesUiState.collectAsStateWithLifecycle()
    val latestNewsUiState by viewModel.newsUiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getAllNews("Breaking News")
    }

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
                        onClick = { /* TODO: Implement search action */ }
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

            when(newsUiState){
                is NewsUiState.Loading -> {
                    LoadingNews(
                        modifier = modifier.fillMaxSize().padding(it),
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

//                    Text(
//                        text = "Total Articles: $articles",
//                        fontSize = 18.sp,
//                        modifier = modifier.padding(it).padding(16.dp),
//                        style = Typography.bodyLarge
//                    )
                    LazyColumn(
                        modifier = modifier.fillMaxSize()
                            .padding(it)
                            .padding(8.dp)
                    ) {
                        items(articles) { article ->
                             NewsItemCard(
                                newsArticle = article,
                                onClick = {
                                    navigateToSingleArticle(article.url ?: "")
                                }
                             )
                        }
                    }
                }
            }
        }
    )
}

