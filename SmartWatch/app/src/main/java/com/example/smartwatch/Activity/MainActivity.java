package com.example.smartwatch.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.smartwatch.Adapter.AdapterLoaiSP;
import com.example.smartwatch.Adapter.AdapterSanPhamMoiNhat;
import com.example.smartwatch.Object.LoaiSP;
import com.example.smartwatch.Object.Login;
import com.example.smartwatch.Object.SanPham;
import com.example.smartwatch.R;
import com.example.smartwatch.Until.CheckConnection;
import com.example.smartwatch.Until.DB;
import com.example.smartwatch.Until.Database;
import com.example.smartwatch.Until.Server;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static String token = "";
    public static String sdt;
    // public static Login login= new Login();
    public static DB database;
    public static final String DATABASE_NAME = "GioHang.db";
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    NavigationView navigationView;
    ListView listView_trangChinh;
    DrawerLayout drawerLayout_trangChinh;

    ArrayList<LoaiSP> list_loaiSP;
    AdapterLoaiSP adapterLoaiSP;

    RecyclerView recyclerView_sanPham;
    ArrayList<SanPham> list_sanPham;
    AdapterSanPhamMoiNhat adapterSanPhamMoiNhat;
    public static Login login=null;

//    public static ArrayList<GioHang> list_gioHang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Controller();

        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
            ActionBar();
            ActionViewLipper();
            SetEventUser();
            duLieuSanPhamMoiNhat();
            //checklogin(this);
        } else {
            CheckConnection.Check(getApplicationContext());
            finish();
        }
    }

    private void ActionViewLipper() {
        int[] imageQC = {R.drawable.qc1, R.drawable.qc2, R.drawable.qc3, R.drawable.qc4, R.drawable.qc5, R.drawable.qc6, R.drawable.qc7};
        for (int i : imageQC) {
            ImageView iv = new ImageView(getApplicationContext());
            iv.setImageResource(i);
            iv.setScaleType(iv.getScaleType().FIT_XY);
            viewFlipper.addView(iv);
        }
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);

        // set animation cho quảng cáo
        Animation animation_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation animation_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_in);
        viewFlipper.setOutAnimation(animation_out);

    }

    private void Controller() {
        toolbar = findViewById(R.id.toolbar_trangChinh);
        viewFlipper = findViewById(R.id.viewLipper_trangChinh);
        navigationView = findViewById(R.id.naviView_trangChinh);
        drawerLayout_trangChinh = findViewById(R.id.draLayout_trangChinh);
        listView_trangChinh = findViewById(R.id.listView_trangChinh);
        list_loaiSP = new ArrayList<>();
        adapterLoaiSP = new AdapterLoaiSP(list_loaiSP, this);
        listView_trangChinh.setAdapter(adapterLoaiSP);
        //database= new DB();
        list_sanPham = new ArrayList<>();
        recyclerView_sanPham = findViewById(R.id.recyc_spMoiNhat);
        adapterSanPhamMoiNhat = new AdapterSanPhamMoiNhat(this, list_sanPham);
        recyclerView_sanPham.setHasFixedSize(true);
        recyclerView_sanPham.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        recyclerView_sanPham.setAdapter(adapterSanPhamMoiNhat);

    }

//    private void checklogin(Context context) {
//        SQLiteDatabase database = Database.initDatabase((Activity) context, "GioHang.db");
//        Cursor cursor = database.rawQuery("SELECT * FROM User ", null);
//        cursor.moveToFirst();
//        sdt = cursor.getString(0);
//    }

    private void duLieuSanPhamMoiNhat() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = Server.sanPham;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response != null) {

                            // Khởi tạo biến toàn cục của sản phẩm
                            String tenSP = "", motaSP = "", loaiSP = "", hinhSP = "";
                            int idSP = 0, giaSP = 0, soLuongSP = 0;

                            for (int i = response.length() - 1; i >= 0; i--) {
                                try {
                                    JSONObject jsonObject = response.getJSONObject(i);
                                    idSP = jsonObject.getInt("idSP");
                                    tenSP = jsonObject.getString("tenSP");
                                    giaSP = jsonObject.getInt("gia");
                                    motaSP = jsonObject.getString("moTa");
                                    JSONObject loai = jsonObject.getJSONObject("loaiSP");
                                    int idLoaiSP = loai.getInt("idLoaiSP");
                                    loaiSP = loai.getString("tenLoai");
                                    JSONObject kieu = jsonObject.getJSONObject("kieuSP");
                                    int idKieuSP = kieu.getInt("idKieusp");
                                    String kieuSP = kieu.getString("tenKieu");
                                    hinhSP = jsonObject.getString("anhSP");
                                    soLuongSP = jsonObject.getInt("soLuongTon");
                                    if (idLoaiSP < 3 && list_sanPham.size() < 16 && soLuongSP > 0) {
                                        list_sanPham.add(new SanPham(idSP, tenSP, giaSP, motaSP, idLoaiSP, loaiSP, idKieuSP, kieuSP, hinhSP, soLuongSP));
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            adapterSanPhamMoiNhat.notifyDataSetChanged();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("============Lỗi rồi===============" + error);

            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void SetEventUser() {
        SQLiteDatabase database = Database.initDatabase(this, MainActivity.DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM USER ", null);
        if (cursor.getCount() == 0) {
            token = "";
        } else {
            cursor.moveToFirst();
            String tk, mk;
            tk = cursor.getString(0).toString();
            mk = cursor.getString(4).toString();
            sdt=tk;
            String jObjStr = "{\n" +
                    "\"tendn\":" + "\"" + tk + "\"" + ",\n" +
                    "\"matkhau\":" + "\"" + mk + "\"\n" +
                    "}";
            JSONObject jObject;
            try {
                jObject = new JSONObject(jObjStr);
                String url = Server.login;
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url, jObject,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                if (response != null) {
                                    try {
                                        MainActivity.token = response.getString("tokenType") + " " + response.getString("accessToken");
                                        System.out.println("0000000000000000000000000000000" + token);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    Toast.makeText(getApplicationContext(), "Tài khoản hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Lỗi --------------------" + error.toString());
                        Toast.makeText(getApplicationContext(), "Tài khoản hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                    }
                });
                queue.add(stringRequest);
            } catch (
                    JSONException ex) {
                ex.printStackTrace();
            }
        }

    }


    @SuppressLint("RestrictedApi")
    private void ActionBar() {
        SQLiteDatabase database = Database.initDatabase(this, DATABASE_NAME);
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
                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(MainActivity.this, DSDongHoActivity.class);
                        startActivity(intent1);
                        break;

                    case 2:
                        Intent intent2 = new Intent(MainActivity.this, DSMatKinhActivity.class);
                        startActivity(intent2);
                        break;

                    case 3:
                        Intent intent3 = new Intent(MainActivity.this, DSPhuKienActivity.class);
                        startActivity(intent3);
                        break;

                    case 4:
                        if (MainActivity.token.equals("")) {
                            Intent intent4 = new Intent(MainActivity.this, DangNhap.class);
                            startActivity(intent4);
                        } else {
                            Intent intent4 = new Intent(MainActivity.this, ThongTinKHActivity.class);
                            startActivity(intent4);
                        }
                        break;

                    case 5:
                        Intent intent5 = new Intent(MainActivity.this, LienHe.class);
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