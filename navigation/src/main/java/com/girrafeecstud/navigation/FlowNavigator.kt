/* Created by Girrafeec */

package com.girrafeecstud.navigation

import android.util.Log
import androidx.navigation.NavController
import com.girrafeecstud.navigation.destination.FlowDestination
import com.girrafeecstud.navigation.extension.setStartDestination

class FlowNavigator : Navigator<FlowDestination> {

    // TODO Inject navController to FlowNavigator
    private var navController: NavController? = null

    fun setNavController(navController: NavController) {
        this.navController = navController
    }

    override fun navigateToDestination(destination: FlowDestination) {
        when (destination) {
            is FlowDestination.FileManagerFlow -> {
                navController?.navigate(
                    MainNavFlowDirections
                        .actionGlobalFileManagerFlowFragment()
                )
            }
            is FlowDestination.FileListFlow -> {
                navController?.navigate(
                    MainNavFlowDirections
                        .actionGlobalFileListFlowFragment()
                        .setDefaultPath(destination.defaultPath)
                )
            }
        }
    }

    override fun setStartDestination(destination: FlowDestination) {
        navController?.setStartDestination(
            destination = destination,
            graphId = R.navigation.main_nav_flow,
            args = destination.args
        )
        Log.i("navigation", "backstack")
        for (entry in navController?.backQueue!!)
            Log.i("navigation entry", entry.destination.toString())
    }
}