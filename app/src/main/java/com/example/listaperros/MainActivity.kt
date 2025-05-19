package com.example.listaperros

import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listaperros.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    lateinit var adapter: DogAdapter
    lateinit var dogList: MutableList<DogsResponse>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initRecyclerView()



    }

    private fun initRecyclerView() {
        binding.rvDog.layoutManager = LinearLayoutManager(this)
    }

    //instancia de objeto retrofit
    //el objeto tendra ->Url original
    // -> conversor de json a datos
    //conf de llamada a interneT
    private fun getRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breed/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //coroutines for search by name
    private fun searchByName(query: String){
        CoroutineScope(Dispatchers.IO).launch {
            //all in this site it was done in "second thread"
            //call is calling to retrofit ¡¡up!! ,
            val call: Response<DogsResponse> = getRetrofit().create(APIService::class.java).getDogsByBreeds("$query/images")
            //We have the response to the dog but ths solution are in the "body"
            val puppies = call.body()
            //exits response
            if (call.isSuccessful){
                //show recyclerview
            }else{
                //show error
            }
        }
    }


}