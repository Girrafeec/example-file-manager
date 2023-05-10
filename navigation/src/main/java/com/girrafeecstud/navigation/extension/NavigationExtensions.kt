/* Created by Girrafeec */

package com.girrafeecstud.navigation.extension

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavController
import com.girrafeecstud.navigation.destination.NavigationDestination
import java.io.Serializable

fun NavController.setStartDestination(
    destination: NavigationDestination,
    graphId: Int,
    args: Map<String, Any>
) {
    val graph = this.navInflater.inflate(graphId)
    graph.setStartDestination(destination.destinationId)
    // Add startDestination args
    val bundle = Bundle().apply {
        for ((key, value) in args)
            when (value) {
                is String -> putString(key, value)
                is Int -> putInt(key, value)
                is Boolean -> putBoolean(key, value)
                is Float -> putFloat(key, value)
                is Serializable -> putSerializable(key, value)
                is Parcelable -> putParcelable(key, value)

                else -> throw IllegalArgumentException("Unsupported argument type: ${value.javaClass}")
            }
    }

    this.setGraph(graph = graph, startDestinationArgs = bundle)
}