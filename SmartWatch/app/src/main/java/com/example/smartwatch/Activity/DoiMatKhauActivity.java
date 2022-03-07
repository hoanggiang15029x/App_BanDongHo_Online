package com.example.smartwatch.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.smartwatch.Object.UpdateGioHang;
import com.example.smartwatch.R;
import com.example.smartwatch.Until.CheckConnection;
import com.example.smartwatch.Until.Server;

import org.json.JSONException;
import org.json.JSONObject;

public class DoiMatKhauActivity extends AppCompatActivity {
    public static String sdt;
    public static String matKhau1;
    TextView tv_sdt;
    EditText edt_mkcu, edt_moi1, edt_moi2;
    Button btn_xacnhan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doimatkhau);
        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
            Controller();
            SetEvent();
        } else {
            CheckConnection.Check(getApplicationContext());
            finish();
        }
    }

    private void Controller() {
        tv_sdt = findViewById(R.id.tv_sdt);
        edt_mkcu = findViewById(R.id.edt_mkcu);
        edt_moi1 = findViewById(R.id.edt_mkmoi1);
        edt_moi2 = findViewById(R.id.edt_mkmoi2);
        btn_xacnhan = findViewById(R.id.btn_xacnhan);
        sdt = getIntent().getStringExtra("phone");
        tv_sdt.setText(sdt);
    }

    private void SetEvent() {
        btn_xacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_mkcu.getText().toString().isEmpty() || edt_moi1.getText().toString().isEmpty() || edt_moi2.getText().toString().isEmpty()) {
                    Toast.makeText(DoiMatKhauActivity.this, "Mật khẩu không được để trống!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (edt_moi1.getText().toString().length() < 8) {
                    Toast.makeText(DoiMatKhauActivity.this, "Mật khẩu phải có 8 ký tự trở lên!", Toast.LENGTH_SHORT).show();
                    return;
                }
                 matKhau1 = edt_moi1.getText().toString();
                String matKhau2 = edt_moi2.getText().toString();
                if (matKhau1.equals(matKhau2) == false) {
                    Toast.makeText(DoiMatKhauActivity.this, "Mật khẩu không chính xác!", Toast.LENGTH_SHORT).show();
                    return;
                }
                String jObjStr = "{\n" +
                        "\"tendn\": " + "\"" + tv_sdt.getText().toString() + "\"" + ",\n" +
                        "\"matkhau\": " + "\"" + edt_mkcu.getText().toString()+ "\"" + ",\n" +
                        "\"matKhauMoi\": " + "\"" + matKhau1 + "\"" + "\n" +
                        "}";
                JSONObject jObject;
                try {
                    jObject = new JSONObject(jObjStr);
                    String url = Server.doimatkhau;
                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                    JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.PUT, url, jObject, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                           // DB db= new DB(DoiMatKhauActivity.this,"GioHang.db",null,1);
                           // db.QueryData("UPDATE User SET matkhau= '"+DoiMatKhauActivity.matKhau1.toString().trim()+"' WHERE sdt= '"+DoiMatKhauActivity.sdt.toString()+"'");

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //Toast.makeText(DoiMatKhauActivity.this, "Sai mật khẩu cũ", Toast.LENGTH_LONG).show();
                            System.out.println("===================== Đổi thaasi bại");
                            return;
                        }
                    });
                    queue.add(stringRequest);
                    Toast.makeText(DoiMatKhauActivity.this, "Đổi mật khẩu thành công", Toast.LENGTH_LONG).show();
                    UpdateGioHang.UpdateUser(DoiMatKhauActivity.this,DoiMatKhauActivity.matKhau1,DoiMatKhauActivity.sdt);
                    Intent intent = new Intent(DoiMatKhauActivity.this,ThongTinKHActivity.class);
                    startActivity(intent);
                } catch (
                        JSONException ex) {
                    ex.printStackTrace();
                }
            }
        });

    }
}
