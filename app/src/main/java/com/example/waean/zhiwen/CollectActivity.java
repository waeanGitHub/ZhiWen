package com.example.waean.zhiwen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.waean.zhiwen.adpter.NewAdapter;
import com.example.waean.zhiwen.utils.NewsCollector;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CollectActivity extends BaseActivity {

    @Bind(R.id.collect_recycler_view)
    RecyclerView mCollectRecyclerView;

    NewAdapter adapter;
    @Bind(R.id.collect_toolbar)
    Toolbar mCollectToolbar;
    @Bind(R.id.text_view)
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        ButterKnife.bind(this);
        mCollectToolbar.setTitle("收藏");
        setSupportActionBar(mCollectToolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if (NewsCollector.newList.size() > 0) {
            mTextView.setVisibility(View.INVISIBLE);
        }

        adapter = new NewAdapter(NewsCollector.newList);
        mCollectRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCollectRecyclerView.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        adapter.setOnItemClickListener(new NewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getBaseContext(), NewActivity.class);
                intent.putExtra("new_data", NewsCollector.newList.get(position));
                startActivity(intent);
            }
        });
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.collect_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.delete_all:
                NewsCollector.removeAllNew();
                adapter.notifyDataSetChanged();
                mTextView.setVisibility(View.VISIBLE);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
