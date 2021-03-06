package com.example.pawar.fastrescue.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pawar.fastrescue.R;
import com.example.pawar.fastrescue.dao.OfficialCollectionDao;
import com.example.pawar.fastrescue.dao.UserCollectionDao;
import com.example.pawar.fastrescue.dao.UserItemDao;
import com.example.pawar.fastrescue.fragment.MainFragment;
import com.example.pawar.fastrescue.manager.Contextor;
import com.example.pawar.fastrescue.manager.HttpMeneger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity implements MainFragment.FragmentListener {

    public static final String USER_LOGIN_URL = "http://pohtecktung.welovepc.com/newproject/android/login_user.php";
    public static final String OFFICIAL_LOGIN_URL = "http://pohtecktung.welovepc.com/newproject/android/login_official.php";


    private String username;
    private String password;
    ProgressDialog progress;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkLogin();


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, MainFragment.newInstance())
                    .commit();
        }


    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onUserLoginClicked(String uusername, String upassword) {

       progress = ProgressDialog.show(this, "โปรดรอสักครู่",
                "Loading...", true);

        if (!uusername.isEmpty() && !upassword.isEmpty()) {
            username = uusername;
            password = upassword;

            StringRequest stringRequest = new StringRequest(Request.Method.POST, USER_LOGIN_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.trim().equals(username)) {
                                getUsernameData(username);
                                Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
                                progress.dismiss();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                            progress.dismiss();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("user_username", username);
                    map.put("user_password", password);
                    return map;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        } else {
            Toast.makeText(MainActivity.this, "กรุณาใส่ Username และ Password ให้ครบถ้วน", Toast.LENGTH_SHORT).show();
            progress.dismiss();

        }


    }

    @Override
    public void onOfficialLoginClicked(String ousername, String opassword) {

        progress = ProgressDialog.show(this, "โปรดรอสักครู่",
                "Loading...", true);

        if (!ousername.isEmpty() && !opassword.isEmpty()) {
            username = "official_"+ousername;
            password = opassword;

            StringRequest stringRequest = new StringRequest(Request.Method.POST, OFFICIAL_LOGIN_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.trim().equals(username)) {
                                getOfficialData(username);
                                //Toast.makeText(MainActivity.this, username, Toast.LENGTH_LONG).show();
                            } else {
                                progress.dismiss();
                                Toast.makeText(MainActivity.this,"กรุณาตรจสอบข้อมูลให้ถูกต้อง", Toast.LENGTH_LONG).show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("official_username", username);
                    map.put("official_password", password);
                    return map;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        } else {
            Toast.makeText(MainActivity.this, "กรุณาใส่ Username และ Password ให้ครบถ้วน", Toast.LENGTH_SHORT).show();
            progress.dismiss();

        }


    }

    @Override
    public void onRegisterClicked() {
        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(intent);

    }

    public void getUsernameData(final String username) {
        Call<UserCollectionDao> call = HttpMeneger.getInstance().getService().loadUserData(username);
        call.enqueue(new Callback<UserCollectionDao>() {
                         @Override
                         public void onResponse(Call<UserCollectionDao> call, retrofit2.Response<UserCollectionDao> response) {
                             if (response.isSuccessful()) {
                                 UserCollectionDao userdao = response.body();
                                 crateUserDataFile(userdao);
                                 //Toast.makeText(Contextor.getInstance().getContext(),
                                        // userdao.getData().get(0).getUserFirstname() + " " + userdao.getData().get(0).getUserLastname(),
                                         //Toast.LENGTH_SHORT).show();

                             } else {
                                 try {
                                     Toast.makeText(Contextor.getInstance().getContext(),
                                              response.errorBody().string(),
                                             Toast.LENGTH_LONG).show();
                                 } catch (IOException e) {
                                     e.printStackTrace();
                                 }

                             }

                         }

                         @Override
                         public void onFailure(Call<UserCollectionDao> call, Throwable t) {
                             Toast.makeText(Contextor.getInstance().getContext(),
                                     t.toString(),
                                     Toast.LENGTH_LONG).show();
                         }

                     }

        );
    }

    public void getOfficialData(final String username) {
        Call<OfficialCollectionDao> call = HttpMeneger.getInstance().getService().loadOfficialData(username);
        call.enqueue(new Callback<OfficialCollectionDao>() {
                         @Override
                         public void onResponse(Call<OfficialCollectionDao> call, retrofit2.Response<OfficialCollectionDao> response) {
                             if (response.isSuccessful()) {
                                 OfficialCollectionDao officialdao = response.body();
                                 crateOfficialDataFile(officialdao);
                                // Toast.makeText(Contextor.getInstance().getContext(),
                                        // officialdao.getData().get(0).getOfficialFirstname() + " " + officialdao.getData().get(0).getOfficialLastname(),
                                         //Toast.LENGTH_SHORT).show();

                             } else {
                                 try {
                                     Toast.makeText(Contextor.getInstance().getContext(),
                                             response.errorBody().string(),
                                             Toast.LENGTH_LONG).show();
                                 } catch (IOException e) {
                                     e.printStackTrace();
                                 }

                             }

                         }

                         @Override
                         public void onFailure(Call<OfficialCollectionDao> call, Throwable t) {
                             Toast.makeText(Contextor.getInstance().getContext(),
                                     t.toString(),
                                     Toast.LENGTH_LONG).show();
                         }

                     }

        );
    }

    public void checkLogin() {
        SharedPreferences sharedPreferences = getSharedPreferences("PREF_Login", Context.MODE_PRIVATE);
        String checkUsername = sharedPreferences.getString("username", null);
        String checkStatus = sharedPreferences.getString("status", null);
        if (checkUsername != null && checkStatus.equals("user")) {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        }
        if (checkUsername != null && checkStatus.equals("official")) {
            Intent intent = new Intent(MainActivity.this, OfficialActivity.class);
            startActivity(intent);
        }
    }


    public void crateUserDataFile(UserCollectionDao dao) {
        String userId = dao.getData().get(0).getUserId();
        String userUsername = dao.getData().get(0).getUserUsername();
        String userFirstname = dao.getData().get(0).getUserFirstname();
        String userLastname = dao.getData().get(0).getUserLastname();
        String userSex = dao.getData().get(0).getUserSex();
        String userTel = dao.getData().get(0).getUserTel();
        String userTeletc = dao.getData().get(0).getUserTeletc();
        String userPersonalid = dao.getData().get(0).getUserPersonalid();
        String userBirthday = dao.getData().get(0).getUserBirthday();
        String userAddress = dao.getData().get(0).getUserAddress();
        String userDisease = dao.getData().get(0).getUserDisease();
        String userBloodgroup = dao.getData().get(0).getUserBloodgroup();
        String userAllergic = dao.getData().get(0).getUserAllergic();
        String userSecurity = dao.getData().get(0).getUserSecurity();
        String status = "user";
        SharedPreferences sharedPreferences = getSharedPreferences("PREF_Login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("id", userId);
        editor.putString("username", userUsername);
        editor.putString("firstname", userFirstname);
        editor.putString("lastname", userLastname);
        editor.putString("sex", userSex);
        editor.putString("tel", userTel);
        editor.putString("teletc", userTeletc);
        editor.putString("birthday", userBirthday);
        editor.putString("address", userAddress);
        editor.putString("disease", userDisease);
        editor.putString("personalid", userPersonalid);
        editor.putString("bloodgroup", userBloodgroup);
        editor.putString("allergic", userAllergic);
        editor.putString("security", userSecurity);
        editor.putString("status", status);
        editor.commit();
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
        progress.dismiss();
    }

    public void crateOfficialDataFile(OfficialCollectionDao dao) {
        String offcialId = dao.getData().get(0).getOfficialId();
        String offcialUsername = dao.getData().get(0).getOfficialUsername();
        String offcialFirstname = dao.getData().get(0).getOfficialFirstname();
        String offcialLastname = dao.getData().get(0).getOfficialLastname();
        String offcialSex = dao.getData().get(0).getOfficialSex();
        String offcialTel = dao.getData().get(0).getOfficialTel();
        String offcialTeletc = dao.getData().get(0).getOfficialTeletc();
        String offcialPersonalid = dao.getData().get(0).getOfficialPersonalid();
        String offcialBirthday = dao.getData().get(0).getOfficialBirthday();
        String offcialAddress = dao.getData().get(0).getOfficialAddress();
        String offcialDisease = dao.getData().get(0).getOfficialDisease();
        String offcialBloodgroup = dao.getData().get(0).getOfficialBloodgroup();
        String offcialAllergic = dao.getData().get(0).getOfficialAllergic();
        String offcialSecurity = dao.getData().get(0).getOfficialSecurity();
        String status = "official";
        SharedPreferences sharedPreferences = getSharedPreferences("PREF_Login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("id", offcialId);
        editor.putString("username", offcialUsername);
        editor.putString("firstname", offcialFirstname);
        editor.putString("lastname", offcialLastname);
        editor.putString("sex", offcialSex);
        editor.putString("tel", offcialTel);
        editor.putString("teletc", offcialTeletc);
        editor.putString("birthday", offcialBirthday);
        editor.putString("address", offcialAddress);
        editor.putString("disease", offcialDisease);
        editor.putString("personalid", offcialPersonalid);
        editor.putString("bloodgroup", offcialBloodgroup);
        editor.putString("allergic", offcialAllergic);
        editor.putString("security", offcialSecurity);
        editor.putString("status", status);
        editor.commit();
        Intent intent = new Intent(MainActivity.this, OfficialActivity.class);
        startActivity(intent);
        finish();
        progress.dismiss();


    }
}