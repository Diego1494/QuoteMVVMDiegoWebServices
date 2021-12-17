package dev.cardoso.quotesmvvm.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import dev.cardoso.quotesmvvm.data.model.QuoteModel
import dev.cardoso.quotesmvvm.databinding.ActivityAgregarBinding

import dev.cardoso.quotesmvvm.domain.UserPreferencesRepository
import dev.cardoso.quotesmvvm.presentation.viewmodel.AddQuoteViewModel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
    class AddQuoteActivity : AppCompatActivity() {
        private lateinit var binding: ActivityAgregarBinding
        private lateinit var userPreferencesRepository: UserPreferencesRepository

        private val addQuoteViewModel: AddQuoteViewModel by viewModels()

        private var token = ""
        override fun onCreate(savedInstanceState: Bundle?) {

            super.onCreate(savedInstanceState)
            binding = ActivityAgregarBinding.inflate(layoutInflater)
            userPreferencesRepository = UserPreferencesRepository(this@AddQuoteActivity)
            setContentView(binding.root)
            getToken()
            binding.btnAgregar.setOnClickListener {
                val id = binding.id.text.toString().toInt()
                val quote = binding.txtdes.text.toString()
                val author= binding.txtautor.text.toString()
                val quoteModel = QuoteModel(id=id,quote=quote,author=author)
                lifecycleScope.launch(Dispatchers.IO){
                    Log.e("TOKEN",token)
                    //mandar datos al servidor
                    addQuoteViewModel.addQuote(quoteModel,"Bearer $token")
                }
            }
            addQuoteViewModel.quoteResponse.let {  }
            observer()
        }
        private fun getToken(){
            lifecycleScope.launch(Dispatchers.IO){
                userPreferencesRepository.token.collect{token = it}

            }
        }
//para cliente MOSTRAR EN CUADRO--- para mostra el resultado

        private fun observer(){
            lifecycleScope.launch(Dispatchers.IO){
                addQuoteViewModel.quoteResponse.collect {
                    binding.txtmessage.text = it.message
                }
            }
        }
    }
