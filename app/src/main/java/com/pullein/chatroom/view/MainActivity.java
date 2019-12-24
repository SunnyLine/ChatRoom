package com.pullein.chatroom.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.pullein.chatroom.R;
import com.pullein.chatroom.utils.ToastUtils;

public class MainActivity extends AppCompatActivity {
    EditText etHost;
    EditText etProt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etHost = findViewById(R.id.etHost);
        etProt = findViewById(R.id.etProt);
    }

    public void join(View v) {
        String host = etHost.getText().toString().trim();
        String prot = etProt.getText().toString().trim();
        if (TextUtils.isEmpty(host) || TextUtils.isEmpty(prot)) {
            ToastUtils.makeToast(this, "信息不能为空！");
            return;
        }
        Intent intent = new Intent(this, ChatRoomActivity.class);
        intent.putExtra("host", host);
        intent.putExtra("prot", prot);
        startActivity(intent);
    }
}
