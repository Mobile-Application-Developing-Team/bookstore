package com.example.bookstore.ui.shoppingcart;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookstore.Book;
import com.example.bookstore.R;
import com.example.bookstore.Shoppingcart;
import com.example.bookstore.ui.home.BookAdapter;

import org.litepal.LitePal;

import java.util.List;

public class ShoppingcartAdapter extends RecyclerView.Adapter<ShoppingcartAdapter.ViewHolder> implements View.OnClickListener {

    private List<Shoppingcart> mShoppingcartList;

    private com.example.bookstore.ui.shoppingcart.ShoppingcartAdapter.OnItemClickListener mItemClickListener;


    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    @Override
    public void onClick(View v) {
        if (mItemClickListener!=null){
            mItemClickListener.onItemClick((Integer) v.getTag());
        }
    }
    public void setItemClickListener(com.example.bookstore.ui.shoppingcart.ShoppingcartAdapter.OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        View shoppingcartView;
        TextView shoppingcartName;
        TextView shoppingcartPrice;
        TextView shoppingcartQuantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            shoppingcartView = itemView;
            shoppingcartName = (TextView) itemView.findViewById(R.id.shoppingcart_name);
            shoppingcartPrice = (TextView) itemView.findViewById(R.id.shoppingcart_price);
            shoppingcartQuantity = (TextView) itemView.findViewById(R.id.shoppingcart_quantity);
        }
    }

    public ShoppingcartAdapter(List<Shoppingcart> shoppingcartList){
        mShoppingcartList = shoppingcartList;
    }
    @NonNull
    @Override
    public com.example.bookstore.ui.shoppingcart.ShoppingcartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shoppingcart_item,parent,false);
        com.example.bookstore.ui.shoppingcart.ShoppingcartAdapter.ViewHolder holder = new com.example.bookstore.ui.shoppingcart.ShoppingcartAdapter.ViewHolder(view);
        view.setOnClickListener(this);//中为每个item添加点击事件
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingcartAdapter.ViewHolder holder, int position) {
        Shoppingcart shoppingcart = mShoppingcartList.get(position);
        Log.d("mShoppingcartList","shoppingcart.getBook_id()");
        Book book = LitePal.find(Book.class,shoppingcart.getBook_id());

        holder.shoppingcartName.setText(book.getName());
        holder.shoppingcartPrice.setText("价格："+ String.valueOf(book.getPrice()));
        holder.shoppingcartQuantity.setText("数量："+String.valueOf(shoppingcart.getQuantity()));
        holder.itemView.setTag(position);

    }

    @Override
    public int getItemCount() {
        return mShoppingcartList.size();
    }

}