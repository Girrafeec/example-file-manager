package com.girrafeecstud.file_list_impl.ui

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.girrafeecstud.core_components.presentation.MainViewModelFactory
import com.girrafeecstud.core_ui.ui.BaseFragment
import com.girrafeecstud.file_list_api.domain.FileInfo
import com.girrafeecstud.file_list_api.domain.FileType
import com.girrafeecstud.file_list_api.domain.PathInfo
import com.girrafeecstud.file_list_api.ui.FileClickEvent
import com.girrafeecstud.file_list_impl.databinding.FragmentFileListBinding
import com.girrafeecstud.file_list_impl.presentation.FileListComponentViewModel
import com.girrafeecstud.file_list_impl.presentation.FileListUiState
import com.girrafeecstud.file_list_impl.presentation.FileListViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class FileListFragment : BaseFragment<FileListUiState>(), FileClickEvent {

    private val args: FileListFragmentArgs  by navArgs()

    private var _binding: FragmentFileListBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    private val fileListViewModel: FileListViewModel by viewModels {
        viewModelFactory
    }

    @Inject
    lateinit var filesAdapter: FilesAdapter

    private var dirPath: String? = null

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                getFiles()
            }
            else {
                TODO()
            }
        }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ViewModelProvider(this).get(FileListComponentViewModel::class.java)
            .filesListComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFileListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.dirPath?.let {
            this.dirPath = it
        }

        initRecView()
        checkReadStoragePermission()
    }

    override fun registerObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                fileListViewModel.state
                    .onEach { state ->
                        setState(state = state)
                    }
                    .launchIn(viewLifecycleOwner.lifecycleScope)
            }
        }
    }

    override fun setListeners() {

    }

    override fun setState(state: FileListUiState) {
        state.files?.let { files ->
            filesAdapter.updateFileList(files = files)
        }
    }

    override fun onFileClicked(file: FileInfo) {
        Log.i("tag file list", "file $file clicked")
        if (file.fileType == FileType.FOLDER) {
            val path = PathInfo(isPathActive = true, name = file.name, path = file.path)
            (requireParentFragment().requireParentFragment() as FileListFlowFragment)
                .openDirectory(path = path, isDirectoryNew = true)
        }
    }

    private fun initRecView() {
        binding.files.let { files ->
            filesAdapter.clickEvent = this
            files.overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            files.adapter = filesAdapter
            files.layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
        }
    }

    private fun checkReadStoragePermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        )
            requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        else {
            getFiles()
        }
    }

    private fun getFiles() {
        // TODO try smth else
        if (dirPath != null)
            fileListViewModel.getFileList(path = dirPath!!)
    }
}