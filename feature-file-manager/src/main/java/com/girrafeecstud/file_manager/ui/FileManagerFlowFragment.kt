/* Created by Girrafeec */

package com.girrafeecstud.file_manager.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.girrafeecstud.core_ui.ui.BaseFlowFragment
import com.girrafeecstud.file_manager.R
import com.girrafeecstud.file_manager.databinding.FragmentFileManagerFlowBinding
import com.girrafeecstud.file_manager.di.FileManagerFeatureComponent
import com.girrafeecstud.modified_files_api.engine.IModifiedFilesEngine
import javax.inject.Inject

class FileManagerFlowFragment : BaseFlowFragment(
    R.id.nav_host_fragment_file_manager_container
) {

    private var _binding: FragmentFileManagerFlowBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var modifiedFilesEngine: IModifiedFilesEngine

    override fun onAttach(context: Context) {
        super.onAttach(context)
        FileManagerFeatureComponent.fileManagerFeatureComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFileManagerFlowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        modifiedFilesEngine.startEngine()
    }

}