package com.readthefuckingmanual.fuckukk.ui.fragments.cashier.menu

import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.readthefuckingmanual.fuckukk.data.model.menu.MenuModel
import com.readthefuckingmanual.fuckukk.databinding.FragmentMenuBinding
import com.readthefuckingmanual.fuckukk.databinding.ItemCashierMenuBinding

class ListMenuAdapter : RecyclerView.Adapter<ListMenuAdapter.ListMenuViewHolder>() {

    private var menuList:ArrayList<MenuModel> = arrayListOf()
    fun setData(data : List<MenuModel>){
        menuList.apply {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }

    inner class ListMenuViewHolder(private val binding: ItemCashierMenuBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(menuItem: MenuModel){

            binding.apply{
                tvNamaMenu.text = menuItem.nama_menu
                tvDeskripsiMenu.text = menuItem.deskripsi
                tvHargaMenu.text = menuItem.harga.toString()
                Glide.with(ivGambarMenu).load(menuItem.path).into(ivGambarMenu)
            }
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListMenuAdapter.ListMenuViewHolder {
        val viewBinding =
            ItemCashierMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListMenuViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ListMenuAdapter.ListMenuViewHolder, position: Int) {
        val item = menuList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return menuList.size
    }
}