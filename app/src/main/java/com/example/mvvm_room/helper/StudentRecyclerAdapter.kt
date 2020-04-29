package com.example.mvvm_room.helper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_room.R
import com.example.mvvm_room.entity.Student

class StudentRecyclerAdapter(private val myDataset: List<Student>):

    RecyclerView.Adapter<StudentRecyclerAdapter.StudentViewHolder>() {

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tvName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        // membuat view baru
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        // mengeset ukuran view, margin, padding, dan parameter layout lainnya
        return StudentViewHolder(v)
    }

    override fun getItemCount(): Int {
        return myDataset.size
        // menghitung ukuran dataset / jumlah data yang ditampilkan di RecyclerView
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        // mengambil elemen dari dataset (ArrayList) pada posisi tertentu
        // mengeset isi view dengan elemen dari dataset tersebut
        holder.tvName.text = myDataset[position].name
    }
}