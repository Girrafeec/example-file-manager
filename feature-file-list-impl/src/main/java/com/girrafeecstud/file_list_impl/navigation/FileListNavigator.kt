/* Created by Girrafeec */

package com.girrafeecstud.file_list_impl.navigation

import android.util.Log
import androidx.navigation.NavController
import com.girrafeecstud.file_list_impl.FileListFlowDirections
import com.girrafeecstud.navigation.Navigator
import com.girrafeecstud.navigation.R
import com.girrafeecstud.navigation.extension.setStartDestination

class FileListNavigator : Navigator<FileListDestination> {

    private var navController: NavController? = null

    fun setNavController(navController: NavController) {
        this.navController = navController
    }

    override fun navigateToDestination(destination: FileListDestination) {
        when (destination) {
            is FileListDestination.FileListFragment -> {
                navController?.navigate(
                    FileListFlowDirections
                        .actionFileListFragment()
                        .setDirPath(destination.dirPath)
                )
            }
        }
        Log.i("navigation", "backstack")
        for (entry in navController?.backQueue!!)
            Log.i("navigation entry", entry.destination.toString())
    }

    override fun setStartDestination(destination: FileListDestination) {
        navController?.setStartDestination(
            destination = destination,
            graphId = R.navigation.file_list_flow,
            args = destination.args
        )
        Log.i("navigation", "backstack")
        for (entry in navController?.backQueue!!)
            Log.i("navigation entry", entry.destination.toString())
    }
}