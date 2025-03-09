package com.hello.online_cooking_recipe_app

<<<<<<< HEAD
import android.content.Intent
=======
>>>>>>> 9ad6373a0db594cf10bc7a72d476514c691c7e4e
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.hello.online_cooking_recipe_app.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var rvAdapter: PopularAdapter
    private lateinit var binding: ActivityHomeBinding
    private lateinit var datalist: ArrayList<Recipe>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // ✅ Correct way to initialize binding
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecylcerView()
<<<<<<< HEAD
        binding.editTextText.setOnClickListener{
            startActivity(Intent(this,SearchActivity::class.java))
        }
=======
>>>>>>> 9ad6373a0db594cf10bc7a72d476514c691c7e4e
    }


    private fun setUpRecylcerView() {
        datalist = ArrayList()
        binding.rvpopular.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val db = Room.databaseBuilder(this, AppDataBase::class.java, "db_name")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("recipe.db")
            .build()

        val daoObject = db.getDao()
        val recipes = daoObject.getAll()

        for(i in recipes!!.indices){
            if(recipes[i]!!.category.contains("Popular")){
                datalist.add(recipes[i]!!)
            }
        }

        rvAdapter = PopularAdapter(datalist, this)
        binding.rvpopular.adapter = rvAdapter
    }
}
