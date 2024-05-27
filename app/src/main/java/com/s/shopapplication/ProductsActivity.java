package com.s.shopapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ProductsActivity extends AppCompatActivity {
    List<Product> productList;
    AppDatabase database;
    ProductDao productDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        setTitle("Продукты");
        database = AppDatabase.getInstance(getApplication());
        productDao = database.productDao();
        loadProductsAndShow();
    }

    private void loadProductsAndShow(){
        productList = new ArrayList<Product>();

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                productList = productDao.getAll();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showProducts();
                    }
                });
            }
        });
    }

    private void showProducts(){
        RecyclerView rvProducts = findViewById(R.id.rv_products);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ProductsActivity.this);
        ProductAdapter productAdapter = new ProductAdapter(productList);
        productAdapter.setOnButtonClickListener(new ProductAdapter.OnButtonClickListener() {
            @Override
            public void onButtonClick(Product product) {
                CartKeeper.saveProductToList(ProductsActivity.this, product);
                Toast.makeText(ProductsActivity.this, "Добавлен в корзину " + product.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        rvProducts.setAdapter(productAdapter);
        rvProducts.setLayoutManager(linearLayoutManager);
        rvProducts.setHasFixedSize(true);
        rvProducts.setNestedScrollingEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.cart_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.cart_menu_btn){
            Intent intent = new Intent(ProductsActivity.this, CartActivity.class);
            startActivity(intent);
        }
        return true;
    }
}