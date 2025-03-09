package com.hello.online_cooking_recipe_app

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.hello.online_cooking_recipe_app.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private lateinit var rvAdapter: SearchAdapter
    private lateinit var datalist: ArrayList<Recipe>
    private var recipes: List<Recipe?>? = null

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.search.requestFocus()

        val db = Room.databaseBuilder(this@SearchActivity, AppDataBase::class.java, "db_name")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("recipe.db")
            .build()

        val daoObject = db.getDao()
        recipes = daoObject.getAll()
        binding.goBackHome.setOnClickListener{
            finish()
        }
        setUpRecyclerView()

        binding.search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrEmpty()) {
                    filterData(s.toString())
                } else {
                    rvAdapter.filterList(recipes as ArrayList<Recipe>)
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        binding.rvSearch.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                imm.hideSoftInputFromWindow(v.windowToken, 0)
            }
            false
        }
    }

    private fun filterData(filterText: String) {
        val filterData = recipes?.filter {
            it?.tittle?.lowercase()?.contains(filterText.lowercase()) == true
        }?.filterNotNull() ?: emptyList()

        rvAdapter.filterList(ArrayList(filterData))
    }

    private fun setUpRecyclerView() {
        datalist = ArrayList()
        binding.rvSearch.layoutManager = LinearLayoutManager(this)

        recipes?.filter { it?.category?.contains("Popular") == true }
            ?.let { datalist.addAll(it.filterNotNull()) }

        rvAdapter = SearchAdapter(datalist, this)
        binding.rvSearch.adapter = rvAdapter
    }
}
