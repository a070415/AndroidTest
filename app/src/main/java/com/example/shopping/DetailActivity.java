package com.example.shopping;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    TextView post_title;
    TextView post_content;

    List<Data> datataList;

    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String title_data = intent.getExtras().getString("title");
        String contnet_data = intent.getExtras().getString("content");

        Call<List<Data>> call = ApiClient.getApiService().getPost();
        call.enqueue(new Callback<List<Data>>() {
            @Override
            public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
                if(response.isSuccessful()) {

                    datataList = response.body();

                    post_title = findViewById(R.id.post_title);
                    post_content = findViewById(R.id.post_content);

                    post_title.setText(title_data);
                    post_content.setText(contnet_data);
//                    Log.i("jun", data.getContent());

                }
            }

            @Override
            public void onFailure(Call<List<Data>> call, Throwable t) {

            }
        });

    }
}