package com.pullein.chatroom.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.pullein.chatroom.R;
import com.pullein.chatroom.bean.ChatBean;
import com.pullein.chatroom.socket.SocketThread;

public class ChatRoomActivity extends AppCompatActivity implements ChatView {
    ListView listView;
    EditText etContent;
    private ChatAdapter mAdapter;

    private String host;
    private String prot;


    private String userId = String.valueOf(System.currentTimeMillis());

    private SocketThread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);
        listView = findViewById(R.id.listView);
        etContent = findViewById(R.id.etContent);
        Intent intent = getIntent();
        host = intent.getStringExtra("host");
        prot = intent.getStringExtra("prot");

        mAdapter = new ChatAdapter(this, userId);
        listView.setAdapter(mAdapter);


        //启动线程，接收服务器发送过来的数据
        thread = new SocketThread(this);
        thread.start();
    }

    /**
     * 如果连接出现异常，弹出AlertDialog！
     */
    public void ShowDialog(String msg) {
        new AlertDialog.Builder(this).setTitle("notification").setMessage(msg)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }

    public void sendEvent(View view) {
        String msg = etContent.getText().toString();
        etContent.setText(null);
        thread.sendMsg(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        thread.sendMsg("exit");
        thread.interrupt();
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public String getProt() {
        return prot;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public void showDiaolg(String msg) {
        new AlertDialog.Builder(this).setMessage(msg).setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                finish();
            }
        }).show();
    }

    @Override
    public void receiveMsg(ChatBean bean) {
        mAdapter.addChatBean(bean);
    }
}
