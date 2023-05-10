/* Created by Girrafeec */

package com.girrafeecstud.navigation.destination

import com.girrafeecstud.navigation.R

sealed class FlowDestination(
    override val destinationId: Int,
    override val args: Map<String, Any> = emptyMap()
) : NavigationDestination {

    object FileManagerFlow : FlowDestination(
        destinationId = R.id.file_manager_flow_fragment
    )

    class FileListFlow(
        val defaultPath: String
    ) : FlowDestination(
        destinationId = R.id.file_list_flow_fragment,
        args = mapOf("defaultPath" to defaultPath)
    )

    object ModifiedFilesFlow : FlowDestination(
        destinationId = R.id.modified_files_flow_fragment,
    )

}