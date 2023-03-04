package com.readthefuckingmanual.fuckukk.ui.fragments.cashier.transaksi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.view.menu.ListMenuItemView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.readthefuckingmanual.fuckukk.databinding.ItemCashierTransactionBinding
import com.readthefuckingmanual.fuckukk.ui.fragments.cashier.menu.ListMenuAdapter

class ListItemTransactionAdapter : RecyclerView.Adapter<ListMenuAdapter.ListMenuViewHolder>(){

    private var transactionItemList : ArrayList<String> = arrayListOf()

    fun setData(data : List<String>){
        transactionItemList.apply {
            clear()
            addAll(data)
        }
    }

    inner class ListItemTransactionViewHolder(private val binding : ItemCashierTransactionBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(itemListBarang :String){
            binding.apply {
                binding.apply{
                    //TODO:
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListMenuAdapter.ListMenuViewHolder {
        //TODO
//        val viewBinding =
//            ItemCashierTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return ListItemTransactionViewHolder(viewBinding)
    }

    override fun getItemCount(): Int {
        return transactionItemList.size
    }

    override fun onBindViewHolder(holder: ListMenuAdapter.ListMenuViewHolder, position: Int) {
        //TODO
//        val item = transactionItemList[position]
//        holder.bind(item)
    }
}