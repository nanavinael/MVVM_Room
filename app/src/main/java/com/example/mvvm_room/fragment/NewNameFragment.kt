package com.example.mvvm_room.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders

import com.example.mvvm_room.R
import com.example.mvvm_room.viewmodel.NewStudentViewModel
import kotlinx.android.synthetic.main.fragment_new_name.*

/**
 * A simple [Fragment] subclass.
 */
//fragment yang berfungsi sebagai tempat/komponen untuk menginput nama
class NewNameFragment : Fragment() {

    //Fragment dapat berkomunikasi dengan parent activity menggunakan sebuah interface dan listener.
    // Buat objek untuk listener
    private var listener: OnFragmentInteractionListener? = null

    // deklarasi ViewModel
    private lateinit var mViewModel: NewStudentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * Menghubungkan activity/fragment dengan NewStudentViewModel
         * */
        mViewModel = ViewModelProviders.of(this).get(NewStudentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Layout XML tampilan untuk fragment ini
        return inflater.inflate(R.layout.fragment_new_name, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //fungsi pada button tambah ketika di klik
        button.setOnClickListener {
            val input = editText.text.toString().trim()

            if (input.isEmpty()) {

                //cek apabila inputan kosong
                Toast.makeText(activity, "Nama dibutuhkan", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (input.length > 30) {

                //cek apabila inputan lebih dari 30 huruf
                Toast.makeText(activity, "Nama terlalu panjang", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            //set data input
            mViewModel.storeMovie(input)

            //Intent fragment NameListFragment
            Toast.makeText(activity, "$input entered", Toast.LENGTH_SHORT).show()
            listener?.goToStudentListFragment()
        }
    }

    //dipanggil saat sebuah fragment terhubung ke activity.
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    //dipanggil saat fragment tidak lagi terhubung ke sebuah activity.
    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun goToStudentListFragment()
    }

    companion object {
        @JvmStatic
        fun newInstance() = NewNameFragment()
    }
}
