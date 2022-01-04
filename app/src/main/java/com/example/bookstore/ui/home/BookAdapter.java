package com.example.bookstore.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookstore.Book;
import com.example.bookstore.R;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> implements View.OnClickListener {

    private List<Book> mBookList;

    private OnItemClickListener mItemClickListener;


    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    @Override
    public void onClick(View v) {
        if (mItemClickListener!=null){
            mItemClickListener.onItemClick((Integer) v.getTag());
        }
    }
    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        View bookView;
        TextView bookName;
        TextView bookprice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookView = itemView;
            bookName = (TextView) itemView.findViewById(R.id.book_name);
            bookprice = (TextView) itemView.findViewById(R.id.book_price);
        }
    }

    public BookAdapter(List<Book> bookList){
        mBookList = bookList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener(this);//中为每个item添加点击事件
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Book book = mBookList.get(position);
        holder.bookName.setText(book.getName());
        holder.itemView.setTag(position);
        holder.bookprice.setText("￥"+String.valueOf(book.getPrice()));

    }

    @Override
    public int getItemCount() {
        return mBookList.size();
    }

}
