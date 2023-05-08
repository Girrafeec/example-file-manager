package com.girrafeecstud.file_list_impl.ui

import android.content.Context
import android.content.Context.STORAGE_SERVICE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.girrafeecstud.core_components.SDCardExists
import com.girrafeecstud.core_ui.extension.removeView
import com.girrafeecstud.core_ui.ui.BaseFragment
import com.girrafeecstud.file_list_impl.R
import com.girrafeecstud.file_list_impl.databinding.FragmentFileManagerDashboardBinding
import com.girrafeecstud.file_list_impl.presentation.DashboardUiState

class FileManagerDashboardFragment
    : BaseFragment<DashboardUiState>() {

    private var _binding: FragmentFileManagerDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
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
            TODO()
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