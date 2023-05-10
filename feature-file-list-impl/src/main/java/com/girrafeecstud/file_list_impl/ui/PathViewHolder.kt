package com.girrafeecstud.file_list_impl.ui

import android.content.Context
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.girrafeecstud.file_list_api.domain.FileInfo
import com.girrafeecstud.file_list_api.domain.PathInfo
import com.girrafeecstud.file_list_impl.R
import com.girrafeecstud.file_list_impl.databinding.PathItemBinding

class PathViewHolder(
    val binding: PathItemBinding,
    private val context: Context
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(path: PathInfo, clickEvent: PathClickEvent?) {

        Log.i("file list path", "path=$path")

        val color = if (path.isPathActive)
            ContextCompat.getColor(context, com.girrafeecstud.core_ui.R.color.light_blue)
        else
            ContextCompat.getColor(context, com.girrafeecstud.core_ui.R.color.light_gray_text)

        val font = if (path.isPathActive)
            ResourcesCompat.getFont(context, com.girrafeecstud.core_ui.R.font.montserrat_bold)
        else
            ResourcesCompat.getFont(context, com.girrafeecstud.core_ui.R.font.montserrat)

        binding.pathName.setTextColor(color)
        binding.pathName.typeface = font
        binding.pathName.text = path.name

        binding.pathItem.setOnClickListener { clickEvent?.onPathClicked(path = path) }
    }

}
