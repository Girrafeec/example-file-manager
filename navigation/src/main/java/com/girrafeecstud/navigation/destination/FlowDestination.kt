package com.girrafeecstud.navigation.destination

import com.girrafeecstud.navigation.*

sealed class FlowDestination(
    private val _destinationId: Int
) : NavigationDestination {

    override val destinationId: Int
        get() = _destinationId

    object FileListFlow : FlowDestination(
        _destinationId = R.id.file_list_flow_fragment
    )

}
