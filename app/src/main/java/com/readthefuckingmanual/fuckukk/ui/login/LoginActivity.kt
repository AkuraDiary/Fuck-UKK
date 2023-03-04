package com.readthefuckingmanual.fuckukk.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.readthefuckingmanual.fuckukk.R
import com.readthefuckingmanual.fuckukk.databinding.ActivityLoginBinding
import com.readthefuckingmanual.fuckukk.ui.main.MainActivity

class LoginActivity : AppCompatActivity() {
    private var binding : ActivityLoginBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        // TODO: check logged in user
//        if (!(userPreferences.getSession())?.user_id.isNullOrEmpty()){
//            moveTOMainActivity()
//        }

//        setupInput()
//        observeLoginResult()
    }

//    TODO: Repository Missing
//    fun observeLoginResult(){
//        Repository.userResponse.observe(this){
//            if (it.user_id?.isNotEmpty()!!){
//                userPreferences.saveSession(it.name!!, it.user_id!!)
//                moveTOMainActivity()
//            }
//        }
//    }

//    TODO: Repository Missing
//    fun setupInput(){
//
//        binding?.btnLogin?.setOnClickListener{
//            //DO LOGIN HERE
//            if (formisvalid()){
//                Repository.doLogin(
//                    binding?.edtUsername?.text.toString(),
//                    binding?.edtPassword?.text.toString()
//                )
//            }
//        }
//    }

    fun formisvalid() : Boolean{
        when{

            binding?.edtUsername?.text.isNullOrEmpty() -> {
                binding?.edtUsername!!.error = "Username tidak boleh kosong"
                return false
            }

            binding?.edtPassword?.text.isNullOrEmpty() -> {
                binding?.edtPassword!!.error = "Password tidak boleh kosong"
                return false
            }
            else -> {
                return true

            }
        }
    }

    fun moveTOMainActivity(){
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}