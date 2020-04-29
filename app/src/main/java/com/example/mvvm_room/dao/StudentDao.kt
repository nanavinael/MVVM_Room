package com.example.mvvm_room.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mvvm_room.entity.Student

//Dao digunakan sebagai tempat untuk query database

@Dao
//Untuk mengakses database kita perlu membuat interface
//yang kita berikan annotation @Dao
interface StudentDao {
    //Query untuk menampilkan seluruh data Murid
    @Query("Select * from student")
    fun getAll(): List<Student>
    //Query untuk Insert data
    @Insert
    fun insertStudent(item: Student)
}