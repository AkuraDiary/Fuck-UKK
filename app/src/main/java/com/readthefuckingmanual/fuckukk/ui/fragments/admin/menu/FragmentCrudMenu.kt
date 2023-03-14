package com.readthefuckingmanual.fuckukk.ui.fragments.admin.menu

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.readthefuckingmanual.fuckukk.data.model.menu.MenuModel
import com.readthefuckingmanual.fuckukk.data.repository.MenuRepository
import com.readthefuckingmanual.fuckukk.data.source.preferences.UserPreferences
import com.readthefuckingmanual.fuckukk.databinding.FragmentCrudMenuBinding
import com.readthefuckingmanual.fuckukk.ui.activities.admin.AdminActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"



/**
 * A simple [Fragment] subclass.
 * Use the [FragmentCrudMenu.newInstance] factory method to
 * create an instance of this fragment.
 */

class FragmentCrudMenu : Fragment() {

    private var binding: FragmentCrudMenuBinding? = null
    private var isedit = false
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
        binding = FragmentCrudMenuBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeSelectedMenu()
        setupButtonSave()
    }

    fun observeSelectedMenu(){
        MenuRepository.selectedmenu.observe(viewLifecycleOwner){
            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main){
                isedit = it != null
                if (isedit){
                    binding?.apply {
                        edtNamaMenu.setText(it?.nama_menu)
                        edtMenuType.setText(it?.jenis)
                        edtDescription.setText(it?.deskripsi)
                        edtPrice.setText(it?.harga)
                    }
                }else{
                    binding?.apply {
                        edtNamaMenu.text.clear()
                        edtMenuType.text.clear()
                        edtDescription.text.clear()
                        edtPrice.text.clear()
                    }
                }

            }

        }
    }

    // Handle the result of the gallery intent
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GALLERY_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            val imageUri = data.data
            if (imageUri != null) {
                // Load the image from the URI into a bitmap
                val inputStream = requireContext().contentResolver.openInputStream(imageUri)
                val bitmap = BitmapFactory.decodeStream(inputStream)

                // Convert the bitmap to a base64-encoded string
                val byteArrayOutputStream = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
                val byteArray = byteArrayOutputStream.toByteArray()
                val base64String = Base64.encodeToString(byteArray, Base64.DEFAULT)

                // Create the menu model with the image data
                val menuModel = MenuModel(
                    nama_menu = binding?.edtNamaMenu?.text.toString(),
                    jenis = binding?.edtMenuType?.text.toString(),
                    deskripsi =binding?.edtDescription?.text.toString(),
                    harga = binding?.edtPrice?.text.toString(),
                    filename = base64String,
                    id_menu = null,
                    path = null,
                )

                Log.d("ADD MENU", menuModel.toString())
                if (!isedit) {
                    MenuRepository.addMenu(userToken!!, menuModel.nama_menu!!, menuModel.jenis!!, menuModel.deskripsi!!, menuModel.filename, menuModel.harga, ).observe(viewLifecycleOwner){
                        if (it != null){
                            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
                                Toast.makeText(requireContext(), "Menu Ditambahkan ${it?.nama_menu}", Toast.LENGTH_SHORT)
                                (activity as AdminActivity).moveToAdminMenuFragment()
                            }
                        }
                    }
                } else {
                    menuModel.apply {
                        filename = MenuRepository.selectedmenu.value?.filename
                        id_menu = MenuRepository.selectedmenu.value?.id_menu
                        path = MenuRepository.selectedmenu.value?.path
                    }
                    MenuRepository.edtMenu(userToken!!,
                        menuModel.id_menu!!, menuModel.nama_menu!!, menuModel.jenis!!, menuModel.deskripsi!!, menuModel.path!!, menuModel.harga ).observe(viewLifecycleOwner){
                        if (it != null){
                            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
                                Toast.makeText(requireContext(), "Menu Diedit ${it?.nama_menu}", Toast.LENGTH_SHORT)
                                (activity as AdminActivity).moveToAdminMenuFragment()

                            }

                        }

                    }
                }
            }
        }
    }

    fun setupButtonSave(){
        binding?.apply {
            btnMenuSave.setOnClickListener() {

                val menuModel = MenuModel(
                    nama_menu = edtNamaMenu.text.toString(),
                    jenis = edtMenuType.text.toString(),
                    deskripsi = edtDescription.text.toString(),
                    harga = edtPrice.text.toString(),
                    filename = null,
                    id_menu = null,
                    path = null,
                )

                Log.d("ADD MENU", menuModel.toString())
                if (!isedit) {

//                    // Create an intent to pick an image from the gallery
//                    val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//                    startActivityForResult(intent, GALLERY_REQUEST_CODE)

                    MenuRepository.addMenu(userToken!!, menuModel.nama_menu!!, menuModel.jenis!!, menuModel.deskripsi!!, menuModel.path, menuModel.harga).observe(viewLifecycleOwner){
                        if (it != null){
                            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
                                Toast.makeText(requireContext(), "Menu Ditambahkan ${it?.nama_menu}", Toast.LENGTH_SHORT)
                                (activity as AdminActivity).moveToAdminMenuFragment()
                            }
                        }

                    }
                } else {
                    menuModel.apply {
                        filename = MenuRepository.selectedmenu.value?.filename
                        id_menu = MenuRepository.selectedmenu.value?.id_menu
                        path = MenuRepository.selectedmenu.value?.path
                    }
                    MenuRepository.edtMenu(userToken!!,
                        menuModel.id_menu!!, menuModel.nama_menu!!, menuModel.jenis!!, menuModel.deskripsi!!, menuModel.path!!, menuModel.harga ).observe(viewLifecycleOwner){
                        if (it != null){
                            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
                                Toast.makeText(requireContext(), "Menu Diedit ${it?.nama_menu}", Toast.LENGTH_SHORT)
                                (activity as AdminActivity).moveToAdminMenuFragment()

                            }

                        }

                    }
                }


            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        MenuRepository.selectedmenu.postValue(null)
    }

    fun deleteMenu(){

    }

    companion object {
        @JvmStatic
        fun newInstance() =
            FragmentCrudMenu()

        private const val GALLERY_REQUEST_CODE = 123

    }
}