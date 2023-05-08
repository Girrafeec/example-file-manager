/* Created by Girrafeec */

package com.girrafeecstud.file_list_impl.ui

import com.girrafeecstud.file_list_api.domain.FileInfo

interface FileClickEvent {

    fun onFileClicked(file: FileInfo)

}