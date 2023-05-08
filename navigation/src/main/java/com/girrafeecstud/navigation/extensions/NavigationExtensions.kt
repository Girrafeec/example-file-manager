package com.girrafeecstud.navigation.extensions

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import com.girrafeecstud.navigation.destination.FlowDestination
import com.girrafeecstud.navigation.destination.NavigationDestination

fun NavController.setStartDestination(
    destination: NavigationDestination,
    graphId: Int
) {
    val graph = this.navInflater.inflate(graphId)
    graph.setStartDestination(destination.destinationId)
    // Add start destination args (default screen value)
    var bundle: Bundle? = null
    if (destination is FlowDestination) {
        bundle = Bundle()
    }
    this.setGraph(graph = graph, startDestinationArgs = bundle)
}

fun NavController.safeNavigate(direction: NavDirections) {
    currentDestination?.getAction(direction.actionId)?.run {
        navigate(direction)
    }
}