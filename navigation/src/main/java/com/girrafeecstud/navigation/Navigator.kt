/* Created by Girrafeec */

package com.girrafeecstud.navigation

interface Navigator<in NavigationDestination> {

    fun navigateToDestination(destination: NavigationDestination)

    fun setStartDestination(destination: NavigationDestination)

}