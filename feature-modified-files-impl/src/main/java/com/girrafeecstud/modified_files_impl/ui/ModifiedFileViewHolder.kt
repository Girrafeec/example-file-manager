package com.girrafeecstud.modified_files_impl.ui

import androidx.recyclerview.widget.RecyclerView
import com.girrafeecstud.core_ui.extension.showView
import com.girrafeecstud.file_list_api.domain.FileInfo
import com.girrafeecstud.file_list_api.domain.FileType
import com.girrafeecstud.file_list_api.getFileCreationDateString
import com.girrafeecstud.file_list_api.getFileSizeString
import com.girrafeecstud.file_list_api.setFileType
import com.girrafeecstud.file_list_api.ui.FileClickEvent
import com.girrafeecstud.modified_files_impl.databinding.FileItemBinding

class ModifiedFileViewHolder(
    val binding: FileItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(file: FileInfo, clickEvent: FileClickEvent?) {
        binding.fileTypeIcon.setFileType(fileType = file.fileType)
        binding.fileName.text = file.name
        binding.fileSize.text = getFileSizeString(size = file.size)
        binding.fileCreationDate.text = getFileCreationDateString(date = file.creationDate)

        binding.fileItem.setOnClickListener { clickEvent?.onFileClicked(file = file) }
    }

}
