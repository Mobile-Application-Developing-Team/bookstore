package com.example.bookstore.ui.shoppingcart;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.example.bookstore.Shoppingcart;
import com.example.bookstore.databinding.FragmentShoppingcartBinding;
import com.example.bookstore.ui.home.BookDetail;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class ShoppingcartFragment extends Fragment implements ShoppingcartAdapter.OnItemClickListener{

    private List<Shoppingcart> shoppingcartList = new ArrayList<>();

    TextView shoppingcartcount ;
    Button shoppingcarttopal;
    double count = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shoppingcart,container,false);
        initShoppingcart();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_shoppingcart);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        ShoppingcartAdapter adapter = new ShoppingcartAdapter(shoppingcartList);
        recyclerView.setAdapter(adapter);

        shoppingcartcount = (TextView) view.findViewById(R.id.shoppingcart_count);
        shoppingcarttopal = (Button) view.findViewById(R.id.shoppingcart_topal);


        shoppingcartcount.setText("购物车总计："+String.valueOf(count));
        shoppingcarttopal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//结算购物车&生成订单信息

            }
        });

        adapter.setItemClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    private void initShoppingcart() {
        shoppingcartList = LitePal.findAll(Shoppingcart.class);//从数据库读出所有购物车信息
        for(Shoppingcart shoppingcart: shoppingcartList) {
            Log.d("Shoppingcartlist",""+shoppingcart.getId());
            count += shoppingcart.getQuantity() * LitePal.find(Book.class,shoppingcart.getBook_id()).getPrice();//计算总价格
        }
    }

    @Override
    public void onItemClick(int position) {//点击购物车的一项之后跳转到该书的详情页面
        Shoppingcart shoppingcart = shoppingcartList.get(position);

        Book book = LitePal.find(Book.class,shoppingcart.getBook_id());
        Log.d("position",""+book.getId());
        BookDetail.actionStart(getActivity(),book);

    }
}