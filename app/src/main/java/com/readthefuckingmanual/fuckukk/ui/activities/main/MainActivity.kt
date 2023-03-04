package com.readthefuckingmanual.fuckukk.ui.activities.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_CLOSE
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN
import com.readthefuckingmanual.fuckukk.R
import com.readthefuckingmanual.fuckukk.data.source.preferences.UserPreferences
import com.readthefuckingmanual.fuckukk.databinding.ActivityMainBinding
import com.readthefuckingmanual.fuckukk.ui.fragments.cashier.history.FragmentHistory
import com.readthefuckingmanual.fuckukk.ui.fragments.cashier.menu.FragmentMenu

class MainActivity : AppCompatActivity() {
    private var binding : ActivityMainBinding? = null
    private val userPreferences by lazy { UserPreferences(this) }

    private val menuFragment = FragmentMenu.newInstance()
    private val historyFragment = FragmentHistory.newInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        greet()
        setupNavigation()

    }

    fun greet(){
//        binding?./text = "Hello ${userPreferences.getSession().username}"
    }

    fun setupNavigation(){
        binding?.apply {
            bottomNavigation?.setOnItemSelectedListener { menu ->
                when (menu.itemId) {
                    R.id.nav_menu -> {
                        changeFragment(menuFragment)
//                    binding?.tvJudul?.text = "Jadwal Piala Dunia"
                        return@setOnItemSelectedListener true
                    }
                    R.id.nav_history -> {
                        changeFragment(historyFragment)
//                    binding?.tvJudul?.text = "Hasil Piala Dunia"
                        return@setOnItemSelectedListener true
                    }
                }
                false
            }
            changeFragment(menuFragment)
        }
    }

    fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setTransition(TRANSIT_FRAGMENT_CLOSE)
            .setTransition(TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.homeRootView, fragment)
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}