package com.example.booksapp

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

@Suppress("UNUSED_ANONYMOUS_PARAMETER")
class BookListFragment : Fragment() {

    companion object {
        fun newInstance() = BookListFragment()
    }

    private lateinit var viewModel: BookListViewModel
    private lateinit var v : View
    private lateinit var recBooks : RecyclerView
    private var repository = BooksRepository()
    private lateinit var booksList: MutableList<Books>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.book_list_fragment, container, false)
        recBooks = v.findViewById(R.id.recBooks)
        return v
    }

    override fun onStart() {
        super.onStart()

        recBooks.setHasFixedSize(true)

        recBooks.layoutManager  = LinearLayoutManager(context)

        recBooks.adapter = BooksAdapter(repository.getBooks()){ Books ->
                Snackbar.make(v, Books.title, Snackbar.LENGTH_SHORT ).show()

        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BookListViewModel::class.java)
        // TODO: Use the ViewModel
    }
}