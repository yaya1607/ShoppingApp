package com.pj.shoppingapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pj.shoppingapp.adapter.ColectionsAdapter;
import com.pj.shoppingapp.model.Colections;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    RecyclerView colectionsRecyclerView;
    ColectionsAdapter colectionsAdapter;
    List<Colections> colectionsList = new ArrayList<>();
    String account;
    LottieAnimationView hi2023;
    DatabaseReference databaseReference;
    EditText search;
    ImageView btnSignOut;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        search = findViewById(R.id.etSearch);
        btnSignOut = findViewById(R.id.btn_signout);
        progressDialog = new ProgressDialog(this);


        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, MainActivity.class);
                Toast.makeText(HomeActivity.this, "Sign out", Toast.LENGTH_SHORT).show();
                mAuth.signOut();
                startActivity(i);
            }
        });
        hi2023 = findViewById(R.id.hi2023);
        hi2023.playAnimation();
        colectionsRecyclerView=findViewById(R.id.colectionsRecycler);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                colectionsAdapter.getFilter().filter(editable);
            }
        });







        account=  getIntent().getStringExtra("Account");

        //add Colection
        colectionsList.add(new Colections("$",account,1,"https://sneakerdaily.vn/wp-content/uploads/2020/11/air-jordan-1-high-dark-mocha-555088-105-1.png.webp","Air Jordan 1 Dark Mocha High OG",115,"4.5","This OG AJ1 employs a new twist on a familiar colour scheme: dark mocha."));
        colectionsList.add(new Colections("$",account,2,"https://sneakerdaily.vn/wp-content/uploads/2022/12/giay-nike-air-jordan-1-mid-gs-noble-red-554725-066.jpg.webp","Nike Air Jordan 1 Mid GS ‘Noble Red’",255,"5.0","Inspired by 2018's 'Bred Toe,' the Air Jordan 1 Mid GS 'Noble Red' is a grade-school shoe with a familiar mix of colors."));
        colectionsList.add(new Colections("$",account,3,"https://sneakerdaily.vn/wp-content/uploads/2022/12/giay-nike-undefeated-x-air-force-1-low-sp-dunk-vs-af1-dm8462-400.jpg.webp","Nike Undefeated x Air Force 1 Low",96,"4.0","Introduced in 1982, the Air Force 1 redefined basketball footwear from the hardwood to the tarmac."));
        colectionsList.add(new Colections("$",account,4,"https://sneakerdaily.vn/wp-content/uploads/2022/09/Thiet-ke-chua-co-ten-2022-09-28T125111.296.jpg.webp","Nike Air Force 1 Shadow",100,"4.5","The Nike Air Force 1 Shadow puts a playful twist on a classic b-ball design.Using a layered approach, doubling the branding and exaggerating the midsole, it highlights AF-1 DNA with a bold, new look."));
        colectionsList.add(new Colections("$",account,5,"https://sneakerdaily.vn/wp-content/uploads/2021/07/giay-nam-air-jordan-4-retro-white-oreo-ct8527-100-1.jpg.webp","Air Jordan 4 Retro ‘White Oreo’",200,"4.5","The Air Jordan 4 Retro 'White Oreo' features a design theme that recalls the original 'Oreo' AJ4 from 1999."));
        colectionsList.add(new Colections("$",account,6,"https://sneakerdaily.vn/wp-content/uploads/2022/08/Thiet-ke-chua-co-ten-7-1.jpg.webp","Nike Air Jordan 1 Retro High OG ‘Taxi’",250,"5.0","The Air Jordan 1 Retro High OG Yellow Toe brings back the distinct color blocking as well as the sneaker´s high silhouette."));
        colectionsList.add(new Colections("$",account,7,"https://sneakerdaily.vn/wp-content/uploads/2022/12/giay-bong-ro-nike-kd-trey-5-ix-black-metallic-cool-grey-cw3400-006.jpg.webp","Nike Kd Trey 5 IX ‘Black Metallic Cool Grey’",125,"5.0","The 9th iteration of Kevin Durant's budget model is a solid choice for price-conscious basketball players."));
        colectionsList.add(new Colections("$",account,8,"https://sneakerdaily.vn/wp-content/uploads/2022/11/giay-bong-ro-nike-kd-trey-5-x-ep-black-gold-dj7554-010.jpg.webp","Nike Kd Trey 5 X Ep ‘Black Gold’",175,"4.5","The 9th iteration of Kevin Durant's budget model is a solid choice for price-conscious basketball players."));
        colectionsList.add(new Colections("$",account,9,"https://sneakerdaily.vn/wp-content/uploads/2022/12/giay-nike-air-force-1-07-le-starfish-dm0970-111-2.jpg.webp","Nike Air Force 1 ’07 LE ‘Starfish’",200,"5.0","Re-imagining the hoops icon through a lens of self-expression, the Dunk Low Disrupt 2 bridges tried-and-tested style with a customisable design."));
        colectionsList.add(new Colections("$",account,10,"https://sneakerdaily.vn/wp-content/uploads/2022/11/giay-bong-ro-nike-kyrie-8-infinity-ep-man-machine-dc9134-101.jpg.webp","Nike Kyrie 8 Infinity EP ‘Man Machine’",150,"4.8","The Kyrie Infinity provides a tight custom fit, enhanced responsiveness in the forefoot and traction up the sides."));



        databaseReference  = FirebaseDatabase.getInstance().getReference("Items");
        databaseReference.setValue(colectionsList);



        setColectionsRecycler((ArrayList<Colections>) colectionsList);


    }

    private void setColectionsRecycler(ArrayList<Colections> dataList) {
        StaggeredGridLayoutManager staggeredGridLayoutManager= new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        colectionsRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        colectionsAdapter = new ColectionsAdapter(this,dataList);
        colectionsRecyclerView.setAdapter(colectionsAdapter);
    }


    public void onClickCart(View view) {
        Intent i = new Intent(HomeActivity.this,CartActivity.class);
        i.putExtra("Account",account);
        startActivity(i);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        setResult(RESULT_OK,data);
        account = data.getStringExtra("Account").toString();
    }
}