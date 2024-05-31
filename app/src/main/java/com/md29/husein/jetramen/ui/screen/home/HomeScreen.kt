package com.md29.husein.jetramen.ui.screen.home

import com.md29.husein.jetramen.ui.components.HomeSection
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.md29.husein.jetramen.R
import com.md29.husein.jetramen.data.model.FakeMenuData
import com.md29.husein.jetramen.data.model.OrderMenu
import com.md29.husein.jetramen.di.Injection
import com.md29.husein.jetramen.ui.ViewModelFactory
import com.md29.husein.jetramen.ui.common.UiState
import com.md29.husein.jetramen.ui.components.MenuItem
import com.md29.husein.jetramen.ui.components.Search

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit,
) {
    val query by viewModel.query

    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllRewards()
            }

            is UiState.Success -> {
                HomeContent(
                    query = query,
                    onQueryChange = viewModel::search,
                    listMenu = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail
                )
            }

            is UiState.Error -> {}
        }
    }
}

@Composable
fun HomeContent(
    query: String,
    onQueryChange: (String) -> Unit,
    listMenu: List<OrderMenu>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
) {
    Scaffold { innerPadding ->
        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            Banner(
                query = query,
                onQueryChange = onQueryChange
            )
            HomeSection(
                title = stringResource(R.string.menu),
                content = {
                    MenuRow(
                        listMenu = listMenu,
                        navigateToDetail = navigateToDetail
                    )
                }
            )
            HomeSection(
                title = stringResource(R.string.menu_favorite),
                content = { FavoriteMenuRow(navigateToDetail = navigateToDetail) }
            )
        }
    }
}

@Composable
fun Banner(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(R.drawable.banner),
            contentDescription = "Banner Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(250.dp)
                .fillMaxWidth(),
        )
        Search(
            query = query,
            onQueryChange = onQueryChange,
            modifier = modifier.testTag("searchBar")
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MenuRow(
    listMenu: List<OrderMenu>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier.testTag("MenuList")
    ) {
        if (listMenu.isEmpty()) {
            item {
                Text(
                    text = stringResource(R.string.empty_list),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        } else {
            items(listMenu, key = {it.menu.id}) { data ->
                MenuItem(
                    image = data.menu.image,
                    title = data.menu.title,
                    price = data.menu.price,
                    modifier = Modifier
                        .animateItemPlacement(tween(durationMillis = 100, easing = FastOutSlowInEasing))
                        .clickable {
                        navigateToDetail(data.menu.id)
                    }
                )
            }
        }
    }
}

@Composable
fun FavoriteMenuRow(
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(FakeMenuData.dummyBestSellerMenu) { data ->
            MenuItem(
                image = data.image,
                title = data.title,
                price = data.price,
                modifier = Modifier.clickable {
                    navigateToDetail(data.id)
                }
            )
        }
    }
}
