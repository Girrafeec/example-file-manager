package com.girrafeecstud.example_file_manager.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.girrafeecstud.example_file_manager.R
import com.girrafeecstud.example_file_manager.databinding.ActivityMainBinding
import com.girrafeecstud.navigation.FlowNavigator
import com.girrafeecstud.navigation.ToFlowNavigable
import com.girrafeecstud.navigation.destination.FlowDestination

class MainActivity : AppCompatActivity(), ToFlowNavigable {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    private val navigator = FlowNavigator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpNavigation()
    }

    private fun setUpNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment

        navController = navHostFragment.navController

        navigator.setNavController(navController)
    }

    override fun navigateToScreen(destination: FlowDestination) {
        navigator.navigateToDestination(destination = destination)
    }
}