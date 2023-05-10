package com.girrafeecstud.file_list_impl.ui

import com.girrafeecstud.file_list_api.domain.FileInfo
import com.girrafeecstud.file_list_api.domain.PathInfo

interface PathClickEvent {

    fun onPathClicked(path: PathInfo)

}
