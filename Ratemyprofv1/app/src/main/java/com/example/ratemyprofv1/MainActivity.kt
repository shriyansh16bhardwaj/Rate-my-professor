package com.example.ratemyprofv1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.ratemyprofv1.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.example.ratemyprofv1.data.SessionManager

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        navController = findNavController(R.id.nav_host_fragment)
        sessionManager = SessionManager(this)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.loginFragment,
                R.id.homeFragment,
                R.id.myReviewsFragment
            ),
            binding.drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.navView.setNavigationItemSelectedListener { menuItem ->
            if (menuItem.itemId == R.id.action_logout) {
                sessionManager.clear()
                navController.navigate(R.id.loginFragment)
                binding.drawerLayout.closeDrawers()
                true
            } else {
                val handled = NavigationUI.onNavDestinationSelected(menuItem, navController)
                if (handled) binding.drawerLayout.closeDrawers()
                handled
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}