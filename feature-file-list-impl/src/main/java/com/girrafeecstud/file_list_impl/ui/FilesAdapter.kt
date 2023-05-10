/* Created by Girrafeec */

package com.girrafeecstud.file_list_impl.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.girrafeecstud.file_list_api.domain.FileInfo
import com.girrafeecstud.file_list_api.ui.FileClickEvent
import com.girrafeecstud.file_list_impl.databinding.FileItemBinding
import javax.inject.Inject

class FilesAdapter @Inject constructor() : RecyclerView.Adapter<FileViewHolder>() {

    var clickEvent: FileClickEvent? = null

    private var files = ArrayList<FileInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileViewHolder {
        val binding = FileItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FileViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: FileViewHolder, position: Int) {
        holder.bind(file = files[position], clickEvent = clickEvent)
    }

    override fun getItemCount(): Int = files.size

    fun updateFileList(files: List<FileInfo>) {
        this.files = ArrayList(files)
        notifyDataSetChanged()
    }
}