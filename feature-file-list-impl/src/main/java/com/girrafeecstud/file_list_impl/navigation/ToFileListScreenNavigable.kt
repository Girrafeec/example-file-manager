/* Created by Girrafeec */

package com.girrafeecstud.file_list_impl.navigation

import com.girrafeecstud.navigation.ToScreenNavigable

interface ToFileListScreenNavigable : ToScreenNavigable<FileListDestination> {
    override fun navigateToScreen(destination: FileListDestination)
}