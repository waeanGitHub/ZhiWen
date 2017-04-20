package com.example.waean.zhiwen.fragment;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.waean.zhiwen.NewActivity;
import com.example.waean.zhiwen.R;
import com.example.waean.zhiwen.adpter.NewAdapter;
import com.example.waean.zhiwen.adpter.SimpleFragmentPagerAdapter;
import com.example.waean.zhiwen.pojo.New;
import com.example.waean.zhiwen.utils.HttpUtil;
import com.example.waean.zhiwen.utils.JsonUtil;
import com.example.waean.zhiwen.utils.LogUtil;
import com.example.waean.zhiwen.utils.NetWorkUtil;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PageFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    public static final String TAG = "PageFragment";

    public static final int REQUEST_INTNET = 1;
    public static final int UPDATE_NEWS = 2;
    private static final String ARG_PAGE = "ARG_PAGE";
    private String mType;
    private int mPage;
    private NewAdapter newAdapter;
    private String mResponse;
    private GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
    private Handler handler;
    private SwipeRefreshLayout mSwipeRefresh;
    private RecyclerView mRecyclerView;

    public static PageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PageFragment pageFragment = new PageFragment();
        pageFragment.setArguments(args);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPage = getArguments().getInt(ARG_PAGE);
        mType = SimpleFragmentPagerAdapter.typeTitles[mPage].toString();
        LogUtil.i(TAG, "onCreate:" + mType);
        //运行时权限申请
        applyPermission();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_page, container, false);

        mSwipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        mSwipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);


        handler = new Handler() {
            private List<New> newList;

            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case UPDATE_NEWS:
                        if (newList != null) {
                            newList.clear();
                        }
                        newList = JsonUtil.parseJSONWithJSONObjiect(mResponse);
                        mRecyclerView.setLayoutManager(layoutManager);
                        newAdapter = new NewAdapter(newList);
                        mRecyclerView.setAdapter(newAdapter);

                        //单击cardview事件
                        newAdapter.setOnItemClickListener(new NewAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
//                                Toast.makeText(getActivity(), "单击" + position, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getContext(), NewActivity.class);
                                intent.putExtra("new_data", newList.get(position));
                                startActivity(intent);
                            }
                        });
                        //双击cardview事件
                        newAdapter.setmOnItemLongClickListener(new NewAdapter.OnItemLongClickListener() {
                            @Override
                            public void onItemLongClick(View view, int position) {
//                                Toast.makeText(getActivity(), "长摁" + position, Toast.LENGTH_SHORT).show();

                            }
                        });

                        if (mSwipeRefresh.isRefreshing()) {
                            mSwipeRefresh.setRefreshing(false);
                        }
                        break;
                    default:
                }
            }
        };
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mSwipeRefresh.setOnRefreshListener(this);
    }

    private void applyPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.INTERNET}, REQUEST_INTNET);
        } else {
            request();
        }
    }


    private void request() {
        //网络访问
        HttpUtil.sendOkHttpRequest(mType, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //访问失败
                LogUtil.i(TAG, "requesto nFailure:" + "");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //访问成功

                mResponse = response.body().string();

                LogUtil.i(TAG, "request onResponse:" + "" + mResponse);
                Message message = new Message();
                message.what = UPDATE_NEWS;
                handler.sendMessage(message);
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_INTNET:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    request();
                } else {
                    //无网络权限
                    Toast.makeText(getActivity(), "无网络权限", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    @Override
    public void onRefresh() {
        //刷新操作
        if (NetWorkUtil.isNetworkConnected(getContext())) {
            request();
        } else {
            Toast.makeText(getContext(), "无网络连接", Toast.LENGTH_SHORT).show();
            if (mSwipeRefresh.isRefreshing()) {
                mSwipeRefresh.setRefreshing(false);
            }
        }
    }
}
