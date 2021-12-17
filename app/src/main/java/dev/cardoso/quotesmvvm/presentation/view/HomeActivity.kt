package dev.cardoso.quotesmvvm.presentation.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import dev.cardoso.quotesmvvm.databinding.ActivityAgregarBinding
import dev.cardoso.quotesmvvm.databinding.ActivityBotonesBinding
import dev.cardoso.quotesmvvm.databinding.ActivityMainBinding
import dev.cardoso.quotesmvvm.domain.UserPreferencesRepository
import dev.cardoso.quotesmvvm.presentation.viewmodel.HomeQuoteViewModel
import dev.cardoso.quotesmvvm.presentation.viewmodel.QuoteViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class HomeActivity: AppCompatActivity(){
    private lateinit var binding: ActivityBotonesBinding
    private lateinit var userPreferencesRepository: UserPreferencesRepository
    private val homeQuoteViewModel: HomeQuoteViewModel by viewModels()


    override  fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBotonesBinding.inflate(layoutInflater)
        userPreferencesRepository = UserPreferencesRepository(this@HomeActivity)
        setContentView(binding.root)
        observer()
        binding = ActivityBotonesBinding.inflate(layoutInflater)
    binding.btnAgregar.setOnClickListener {

        val intent = Intent(this, AddQuoteActivity::class.java)
        startActivity(intent)
    }
        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.btnEditar.setOnClickListener {
            val intent = Intent(this, EditQuoteActivity::class.java)
            startActivity(intent)
        }


    }
//para cliente MOSTRAR EN CUADRO--- para mostra el resultado

    private fun observer(){
        lifecycleScope.launch(Dispatchers.IO){
            homeQuoteViewModel.quoteResponse.collect {
                binding.txtMessage.text = it.message
            }
        }
    }


}