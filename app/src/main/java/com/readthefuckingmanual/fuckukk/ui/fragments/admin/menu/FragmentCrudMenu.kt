package com.readthefuckingmanual.fuckukk.ui.fragments.admin.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.readthefuckingmanual.fuckukk.R
import com.readthefuckingmanual.fuckukk.data.repository.MenuRepository
import com.readthefuckingmanual.fuckukk.data.source.preferences.UserPreferences
import com.readthefuckingmanual.fuckukk.databinding.FragmentCrudMenuBinding
import com.readthefuckingmanual.fuckukk.databinding.FragmentMenuBinding
import com.readthefuckingmanual.fuckukk.ui.activities.admin.AdminActivity

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
    }

    fun observeSelectedMenu(){
        MenuRepository.selectedmenu.observe(viewLifecycleOwner){
            isedit = it != null
        }
    }
    fun setupButtonSave(){
        binding?.btnMenuSave?.setOnClickListener() {
//            MenuRepository.addMenu()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentCrudMenu.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentCrudMenu()

    }
}