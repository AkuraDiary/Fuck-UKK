package com.readthefuckingmanual.fuckukk.ui.fragments.cashier.menu

import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.readthefuckingmanual.fuckukk.databinding.FragmentMenuBinding
import com.readthefuckingmanual.fuckukk.databinding.ItemCashierMenuBinding

class ListMenuAdapter : RecyclerView.Adapter<ListMenuAdapter.ListMenuViewHolder>() {

    private var menuList:ArrayList<Menu> = arrayListOf()
    fun setData(data : List<>){
        menuList.apply {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }

    inner class ListMenuViewHolder(private val binding: ItemCashierMenuBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(menuItem: Menu){

            binding.apply{

            }
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListMenuAdapter.ListMenuViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ListMenuAdapter.ListMenuViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}