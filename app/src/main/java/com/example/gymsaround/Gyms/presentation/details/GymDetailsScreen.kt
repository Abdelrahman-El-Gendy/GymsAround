package com.example.gymsaround.Gyms.presentation.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gymsaround.Gyms.presentation.gymslist.DefaultGymIcon
import com.example.gymsaround.Gyms.presentation.gymslist.GymDetails

@Composable
fun GymDetailsScreen() {
    val viewModel: GymsDetailsViewModel = viewModel()

    val item = viewModel.state.value

    item?.let {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DefaultGymIcon(
                icon = Icons.Filled.Place,
                modifier = Modifier.padding(bottom = 32.dp, top = 32.dp),
                contentDescription = "Gym Location"
            )
            GymDetails(
                gym = it,
                modifier = Modifier.padding(bottom = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            Text(
                text = if (item.isOpen) "Gym is open!" else "Gym is close!",
                color = if (item.isOpen) Color.Green else Color.Red
            )

        }
    }
}