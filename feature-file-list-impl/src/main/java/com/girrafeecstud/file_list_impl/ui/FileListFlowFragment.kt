package com.girrafeecstud.file_list_impl.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.girrafeecstud.core_ui.ui.BaseFlowFragment
import com.girrafeecstud.file_list_api.domain.FileInfo
import com.girrafeecstud.file_list_api.domain.PathInfo
import com.girrafeecstud.file_list_impl.R
import com.girrafeecstud.file_list_impl.databinding.FragmentFileListFlowBinding
import com.girrafeecstud.file_list_impl.di.FileListFeatureComponent
import com.girrafeecstud.file_list_impl.navigation.FileListDestination
import com.girrafeecstud.file_list_impl.navigation.FileListNavigator
import com.girrafeecstud.file_list_impl.navigation.ToFileListScreenNavigable
import javax.inject.Inject

class FileListFlowFragment : BaseFlowFragment(
    R.id.nav_host_fragment_file_list_container
), PathClickEvent, ToFileListScreenNavigable {

    private val args: FileListFlowFragmentArgs by navArgs()

    private val navigator = FileListNavigator()

    private var _binding: FragmentFileListFlowBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var pathsAdapter: PathsAdapter

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

        args.defaultPath?.let {
            initPathRecView(defaultPath = it)
        }
    }

    override fun setUpNavigation() {
        navigator.setNavController(navController = navController)
        args.defaultPath?.let {
            navigator.setStartDestination(
                destination = FileListDestination.FileListFragment(dirPath = it)
            )
        }
    }

    override fun onPathClicked(path: PathInfo) {
        if (!path.isPathActive) {
            pathsAdapter.goToPath(dirPath = path.path)
            openDirectory(path = path)
        }
    }

    override fun navigateToScreen(destination: FileListDestination) {
        navigator.navigateToDestination(destination = destination)
    }

    private fun initPathRecView(defaultPath: String) {

        val defaultPathInfo = PathInfo(isPathActive = true, path = defaultPath, name = "Phone")

        binding.directoryPaths.let { paths ->
            pathsAdapter.clickEvent = this
            pathsAdapter.addPath(defaultPathInfo)
            paths.overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            paths.adapter = pathsAdapter
            paths.layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }
    }

    fun openDirectory(path: PathInfo, isDirectoryNew: Boolean = false) {
        if (isDirectoryNew) {
            pathsAdapter.addPath(path)
            binding.directoryPaths.scrollToPosition(pathsAdapter.itemCount - 1)
        }
        navigator.navigateToDestination(
            destination = FileListDestination.FileListFragment(dirPath = path.path)
        )
    }
}