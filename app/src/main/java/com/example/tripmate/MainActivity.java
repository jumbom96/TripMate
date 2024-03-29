package com.example.tripmate;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    BottomNavigationView bottomNavigationView;
    Button open_close;
    DrawerLayout drawerLayout;

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private fragmentActivity1 fragment1 = new fragmentActivity1();
    private fragmentActivity2 fragment2 = new fragmentActivity2();
    private fragmentActivity3 fragment3 = new fragmentActivity3();
    private fragmentActivity4 fragment4 = new fragmentActivity4();
    private NearLocationFragment fragment5 = new NearLocationFragment();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //첫 화면 지정
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout,fragment1).commitAllowingStateLoss();

        //상단 툴바 생성
        toolbar = (Toolbar)findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        //툴바에 홈버튼을 활성화
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //툴바의 홈버튼의 이미지를 변경(기본 이미지는 뒤로가기 화살표)
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_appbar_menu);
        //툴바 중앙 제목
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //Navigation Drawer 생성 및 초기화
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        //하단 툴바 생성
        bottomNavigationView =(BottomNavigationView)findViewById(R.id.bottom_navigation);


        // BottomNavigationView 메뉴를 선택할 때마다 위치가 변하지 않도록
        BottomNavigationHelper.disableShiftMode(bottomNavigationView);


        // bottomNavigationView의 아이템이 선택될 때 호출될 리스너 등록
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                FragmentTransaction transaction = fragmentManager.beginTransaction();

                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        transaction.replace(R.id.frameLayout, fragment1).commitAllowingStateLoss();
                        break;
                    case R.id.action_home2:
                        transaction.replace(R.id.frameLayout, fragment2).commitAllowingStateLoss();
                        break;
                    case R.id.action_home3:
                        transaction.replace(R.id.frameLayout, fragment3).commitAllowingStateLoss();
                        break;
                    case R.id.action_home4:
                        transaction.replace(R.id.frameLayout, fragment4).commitAllowingStateLoss();
                        break;
                    case R.id.action_home5:
                        transaction.replace(R.id.frameLayout, fragment5).commitNowAllowingStateLoss();

                        break;
                }
                return true;
            }


        });
    } //onCreate

    //메뉴 연동
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:

                drawerLayout.openDrawer(GravityCompat.START);
                break;
            }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawers();
        }
        else{
            super.onBackPressed();
        }
    }
}
