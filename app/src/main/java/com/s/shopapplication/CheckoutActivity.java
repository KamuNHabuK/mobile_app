package com.s.shopapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CheckoutActivity extends AppCompatActivity {

    private TextView tvSum;
    private EditText etNumber;
    private EditText etCode;
    private Button btnGetCode;
    private Button btnToCatalog;
    private Button btnPay;
    private String address;
    private double orderSum;

    private CardView infoCard;

    private TextView tvOrderInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        setTitle("Оплата заказа");

        tvSum = findViewById(R.id.tv_sum);
        etNumber = findViewById(R.id.et_number);
        etCode = findViewById(R.id.et_code);
        btnGetCode = findViewById(R.id.btn_get_code);
        btnPay = findViewById(R.id.btn_pay);
        btnToCatalog = findViewById(R.id.btn_to_catalog);
        infoCard = findViewById(R.id.info_card);
        tvOrderInfo = findViewById(R.id.tv_order_info);

        address = getIntent().getStringExtra("address");
        orderSum = getIntent().getDoubleExtra("orderSum", 0);

        tvSum.setText(String.valueOf(orderSum) + " руб.");

        btnGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (etNumber.getText().toString().equals("")) {
                        throw new IllegalArgumentException("Необходимо указать номер карты!");
                    }
                    Toast.makeText(CheckoutActivity.this, "Код для подтверждения должен быть отправлен банковским приложением!", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(CheckoutActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        etCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!etCode.getText().toString().equals("")){
                    btnPay.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CheckoutActivity.this, "Успешно! Ждите заказанные товары по адресу: " + address, Toast.LENGTH_SHORT).show();
                btnPay.setEnabled(false);
                infoCard.setVisibility(View.VISIBLE);
                StringBuilder orderInfo = new StringBuilder("" +
                        "Адрес: " + address + "\n" +
                        "Имя клиента: " + ClientInfoKeeper.getClientName(CheckoutActivity.this) + "\n" +
                        "Номер клиента: " + ClientInfoKeeper.getClientNumber(CheckoutActivity.this) + "\n" +
                        "Сумма заказа: " + orderSum + " руб." + "\n" +
                        "Товары в заказе: \n")
                        ;

                List<Product> products = CartKeeper.getProductList(CheckoutActivity.this);
                for (Product product : products) {
                    orderInfo.append(product.getName()).append(" (").append(product.getPrice()).append(" руб.)").append("\n");
                }
                tvOrderInfo.setText(orderInfo.toString());
                CartKeeper.removeProductList(CheckoutActivity.this);
                btnToCatalog.setVisibility(View.VISIBLE);
            }
        });

        btnToCatalog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CheckoutActivity.this, ProductsActivity.class);
                startActivity(intent);
            }
        });
    }
}