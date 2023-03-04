package com.readthefuckingmanual.fuckukk.data.model.transaksi

import com.readthefuckingmanual.fuckukk.data.model.menu.MenuModel

data class DetailTransaksiModel(
    var barang: List<MenuModel>?,
    var id_meja: String?,
    var message: String?,
    var nama_pelanggan: String?,
    var status: String?,
    var total_harga: Int?,
    var user: Int?
)