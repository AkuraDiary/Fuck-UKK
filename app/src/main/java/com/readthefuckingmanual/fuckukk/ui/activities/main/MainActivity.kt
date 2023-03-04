package com.readthefuckingmanual.fuckukk.ui.activities.main

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_CLOSE
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN
import androidx.lifecycle.lifecycleScope
import com.readthefuckingmanual.fuckukk.R
import com.readthefuckingmanual.fuckukk.data.repository.MenuRepository
import com.readthefuckingmanual.fuckukk.data.source.preferences.UserPreferences
import com.readthefuckingmanual.fuckukk.databinding.ActivityMainBinding
import com.readthefuckingmanual.fuckukk.ui.fragments.cashier.history.FragmentHistory
import com.readthefuckingmanual.fuckukk.ui.fragments.cashier.menu.FragmentMenu
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private var binding : ActivityMainBinding? = null
    private val userPreferences by lazy { UserPreferences(this) }

    private val menuFragment = FragmentMenu.newInstance()
    private val historyFragment = FragmentHistory.newInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setupNavigation()
    }

//    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
//        observeSelectedMenu()
//        return super.onCreateView(name, context, attrs)
//    }



    fun setupNavigation(){
        binding?.apply {
            bottomNavigation?.setOnItemSelectedListener { menu ->
                when (menu.itemId) {
                    R.id.nav_menu -> {
                        changeFragment(menuFragment)
                        observeSelectedMenu()
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
            observeSelectedMenu()
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

    fun observeSelectedMenu() {
        MenuRepository.keranjang.observe(this@MainActivity) { it ->
            Log.d("MainActivity", "observeSelectedMenu: ${it.size}")
                binding?.apply {
                    Log.d("MainActivity", "btnCheckout visibility: ${btnCheckout.visibility}")
//                    this@MainActivity.lifecycleScope.launch(Dispatchers.Main) {
                    if (it.isNotEmpty()) {
                        btnCheckout.visibility = View.VISIBLE
                    } else {
                        btnCheckout.visibility = View.GONE
                    }
                    btnCheckout.text = "Checkout (${it.size} Item)"

//                }

            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}