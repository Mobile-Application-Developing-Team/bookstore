package com.example.bookstore.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.example.bookstore.Book;
import com.example.bookstore.R;
import com.example.bookstore.Shoppingcart;

public class BookDetail extends AppCompatActivity implements View.OnClickListener{

    TextView textView_name;
    Button rmBt;
    Button addBt;
    Button addShop;
    EditText count;

    Book book;

    public static void actionStart(FragmentActivity activity, Book book) {
        Intent intent = new Intent(activity,BookDetail.class);
        intent.putExtra("book", book);
        activity.startActivity(intent);
        Log.d("actionstart","start");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_detail);
        textView_name = (TextView) findViewById(R.id.detail_name);
        rmBt= (Button) findViewById(R.id.detail_removeBt);
        addBt= (Button) findViewById(R.id.detail_addBt);
        addShop= (Button) findViewById(R.id.detail_addShop);
        count= (EditText) findViewById(R.id.detial_count);

        rmBt.setOnClickListener(BookDetail.this);
        addBt.setOnClickListener(BookDetail.this);
        addShop.setOnClickListener(BookDetail.this);

        book = (Book) getIntent().getSerializableExtra("book");
        textView_name.setText(book.getName());
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.detail_removeBt:
                int c1 = Integer.parseInt(count.getText().toString());
                c1--;
                if(c1<0)c1=0;
                count.setText(String.valueOf(c1));
                break;
            case R.id.detail_addBt:
                int c2 = Integer.parseInt(count.getText().toString());
                c2++;
                count.setText(String.valueOf(c2));
                break;
            case R.id.detail_addShop:
                int c3 = Integer.parseInt(count.getText().toString());
                Toast.makeText(BookDetail.this,""+c3+"本"+book.getName()+"已加入您的购物车",Toast.LENGTH_SHORT).show();;

                Shoppingcart shoppingcart = new Shoppingcart(book.getId(),c3);
                shoppingcart.save();
                break;
        }
    }
}
