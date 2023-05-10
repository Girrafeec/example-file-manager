package com.girrafeecstud.modified_files_impl.di.module

import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.girrafeecstud.core_base.di.ViewModelKey
import com.girrafeecstud.modified_files_impl.di.annotation.ModifiedFilesScope
import com.girrafeecstud.modified_files_impl.presentation.ModifiedFilesViewModel
import com.girrafeecstud.modified_files_impl.ui.ModifiedFileViewHolder
import com.girrafeecstud.modified_files_impl.ui.ModifiedFilesAdapter
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ModifiedFilesModule {

    @ModifiedFilesScope
    @Binds
    @ViewModelKey(ModifiedFilesViewModel::class)
    @IntoMap
    fun bindModifiedFilesViewModel(impl: ModifiedFilesViewModel): ViewModel

    @ModifiedFilesScope
    @Binds
    fun bindModifiedFilesAdapter(impl: ModifiedFilesAdapter): RecyclerView.Adapter<ModifiedFileViewHolder>
}
