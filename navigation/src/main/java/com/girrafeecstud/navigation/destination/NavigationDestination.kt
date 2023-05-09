/* Created by Girrafeec */

package com.girrafeecstud.navigation.destination

interface NavigationDestination {
    val destinationId: Int
    val args: Map<String, Any>
}