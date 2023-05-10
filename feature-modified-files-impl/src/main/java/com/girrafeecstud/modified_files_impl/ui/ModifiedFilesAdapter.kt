package com.girrafeecstud.modified_files_impl.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.girrafeecstud.file_list_api.domain.FileInfo
import com.girrafeecstud.file_list_api.ui.FileClickEvent
import com.girrafeecstud.modified_files_impl.databinding.FileItemBinding
import javax.inject.Inject

class ModifiedFilesAdapter @Inject constructor()
    : RecyclerView.Adapter<ModifiedFileViewHolder>() {

    var clickEvent: FileClickEvent? = null

    private var files = ArrayList<FileInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModifiedFileViewHolder {
        val binding = FileItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ModifiedFileViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: ModifiedFileViewHolder, position: Int) {
        holder.bind(file = files[position], clickEvent = clickEvent)
    }

    override fun getItemCount(): Int = files.size

    fun updateFileList(files: List<FileInfo>) {
        this.files = ArrayList(files)
        notifyDataSetChanged()
    }

}
