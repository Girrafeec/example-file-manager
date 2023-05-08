package com.girrafeecstud.navigation

import com.girrafeecstud.navigation.destination.FlowDestination

interface ToFlowNavigable : ToScreenNavigable<FlowDestination> {
    override fun navigateToScreen(destination: FlowDestination)
}