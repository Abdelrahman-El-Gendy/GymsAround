package com.example.gymsaround

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.ContentAlpha
import androidx.wear.compose.material.LocalContentAlpha
import com.example.gymsaround.ui.theme.GymsAroundTheme
import com.example.gymsaround.ui.theme.Purple200

@Composable
fun GymsScreen(onItemClick: (id: Int) -> Unit) {

    val vm: GymsViewModel = viewModel()
//    LaunchedEffect(
//        key1 = "request_gym_list"
//    ) {
//        vm.getGyms()
//    }
    LazyColumn {
        items(vm.state) { gymItem ->
            GymItem(
                gym = gymItem,
                onFavouriteIconClick = { vm.toggleFavouriteState(it) },
                onItemClick = { id -> onItemClick(id) }
            )
        }
    }
//    Column(Modifier.verticalScroll(rememberScrollState())) {
//        listOfGyms.forEach { gymItem ->
//            GymItem(gymItem)
//        }
//    }
}

@Composable
fun GymItem(
    gym: Gym,
    onFavouriteIconClick: (id: Int) -> Unit,
    onItemClick: (id: Int) -> Unit
) {
    // State Hoisting (Leveling Up)
    //var isFavourite by remember { mutableStateOf(false) }
    val icon = if (gym.isFavourite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .padding(8.dp)
            .clickable { onItemClick(gym.id) }
    )
    {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp),
        ) {
            DefaultGymIcon(
                Icons.Filled.Place,
                Modifier.weight(0.15f),
                contentDescription = "Location Icon"
            )
            GymDetails(gym, Modifier.weight(0.70f))
            DefaultGymIcon(
                icon,
                Modifier.weight(0.15f),
                contentDescription = "Favourite Gym Icon"
            ) {
                onFavouriteIconClick(gym.id)
            }

        }
    }
}

@Composable
fun DefaultGymIcon(
    icon: ImageVector,
    modifier: Modifier,
    contentDescription: String,
    onFavouriteIconClick: () -> Unit = {}
) {

    Image(
        imageVector = icon,
        contentDescription = contentDescription,
        modifier = modifier
            .padding(8.dp)
            .clickable {
                onFavouriteIconClick()
            },
        colorFilter = ColorFilter.tint(
            Color.DarkGray
        )
    )
}


@Composable
fun GymDetails(
    gym: Gym,
    modifier: Modifier,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start
) {
    Column(modifier = modifier.padding(8.dp)) {
        Text(
            text = gym.name,
            style = MaterialTheme.typography.headlineSmall,
            color = Purple200,
        )
        CompositionLocalProvider(
            LocalContentAlpha provides ContentAlpha.medium
        ) {

            Text(
                text = gym.address,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}


//@Preview(showSystemUi = true)
//@Composable
//private fun GymsScreenPreview() {
//    GymsAroundTheme {
//        GymsScreen()
//    }
//}


