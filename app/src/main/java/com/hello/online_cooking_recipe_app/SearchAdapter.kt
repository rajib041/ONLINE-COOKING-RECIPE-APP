package com.hello.online_cooking_recipe_app

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hello.online_cooking_recipe_app.databinding.SearchRvBinding

class SearchAdapter(var dataList: ArrayList<Recipe>,var context: Context):RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: SearchRvBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view= SearchRvBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
    fun filterList(filterList:ArrayList<Recipe>){
        dataList=filterList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(dataList.get(position).img).into(holder.binding.searchImg)
        holder.binding.searchTxt.text=dataList.get(position).tittle
    }
}