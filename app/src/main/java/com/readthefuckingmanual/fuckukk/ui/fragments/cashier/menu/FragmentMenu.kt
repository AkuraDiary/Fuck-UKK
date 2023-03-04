package com.readthefuckingmanual.fuckukk.ui.fragments.cashier.menu

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.readthefuckingmanual.fuckukk.data.model.menu.MenuModel
import com.readthefuckingmanual.fuckukk.data.repository.MenuRepository
import com.readthefuckingmanual.fuckukk.data.source.preferences.UserPreferences
import com.readthefuckingmanual.fuckukk.databinding.FragmentMenuBinding
import com.readthefuckingmanual.fuckukk.ui.activities.login.LoginActivity

class FragmentMenu : Fragment() {

    private var binding : FragmentMenuBinding? = null

    private var rvMenuMakananAdapter: ListMenuAdapter? = null
    private var rvMenuMinumanAdapter: ListMenuAdapter? = null

    private val userPreference by lazy {
        UserPreferences(requireContext())
    }

    private val userToken by lazy {
        userPreference.getSession().token
    }

    private var menuMakanan : List<MenuModel?>? = listOf()
    private var menuMinuman : List<MenuModel?>? = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rvMenuMakananAdapter = ListMenuAdapter()
        rvMenuMinumanAdapter = ListMenuAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMenuBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    fun setupBtnLogout(){
        binding?.btnLogoutCashierMenu?.setOnClickListener {
            userPreference.deleteSession()
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MenuRepository.getAllMenus(userToken!!).observe(viewLifecycleOwner){
            menuMakanan = it?.values?.filter { menu -> menu?.jenis == "makanan" }
            menuMinuman = it?.values?.filter { menu -> menu?.jenis == "minuman" }
            rvMenuMakananAdapter?.setData(menuMakanan as List<MenuModel>)
            rvMenuMinumanAdapter?.setData(menuMinuman as List<MenuModel>)


        }
        setupRvMenu()

        this.binding?.tvNameMenu?.text = userPreference.getSession().username
//        observeSelectedMenu()
    }

    fun setupRvMenu(){
        binding?.rvCashierMakanan?.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = rvMenuMakananAdapter
        }
        binding?.rvCashierMinuman?.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = rvMenuMinumanAdapter
        }
    }

    fun observeSelectedMenu(){
        // TODO: add selected menu into list in repository
    }
    companion object {
        @JvmStatic
        fun newInstance() =
            FragmentMenu()
    }
}