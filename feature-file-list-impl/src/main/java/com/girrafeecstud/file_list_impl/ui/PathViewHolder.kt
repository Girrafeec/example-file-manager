package com.girrafeecstud.file_list_impl.ui

import androidx.recyclerview.widget.RecyclerView
import com.girrafeecstud.file_list_api.domain.FileInfo
import com.girrafeecstud.file_list_api.domain.PathInfo
import com.girrafeecstud.file_list_impl.databinding.PathItemBinding

class PathViewHolder(
    val binding: PathItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(path: PathInfo, clickEvent: PathClickEvent?) {
        binding.pathName.text = path.name

        binding.pathItem.setOnClickListener { clickEvent?.onPathClicked(path = path) }
    }

}
