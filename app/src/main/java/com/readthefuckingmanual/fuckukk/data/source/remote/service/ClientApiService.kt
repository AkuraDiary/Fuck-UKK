package com.readthefuckingmanual.fuckukk.data.source.remote.service

import com.readthefuckingmanual.fuckukk.data.model.meja.ListMejaResponse
import com.readthefuckingmanual.fuckukk.data.model.meja.MejaModel
import com.readthefuckingmanual.fuckukk.data.model.menu.MenuModel
import com.readthefuckingmanual.fuckukk.data.model.transaksi.ListTransaksiResponse
import com.readthefuckingmanual.fuckukk.data.model.transaksi.TransaksiModel
import com.readthefuckingmanual.fuckukk.data.model.user.UserModel
import okhttp3.Response
import retrofit2.Call

import retrofit2.http.*

interface ClientApiService {

    //AUTH
    @FormUrlEncoded
    @POST("login")
    fun doLogin(
        @Field("email") username : String,
        @Field("password") password : String
    ): Call<UserModel>
    //AUTH

    /* NEED BEARER TOKEN AS HEADER AUTH */

    //MENUS

    /* get all menus */
    @GET("menu")
    fun getAllMenus(
        @Header("Authorization") bearerToken : String
    ): Call<List<MenuModel?>?>?

    /* get menu by id */
    @GET("menu/{id_menu}")
    fun getMenuById(
        @Header("Authorization") bearerToken : String,
        @Path("id_menu") id_menu : Int
    ): Call<MenuModel?>?

    /* add menu */
    @FormUrlEncoded
    @POST("menu")
    fun addMenu(
        @Header("Authorization") bearerToken : String,
        @Field("nama_menu") nama_menu : String,
        @Field("harga") harga : Int,
        @Field("kategori") kategori : String,
        @Field("gambar") gambar : String
    )//: Call<Response>

    /* update menu */
    @FormUrlEncoded
    @POST("menu/{id_menu}")
    fun updateMenu(
        @Header("Authorization") bearerToken : String,
        @Path("id_menu") id_menu : Int,
        @Field("nama_menu") nama_menu : String?,
        @Field("harga") harga : Int?,
        @Field("kategori") kategori : String?,
        @Field("gambar") gambar : String?
    ): Call<Response>

    /* delete menu */
    @DELETE("menu/{id_menu}")
    fun deleteMenu(
        @Header("Authorization") bearerToken : String,
        @Path("id_menu") id_menu : Int
    ): Call<Response>
    //MENUS

    //MEJA

    /* get all Meja */
    @GET("meja")
    fun getAllMeja(
        @Header("Authorization") bearerToken : String
    ): Call<ListMejaResponse?>?

    /* get meja by id */
    @GET("meja/{id_meja}")
    fun getMejaById(
        @Header("Authorization") bearerToken : String,
        @Path("id_meja") id_meja : Int
    ): Call<MejaModel?>?

    /* add meja */
    @FormUrlEncoded
    @POST("meja")
    fun addMeja(
        @Header("Authorization") bearerToken : String,
        @Field("nomor_meja") nama_meja : String,
    )//: Call<Response>

    /* update meja */
    @FormUrlEncoded
    @POST("meja/{id_meja}")
    fun updateMeja(
        @Header("Authorization") bearerToken : String,
        @Path("id_meja") id_meja : Int,
        @Field("nomor_meja") nama_meja : String,
    ): Call<Response>

    /* delete meja */
    @DELETE("meja/{id_meja}")
    fun deleteMeja(
        @Header("Authorization") bearerToken : String,
        @Path("id_meja") id_meja : Int
    ): Call<Response>

    //MEJA

    //TRANSAKSI

    /* get all transaksi */
    fun getAllTransaksi(
        @Header("Authorization") bearerToken : String
    ): Call<ListTransaksiResponse?>?

    /* get transaksi by id */
    fun getTransaksiById(
        @Header("Authorization") bearerToken : String,
        @Path("id_transaksi") id_transaksi : Int
    ): Call<TransaksiModel?>?

    /* add transaksi */
    fun addTransaksi(
        @Header("Authorization") bearerToken : String,
        @Field("id_meja") id_meja : Int,
        @Field("nama_pelanggan") id_menu : Int,
        @Field("status") status : String,
        @Field("barang1") barang1 : Int,
        @Field("barang2") barang2 : Int?,
        @Field("barang3") barang3 : Int?,
        @Field("barang4") barang4: Int?,
        @Field("barang5") barang5 : Int?,
        @Field("barang6") barang6 : Int?,
        @Field("barang7") barang7 : Int?,
        @Field("barang8") barang8 : Int?,
        @Field("barang9") barang9 : Int?,
        @Field("barang10") barang10 : Int?
    )//: Call<Response>


    /* update transaksi */
    fun updateTransaksi(
        @Header("Authorization") bearerToken : String,
        @Path("id_transaksi") id_transaksi : Int,
        @Field("status") id_meja : String,

    ): Call<Response>
    /* delete transaksi */
    fun deleteTransaksi(
        @Header("Authorization") bearerToken : String,
        @Path("id_transaksi") id_transaksi : Int
    ): Call<Response>

    //TRANSAKSI


}