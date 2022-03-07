package com.example.smartwatch.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.smartwatch.Adapter.AdapterLoaiSP;
import com.example.smartwatch.Adapter.DonHangFragmentAdapter;
import com.example.smartwatch.Object.LoaiSP;
import com.example.smartwatch.R;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class DonHangActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView_trangChinh;
    NavigationView navigationView;
    ListView listView_trangChinh;
    DrawerLayout drawerLayout_trangChinh;
    ArrayList<LoaiSP> list_loaiSP;
    AdapterLoaiSP adapterLoaiSP;

    private TabLayout tabLayout_donHang;
    private ViewPager viewPager_donHang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donhang);
        controller();
        setEvent();
    }

    private void controller() {
        toolbar = findViewById(R.id.toolbar_trangChinh);
        recyclerView_trangChinh = findViewById(R.id.recyc_spMoiNhat);
        navigationView = findViewById(R.id.naviView_trangChinh);
        listView_trangChinh = findViewById(R.id.listView_trangChinh);
        drawerLayout_trangChinh = findViewById(R.id.draLayout_trangChinh);
        list_loaiSP = new ArrayList<>();
        adapterLoaiSP = new AdapterLoaiSP(list_loaiSP, this);
        listView_trangChinh.setAdapter(adapterLoaiSP);


        tabLayout_donHang = findViewById(R.id.tabLayout_donHang);
        viewPager_donHang = findViewById(R.id.viewPager_datHang);
    }

    private void setEvent() {
        DonHangFragmentAdapter donHangFragmentAdapter = new DonHangFragmentAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager_donHang.setAdapter(donHangFragmentAdapter);
        tabLayout_donHang.setupWithViewPager(viewPager_donHang);

        View root = tabLayout_donHang.getChildAt(0);
        if (root instanceof LinearLayout) {
            ((LinearLayout) root).setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
            GradientDrawable drawable = new GradientDrawable();
            drawable.setColor(Color.GRAY);
            drawable.setSize(5, 1);
            ((LinearLayout) root).setDividerPadding(10);
            ((LinearLayout) root).setDividerDrawable(drawable);
        }
    }


    @SuppressLint("RestrictedApi")
    private void ActionBar() {
        list_loaiSP.add(new LoaiSP("Trang Chủ", R.drawable.home));
        list_loaiSP.add(new LoaiSP("Đồng Hồ Chính Hãng", R.drawable.dongho));
        list_loaiSP.add(new LoaiSP("Mắt Kính Thời Trang", R.drawable.matkinh));
        list_loaiSP.add(new LoaiSP("Phụ Kiện Đồng Hồ", R.drawable.phukien));
        list_loaiSP.add(new LoaiSP("Tài Khoản", R.drawable.taikhoan));
        list_loaiSP.add(new LoaiSP("Liên hệ", R.drawable.lienhe));
        list_loaiSP.add(new LoaiSP("Thoát", R.drawable.thoat));
        adapterLoaiSP.notifyDataSetChanged();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout_trangChinh.openDrawer(GravityCompat.START);
            }
        });

        listView_trangChinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(getApplicationContext(), DSDongHoActivity.class);
                        startActivity(intent1);
                        break;

                    case 2:
                        Intent intent2 = new Intent(getApplicationContext(), DSMatKinhActivity.class);
                        startActivity(intent2);
                        break;

                    case 3:
                        Intent intent3 = new Intent(getApplicationContext(), DSPhuKienActivity.class);
                        startActivity(intent3);
                        break;

                    case 4:
                        if (MainActivity.token.equals("")) {
                            Intent intent4 = new Intent(getApplicationContext(), DangNhap.class);
                            startActivity(intent4);
                        } else {
                            Intent intent4 = new Intent(getApplicationContext(), ThongTinKHActivity.class);
                            startActivity(intent4);
                        }
                        break;

                    case 5:
                        Intent intent5 = new Intent(getApplicationContext(), LienHe.class);
                        startActivity(intent5);
                        break;

                    case 6:
                        finishAffinity();
                        System.exit(0);
                        break;
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_gioHang:
                Intent intent = new Intent(getApplicationContext(), GioHangActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


}
