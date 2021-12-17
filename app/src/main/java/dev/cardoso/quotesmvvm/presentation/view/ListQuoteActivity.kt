package dev.cardoso.quotesmvvm.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import dev.cardoso.quotesmvvm.databinding.ActivityMenuBinding
//import dev.cardoso.quotesmvvm.presentation.viewmodel.ListQuoteViewModel
import dev.cardoso.quotesmvvm.presentation.viewmodel.QuoteViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
/*
@AndroidEntryPoint
class ListQuoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding
    private val listQuoteViewModel: ListQuoteViewModel by ViewModels()
    private lateinit var quoteList: List<QuoteViewModel>
    private lateinit var listQuoteAdapter : ListQuoteAdapter



    override  fun onCreate(saveInstanceStateException: Bundle?){

        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observer()


    }
    private fun observer(){
        lifecycleScope.launch {
            log.e("OBSERVER","interceptando datos en el observer")
            listQuoteViewModel.quoteResponse.collect{
                if (it.success){
                    quoteList=it.data
                    val layoutManager : RecyclerView.layoutManager
                    =  LinearLayoutManager( this@ListQuoteActivity)
                    binding.quoteRecycleView.layoutManager = layoutManager
                    listQuoteaAdapter = ListQuoteAdapter (this@ListQuoteActivity,quoteList)
                    binding.quoteRecyclerView.adapter = listQuoteAdapter

                } else if (it.message.trim()!="") {
                    val alert = CustomAlert()
                    alert.showDialog(this@ListQuoteActivity,it.message)
                }
            }
        }
    }


}*/