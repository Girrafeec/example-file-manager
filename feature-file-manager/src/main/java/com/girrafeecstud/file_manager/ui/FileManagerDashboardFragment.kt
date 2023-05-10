package com.girrafeecstud.file_manager.ui

import android.content.Context
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.girrafeecstud.core_ui.ui.BaseFragment
import com.girrafeecstud.file_manager.databinding.FragmentFileManagerDashboardBinding
import com.girrafeecstud.file_manager.presentation.DashboardUiState
import com.girrafeecstud.file_manager.presentation.FileManagerDashboardComponentViewModel
import com.girrafeecstud.navigation.ToFlowNavigable
import com.girrafeecstud.navigation.destination.FlowDestination

class FileManagerDashboardFragment
    : BaseFragment<DashboardUiState>() {

    private var _binding: FragmentFileManagerDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ViewModelProvider(this).get(FileManagerDashboardComponentViewModel::class.java)
            .fileManagerDashboardComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFileManagerDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    override fun setListeners() {
        binding.phoneCard.setOnClickListener {
            val phoneDirPath = Environment.getExternalStorageDirectory().absolutePath
            (requireActivity() as ToFlowNavigable).navigateToScreen(
                destination = FlowDestination.FileListFlow(defaultPath = phoneDirPath)
            )
        }
        binding.modifiedFilesCard.setOnClickListener {
            (requireActivity() as ToFlowNavigable).navigateToScreen(
                destination = FlowDestination.ModifiedFilesFlow
            )
        }
//        binding.sdCard.setOnClickListener {
//            TODO()
//        }
    }

    private fun initViews() {
//        if (!requireActivity().SDCardExists()) {
//            binding.sdCard.removeView()
//        }
    }
}