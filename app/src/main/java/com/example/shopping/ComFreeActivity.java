package com.example.shopping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComFreeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    List<Data> dataInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com_free);

        dataInfo = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Retrofit 호출
        Call<List<Data>> call = ApiClient.getApiService().getPost();
        call.enqueue(new Callback<List<Data>>() {
            @Override
            public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {

                dataInfo = response.body();

                Log.i("jun", ""+dataInfo);

                recyclerAdapter = new RecyclerAdapter(getApplicationContext(), dataInfo);
                recyclerView.setAdapter(recyclerAdapter);
            }
            @Override
            public void onFailure(Call<List<Data>> call, Throwable t) {

            }
        });
    }
}