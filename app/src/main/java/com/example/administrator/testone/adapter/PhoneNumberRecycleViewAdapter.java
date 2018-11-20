package com.example.administrator.testone.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.testone.R;
import com.example.administrator.testone.entity.PhoneNumberEntity;

import java.util.List;

/**
 * 创建时间:2018/11/16
 * 作者:LiBW
 * 描述:
 */
public class PhoneNumberRecycleViewAdapter extends RecyclerView.Adapter<PhoneNumberRecycleViewAdapter.ViewHolder>{
    private Context context;
    private List<PhoneNumberEntity> mList;
    private PhoneNumberEntity mData;


    public PhoneNumberRecycleViewAdapter(Context context, List<PhoneNumberEntity> mList) {
        this.context = context;
        this.mList = mList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.item_phone_number,viewGroup,false);
        ViewHolder holder=new ViewHolder(view);



        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneNumberRecycleViewAdapter.ViewHolder viewHolder, int position) {
        mData=mList.get(position);
        viewHolder.tv_name.setText(mData.getName());
        viewHolder.tv_number.setText(mData.getNumber());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        TextView tv_number;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name=itemView.findViewById(R.id.tv_name);
            tv_number =itemView.findViewById(R.id.tv_number);


        }
    }
}
