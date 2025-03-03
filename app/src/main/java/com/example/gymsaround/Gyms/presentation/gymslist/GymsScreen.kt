package com.example.gymsaround.Gyms.presentation.gymslist

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.ContentAlpha
import androidx.wear.compose.material.LocalContentAlpha
import com.example.gymsaround.Gyms.domain.Gym
import com.example.gymsaround.Gyms.presentation.SemanticsDescription
import com.example.gymsaround.ui.theme.Purple200

@Composable
fun GymsScreen(
    onItemClick: (id: Int) -> Unit,
    state: GymsScreenState,
    onFavouriteIconClick: (Int, Boolean) -> Unit

) {

//    val vm: GymsViewModel = viewModel()
//    val state = vm.state.value

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center,

        )
    {
        LazyColumn {
            items(state.gyms) { gymItem ->
                GymItem(
                    gym = gymItem,
                    onFavouriteIconClick = { id, oldState ->
                        onFavouriteIconClick(id, oldState)
                    },
                    onItemClick = { id -> onItemClick(id) }
                )
            }
        }
        if (state.isLoading) CircularProgressIndicator(
            Modifier.semantics {
                this.contentDescription = SemanticsDescription.GYMS_LIST_LOADING
            }
        )
        state.error?.let { errorText ->
            Text(text = errorText)
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
    onFavouriteIconClick: (id: Int, oldState: Boolean) -> Unit,
    onItemClick: (id: Int) -> Unit
) {
    // State Hoisting (Leveling Up)
    //var isFavourite by remember { mutableStateOf(false) }
    val icon = if (gym.isFavourite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .padding(6.dp)
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
                onFavouriteIconClick(gym.id, gym.isFavourite)
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
    Column(modifier = modifier.padding(2.dp)) {
        Text(
            text = gym.name,
            style = MaterialTheme.typography.headlineSmall,
            color = Purple200,
            fontSize = 20.sp
        )
        CompositionLocalProvider(
            LocalContentAlpha provides ContentAlpha.medium
        ) {

            Text(
                text = gym.address,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 14.sp
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


