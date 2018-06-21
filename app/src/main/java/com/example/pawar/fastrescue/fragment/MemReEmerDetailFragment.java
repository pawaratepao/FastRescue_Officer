package com.example.pawar.fastrescue.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pawar.fastrescue.R;
import com.example.pawar.fastrescue.dao.EmerItemDao;
import com.example.pawar.fastrescue.dao.OfficialCollectionDao;
import com.example.pawar.fastrescue.dao.UserCollectionDao;
import com.example.pawar.fastrescue.manager.Contextor;
import com.example.pawar.fastrescue.manager.HttpMeneger;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;


public class MemReEmerDetailFragment extends Fragment {

    TextView name, sex, tel, teletc, day, address, dis, blood, allergic, sec;
    String memEmerUsername, memEmerOfficial, check;


    public MemReEmerDetailFragment() {
        super();
    }

    public static MemReEmerDetailFragment newInstance() {
        MemReEmerDetailFragment fragment = new MemReEmerDetailFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mememer_detail, container, false);

        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {


        name = (TextView) rootView.findViewById(R.id.tv_emername);
        sex = (TextView) rootView.findViewById(R.id.tv_emersex);
        tel = (TextView) rootView.findViewById(R.id.tv_emertel);
        teletc = (TextView) rootView.findViewById(R.id.tv_emerteletc);
        day = (TextView) rootView.findViewById(R.id.tv_emerbirthday);
        address = (TextView) rootView.findViewById(R.id.tv_emeraddress);
        dis = (TextView) rootView.findViewById(R.id.tv_emerdisease);
        blood = (TextView) rootView.findViewById(R.id.tv_emerbloodgroup);
        allergic = (TextView) rootView.findViewById(R.id.tv_emerallergic);
        sec = (TextView) rootView.findViewById(R.id.tv_emersecurity);

        SharedPreferences sp = getActivity().getSharedPreferences("RECEIVE_EMER", Context.MODE_PRIVATE);
        memEmerUsername = sp.getString("emer_user", null);
        memEmerOfficial = sp.getString("emer_off", null);

        //Toast.makeText(getActivity(),check,Toast.LENGTH_SHORT).show();
        if (memEmerUsername.isEmpty()) {
            getOfficialData(memEmerOfficial);
        } else {
            getUsernameData(memEmerUsername);

        }


        // Init 'View' instance(s) with rootView.findViewById here
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    /*
     * Save Instance State Here
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    /*
     * Restore Instance State Here
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore Instance State here
        }
    }

    public void getUsernameData(final String username) {
        Call<UserCollectionDao> call = HttpMeneger.getInstance().getService().loadUserDataid(username);
        call.enqueue(new Callback<UserCollectionDao>() {
                         @Override
                         public void onResponse(Call<UserCollectionDao> call, retrofit2.Response<UserCollectionDao> response) {
                             if (response.isSuccessful()) {
                                 UserCollectionDao userdao = response.body();
                                 setData(userdao);
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

    public void setData(UserCollectionDao dao) {
        name.setText("ชื่อ: " + dao.getData().get(0).getUserFirstname() + " " + dao.getData().get(0).getUserLastname());
        sex.setText("เพศ: " + dao.getData().get(0).getUserSex());
        tel.setText("เบอร์: " + dao.getData().get(0).getUserTel());
        teletc.setText("เบอร์ญาติ: " + dao.getData().get(0).getUserTeletc());
        day.setText("วันเกิด: " + dao.getData().get(0).getUserBirthday());
        address.setText("ที่อยู่: " + dao.getData().get(0).getUserAddress());
        dis.setText("โรคประจำตัว: " + dao.getData().get(0).getUserAllergic());
        blood.setText("กรุ๊ปเลือด: " + dao.getData().get(0).getUserBloodgroup());
        allergic.setText("ยาที่แพ้: " + dao.getData().get(0).getUserAllergic());
        sec.setText("สิทธิประกันสังคม: " + dao.getData().get(0).getUserSecurity());


    }

    public void getOfficialData(final String username) {
        Call<OfficialCollectionDao> call = HttpMeneger.getInstance().getService().loadOfficialDataid(username);
        call.enqueue(new Callback<OfficialCollectionDao>() {
                         @Override
                         public void onResponse(Call<OfficialCollectionDao> call, retrofit2.Response<OfficialCollectionDao> response) {
                             if (response.isSuccessful()) {
                                 OfficialCollectionDao officialdao = response.body();
                                 setOffData(officialdao);
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

    public void setOffData(OfficialCollectionDao dao) {
        name.setText("ชื่อ: " + dao.getData().get(0).getOfficialFirstname() + " " + dao.getData().get(0).getOfficialLastname());
        sex.setText("เพศ: " + dao.getData().get(0).getOfficialSex());
        tel.setText("เบอร์: " + dao.getData().get(0).getOfficialTel());
        teletc.setText("เบอร์ญาติ: " + dao.getData().get(0).getOfficialTeletc());
        day.setText("วันเกิด: " + dao.getData().get(0).getOfficialBirthday());
        address.setText("ที่อยู่: " + dao.getData().get(0).getOfficialAddress());
        dis.setText("โรคประจำตัว: " + dao.getData().get(0).getOfficialDisease());
        blood.setText("กรุ๊ปเลือด: " + dao.getData().get(0).getOfficialBloodgroup());
        allergic.setText("ยาที่แพ้: " + dao.getData().get(0).getOfficialAllergic());
        sec.setText("สิทธิประกันสังคม: " + dao.getData().get(0).getOfficialSecurity());


    }


}
