package com.girrafeecstud.navigation

import android.util.Log
import androidx.navigation.NavController
import com.girrafeecstud.navigation.destination.FlowDestination
import com.girrafeecstud.navigation.extensions.setStartDestination

class FlowNavigator : Navigator<FlowDestination> {

    // TODO Inject navController to FlowNavigator
    private var navController: NavController? = null

    fun setNavController(navController: NavController) {
        this.navController = navController
    }

    override fun navigateToDestination(destination: FlowDestination) {
//        when (destination) {
//            is FlowDestination.FileListFlow -> {
//                navController?.navigate(
//                    MainNavFlowDirections
//                        .actionGlobalOnBoardFlow()
//                        .setDefaultScreen(destination.defaultScreen as DefaultOnBoardFlowScreen)
//                )
//            }
//        }
        Log.i("navigation", "backstack")
        for (entry in navController?.backQueue!!)
            Log.i("navigation entry", entry.destination.toString())
    }

    override fun setStartDestination(destination: FlowDestination) {
        navController?.setStartDestination(
            destination = destination, graphId = R.navigation.main_nav_flow
        )
        Log.i("navigation", "backstack")
        for (entry in navController?.backQueue!!)
            Log.i("navigation entry", entry.destination.toString())
    }

}