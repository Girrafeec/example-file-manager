package com.girrafeecstud.file_list_impl.di.module

import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.girrafeecstud.core_base.di.ViewModelKey
import com.girrafeecstud.file_list_impl.di.annotation.FileListScope
import com.girrafeecstud.file_list_impl.presentation.FileListViewModel
import com.girrafeecstud.file_list_impl.ui.FileViewHolder
import com.girrafeecstud.file_list_impl.ui.FilesAdapter
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface FileListModule {

    @FileListScope
    @Binds
    @ViewModelKey(FileListViewModel::class)
    @IntoMap
    fun bindFileListViewModel(impl: FileListViewModel): ViewModel

    @FileListScope
    @Binds
    fun bindFilesAdapter(impl: FilesAdapter): RecyclerView.Adapter<FileViewHolder>

}
