package com.readthefuckingmanual.fuckukk.ui.activities.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.readthefuckingmanual.fuckukk.R
import com.readthefuckingmanual.fuckukk.data.source.preferences.UserPreferences
import com.readthefuckingmanual.fuckukk.databinding.ActivityAdminBinding
import com.readthefuckingmanual.fuckukk.ui.fragments.admin.menu.FragmentAdminMenu
import com.readthefuckingmanual.fuckukk.ui.fragments.admin.table.FragmentTable
import com.readthefuckingmanual.fuckukk.ui.fragments.admin.user.FragmentUser

class AdminActivity : AppCompatActivity() {

    private var binding : ActivityAdminBinding? = null
    private val userPreferences by lazy { UserPreferences(this) }
    private val userToken by lazy { userPreferences.getSession().token }
    private val userFragment = FragmentUser.newInstance()
    private val tableFragment = FragmentTable.newInstance()
    private val menuFragment = FragmentAdminMenu.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminBinding.inflate(layoutInflater)

        setContentView(binding?.root)


    }

    fun setupNavigation(){
        binding?.apply {
            bottomNavigationAdmin?.setOnItemSelectedListener { menu ->
                when (menu.itemId) {
                    R.id.nav_menu_admin -> {
                        changeFragment(menuFragment)

//                    binding?.tvJudul?.text = "Jadwal Piala Dunia"
                        return@setOnItemSelectedListener true
                    }
                    R.id.nav_table -> {
                        changeFragment(tableFragment)
//                    binding?.tvJudul?.text = "Hasil Piala Dunia"
                        return@setOnItemSelectedListener true
                    }
                    R.id.nav_user -> {
                        changeFragment(userFragment)

                        return@setOnItemSelectedListener true
                    }
                }
                false
            }
            changeFragment(userFragment)
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.homeRootView, fragment)
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    fun moveToAdminTableFragment() {
        changeFragment(tableFragment)
    }

    fun moveToAdminMenuFragment() {
        changeFragment(menuFragment)
    }
}