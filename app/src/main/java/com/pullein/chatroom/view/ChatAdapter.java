package com.pullein.chatroom.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.pullein.chatroom.R;
import com.pullein.chatroom.bean.ChatBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/16 0016.
 */
public class ChatAdapter extends BaseAdapter {

    private List<ChatBean> mList;
    private LayoutInflater mInflater;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private String userId;

    public ChatAdapter(Context ctx, String userId) {
        mInflater = LayoutInflater.from(ctx);
        mList = new ArrayList<>();
        this.userId = userId;
    }

    public void addChatBean(ChatBean chatBean) {
        if (chatBean == null) return;
        mList.add(chatBean);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.lv_item_chat, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.init(position);
        return convertView;
    }

    class ViewHolder {
        TextView tvTime;
        TextView tvLeft;
        TextView tvRight;
        TextView tvTip;
        FrameLayout frameLeft;
        FrameLayout frameRight;

        public ViewHolder(View v) {
            tvTime = v.findViewById(R.id.tvTime);
            tvLeft = v.findViewById(R.id.tvLeft);
            tvRight = v.findViewById(R.id.tvRight);
            tvTip = v.findViewById(R.id.tvTip);
            frameLeft = v.findViewById(R.id.frameLeft);
            frameRight = v.findViewById(R.id.frameRight);
        }

        public void init(int position) {
            ChatBean bean = mList.get(position);
            if (position == 0 || position > 0 && bean.time - mList.get(position - 1).time > 60000) {
                tvTime.setVisibility(View.VISIBLE);
                tvTime.setText(format.format(bean.time));
            } else {
                tvTime.setVisibility(View.GONE);
            }
            frameLeft.setVisibility(View.GONE);
            frameRight.setVisibility(View.GONE);
            if (bean.content.equals("exit")) {
                tvTip.setVisibility(View.VISIBLE);
                tvTip.setText(bean.name.equals(userId) ? "您已退出讨论组" : bean.name + "已退出讨论组");
            } else if (bean.content.equals("join")) {
                tvTip.setVisibility(View.VISIBLE);
                tvTip.setText(bean.name.equals(userId) ? "您已加入讨论组" : bean.name + "加入讨论组");
            } else {
                tvTip.setVisibility(View.GONE);
                frameLeft.setVisibility(bean.name.equals(userId) ? View.GONE : View.VISIBLE);
                frameRight.setVisibility(bean.name.equals(userId) ? View.VISIBLE : View.GONE);
                if (bean.name.equals(userId)) {
                    tvRight.setText(bean.content);
                } else {
                    tvLeft.setText(bean.content);
                }
            }
        }
    }
}
