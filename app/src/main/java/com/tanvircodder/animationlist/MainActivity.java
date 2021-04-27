package com.tanvircodder.animationlist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.tanvircodder.animationlist.network.ParseMyJson;
import com.tanvircodder.animationlist.network.ParseTheUrl;
import com.tanvircodder.animationlist.utility.Util;

import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List> {
    private RecyclerView mRecyclerVieww;
    private ListAdapter mAdapter;
    private static final int LOADER_ID = 0;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerVieww = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerVieww.setLayoutManager(new LinearLayoutManager(this));
        mAdapter  = new ListAdapter(this);
        mRecyclerVieww.setAdapter(mAdapter);
        getSupportLoaderManager().initLoader(LOADER_ID,null,this);
    }

    @NonNull
    @Override
    public Loader<List> onCreateLoader(int id, @Nullable Bundle args) {
        return new AsyncTaskLoader<List>(this) {
            private List<Util> mListData;
            @Override
            protected void onStartLoading() {
                super.onStartLoading();
                if (mListData != null){
                    deliverResult(mListData);
                }else {
                    forceLoad();
                }
            }

            @Nullable
            @Override
            public List loadInBackground() {
                try {
                    URL uri = ParseTheUrl.buildUrl();
                    Log.e(LOG_TAG,"The url : " + uri);
                    String httpRequest = ParseTheUrl.urlRequest(uri);
                    List<Util> jsonParsing = ParseMyJson.parsingJson(MainActivity.this,httpRequest);
                    Log.e(LOG_TAG,"the json : " + jsonParsing);
                    return jsonParsing;
                }catch (Exception e){
                    return null;
                }
            }

        };
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List> loader, List data) {
        mAdapter.swapTheData(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List> loader) {
        mAdapter.swapTheData(null);
    }
}