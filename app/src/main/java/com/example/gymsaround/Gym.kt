package com.example.gymsaround

data class Gym(
    var id: Int,
    val name: String,
    val address: String,
    var isFavourite: Boolean = false
)

val listOfGyms = listOf<Gym>(
    Gym(
        1,
        name = "UpTown Gym",
        address = "20 El-Gihad, Mit Akaba, Agoza, Gize Governorate 3745125, Egypt"
    ),
    Gym(
        2,
        name = "Gold's Gym",
        address = "61-Syria, Mit Akaba, Agoza, Gize Governorate 3745125, Egypt"
    ),
    Gym(
        3,
        name = "Hammer Gym",
        address = "7-sphinx Square, Al Huwaiytagyah, Agoza, Gize Governorate 3745125, Egypt"
    ),
    Gym(
        4,
        name = "I-Energy Gym",
        address = "22-Gool Gamal, Al Huwaiytagyah, Agoza, Gize Governorate 3745125, Egypt"
    ),
    Gym(
        5,
        name = "H2o Gym & Spa",
        address = "32-Anas Ibrahim Malek, Mit Akaba, Agoza, Gize Governorate 3745125, Egypt"
    )

)