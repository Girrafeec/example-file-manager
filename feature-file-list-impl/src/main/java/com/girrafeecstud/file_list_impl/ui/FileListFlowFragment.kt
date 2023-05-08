package com.girrafeecstud.file_list_impl.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.girrafeecstud.core_ui.ui.BaseFlowFragment
import com.girrafeecstud.file_list_impl.R
import com.girrafeecstud.file_list_impl.databinding.FragmentFileListFlowBinding
import com.girrafeecstud.file_list_impl.di.FileListFeatureComponent

class FileListFlowFragment : BaseFlowFragment(
    R.id.nav_host_fragment_file_list_container
) {

    private var _binding: FragmentFileListFlowBinding? = null

    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        FileListFeatureComponent.fileListFeatureComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFileListFlowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}