package com.example.smartwatch.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.smartwatch.Adapter.AdapterLoaiSP;
import com.example.smartwatch.Adapter.CTDonHangAdapter;
import com.example.smartwatch.Object.CTDonHang;
import com.example.smartwatch.Object.DonHang;
import com.example.smartwatch.Object.LoaiSP;
import com.example.smartwatch.R;
import com.example.smartwatch.Until.Server;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class CTDonHangActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView_trangChinh;
    NavigationView navigationView;
    ListView listView_trangChinh;
    DrawerLayout drawerLayout_trangChinh;
    ArrayList<LoaiSP> list_loaiSP;
    AdapterLoaiSP adapterLoaiSP;

    TextView textView_ngay, textView_maDH, textView_tenKH, textView_soDT, textView_diaChi, textView_tongCong, textView_email;
    ListView listView_dsDH;

    ArrayList<CTDonHang> list_ctDonHangs;
    CTDonHangAdapter ctDonHangAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiet_donhang);
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


        textView_diaChi = findViewById(R.id.textView_chiTietDH_diaChi);
        textView_ngay = findViewById(R.id.textView_chiTietDH_ngay);
        textView_maDH = findViewById(R.id.textView_chiTietDH_maDH);
        textView_tenKH = findViewById(R.id.textView_chiTietDH_tenKH);
        textView_tongCong = findViewById(R.id.textView_chiTietDH_tongCong);
        textView_soDT = findViewById(R.id.textView_chiTietDH_sdt);
        textView_email = findViewById(R.id.textView_chiTietDH_email);
        listView_dsDH = findViewById(R.id.listView_chiTietDH_dsSP);

    }

    private void setEvent() {
        DonHang donHang = getIntent().getParcelableExtra("donhang");
        textView_diaChi.setText(donHang.getDiacChi());
        textView_ngay.setText(donHang.getNgay() + " " + donHang.getGio());
        textView_maDH.setText(donHang.getIdDH() + "");
        textView_tenKH.setText(donHang.getTenKH());
        textView_soDT.setText(donHang.getSdt());
        textView_email.setText(donHang.getEmail());

        list_ctDonHangs = new ArrayList<>();
        ctDonHangAdapter = new CTDonHangAdapter(getApplicationContext(), list_ctDonHangs);
        listView_dsDH.setAdapter(ctDonHangAdapter);
        long tong = 0;
        duLieuSanPham(donHang);
    }

    private void duLieuSanPham(DonHang donHang) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = Server.ctdonHang + "/" + donHang.getIdDH();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response != null) {
                            // Khởi tạo biến toàn cục của sản phẩm
                            String tenSP = "", anhSP = "";
                            long giaSP = 0;
                            int soLuong = 0;
                            long tong = 0;
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject jsonObject = response.getJSONObject(i);
                                    tenSP = jsonObject.getString("tenSP");
                                    giaSP = jsonObject.getLong("gia");
                                    anhSP = jsonObject.getString("anhSP");
                                    soLuong = jsonObject.getInt("soLuong");
                                    tong += giaSP;
                                    list_ctDonHangs.add(new CTDonHang(tenSP, soLuong, giaSP, anhSP));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            ctDonHangAdapter.notifyDataSetChanged();

                            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                            textView_tongCong.setText(decimalFormat.format(tong) + " Đ");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("============Lỗi rồi===============" + error);

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                headers.put("Authorization", MainActivity.token);
                return headers;
            }
        };
        requestQueue.add(jsonArrayRequest);
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
