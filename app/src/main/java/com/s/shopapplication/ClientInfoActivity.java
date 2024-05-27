package com.s.shopapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ClientInfoActivity extends AppCompatActivity {

    private EditText etNumber;
    private EditText etName;

    private Button btnToProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_info);
        setTitle("Личная информация");

        etName = findViewById(R.id.et_name);
        etNumber = findViewById(R.id.et_number);
        btnToProducts = findViewById(R.id.btn_to_products);

        btnToProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (etNumber.getText().toString().equals("")) {
                        throw new IllegalArgumentException("Необходимо указать номер!");
                    }
                    if (etName.getText().toString().equals("")) {
                        throw new IllegalArgumentException("Необходимо указать имя!");
                    }
                    ClientInfoKeeper.saveClientInfo(ClientInfoActivity.this, etNumber.getText().toString(), etName.getText().toString());
                    Intent intent = new Intent(ClientInfoActivity.this, ProductsActivity.class);
                    startActivity(intent);
                }
                catch (Exception e) {
                    Toast.makeText(ClientInfoActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}