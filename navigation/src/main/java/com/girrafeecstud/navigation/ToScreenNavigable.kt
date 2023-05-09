/* Created by Girrafeec */

package com.girrafeecstud.navigation

interface ToScreenNavigable<in NavigationDestination> {
    fun navigateToScreen(destination: NavigationDestination)
}