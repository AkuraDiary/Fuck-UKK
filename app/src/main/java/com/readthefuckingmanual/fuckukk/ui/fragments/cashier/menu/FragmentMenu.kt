package com.readthefuckingmanual.fuckukk.ui.fragments.cashier.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.readthefuckingmanual.fuckukk.R
import com.readthefuckingmanual.fuckukk.databinding.FragmentMenuBinding

class FragmentMenu : Fragment() {

    private var binding : FragmentMenuBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //
        rvCashierMenu = ListDokterAdapter()
        binding = FragmentPilihDokterBinding.inflate(layoutInflater)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMenuBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


    companion object {
        @JvmStatic
        fun newInstance() =
            FragmentMenu()
    }
}