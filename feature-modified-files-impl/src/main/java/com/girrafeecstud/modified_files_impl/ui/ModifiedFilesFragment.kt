/* Created by Girrafeec */

package com.girrafeecstud.modified_files_impl.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.girrafeecstud.core_components.presentation.MainViewModelFactory
import com.girrafeecstud.core_ui.extension.removeView
import com.girrafeecstud.core_ui.extension.showView
import com.girrafeecstud.core_ui.ui.BaseFragment
import com.girrafeecstud.file_list_api.domain.FileInfo
import com.girrafeecstud.file_list_api.domain.FileType
import com.girrafeecstud.file_list_api.ui.FileClickEvent
import com.girrafeecstud.modified_files_impl.databinding.FragmentModifiedFilesBinding
import com.girrafeecstud.modified_files_impl.presentation.ModifiedFilesComponentViewModel
import com.girrafeecstud.modified_files_impl.presentation.ModifiedFilesUiState
import com.girrafeecstud.modified_files_impl.presentation.ModifiedFilesViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class ModifiedFilesFragment
    : BaseFragment<ModifiedFilesUiState>(), FileClickEvent {

    private var _binding: FragmentModifiedFilesBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var modifiedFilesAdapter: ModifiedFilesAdapter

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    private val modifiedFilesViewModel: ModifiedFilesViewModel by viewModels {
        viewModelFactory
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ViewModelProvider(this).get(ModifiedFilesComponentViewModel::class.java)
            .modifiedFilesComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentModifiedFilesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initFilesRecView()
    }

    override fun registerObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                modifiedFilesViewModel.state
                    .onEach { state ->
                        setState(state = state)
                    }
                    .launchIn(viewLifecycleOwner.lifecycleScope)
            }
        }
    }

    override fun setListeners() {
        super.setListeners()
    }

    override fun setState(state: ModifiedFilesUiState) {
        state.files.let {
            if (it == null) {
                binding.files.removeView()
                binding.noModifiedFilesMessageContainer.showView()
                return
            }
            binding.files.showView()
            modifiedFilesAdapter.updateFileList(files = it)
        }
        state.isLoading.let { isLoading ->
            if (isLoading) {
                binding.progress.showView()
                binding.files.removeView()
                binding.noModifiedFilesMessageContainer.removeView()
                return
            }
        }
    }

    override fun onFileClicked(file: FileInfo) {
        if (file.fileType == FileType.FOLDER)
            return
    }

    private fun initFilesRecView() {
        binding.files.let { files ->
            modifiedFilesAdapter.clickEvent = this
            files.overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            files.adapter = modifiedFilesAdapter
            files.layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
        }
    }

}