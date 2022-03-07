package com.example.smartwatch.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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
import com.example.smartwatch.Until.Server;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DangKyOTP extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView_trangChinh;
    NavigationView navigationView;
    ListView listView_trangChinh;
    DrawerLayout drawerLayout_trangChinh;
    ArrayList<LoaiSP> list_loaiSP;
    AdapterLoaiSP adapterLoaiSP;

    Button btn_OTP;
    EditText dangKy_sdt;
    public static int status = 1;

    private static final int MY_PERMISSION_REQUEST_CODE_SEND_SMS = 1;

    private static final String LOG_TAG = "AndroidExample";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky_otp);

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

        btn_OTP = findViewById(R.id.btn_dangKy_nhanOTP);
        dangKy_sdt = findViewById(R.id.edt_dangKy_sdt);

    }

    //    private boolean checkSDT(String key){
//       boolean check = false;
//        //ArrayList<Temp> listsdt= new ArrayList<>();
//        //Temp temp= new Temp(key,"Thể Cường","quận 9,tp Hồ Chí Minh","nguyenhieu99@gmail.com","123");
//         //String ngayDatHang="",sdt="",trangThai="";
//        String jObjStr = "{\n" +
//                "\"sdt\": " + "\"" + "0589932089"+ "\"" + ",\n" +
//                "\"tenKH\": " + "\"" + "Thanh Hiệu" + "\"" + ",\n" +
//                "\"diaChi\": " + "\"" + "quận 9,tp Hồ Chí Minh" + "\"" + ",\n" +
//                "\"email\": " + "\"" + "nguyenhieu99@gmail.com" + "\"" +",\n" +
//                "\"matKhau\": " + "\"" + "hieune" + "\"" +"\n" +
//                "}";
//        JSONObject jObject;
//        try {
//            jObject = new JSONObject(jObjStr);
//            String url = Server.khachHang ;
//            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
//            JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url, jObject, new Response.Listener<JSONObject>() {
//                @Override
//                public void onResponse(JSONObject jsonObject) {
//                    Toast.makeText(DangKyOTP.this,jsonObject.toString(),Toast.LENGTH_SHORT).show();
//                    System.out.println("----------------"+  jsonObject.toString().trim());
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError volleyError) {
//                    Toast.makeText(DangKyOTP.this,"error" ,Toast.LENGTH_SHORT).show();
//                    System.out.println("-------------------"+volleyError.hashCode());
//                    System.out.println("-------------"+volleyError);
//                }
//            });
//                    queue.add(stringRequest);
//        } catch (
//                JSONException ex) {
//            ex.printStackTrace();
//        }
//        return check;
//    }
    private void SetEvent() {

        btn_OTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //boolean check= checkSDT(dangKy_sdt.getText().toString().trim());
                if (dangKy_sdt.getText().toString().trim().isEmpty()) {
                    Toast.makeText(DangKyOTP.this, "Nhập số điện thoại!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (dangKy_sdt.getText().toString().trim().length() < 10) {
                    Toast.makeText(DangKyOTP.this, "Số điện thoại phải có 10 số!", Toast.LENGTH_SHORT).show();
                    return;
                }
                String url = Server.checksdt + dangKy_sdt.getText().toString().trim();
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            status = jsonObject.getInt("status");
                            System.out.println(status);
                            if (status == 2) {
                                Toast.makeText(DangKyOTP.this, "Số điện thoại đã được tao", Toast.LENGTH_LONG).show();
                                return;
                            } else {
                                Toast.makeText(DangKyOTP.this, "Tạo thành công", Toast.LENGTH_LONG).show();
                                btn_OTP.setVisibility(View.VISIBLE);
                                Intent intent = new Intent(DangKyOTP.this, DangKy.class);
                                intent.putExtra("phone", dangKy_sdt.getText().toString());
                                //intent.putExtra("verificationId", verificationId);
                                startActivity(intent);
//                                PhoneAuthProvider.getInstance().verifyPhoneNumber(
//                                        "+84" + dangKy_sdt.getText().toString(), 60,
//                                        TimeUnit.SECONDS, DangKyOTP.this,
//                                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//                                            @Override
//                                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
//                                                btn_OTP.setVisibility(View.VISIBLE);
//                                            }
//
//                                            @Override
//                                            public void onVerificationFailed(@NonNull FirebaseException e) {
//                                                btn_OTP.setVisibility(View.VISIBLE);
//                                                Toast.makeText(DangKyOTP.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                                            }
//
//                                            @Override
//                                            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//                                                btn_OTP.setVisibility(View.VISIBLE);
//                                                Intent intent = new Intent(DangKyOTP.this, XacNhanOTP.class);
//                                                intent.putExtra("phone", dangKy_sdt.getText().toString());
//                                                intent.putExtra("verificationId", verificationId);
//                                                startActivity(intent);
//                                            }
//                                        }
//                                );
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                    }
                });
                queue.add(stringRequest);


//                if(check ==true){
//                    Toast.makeText(DangKyOTP.this, "Số điện thoại đã được tao", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                else{
//                   // btn_OTP.setVisibility(View.INVISIBLE);
//                    Toast.makeText(DangKyOTP.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
//                    return;
////                    Intent intent = new Intent(DangKyOTP.this, XacNhanOTP.class);
////                    intent.putExtra("phone", dangKy_sdt.getText().toString());
////                    startActivity(intent);
//                }


                /*PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+84" + dangKy_sdt.getText().toString(), 60,
                        TimeUnit.SECONDS, DangKyOTP.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential){
                                btn_OTP.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                btn_OTP.setVisibility(View.VISIBLE);
                                Toast.makeText(DangKyOTP.this, e.getMessage(),Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                btn_OTP.setVisibility(View.VISIBLE);
                                Intent intent = new Intent(DangKyOTP.this, XacNhanOTP.class);
                                intent.putExtra("phone", dangKy_sdt.getText().toString());
                                intent.putExtra("verificationId", verificationId);
                                startActivity(intent);
                            }
                        }
                );*/

            }
        });
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
                        Intent intent = new Intent(DangKyOTP.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(DangKyOTP.this, DSDongHoActivity.class);
                        startActivity(intent1);
                        break;

                    case 2:
                        Intent intent2 = new Intent(DangKyOTP.this, DSMatKinhActivity.class);
                        startActivity(intent2);
                        break;

                    case 3:
                        Intent intent3 = new Intent(DangKyOTP.this, DSPhuKienActivity.class);
                        startActivity(intent3);
                        break;

                    case 4:
                        Intent intent4 = new Intent(DangKyOTP.this, DangNhap.class);
                        startActivity(intent4);
                        break;

                    case 5:
                        Intent intent5 = new Intent(DangKyOTP.this, LienHe.class);
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
