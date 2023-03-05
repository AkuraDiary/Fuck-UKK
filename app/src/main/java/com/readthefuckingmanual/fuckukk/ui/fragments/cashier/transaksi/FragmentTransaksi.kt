package com.readthefuckingmanual.fuckukk.ui.fragments.cashier.transaksi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.readthefuckingmanual.fuckukk.R
import com.readthefuckingmanual.fuckukk.data.model.meja.MejaModel
import com.readthefuckingmanual.fuckukk.data.repository.MejaRepository
import com.readthefuckingmanual.fuckukk.data.source.preferences.UserPreferences

class FragmentTransaksi : Fragment() {

    private val userPreference by lazy {
        UserPreferences(requireContext())
    }

    private val userToken by lazy {
        userPreference.getSession().token
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transaksi, container, false)
    }

    fun setupSpinner(){
        MejaRepository.getListMeja(userToken!!).observe(viewLifecycleOwner) {listMeja ->

            val listMejaAvailable : ArrayList<MejaModel?>? = listMeja?.values as ArrayList<MejaModel?>?
            val adapter = ArrayAdapter(requireContext(), R.layout.item_spinner_meja, listMeja!!.map {
                it?.name
            })
            adapter.setDropDownViewResource(R.layout.item_spinner_layout)
            binding?.apply {
                .adapter = adapter
                edtIndustriUsahaSpinner.onItemSelectedListener = object :
                    AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        p0: AdapterView<*>?,
                        p1: View?,
                        p2: Int,
                        p3: Long
                    ) {
                        val selection = edtIndustriUsahaSpinner.selectedItem
                        val selectedIdIndustry = listIndustry?.first {
                            it?.name == selection
                        }?.id
                        if (selectedIdIndustry != null) {
                            usahaIndustri = selectedIdIndustry
                        }
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {

                    }

                }

            }

        }
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            FragmentTransaksi()

    }
}