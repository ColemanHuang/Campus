package com.example.campus;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

    NewsBean newsBean;
    private Context context;

    public NewsAdapter(Context context, NewsBean newsBean) {

        this.context = context;
        this.newsBean = newsBean;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date publishTime = newsBean.getData().get(position).getPublishTime();
//        try {
//            Date date = sdf.parse(publishTime);
//            Log.d("NewsAdapter", "onBindViewHolder: "+ date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        holder.newsTitle.setText(newsBean.getData().get(position).getTitle());
        holder.newsContent.setText(newsBean.getData().get(position).getDesc());
        holder.newsDate.setText(sdf.format(publishTime));
        holder.newsIssuer.setText(newsBean.getData().get(position).getPublishAccount());
        Glide.with(context)
                .load(newsBean.getData().get(position).getImageUrl())
                .into(holder.newsImg);
    }

    @Override
    public int getItemCount() {
        if (newsBean != null)
            return newsBean.getData().size();
        return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView newsTitle;
        TextView newsContent;
        TextView newsIssuer;
        TextView newsDate;
        ImageView newsImg;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            newsTitle = itemView.findViewById(R.id.tv_news_title);
            newsContent = itemView.findViewById(R.id.tv_news_content);
            newsIssuer = itemView.findViewById(R.id.tv_news_issuer);
            newsDate = itemView.findViewById(R.id.tv_news_date);
            newsImg = itemView.findViewById(R.id.iv_news_img);
        }
    }
}
