package com.girrafeecstud.file_list_impl.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.girrafeecstud.file_list_api.domain.FileInfo
import com.girrafeecstud.file_list_api.domain.PathInfo
import com.girrafeecstud.file_list_impl.databinding.PathItemBinding
import javax.inject.Inject

class PathsAdapter @Inject constructor(

) : RecyclerView.Adapter<PathViewHolder>() {

    private var paths = ArrayList<PathInfo>()

    var clickEvent: PathClickEvent? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PathViewHolder {
        val binding = PathItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PathViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PathViewHolder, position: Int) {
        holder.bind(path = paths[position], clickEvent = clickEvent)
    }

    override fun getItemCount(): Int = paths.size

//    fun updatePaths(paths: List<PathInfo>) {
//        this.paths = ArrayList(paths)
//        notifyDataSetChanged()
//    }

    fun addPath(path: PathInfo) {
        for (pathItem in paths)
            pathItem.isPathActive = false
        paths.add(path)
        notifyDataSetChanged()
    }

    fun goToPath(dirPath: String) {
        for (i in paths.size-1 downTo 0) {
            val path = paths[i].path
            if (path == dirPath) {
                Log.i("paths adapter", "paths size before=${paths.size}")
                paths.subList(i+1, paths.size).clear()
                Log.i("paths adapter", "paths size after=${paths.size}")
                notifyDataSetChanged()
                return
            }
        }

    }
}
