package com.readthefuckingmanual.fuckukk.ui.fragments.cashier.history

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.readthefuckingmanual.fuckukk.R
import com.readthefuckingmanual.fuckukk.data.model.transaksi.TransaksiModel
import com.readthefuckingmanual.fuckukk.data.repository.TransaksiRepository
import com.readthefuckingmanual.fuckukk.data.source.preferences.UserPreferences
import com.readthefuckingmanual.fuckukk.databinding.FragmentHistoryBinding
import com.readthefuckingmanual.fuckukk.databinding.FragmentMenuBinding
import com.readthefuckingmanual.fuckukk.ui.activities.login.LoginActivity

class FragmentHistory : Fragment() {


    private var binding : FragmentHistoryBinding? = null
    private var rvHistoryAdapter : ListHistoryAdapter? = null

    private val userPreference by lazy {
        UserPreferences(requireContext())
    }

    private val userToken by lazy {
        userPreference.getSession().token
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rvHistoryAdapter = ListHistoryAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        TransaksiRepository.getListTransaksi(userToken!!).observe(viewLifecycleOwner){
            rvHistoryAdapter?.setData(it?.values as List<TransaksiModel>)
        }
        setupRvHistory()
        setupBtnLogout()
        binding?.tvStatusCashierName?.text = userPreference.getSession().username
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

    fun setupBtnLogout(){
        binding?.btnLogoutCashierHistory?.setOnClickListener {
            userPreference.deleteSession()
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic fun newInstance() =
                FragmentHistory()

    }
}