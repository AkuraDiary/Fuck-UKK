package com.readthefuckingmanual.fuckukk.ui.fragments.cashier.transaksi

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.readthefuckingmanual.fuckukk.R
import com.readthefuckingmanual.fuckukk.data.model.meja.MejaModel
import com.readthefuckingmanual.fuckukk.data.repository.MejaRepository
import com.readthefuckingmanual.fuckukk.data.repository.MenuRepository
import com.readthefuckingmanual.fuckukk.data.source.preferences.UserPreferences
import com.readthefuckingmanual.fuckukk.data.source.remote.datasource.MejaRemoteDataSource
import com.readthefuckingmanual.fuckukk.databinding.FragmentTransaksiBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
         binding = FragmentTransaksiBinding.inflate(layoutInflater, container, false)

        return binding?.root
    }

    var totalharga = 0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        MenuRepository.keranjang.observe(viewLifecycleOwner){
            rvItemListAdapter?.setData(it)
            for (i in it){
                totalharga += i.harga!!
            }
            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main){
                binding?.apply {
                    tvTotal.text = "Rp. $totalharga"

                }
            }

        }
        setupSpinner()

        setuprvItem()

    }

    fun setupSpinner(){
//        var listMejaAvailable: List<MejaModel?>? = listOf()
        MejaRemoteDataSource.mejaList.observe(viewLifecycleOwner) {listMeja ->
            val listMejaAvailable = listMeja?.values
            listMejaAvailable?.filter {
                it?.available == 1
            }
            val adapter = ArrayAdapter(requireContext(), R.layout.item_spinner_meja, listMejaAvailable!!.map {
                it?.nomor_meja
            })
            adapter.setDropDownViewResource(R.layout.item_spinner_meja)
            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main){
                Log.d("TAG", "onViewCreated: Spinner")
                adapter.apply {
                    clear()
                    addAll(listMejaAvailable!!.map {
                        it?.nomor_meja
                    })
                    notifyDataSetChanged()
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
                                val selecedIdMeja = listMejaAvailable!!.first {
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