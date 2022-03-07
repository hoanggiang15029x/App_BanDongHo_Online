package com.example.smartwatch.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.smartwatch.Adapter.AdapterLoaiSP;
import com.example.smartwatch.Object.LoaiSP;
import com.example.smartwatch.R;
import com.example.smartwatch.Until.DB;
import com.example.smartwatch.Until.Server;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DangNhap extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView_trangChinh;
    NavigationView navigationView;
    ListView listView_trangChinh;
    DrawerLayout drawerLayout_trangChinh;
    ArrayList<LoaiSP> list_loaiSP;
    AdapterLoaiSP adapterLoaiSP;
    EditText edtsdt;
    EditText edtmk;
    TextView tvquenmatkhau;
    Button dangKy;
    public static String tk,mk;
    boolean check;
    Button btndangnhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);
        Controller();
        ActionBar();
        SetEvent();
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
        edtsdt = findViewById(R.id.edtsdt);
        edtmk = findViewById(R.id.edtmk);
        dangKy = findViewById(R.id.btn_dangNhap_dangKy);
        btndangnhap = findViewById(R.id.btn_dangnhap);
        tvquenmatkhau = findViewById(R.id.tv_quenmk);
    }

    private void SetEvent() {
        dangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangNhap.this, DangKyOTP.class);
                startActivity(intent);
            }
        });
        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }
     private void themUser(String tk,String mk){
         String url = Server.getkhachHang+tk;
         RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
         JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
             @Override
             public void onResponse(JSONObject response) {
                 String tenKH,diaChi,email;
                 try {
                     tenKH=response.getString("tenKH");
                     diaChi=response.getString("diaChi");
                     email= response.getString("email");
                     DB db= new DB(DangNhap.this,"GioHang.db",null,1);
                     db.themUser(tk,tenKH,diaChi,email,mk);
                     // UpdateGioHang.InsertUser(DangNhap.this,tk,tenKH,diaChi,email,mk);
                 } catch (JSONException e) {
                     e.printStackTrace();
                 }
             }
         }, new Response.ErrorListener() {
             @Override
             public void onErrorResponse(VolleyError error) {

             }
         });
         queue.add(stringRequest);
     }

    private void login() {
        tk = edtsdt.getText().toString().trim();
        mk = edtmk.getText().toString().trim();
        if (tk.equals("")) {
            Toast.makeText(getApplicationContext(), "Nhập tài khoản", Toast.LENGTH_SHORT).show();
        }
        if (mk.equals("")) {
            Toast.makeText(getApplicationContext(), "Nhập mật khẩu", Toast.LENGTH_SHORT).show();
        }
        if (tk.equals("") == false && mk.equals("") == false) {
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
                                        Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_LONG).show();
                                        themUser(DangNhap.tk,DangNhap.mk);
                                        Intent intent = new Intent(DangNhap.this, MainActivity.class);
                                        startActivity(intent);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    //MainActivity.database.Login(tk,mk,token);
                                } else {
                                    Toast.makeText(getApplicationContext(), "Tài khoản hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                                    edtmk.setText("");
                                    edtsdt.setText("");
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Lỗi --------------------" + error.toString());
                        Toast.makeText(getApplicationContext(), "Tài khoản hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                        return;
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
                        Intent intent = new Intent(DangNhap.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(DangNhap.this, DSDongHoActivity.class);
                        startActivity(intent1);
                        break;

                    case 2:
                        Intent intent2 = new Intent(DangNhap.this, DSMatKinhActivity.class);
                        startActivity(intent2);
                        break;

                    case 3:
                        Intent intent3 = new Intent(DangNhap.this, DSPhuKienActivity.class);
                        startActivity(intent3);
                        break;

                    case 4:
                        if (MainActivity.token == "") {
                            Intent intent4 = new Intent(DangNhap.this, DangNhap.class);
                            startActivity(intent4);
                        } else {
                            Intent intent4 = new Intent(DangNhap.this, ThongTinKHActivity.class);
                            startActivity(intent4);
                        }
                        break;

                    case 5:
                        Intent intent5 = new Intent(DangNhap.this, LienHe.class);
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


}
