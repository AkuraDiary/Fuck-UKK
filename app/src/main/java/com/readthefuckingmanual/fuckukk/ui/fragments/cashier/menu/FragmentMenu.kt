package com.readthefuckingmanual.fuckukk.ui.fragments.cashier.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.readthefuckingmanual.fuckukk.R
import com.readthefuckingmanual.fuckukk.data.repository.MenuRepository
import com.readthefuckingmanual.fuckukk.databinding.FragmentMenuBinding

class FragmentMenu : Fragment() {

    private var binding : FragmentMenuBinding? = null
    private var rvMenuAdapter: ListMenuAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rvMenuAdapter = ListMenuAdapter()
//        binding = FragmentMenuBinding.inflate(layoutInflater)

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRvMenu()

        observeSelectedMenu()
    }

    fun setupRvMenu(){
        binding?.rvCashierMakanan?.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = rvMenuAdapter
        }
    }

    fun observeSelectedMenu(){
        // TODO:
    }
    companion object {
        @JvmStatic
        fun newInstance() =
            FragmentMenu()
    }
}