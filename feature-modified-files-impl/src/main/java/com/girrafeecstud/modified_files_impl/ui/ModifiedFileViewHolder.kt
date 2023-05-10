package com.girrafeecstud.modified_files_impl.ui

import androidx.recyclerview.widget.RecyclerView
import com.girrafeecstud.file_list_api.domain.FileInfo
import com.girrafeecstud.file_list_api.ui.FileClickEvent
import com.girrafeecstud.modified_files_impl.databinding.FileItemBinding

class ModifiedFileViewHolder(
    val binding: FileItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(file: FileInfo, clickEvent: FileClickEvent?) {
        binding.fileName.text = file.name

        binding.fileItem.setOnClickListener { clickEvent?.onFileClicked(file = file) }
    }

}
