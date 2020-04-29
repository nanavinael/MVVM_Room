package com.example.mvvm_room.viewmodel

import android.app.Application
import android.graphics.Movie
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvm_room.data.AppDatabase
import com.example.mvvm_room.entity.Student
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber

class NewStudentViewModel(application: Application) : AndroidViewModel(application) {
    private val mDb: AppDatabase? = AppDatabase.getInstance(application)
    private val allStudent = MutableLiveData<List<Student>>() // MutableLiveData dapat merubah value dari data nya (setValue||postValue)

    //fungsi untuk menyimpan input an ke database
    fun storeMovie(title: String) {

        val student = Student()
        student.name = title

        GlobalScope.launch {

            //memanggil query insertdata pada kelas studentDao
            mDb?.studentDao()?.insertStudent(student)
        }
    }
    fun retrieveStudent(): LiveData<List<Student>>
    // fungsi ini akan mengembalikan data hasil input diatas dalam livedata
    {

        GlobalScope.launch {

            //memanggil query menmpilkan pada kelas studentDao

            val list = mDb?.studentDao()?.getAll()

            Timber.i("Data yang ada sejumlah {${list?.size}}")
            allStudent.postValue(list)
        }

        return allStudent
    }
}