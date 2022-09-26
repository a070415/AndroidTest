package com.example.shopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class WeatherActivity extends AppCompatActivity {

    TextView tv;
    String tem;
    private String URL = "https://m.search.naver.com/search.naver?sm=mtp_hty.top&where=m&query=%EC%84%9C%EC%9A%B8+%EB%82%A0%EC%94%A8";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        tv = findViewById(R.id.tv_weather);
        final Bundle bundle = new Bundle();

        new Thread() {
            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect(URL).get();
                    Elements temper = doc.select(".temperature_text");
                    tem = temper.get(0).text().substring(5);
                    bundle.putString("temperature", tem);

                    Message msg = handler.obtainMessage();
                    msg.setData(bundle);
                    handler.sendMessage(msg);


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            Bundle bundle = msg.getData();
            tv.setText("" + bundle.getString("temperature"));
        }
    };
}