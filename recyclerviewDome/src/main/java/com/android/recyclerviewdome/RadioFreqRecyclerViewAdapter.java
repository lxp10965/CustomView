package com.android.recyclerviewdome;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class RadioFreqRecyclerViewAdapter extends RecyclerView.Adapter<RadioFreqRecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<String> content_list;//搜到的电台列表
//    private QtActivity qtActivity;

    /*   public RadioFreqRecyclerViewAdapter(Context mContext, List<ChannelBean> content_list){
           this.mContext = mContext;
           this.content_list = content_list;
           qtActivity = new QtActivity();

       }*/
    RecyclerCoverFlow recyclerCoverFlow;


  /*  public RadioFreqRecyclerViewAdapter(Context mContext, List<ChannelBean> content_list,RecyclerCoverFlow recyclerCoverFlow){
        this.mContext = mContext;
        this.content_list = content_list;
        qtActivity = new QtActivity();
        this.recyclerCoverFlow = recyclerCoverFlow;
    }*/

    public RadioFreqRecyclerViewAdapter(Context mContext, List<String> content_list, RecyclerCoverFlow recyclerCoverFlow) {
        this.mContext = mContext;
        this.content_list = content_list;
//        qtActivity = new QtActivity();
        this.recyclerCoverFlow = recyclerCoverFlow;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView item_text;
        ImageView item_image;
        RecyclerCoverFlow coverFlow;

        public MyViewHolder(View itemView) {
            super(itemView);
            item_text = itemView.findViewById(R.id.radio_fq_text);
            item_image = itemView.findViewById(R.id.radio_fq_bg);
            coverFlow = itemView.findViewById(R.id.recycler_radio_freq);
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_radio_freq, parent, false);
        MyViewHolder holder = new MyViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

//        String freq = content_list.get(position);
//
//        if (freq.contains("FM")) {
//            String[] freq_arr = freq.split("FM1");
//           /* int f = Integer.parseInt(freq_arr[0]);
//            float freq_re = f/1000;*/
//            Float f = Float.parseFloat(freq_arr[0]) / 1000;
//            holder.item_text.setText(f + "\n" + "FM");
//        } else {
//            holder.item_text.setText(freq + "");
//        }
//
////            holder.item_text.setText(content_list.get(position) + "");
//        holder.item_image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                    Toast.makeText(mContext, "current content." + content_list.get(position),Toast.LENGTH_SHORT).show();
////                    recyclerCoverFlow.getCoverFlowLayout().scrollToPosition(position);
//                recyclerCoverFlow.getCoverFlowLayout().smoothScrollToPosition(recyclerCoverFlow, new RecyclerView.State(), position);
//            }
//
//        });
    }


    @Override
    public int getItemCount() {
        return content_list.size();
//        return  12;
    }


}
