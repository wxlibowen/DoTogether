package com.example.administrator.testone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.administrator.testone.R;
import com.example.administrator.testone.entity.PhoneNumberEntity;


import java.util.List;

public class PhoneNumberAdapter extends BaseAdapter {
    private List<PhoneNumberEntity> mList;
    private Context context;
    private PhoneNumberEntity data;
    private LayoutInflater inflater;

    public PhoneNumberAdapter(List<PhoneNumberEntity> mList, Context context) {
        this.mList = mList;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount(){
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
        View view;
        viewHolder holder;
        if (convertView == null) {
            view = inflater.inflate(R.layout.item_phone_number, null,false);
            holder = new viewHolder(view);

            view.setTag(holder);
        } else {
            view = convertView;
            holder = (viewHolder) view.getTag();
        }

        data = mList.get(position);

        //第一位
        if (position == 0) {
            holder.tv_tou.setVisibility(View.VISIBLE);
            holder.tv_tou.setText(data.getPinYin().substring(0, 1));
            holder.rl.setBackground(context.getResources().getDrawable(R.drawable.bg_up));
        } else {
            holder.rl.setBackground(context.getResources().getDrawable(R.drawable.bg_all));
        }

        if (position != 0 && position != mList.size() - 1) {
            //当前item和上一个item首字母不同的时候
            if (!data.getPinYin().substring(0, 1).equalsIgnoreCase(mList.get(position - 1).getPinYin().substring(0, 1))) {
                holder.tv_tou.setVisibility(View.VISIBLE);
                holder.tv_tou.setText(data.getPinYin().substring(0, 1));
                holder.rl.setBackground(context.getResources().getDrawable(R.drawable.bg_up));
                //当前item和下一个item首字母不同的时候
            } else if (!data.getPinYin().substring(0, 1).equalsIgnoreCase(mList.get(position + 1).getPinYin().substring(0, 1))) {
                if (holder.rl != null) {
                    holder.rl.setBackground(context.getResources().getDrawable(R.drawable.bg_down));
                }
            } else {
                holder.tv_tou.setVisibility(View.GONE);
                holder.rl.setBackground(context.getResources().getDrawable(R.drawable.bg_all));
            }
        }
        //最后一个
        if (position == mList.size() - 1) {
            holder.rl.setBackground(context.getResources().getDrawable(R.drawable.bg_down));
        }
        //仅有一个item
        if (position != 0 && position != mList.size() - 1) {
            if (!data.getPinYin().substring(0, 1).equalsIgnoreCase(mList.get(position - 1).getPinYin().substring(0, 1)) && !data.getPinYin().substring(0, 1).equalsIgnoreCase(mList.get(position + 1).getPinYin().substring(0, 1))) {
                holder.rl.setBackground(context.getResources().getDrawable(R.drawable.bg_only));
            }
        }


        holder.tv_name.setText(data.getName());
        holder.tv_number.setText(data.getNumber());
        holder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                data=mList.get(position);
//                Intent i=new Intent();
//                i.setAction(Intent.ACTION_CALL);
//                Uri str=Uri.parse("tel:"+data.getNumber());
//                i.setData(str);
//                context.startActivity(i);
            }
        });


        return view;
    }


    class viewHolder {
        TextView tv_name;
        TextView tv_number;
        TextView tv_tou;
        RelativeLayout rl;

        public viewHolder(View contentView) {
            tv_name=contentView.findViewById(R.id.tv_name);
            tv_number=contentView.findViewById(R.id.tv_number);
            tv_tou=contentView.findViewById(R.id.tv_tou);
            rl=contentView.findViewById(R.id.rl);

        }
    }


}
