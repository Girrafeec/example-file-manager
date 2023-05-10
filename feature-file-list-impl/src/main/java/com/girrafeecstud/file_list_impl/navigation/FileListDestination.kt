package com.girrafeecstud.file_list_impl.navigation

import com.girrafeecstud.file_list_impl.R
import com.girrafeecstud.navigation.destination.NavigationDestination

sealed class FileListDestination(
    override val destinationId: Int,
    override val args: Map<String, Any> = emptyMap()
) : NavigationDestination {

    class FileListFragment(
        val dirPath: String
    ) : FileListDestination(
        destinationId = R.id.file_list_fragment,
        args = mapOf("dirPath" to dirPath)
    )

}
