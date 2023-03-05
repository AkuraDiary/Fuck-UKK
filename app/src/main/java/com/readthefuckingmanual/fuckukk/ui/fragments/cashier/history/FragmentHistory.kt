package com.readthefuckingmanual.fuckukk.ui.fragments.cashier.history

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.readthefuckingmanual.fuckukk.R
import com.readthefuckingmanual.fuckukk.data.model.transaksi.TransaksiModel
import com.readthefuckingmanual.fuckukk.data.repository.TransaksiRepository
import com.readthefuckingmanual.fuckukk.data.source.preferences.UserPreferences
import com.readthefuckingmanual.fuckukk.databinding.DialogDetailTransaksiBinding
import com.readthefuckingmanual.fuckukk.databinding.FragmentHistoryBinding
import com.readthefuckingmanual.fuckukk.databinding.FragmentMenuBinding
import com.readthefuckingmanual.fuckukk.ui.activities.login.LoginActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FragmentHistory : Fragment() {


    private var binding: FragmentHistoryBinding? = null
    private var rvHistoryAdapter: ListHistoryAdapter? = null

    private val userPreference by lazy {
        UserPreferences(requireContext())
    }

    private val userToken by lazy {
        userPreference.getSession().token
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rvHistoryAdapter = ListHistoryAdapter(::observeSelectedHistory)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        TransaksiRepository.getListTransaksi(userToken!!).observe(viewLifecycleOwner) {
            rvHistoryAdapter?.setData(it?.values as List<TransaksiModel>)
        }
        setupRvHistory()
        setupBtnLogout()
        binding?.tvStatusCashierName?.text = userPreference.getSession().username
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHistoryBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun setupRvHistory() {
        binding?.rvHistoryTransaksi?.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = rvHistoryAdapter
        }
    }

    private fun observeSelectedHistory(idTransaksi: Int) {
        showDetailTransaksi(idTransaksi)
    }

    private fun showDetailTransaksi(idTransaksi: Int) {


        // Call getDetailTransaksi() and observe the response
        val dialogBinding = DialogDetailTransaksiBinding.inflate(layoutInflater, binding?.root, false)

        TransaksiRepository.getDetailTransaksi(userToken!!, idTransaksi)
            .observe(viewLifecycleOwner) { detailTransaksi ->
                // Show the detail transaksi in a dialog
                dialogBinding.apply {
                    tvDialogDetailTransaksiId.text =
                        "ID Meja : " + detailTransaksi?.id_meja.toString()
                    tvDialogDetailTransaksiTanggal.text =
                        detailTransaksi?.tgl_transaksi?.replace("T", " ") ?: "".replace(
                            ".000Z",
                            " "
                        )
                    tvDialogDetailTransaksiStatus.text = "Status : " + detailTransaksi?.status

                    tvDialogDetailTransaksiTotal.text = "Total Harga : " + detailTransaksi?.total_harga.toString()

                    // if the transaction status is "belum_bayar", show the "selesaikan transaksi" button

                    if (detailTransaksi?.status == "belum_bayar") {
                        btnDialogDetailTransaksiSelesaikan.visibility = View.VISIBLE
                        btnDialogDetailTransaksiSelesaikan.setOnClickListener {
                            // handle the "selesaikan transaksi" button click

                        }
                    } else {
                        btnDialogDetailTransaksiSelesaikan.visibility = View.GONE
                    }
                }
                // Set up the rest of the dialog UI with detailTransaksi data
                // ...

                viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main){



                    val dialog = AlertDialog.Builder(requireContext())
                        .setView(dialogBinding.root)
                        .create()
                    // Remove the view from its parent before adding it to the dialog
                    val parent = dialogBinding.root.parent as? ViewGroup
                    parent?.removeView(dialogBinding.root)
                    dialog.show()
                }

            }
    }


//    private fun showDetailDialog(historyItem: TransaksiModel) {
//        val builder = AlertDialog.Builder(requireContext())
//        val dialogBinding = DialogDetailTransaksiBinding.inflate(layoutInflater)
//        val dialogView = dialogBinding.root
//        builder.setView(dialogView)
//
//        // populate the dialog with transaction details
//
//
//
//        dialogBinding.apply {
//            tvDialogDetailTransaksiId.text = "Id Transaksi : " + historyItem.id_transaksi.toString()
//            tvDialogDetailTransaksiTanggal.text = historyItem.tgl_transaksi?.replace("T", " ") ?: "".replace(".000Z", " ")
//            tvDialogDetailTransaksiStatus.text = "Status : " + historyItem.status
//
//            // if the transaction status is "belum_bayar", show the "selesaikan transaksi" button
//
//            if (historyItem.status == "belum_bayar") {
//                btnDialogDetailTransaksiSelesaikan.visibility = View.VISIBLE
//                btnDialogDetailTransaksiSelesaikan.setOnClickListener {
//                    // handle the "selesaikan transaksi" button click
//                    TransaksiRepository.getDetailTransaksi(userToken!!, historyItem.id_transaksi!!)
//                        .observe(viewLifecycleOwner) { detailTransaksi ->
//                            // TODO: handle the detailTransaksi response
//                        }
//                }
//            } else {
//                btnDialogDetailTransaksiSelesaikan.visibility = View.GONE
//            }
//        }
//
//
//
//        builder.show()
//    }

    fun setupBtnLogout() {
        binding?.btnLogoutCashierHistory?.setOnClickListener {
            userPreference.deleteSession()
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            FragmentHistory()

    }
}