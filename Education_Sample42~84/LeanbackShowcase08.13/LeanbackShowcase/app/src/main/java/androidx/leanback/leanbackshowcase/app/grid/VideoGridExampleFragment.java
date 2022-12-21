/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package androidx.leanback.leanbackshowcase.app.grid;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.RequiresApi;
import androidx.leanback.app.VerticalGridFragment;
import androidx.leanback.leanbackshowcase.R;
import androidx.leanback.leanbackshowcase.cards.presenters.CardPresenterSelector;
import androidx.leanback.leanbackshowcase.models.VideoCard;
import androidx.leanback.leanbackshowcase.models.VideoRow;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.FocusHighlight;
import androidx.leanback.widget.OnItemViewClickedListener;
import androidx.leanback.widget.OnItemViewSelectedListener;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.PresenterSelector;
import androidx.leanback.widget.Row;
import androidx.leanback.widget.RowPresenter;
import androidx.leanback.widget.VerticalGridPresenter;

import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.nio.charset.StandardCharsets;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// It is similar GridExampleFragment with VideoGridExampleFragment
public class VideoGridExampleFragment extends VerticalGridFragment implements
        OnItemViewSelectedListener, OnItemViewClickedListener {

    // A medium zoom factor, recommended for medium sized item views.
    private static final int ZOOM_FACTOR = FocusHighlight.ZOOM_FACTOR_MEDIUM;
    // setNumberOfColumns : 4
    private static final int COLUMNS = 4;

    private static final String TAG = "VideoFragment";
    private static final String TAG_CATEGORY = "categories";
    private Map<String, List<VideoCard>> categoryVideosMap = new HashMap<>();

    private ArrayObjectAdapter mAdapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupRowAdapter();
        showTitle(false);
    }

    // This is VerticalGridPresenter, PresenterSelector Setup Code.
    private void setupRowAdapter() {
        VerticalGridPresenter videoGridPresenter = new VerticalGridPresenter(ZOOM_FACTOR);
        videoGridPresenter.setNumberOfColumns(COLUMNS);
        // note: The click listeners must be called before setGridPresenter for the event listeners
        // to be properly registered on the viewholders.
        setOnItemViewSelectedListener(this);
        setOnItemViewClickedListener(this);
        setGridPresenter(videoGridPresenter);

        PresenterSelector cardPresenterSelector = new CardPresenterSelector(getActivity());
        // VideoCardViewPresenter videoCardViewPresenter = new VideoCardViewPresenter(getActivity());
        mAdapter = new ArrayObjectAdapter(cardPresenterSelector);
        setAdapter(mAdapter);

        prepareEntranceTransition();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                createRows();
            }
        }, 1000);
    }

    private void createRows() {
        String urlToFetch = getResources().getString(R.string.videos_url);
        fetchVideosInfo(urlToFetch);
    }

    // VideosInfo
    private void onFetchVideosInfoSuccess(JSONObject jsonObj) {
        try {
            String videoRowsJson = jsonObj.getString(TAG_CATEGORY);
            // java - androidx.leanback.leanbackshowcase - models - VideoRow Java Class
            VideoRow[] videoRows = new Gson().fromJson(videoRowsJson, VideoRow[].class);
            for (VideoRow videoRow : videoRows) {
                if (!categoryVideosMap.containsKey(videoRow.getCategory())) {
                    categoryVideosMap.put(videoRow.getCategory(), new ArrayList<VideoCard>());
                }
                categoryVideosMap.get(videoRow.getCategory()).addAll(videoRow.getVideos());
                mAdapter.addAll(mAdapter.size(), videoRow.getVideos());
                startEntranceTransition();
            }
        } catch (JSONException ex) {
            Log.e(TAG, "A JSON error occurred while fetching videos: " + ex.toString());
        }
    }

    // VideosInfo
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void onFetchVideosInfoError(Exception ex) {
        Log.e(TAG, "Error fetching videos from " + getResources().getString(R.string.videos_url) +
                ", Exception: " + ex.toString());
        Toast.makeText(getContext(), "Error fetching videos from json file", Toast.LENGTH_LONG).show();
    }

    private static class FetchResult {
        private boolean isSuccess;
        private Exception exception;
        JSONObject jsonObj;

        FetchResult(JSONObject obj) {
            jsonObj = obj;
            isSuccess = true;
            exception = null;
        }
        FetchResult(Exception ex) {
            jsonObj = null;
            isSuccess = false;
            exception = ex;
        }
    }

    // This is JSON Parsing Code using Async Task.
    private void fetchVideosInfo(final String urlString) {

        new AsyncTask<Void, Void, FetchResult>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            protected void onPostExecute(FetchResult fetchResult) {
                if (fetchResult.isSuccess) {
                    onFetchVideosInfoSuccess(fetchResult.jsonObj);
                } else {
                    onFetchVideosInfoError(fetchResult.exception);
                }
            }

            @Override
            protected FetchResult doInBackground(Void... params) {
                BufferedReader reader = null;
                HttpURLConnection urlConnection = null;

                try {
                    URL url = new URL(urlString);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8));
                    // Using StringBuilder
                    StringBuilder sb = new StringBuilder();

                    String line;

                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }
                    return new FetchResult(new JSONObject(sb.toString()));
                } catch (JSONException ex) {
                    Log.e(TAG, "A JSON error occurred while fetching videos: " + ex.toString());
                    return new FetchResult(ex);
                } catch (IOException ex) {
                    // data를 얻어내지 못한다면, 데이터 parsing을 진행할 필요가 없음
                    Log.e(TAG, "An I/O error occurred while fetching videos: " + ex.toString());
                    return new FetchResult(ex);
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException ex) {
                            Log.e(TAG, "JSON reader could not be closed! " + ex);
                        }
                    }
                }
            }
        }.execute();
    }


    @Override
    public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item,
                              RowPresenter.ViewHolder rowViewHolder, Row row) {
        if (item instanceof VideoCard) {
            VideoCard itemCard = (VideoCard) item;
            List<String> videoSources = itemCard.getVideoSources();

            if (videoSources == null || videoSources.isEmpty()) {
                return;
            }

            // java - androidx.leanback.leanbackshowcase - app - grid - VideoFull Java Class
            Intent intent = new Intent(getActivity(), VideoFull.class);
            intent.putExtra("source", videoSources.get(0));
            intent.setData(Uri.parse(videoSources.get(0)));

            getActivity().startActivity(intent);
        }
    }

    @Override
    public void onItemSelected(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
    }

}
