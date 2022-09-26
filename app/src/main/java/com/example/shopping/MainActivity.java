package com.example.shopping;

import static android.widget.Toast.LENGTH_SHORT;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    View drawerView;


    private FragmentManager fragmentManager = getSupportFragmentManager();
    private MainMenuFavoriteFragment fragmentBoard = new MainMenuFavoriteFragment();
    private MainMenuHomeFragment fragmentHome = new MainMenuHomeFragment();
    private MainMenuClothFragment fragmentInfo = new MainMenuClothFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        drawerView = findViewById(R.id.drawer);

        // 메뉴바 아이콘 터치시 슬라이드 구현
        ImageButton btn_open = findViewById(R.id.btn_open);
        btn_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(drawerView);
            }
        });
        // 오늘의 추천 데일리룩
        findViewById(R.id.menu_daily).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "데일리룩", LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, DailyActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawers();
            }
        });
        // 날씨별 코디 추천
        findViewById(R.id.menu_weather).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "날씨추천", LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawers();
            }
        });
        // 오늘의 행운의 컬러
        findViewById(R.id.menu_color).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "행운컬러", LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, ColorActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawers();
            }
        });
        // 자유게시판
        findViewById(R.id.menu_community_free).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "자유게시판", LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, ComFreeActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawers();
            }
        });
        // 코디추천
        findViewById(R.id.menu_community_recommend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "코디추천", LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, ComRecommendActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawers();
            }
        });
        // 중고거래
        findViewById(R.id.menu_community_trade).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "중고거래", LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, ComTradeActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawers();
            }
        });

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.menu_frame_layout, fragmentHome).commitAllowingStateLoss();

        // 하단 네비겡션바 연결
        BottomNavigationView bottomNavigationView = findViewById(R.id.menu_bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());
    }

//     하단 네비게이션바 클릭 시 각각의 프레그먼트로 이동
    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch (item.getItemId()) {
                case R.id.icon_favorite:
                    transaction.replace(R.id.menu_frame_layout, fragmentBoard).commitAllowingStateLoss();
                    break;
                case R.id.icon_home:
                    transaction.replace(R.id.menu_frame_layout, fragmentHome).commitAllowingStateLoss();
                    break;
                case R.id.icon_my_cloth:
                    transaction.replace(R.id.menu_frame_layout, fragmentInfo).commitAllowingStateLoss();
                    break;
            }
            return true;
        }
    }

}

