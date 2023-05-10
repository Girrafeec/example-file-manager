/* Created by Girrafeec */

package com.girrafeecstud.file_list_impl.ui

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.girrafeecstud.core_ui.extension.showView
import com.girrafeecstud.file_list_api.domain.FileInfo
import com.girrafeecstud.file_list_api.domain.FileType
import com.girrafeecstud.file_list_api.getFileCreationDateString
import com.girrafeecstud.file_list_api.getFileSizeString
import com.girrafeecstud.file_list_api.setFileType
import com.girrafeecstud.file_list_api.ui.FileClickEvent
import com.girrafeecstud.file_list_impl.databinding.FileItemBinding

class FileViewHolder(
    val binding: FileItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(file: FileInfo, clickEvent: FileClickEvent?) {
        binding.fileTypeIcon.setFileType(fileType = file.fileType)
        binding.fileName.text = file.name
        binding.fileSize.text = getFileSizeString(size = file.size)
        binding.fileCreationDate.text = getFileCreationDateString(date = file.creationDate)

        if (file.fileType == FileType.FOLDER)
            binding.arrowForward.showView()

        binding.fileItem.setOnClickListener { clickEvent?.onFileClicked(file = file) }
    }

}