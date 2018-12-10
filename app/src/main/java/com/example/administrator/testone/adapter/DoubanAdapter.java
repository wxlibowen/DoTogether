package com.example.administrator.testone.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.testone.R;
import com.example.administrator.testone.entity.DouBanEntity;

import java.util.List;


/**
 * 创建时间:2018/11/28
 * 作者:LiBW
 * 描述:
 */
public class DoubanAdapter extends RecyclerView.Adapter<DoubanAdapter.ViewHolder> {
    private List<DouBanEntity.SubjectsBean> list;
    private Context context;
    private DouBanEntity.SubjectsBean data;
    private WindowManager manager;
    private View view;

    public DoubanAdapter(Context context, List<DouBanEntity.SubjectsBean> list) {
        this.list = list;
        this.context = context;
        manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        view = LayoutInflater.from(context).inflate(R.layout.item_douban_adapter, null, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        data = list.get(position);
        Glide.with(context).load(data.getImages().getSmall()).into(viewHolder.iv_show);
        viewHolder.iv_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "点击了" + list.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        Log.d("日志", "onBindViewHolder: "+viewHolder.iv_show.getWidth());



//        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams((manager.getDefaultDisplay().getWidth()-viewHolder.iv_show.getWidth())/2-10,LinearLayout.LayoutParams.MATCH_PARENT);
//        if (position == 0) {
//            viewHolder.view_left.setVisibility(View.VISIBLE);
//            viewHolder.view_right.setVisibility(View.GONE);
//            viewHolder.view_left.setLayoutParams(params);
//        } else if (position == list.size() - 1) {
//            viewHolder.view_left.setVisibility(View.GONE);
//            viewHolder.view_right.setVisibility(View.VISIBLE);
//        } else {
//            viewHolder.view_left.setVisibility(View.GONE);
//            viewHolder.view_right.setVisibility(View.GONE);
//            viewHolder.view_right.setLayoutParams(params);
//        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_show;
        LinearLayout ll;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_show = itemView.findViewById(R.id.iv_dou);
            ll = itemView.findViewById(R.id.ll);


        }
    }
}
