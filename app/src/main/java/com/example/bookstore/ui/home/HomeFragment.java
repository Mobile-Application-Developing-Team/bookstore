package com.example.bookstore.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookstore.Book;
import com.example.bookstore.R;
import com.example.bookstore.databinding.FragmentHomeBinding;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements BookAdapter.OnItemClickListener{

    private List<Book> bookList = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        initBook();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        BookAdapter adapter = new BookAdapter(bookList);
        recyclerView.setAdapter(adapter);

        adapter.setItemClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    private void initBook() {
        bookList = LitePal.findAll(Book.class);//从数据库读出
        for(Book book: bookList) {
            Log.d("booklist",""+book.getId()+book.getName());
        }
    }

    @Override
    public void onItemClick(int position) {
        Book book = bookList.get(position);
        Log.d("position",""+book.getId());
        BookDetail.actionStart(getActivity(),book);


    }
}