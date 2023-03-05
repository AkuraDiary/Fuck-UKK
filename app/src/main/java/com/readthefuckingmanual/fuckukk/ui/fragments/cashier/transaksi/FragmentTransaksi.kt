package com.readthefuckingmanual.fuckukk.ui.fragments.cashier.transaksi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.readthefuckingmanual.fuckukk.R
import com.readthefuckingmanual.fuckukk.data.repository.MejaRepository
import com.readthefuckingmanual.fuckukk.data.repository.MenuRepository
import com.readthefuckingmanual.fuckukk.data.source.preferences.UserPreferences
import com.readthefuckingmanual.fuckukk.databinding.FragmentTransaksiBinding

class FragmentTransaksi : Fragment() {

    private var selectedMejaId: Int = 0
    private var binding : FragmentTransaksiBinding? = null
    private var rvItemListAdapter : ListItemTransactionAdapter? = null

    private val userPreference by lazy {
        UserPreferences(requireContext())
    }

    private val userToken by lazy {
        userPreference.getSession().token
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rvItemListAdapter = ListItemTransactionAdapter()
        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transaksi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSpinner()
        MenuRepository.keranjang.observe(viewLifecycleOwner){
            rvItemListAdapter?.setData(it)
        }
        setuprvItem()
    }

    fun setupSpinner(){
        MejaRepository.getListMeja(userToken!!).observe(viewLifecycleOwner) {listMeja ->

            val listMejaAvailable = listMeja?.values //as ArrayList<MejaModel?>?
            listMejaAvailable?.filter {
                it?.available == 1
            }
            val adapter = ArrayAdapter(requireContext(), R.layout.item_spinner_meja, listMejaAvailable!!.map {
                it?.nomor_meja
            })
            adapter.setDropDownViewResource(R.layout.item_spinner_meja)
            binding?.apply {
                edtIndustriUsahaSpinner.adapter = adapter
                edtIndustriUsahaSpinner.onItemSelectedListener = object :
                    AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        p0: AdapterView<*>?,
                        p1: View?,
                        p2: Int,
                        p3: Long
                    ) {
                        val selection = edtIndustriUsahaSpinner.selectedItem
                        val selecedIdMeja = listMejaAvailable?.first {
                            it?.nomor_meja == selection
                        }?.id_meja
                        if (selecedIdMeja != null) {
                            selectedMejaId = selecedIdMeja
                        }
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {

                    }

                }

            }

        }
    }

    fun setuprvItem(){
       binding?.rvItemKeranjang.apply {
           this?.layoutManager = LinearLayoutManager(requireContext())
           this?.adapter = rvItemListAdapter
       }
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            FragmentTransaksi()

    }
}