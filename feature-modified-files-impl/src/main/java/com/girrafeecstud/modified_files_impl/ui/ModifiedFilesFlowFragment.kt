package com.girrafeecstud.modified_files_impl.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.girrafeecstud.core_ui.ui.BaseFlowFragment
import com.girrafeecstud.modified_files_impl.R
import com.girrafeecstud.modified_files_impl.databinding.FragmentModifiedFilesFlowBinding
import com.girrafeecstud.modified_files_impl.di.ModifiedFilesFeatureComponent

class ModifiedFilesFlowFragment : BaseFlowFragment(
    R.id.nav_host_fragment_modified_files_container
) {

    private var _binding: FragmentModifiedFilesFlowBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ModifiedFilesFeatureComponent.modifiedFilesFeatureComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentModifiedFilesFlowBinding.inflate(inflater, container, false)
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
