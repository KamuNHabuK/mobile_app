package com.s.shopapplication;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{

    private List<Product> productList;
    private OnButtonClickListener onButtonClickListener;

    public ProductAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ProductAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.tvProductName.setText(product.getName());
        holder.tvProductDescription.setText(product.getDescription());
        holder.tvPrice.setText("Цена: " + String.valueOf(product.getPrice()) + " руб.");
        Glide.with(holder.itemView.getContext())
                .load(Uri.parse(product.getImageUri()))
                .into(holder.imvProduct);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder{

        TextView tvProductName;
        TextView tvProductDescription;
        TextView tvPrice;
        ImageView imvProduct;
        Button btnAddToCart;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvProductName = itemView.findViewById(R.id.tv_product_name);
            this.tvProductDescription = itemView.findViewById(R.id.tv_product_description);
            this.tvPrice = itemView.findViewById(R.id.tv_price);
            this.imvProduct = itemView.findViewById(R.id.imv_product);
            this.btnAddToCart = itemView.findViewById(R.id.btn_add_to_cart);

            btnAddToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (onButtonClickListener != null && position != RecyclerView.NO_POSITION) {
                        onButtonClickListener.onButtonClick(productList.get(position));
                    }
                }
            });
        }
    }

    public interface OnButtonClickListener {
        void onButtonClick(Product product);
    }

    public void setOnButtonClickListener(OnButtonClickListener listener) {
        this.onButtonClickListener = listener;
    }
}
