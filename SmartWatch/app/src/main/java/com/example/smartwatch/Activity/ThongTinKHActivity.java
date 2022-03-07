package com.example.smartwatch.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartwatch.Adapter.AdapterLoaiSP;
import com.example.smartwatch.Object.LoaiSP;
import com.example.smartwatch.R;
import com.example.smartwatch.Until.CheckConnection;
import com.example.smartwatch.Until.DB;
import com.example.smartwatch.Until.Database;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class ThongTinKHActivity extends AppCompatActivity {
    Button btn_donHang, btn_doimk, btn_dangxuat;
    TextView tv_sdt, tv_hoten, tv_email, tv_diachi;
    Toolbar toolbar;
    RecyclerView recyclerView_trangChinh;
    NavigationView navigationView;
    ListView listView_trangChinh;
    DrawerLayout drawerLayout_trangChinh;
    ArrayList<LoaiSP> list_loaiSP;
    AdapterLoaiSP adapterLoaiSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activaty_thongtin_kh);

        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
            Controller();
            Thontin();
            SetEvent();
            ActionBar();
        } else {
            CheckConnection.Check(getApplicationContext());
            finish();
        }
    }

    private void Thontin() {
        SQLiteDatabase database = Database.initDatabase(this, MainActivity.DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM USER ", null);
        cursor.moveToFirst();
        String tk, hoten, email, diachi, mk;
        tk = cursor.getString(0).toString();
        hoten = cursor.getString(1).toString();
        email = cursor.getString(2).toString();
        diachi = cursor.getString(3).toString();
        String mk1 = cursor.getString(4).toString();
        System.out.println(mk1 + "   ==========================");
        tv_sdt.setText(tk);
        tv_hoten.setText(hoten);
        tv_email.setText(email);
        tv_diachi.setText(diachi);

    }

    private void SetEvent() {
        btn_donHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DonHangActivity.class);
                startActivity(intent);
            }
        });
        btn_dangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DB db = new DB(ThongTinKHActivity.this, "GioHang.db", null, 1);
                db.deleteUser();
                MainActivity.token = "";
                MainActivity.sdt = "";
                Intent intent = new Intent(getApplicationContext(), DangNhap.class);
                startActivity(intent);
                Toast.makeText(ThongTinKHActivity.this, "Đăng xuất thành công", Toast.LENGTH_LONG).show();
            }
        });
        btn_doimk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DoiMatKhauActivity.class);
                intent.putExtra("phone", tv_sdt.getText().toString());
                startActivity(intent);
            }
        });
    }

    private void Controller() {
        toolbar = findViewById(R.id.toolbar_trangChinh);
        recyclerView_trangChinh = findViewById(R.id.recyc_spMoiNhat);
        navigationView = findViewById(R.id.naviView_trangChinh);
        listView_trangChinh = findViewById(R.id.listView_trangChinh);
        drawerLayout_trangChinh = findViewById(R.id.draLayout_trangChinh);
        list_loaiSP = new ArrayList<>();
        adapterLoaiSP = new AdapterLoaiSP(list_loaiSP, this);
        listView_trangChinh.setAdapter(adapterLoaiSP);

        tv_sdt = findViewById(R.id.tv_sdt);
        tv_hoten = findViewById(R.id.tv_hoten);
        tv_email = findViewById(R.id.tv_email);
        tv_diachi = findViewById(R.id.tv_diachi);
        btn_donHang = findViewById(R.id.btn_donhang);
        btn_doimk = findViewById(R.id.btn_doimatkhau);
        btn_dangxuat = findViewById(R.id.btn_dangxuat);

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