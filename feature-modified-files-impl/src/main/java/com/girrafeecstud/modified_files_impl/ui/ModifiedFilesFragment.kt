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
import com.girrafeecstud.file_list_api.domain.FileSortType
import com.girrafeecstud.file_list_api.domain.FileType
import com.girrafeecstud.file_list_api.ui.FileClickEvent
import com.girrafeecstud.modified_files_impl.databinding.BottomSheetSortBinding
import com.girrafeecstud.modified_files_impl.databinding.FragmentModifiedFilesBinding
import com.girrafeecstud.modified_files_impl.presentation.ModifiedFilesComponentViewModel
import com.girrafeecstud.modified_files_impl.presentation.ModifiedFilesUiState
import com.girrafeecstud.modified_files_impl.presentation.ModifiedFilesViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
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

        applySortTypeTitle()
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
        binding.sortTypeBtn.setOnClickListener { showSortDialog() }
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

    private fun applySortTypeTitle() {
        when (modifiedFilesViewModel.fileSortType) {
            FileSortType.BY_NAME ->
                binding.sortTypeName.text = "Sort by name"
            FileSortType.BY_SIZE_UP ->
                binding.sortTypeName.text = "Sort by size Up"
            FileSortType.BY_SIZE_DOWN ->
                binding.sortTypeName.text = "Sort by size Down"
            FileSortType.BY_EXTENSION ->
                binding.sortTypeName.text = "Sort By Extension"
        }
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

    private fun showSortDialog() {
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        val binding = BottomSheetSortBinding.inflate(layoutInflater)
        bottomSheetDialog.setContentView(binding.root)

        // Set the initial checked radio button based on the current sort type
        val currentSortType = modifiedFilesViewModel.fileSortType
        val checkedRadioButtonId = when (currentSortType) {
            FileSortType.BY_NAME -> binding.nameSort.id
            FileSortType.BY_SIZE_UP -> binding.sizeUpSort.id
            FileSortType.BY_SIZE_DOWN -> binding.sizeDownSort.id
            FileSortType.BY_EXTENSION -> binding.extensionSort.id
        }
        binding.radioGroupSort.check(checkedRadioButtonId)

        binding.radioGroupSort.setOnCheckedChangeListener { group, checkedId ->
            val sortType = when (checkedId) {
                binding.nameSort.id -> FileSortType.BY_NAME
                binding.sizeUpSort.id -> FileSortType.BY_SIZE_UP
                binding.sizeDownSort.id -> FileSortType.BY_SIZE_DOWN
                binding.extensionSort.id -> FileSortType.BY_EXTENSION
                else -> currentSortType
            }
            modifiedFilesViewModel.sortFiles(sortType)
            applySortTypeTitle()
            bottomSheetDialog.dismiss()
        }

        bottomSheetDialog.show()
    }

}