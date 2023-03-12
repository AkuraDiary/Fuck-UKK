package com.readthefuckingmanual.fuckukk.ui.fragments.admin.user

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.readthefuckingmanual.fuckukk.R
import com.readthefuckingmanual.fuckukk.data.model.user.UserAdminModel
import com.readthefuckingmanual.fuckukk.data.repository.MenuRepository
import com.readthefuckingmanual.fuckukk.data.source.preferences.UserPreferences
import com.readthefuckingmanual.fuckukk.databinding.FragmentUserBinding
import com.readthefuckingmanual.fuckukk.ui.fragments.cashier.menu.FragmentMenu
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FragmentUser : Fragment() {

    private var binding : FragmentUserBinding? = null

    private var rvUserAdapter: ListUserAdapter? = null

    private val userPreference by lazy {
        UserPreferences(requireContext())
    }

    private val userToken by lazy{
        userPreference.getSession().token
    }

    private var userAdmin : List<UserAdminModel?>? = listOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun setupRvUser() {
        binding?.rvUserAdmin?.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = rvUserAdapter
        }
    }
    companion object {
        @JvmStatic
        fun newInstance() =
            FragmentMenu()
    }

}