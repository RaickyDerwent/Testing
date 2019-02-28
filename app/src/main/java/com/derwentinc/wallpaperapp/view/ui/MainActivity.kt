package com.derwentinc.wallpaperapp.view.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import com.derwentinc.wallpaperapp.R
import kotlinx.android.synthetic.main.main_activity.*

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        findNavController(R.id.nav_host_fragment).addOnDestinationChangedListener { _, navDestination: NavDestination, _ ->
            when (navDestination.id) {
                R.id.photoViewFragment -> hideBottomNavigation()
                else -> showBottomNavigation()
            }
        }
    }

    private fun hideBottomNavigation() {
        // bottom_navigation is BottomNavigationView
        with(bottom_nav_view) {
            if (visibility == View.VISIBLE && alpha == 1f) {
                animate()
                    .alpha(0f)
                    .withEndAction { visibility = View.GONE }
                    .duration = 2.toLong()
            }
        }
    }


    private fun showBottomNavigation() {
        // bottom_navigation is BottomNavigationView
        with(bottom_nav_view) {
            visibility = View.VISIBLE
            animate()
                .alpha(1f)
                .duration = 2.toLong()
        }
    }
}