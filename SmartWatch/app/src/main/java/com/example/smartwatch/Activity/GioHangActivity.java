package com.example.smartwatch.Activity;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.smartwatch.Adapter.AdapterGioHang;
import com.example.smartwatch.Adapter.AdapterLoaiSP;
import com.example.smartwatch.Object.GioHang;
import com.example.smartwatch.Object.KhachHang;
import com.example.smartwatch.Object.LoaiSP;
import com.example.smartwatch.R;
import com.example.smartwatch.Until.CheckConnection;
import com.example.smartwatch.Until.DB;
import com.example.smartwatch.Until.Database;
import com.example.smartwatch.Until.MyApplication;
import com.example.smartwatch.Until.Server;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GioHangActivity extends AppCompatActivity {
    public Integer status = new Integer(0);
    final String DATABASE_NAME = "GioHang.db";
    Toolbar toolbar;
    NavigationView navigationView;
    ListView listView_trangChinh;
    DrawerLayout drawerLayout_trangChinh;
    ArrayList<LoaiSP> list_loaiSP;
    AdapterLoaiSP adapterLoaiSP;
    ListView listView_gioHang;
    public static ArrayList<GioHang> list_gioHang = new ArrayList<>();
    AdapterGioHang adapterGioHang;
    Button button_thanhToan;
    public static TextView textView_tongTien;
    public static TextView textView_gioTrong;
    long tongTien = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giohang);
        Controller();
        list_gioHang.clear();
        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
            ActionBar();
            SetEventGioHang();
            SetEventThanhToan();
        } else {
            CheckConnection.Check(getApplicationContext());
            finish();
        }
    }


    private void Controller() {

        toolbar = findViewById(R.id.toolbar_trangChinh);
        navigationView = findViewById(R.id.naviView_trangChinh);
        drawerLayout_trangChinh = findViewById(R.id.draLayout_trangChinh);
        listView_trangChinh = findViewById(R.id.listView_trangChinh);
        list_loaiSP = new ArrayList<>();
        adapterLoaiSP = new AdapterLoaiSP(list_loaiSP, this);
        listView_trangChinh.setAdapter(adapterLoaiSP);
        list_gioHang = new ArrayList<>();
        listView_gioHang = findViewById(R.id.listView_gioHang_danhSachSP);
        adapterGioHang = new AdapterGioHang(list_gioHang, this);
        listView_gioHang.setAdapter(adapterGioHang);
        textView_tongTien = findViewById(R.id.textView_gioHang_tongTien);
        button_thanhToan = findViewById(R.id.button_gioHang_thanhToan);
        textView_gioTrong = findViewById(R.id.textView_gioHang_gioTrong);
    }

    private void SetEventGioHang() {

        SQLiteDatabase database = Database.initDatabase(this, DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM GIOHANG ", null);
        list_gioHang.clear();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            int maSP = cursor.getInt(0);
            String tenSP = cursor.getString(1);
            long giaSP = cursor.getLong(2);
            String hinhSP = cursor.getString(3);
            int soLuongSP = cursor.getInt(4);
            tongTien += giaSP;
            list_gioHang.add(new GioHang(maSP, tenSP, giaSP, hinhSP, soLuongSP));
        }
        if (list_gioHang.isEmpty() == false) {
            textView_gioTrong.setVisibility(View.INVISIBLE);
        } else {
            textView_gioTrong.setVisibility(View.VISIBLE);
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###,###");
        textView_tongTien.setText(decimalFormat.format(tongTien) + " Đ");
        adapterGioHang.notifyDataSetChanged();
    }
    private  ArrayList<GioHang> listGiohang(){
        SQLiteDatabase database = Database.initDatabase(this, DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM GIOHANG ", null);
        list_gioHang.clear();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            int maSP = cursor.getInt(0);
            int soLuongSP = cursor.getInt(4);
            list_gioHang.add(new GioHang(maSP, soLuongSP));
        }
        return list_gioHang;
    }
    private void postCTDH(int idDH){
        String jArrStr= "[\n";
        for(int i=0; i<list_gioHang.size();i++){
            if(i<list_gioHang.size()-1){
                jArrStr = jArrStr + "{\n" +
                        "\"idDH\":" + idDH +",\n" +
                        "\"idSP\":" + list_gioHang.get(i).getIdSP()  + ",\n" +
                        "\"soLuong\":" +list_gioHang.get(i).getSoLuongSP() + "\n" +
                        "},\n";
            }
            else{
                jArrStr = jArrStr+"{\n" +
                        "\"idDH\":" + idDH +",\n" +
                        "\"idSP\":" + list_gioHang.get(i).getIdSP()  + ",\n" +
                        "\"soLuong\":" +list_gioHang.get(i).getSoLuongSP() + "\n" +
                        "}\n";
            }
        }
        jArrStr= jArrStr+"]";
        System.out.println(jArrStr);
        JSONArray jArr;
        try {
            jArr = new JSONArray(jArrStr);
            String url = Server.ctdonHang ;
            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            JsonArrayRequest stringRequest = new JsonArrayRequest(Request.Method.POST, url, jArr, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    System.out.println("Thành công");
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            })
            {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap headers = new HashMap();
                    headers.put("Authorization", MainActivity.token);
                    return headers;
                }
            };
            queue.add(stringRequest);
        } catch (
                JSONException ex) {
            ex.printStackTrace();
        }
    }
    private void SetEventThanhToan() {
        final Date[] date = {new Date()};
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        String timenow = formatter.format(date[0]).toString();
        KhachHang kh = new KhachHang( MainActivity.sdt,timenow, (int) tongTien, "Chờ duyệt");
        button_thanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(GioHangActivity.this);
                    builder.setTitle("Bạn chắc chắn mua hàng?");
                    builder.setMessage("Lựa chọn để xác nhận!");
                    builder.setIcon(android.R.drawable.ic_dialog_info);
                    builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String jObjStr = "{\n" +
                                    "\"sdt\":" + "\"" + kh.sdt + "\"" + ",\n" +
                                    "\"ngayDatHang\":" + "\"" + kh.ngayDatHang + "\"" + ",\n" +
                                    "\"tongTien\":" + kh.tongTien + ",\n" +
                                    "\"trangThai\":" + "\"" + kh.trangThai + "\"" + "\n" +
                                    "}";
                            JSONObject jObject;
                            try {
                                jObject = new JSONObject(jObjStr);
                                String url = Server.donHang;
                                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                                JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url, jObject,
                                        new Response.Listener<JSONObject>() {
                                            @Override
                                            public void onResponse(JSONObject response) {
                                                try {
                                                    status = response.getInt("status");
                                                    System.out.println("OK --------------------" + response.toString());
                                                    System.out.println("STATUS --------------------" + status);

                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                } finally {
                                                    if (status != 0) {
                                                        System.out.println("Tạo thành công");
                                                        postCTDH(status);

                                                    } else {
                                                        System.out.println("Tạo thất bại");
                                                    }
                                                }
                                            }
                                        }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                });
                                queue.add(stringRequest);

                            } catch (
                                    JSONException ex) {
                                ex.printStackTrace();
                            } finally {
                                DB db = new DB(GioHangActivity.this, "GioHang.db", null, 1);
                                db.deleteGioHang();
                                Toast.makeText(GioHangActivity.this, "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
                                sendNotification();
                                Intent intent = new Intent(GioHangActivity.this, MainActivity.class);
                                startActivity(intent);
                            }

                        }
                    });

                    builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            return;
                        }
                    });
                    builder.show();

            }
        });
    }


    private void sendNotification(){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);

        Notification notification = new NotificationCompat.Builder(this, MyApplication.CHANNEL_ID)
                .setContentTitle("Đặt Hàng")
                .setContentText("Cám ơn bạn đã đặt hàng của chúng tôi! Hãy chờ mail khi chúng tôi xác nhận đơn hàng của bạn")
                .setSmallIcon(R.drawable.dongho)
                .setLargeIcon(bitmap)
                .setColor(getResources().getColor(R.color.browser_actions_bg_grey))
                .build();

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(getNotificationId(),notification);
    }

    private int getNotificationId() {
        return (int) new Date().getTime();
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
                        Intent intent = new Intent(GioHangActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(GioHangActivity.this, DSDongHoActivity.class);
                        startActivity(intent1);
                        break;

                    case 2:
                        Intent intent2 = new Intent(GioHangActivity.this, DSMatKinhActivity.class);
                        startActivity(intent2);
                        break;

                    case 3:
                        Intent intent3 = new Intent(GioHangActivity.this, DSPhuKienActivity.class);
                        startActivity(intent3);
                        break;

                    case 4:
                        if (MainActivity.token.equals("")) {
                            Intent intent4 = new Intent(GioHangActivity.this, DangNhap.class);
                            startActivity(intent4);
                        }
                        else{
                            Intent intent4 = new Intent(GioHangActivity.this, ThongTinKHActivity.class);
                            startActivity(intent4);
                        }
                        break;

                    case 5:
                        Intent intent5 = new Intent(GioHangActivity.this, LienHe.class);
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