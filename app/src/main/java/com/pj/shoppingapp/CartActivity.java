package com.pj.shoppingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;
import com.pj.shoppingapp.adapter.CartAdapter;
import com.pj.shoppingapp.adapter.ColectionsAdapter;
import com.pj.shoppingapp.model.Cart;
import com.pj.shoppingapp.model.Colections;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity implements CartListener {

    ArrayList<Cart> cartList;
    CartAdapter cartAdapter;
    DatabaseReference databaseReference,databaseReferencechild ;
    String account;
    RecyclerView cartRecyclerView;
    String name;
    ImageView btnBack;
    Button btn_payment;
    ArrayList<String> keysList= new ArrayList<String>(),positionsList = new ArrayList<String>();
    TextView tvTotalPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
//        Intent i = getIntent();
//
//        name = i.getStringExtra("name");
        btn_payment=findViewById(R.id.payment);
        btnBack = findViewById(R.id.btn_back);
        cartList = new ArrayList<Cart>();
        account = getIntent().getStringExtra("Account");
        cartRecyclerView = findViewById(R.id.rcvCart);

        databaseReference = FirebaseDatabase.getInstance().getReference("Cart");
//        databaseReferencechild = FirebaseDatabase.getInstance().getReference("Cart").child(String.valueOf(id));

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!cartList.isEmpty()) {
                    cartList.clear();
                    keysList.clear();
                }
                for (DataSnapshot data : snapshot.getChildren()) {
                    Cart cart = data.getValue(Cart.class);
                    if (!keysList.contains(data.getKey())) {
                        keysList.add(data.getKey());
                    }
                    if (cart.getAccount() != null) {
                        if (cart.getAccount().equals(account)) {
                            cartList.add(cart);
                        }
                    }
                }
                setCartRecycler((ArrayList<Cart>) cartList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });



        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        cartRecyclerView.addItemDecoration(itemDecoration);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                deleteItem(position);
                cartList.remove(position);
                cartAdapter.notifyDataSetChanged();

            }
        });
        itemTouchHelper.attachToRecyclerView(cartRecyclerView);
        //cartList.add( new Cart("Nike Air Jordan 1 Mid GS ‘Noble Red’","255$","https://sneakerdaily.vn/wp-content/uploads/2022/12/giay-nike-air-jordan-1-mid-gs-noble-red-554725-066.jpg.webp","30"));



    }

    private void setCartRecycler(ArrayList<Cart> dataList) {
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        cartRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        cartAdapter = new CartAdapter(this, dataList,this);
        cartRecyclerView.setAdapter(cartAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent returnIntent = new Intent();
        returnIntent.putExtra("Account",getIntent().getStringExtra("Account"));
        setResult(this.RESULT_OK, returnIntent);
        finish();
    }

    private void deleteItem(int position ){
        databaseReference = FirebaseDatabase.getInstance().getReference("Cart");
        databaseReference.child(keysList.get(position)).removeValue();
    }
    DatabaseReference databaseReference1;

    public void onPaymentPressed(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);
        builder.setTitle("Payment");
        builder.setMessage("Do you want to pay?");

        builder.setCancelable(false);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                while(!positionsList.isEmpty()){
                    databaseReference1 = FirebaseDatabase.getInstance().getReference("Bill");
                    databaseReference1.push().setValue(cartList.get(Integer.parseInt(positionsList.get(0))));
                    databaseReference.child(keysList.get(Integer.parseInt(positionsList.get(0)))).removeValue();
                    positionsList.remove(0);
                }
                keysList.clear();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();






   }

    public void onQuit(View view) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("Account",getIntent().getStringExtra("Account"));
        setResult(this.RESULT_OK, returnIntent);
        finish();
    }

    @Override
    public void onItemChange(int totalPrice, ArrayList<String> positions) {
        tvTotalPrice = findViewById(R.id.tv_price);
        tvTotalPrice.setText(String.valueOf(totalPrice));
        positionsList = positions;
    }
}