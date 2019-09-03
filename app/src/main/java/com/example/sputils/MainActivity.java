package com.example.sputils;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.edt_user_name)
    EditText edtUserName;
    @BindView(R.id.edt_psw)
    EditText edtPsw;
    @BindView(R.id.cb_save_psw_and_name)
    CheckBox cbSavePswAndName;
    @BindView(R.id.btn_login)
    Button btnLogin;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        SharedPreferences userInfo = SPUtils.getInstance().getUserInfo(this);
        String username = userInfo.getString("username", "");
        String password = userInfo.getString("password", "");
        Log.e(TAG, "initViews: username = = " + username);
        Log.e(TAG, "initViews: password = = " + password);
        edtUserName.setText(username);
        edtPsw.setText(password);
    }


    @OnClick({R.id.btn_login})
    public void onViewClicked(View view) {

        if (cbSavePswAndName.isChecked()) {
            if (TextUtils.isEmpty(edtUserName.getText().toString()) || TextUtils.isEmpty(edtPsw.getText().toString())) {
                Toast.makeText(this, getResources().getString(R.string.string_account_psw_nonullabled), Toast.LENGTH_SHORT).show();
            } else {
                SPUtils.getInstance().saveUserInfo(this, edtUserName.getText().toString().trim(), edtPsw.getText().toString().trim());
            }
        } else {
            SPUtils.getInstance().clearUserInfo(this);
        }
    }
}
