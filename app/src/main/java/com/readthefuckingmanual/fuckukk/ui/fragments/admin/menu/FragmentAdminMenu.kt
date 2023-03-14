package com.readthefuckingmanual.fuckukk.ui.fragments.admin.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.readthefuckingmanual.fuckukk.R
import com.readthefuckingmanual.fuckukk.ui.fragments.cashier.menu.FragmentMenu

class FragmentAdminMenu : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin_menu, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            FragmentAdminMenu()
    }
}