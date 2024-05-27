package com.s.shopapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    private TextView tvSum;
    private EditText etAddress;

    private Button btnToPayment;
    private double orderSum;
    private List<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        setTitle("Корзина");

        tvSum = findViewById(R.id.tv_sum);
        etAddress = findViewById(R.id.et_address);
        btnToPayment = findViewById(R.id.btnToPayment);
        orderSum = 0;

        btnToPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toCheckout();
            }
        });
        loadProductsAndCalcSum();
    }

    private void loadProductsAndCalcSum() {
        products = CartKeeper.getProductList(CartActivity.this);
        for (Product product : products) {
            orderSum += product.getPrice();
        }
        tvSum.setText(String.valueOf(orderSum) + " руб.");
    }

    private void toCheckout() {
        try {
            if (etAddress.getText().toString().equals("")) {
                throw new IllegalArgumentException("Необходимо указать адрес доставки!");
            }
            if (orderSum == 0){
                throw new IllegalArgumentException("Необходимо выбрать товары!");
            }
            Intent intent = new Intent(CartActivity.this, CheckoutActivity.class);
            intent.putExtra("orderSum", orderSum);
            intent.putExtra("address", etAddress.getText().toString());
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}