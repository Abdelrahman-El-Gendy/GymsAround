package com.example.gymsaround

import com.example.gymsaround.Gyms.data.remote.RemoteGym

object DummyGymsList {

    fun getDummyGymsList() = arrayListOf(
        RemoteGym(0, "n0", "p0", false),
        RemoteGym(1, "n1", "p1", false),
        RemoteGym(2, "n2", "p2", false),
        RemoteGym(3, "n3", "p3", false),
        RemoteGym(4, "n4", "p4", false)
    )
}
