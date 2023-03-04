package com.readthefuckingmanual.fuckukk.ui.fragments.cashier.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.readthefuckingmanual.fuckukk.R
import com.readthefuckingmanual.fuckukk.databinding.FragmentHistoryBinding
import com.readthefuckingmanual.fuckukk.databinding.FragmentMenuBinding

class FragmentHistory : Fragment() {

    private var binding : FragmentHistoryBinding? = null
    private var rvHistoryAdapter : ListHistoryAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rvHistoryAdapter = ListHistoryAdapter()


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentHistoryBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun setupRvHistory(){
        binding?.rvHistoryTransaksi?.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = rvHistoryAdapter
        }
    }

    fun observeSelectedHistory(){
        // TODO:
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic fun newInstance(param1: String, param2: String) =
                FragmentHistory()

    }
}