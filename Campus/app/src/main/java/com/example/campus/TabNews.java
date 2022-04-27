package com.example.campus;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class TabNews extends Fragment {

    private List<String> newsTitle;
    private List<String> newsContent;
    private List<String> newsDate;
    private List<String> newsIssuer;
    private NewsBean newsBean;

    private Context context;
    private NewsAdapter newsAdapter;
    private RecyclerView recyclerView;

    public TabNews() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_tab_news, container, false);
        context = view.getContext();
        recyclerView = view.findViewById(R.id.news_recycler_view);
        initData();
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        newsAdapter = new NewsAdapter(context, newsBean);
        recyclerView.setAdapter(newsAdapter);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));
        return view;
    }

    private void initData() {
        String url = "http://49.235.134.191:8080/news/get";
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .get()//默认就是GET请求，可以不写
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d("TabNews", "onFailure");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.code() != 200) {
                    Toast.makeText(context, "请求错误", Toast.LENGTH_SHORT).show();
                }
                ResponseBody body = response.body();
                if (body == null) {
                    return;
                }

                String json = body.string();
//                Gson gson = new Gson();
                Gson gson = new GsonBuilder()
                        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX")
                        .create();
                newsBean = new NewsBean();
                newsBean =gson.fromJson(json, NewsBean.class);
                Log.d("TabNews", "Response: " + newsBean);
            }
        });
//        newsTitle = new ArrayList<String>();
//        newsContent = new ArrayList<String>();
//        newsIssuer = new ArrayList<String>();
//        newsDate = new ArrayList<String>();
//        for (int i = 0; i < 10; i++) {
//            newsTitle.add("疫情防控");
//            newsContent.add("近日发现有些同学没带口罩直接进入食堂，" +
//                    "请大家进入食堂前自觉佩戴好口罩");
//            newsIssuer.add("小明");
//            newsDate.add("2022-04-25 17:56");
//        }


    }

}