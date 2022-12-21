/*
 * Copyright (C) 2015 The Android Open Source Project
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

package androidx.leanback.leanbackshowcase.app;

import android.content.Intent;
import android.os.Bundle;
import androidx.core.app.ActivityOptionsCompat;
import androidx.leanback.app.BrowseSupportFragment;
import androidx.leanback.leanbackshowcase.R;
import androidx.leanback.leanbackshowcase.app.grid.GridExampleActivity;
import androidx.leanback.leanbackshowcase.app.grid.VideoGridExampleActivity;
import androidx.leanback.leanbackshowcase.app.grid.SlideShow;
import androidx.leanback.leanbackshowcase.cards.presenters.CardPresenterSelector;
import androidx.leanback.leanbackshowcase.models.Card;
import androidx.leanback.leanbackshowcase.models.CardRow;
import androidx.leanback.leanbackshowcase.utils.Utils;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.ListRow;
import androidx.leanback.widget.ListRowPresenter;
import androidx.leanback.widget.OnItemViewClickedListener;
import androidx.leanback.widget.OnItemViewSelectedListener;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.PresenterSelector;
import androidx.leanback.widget.Row;
import androidx.leanback.widget.RowPresenter;
import com.google.gson.Gson;


public class MainFragment extends BrowseSupportFragment {

    private ArrayObjectAdapter mRowsAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupUIElements();
        setupRowAdapter();
        setupEventListeners();
    }

    // setupRowAdapter
    private void setupRowAdapter() {
        mRowsAdapter = new ArrayObjectAdapter(new ListRowPresenter());
        createRows();
        setAdapter(mRowsAdapter);
    }

    private void createRows() {
        String json = Utils
                .inputStreamToString(getResources().openRawResource(R.raw.launcher_cards));
        CardRow[] rows = new Gson().fromJson(json, CardRow[].class);

        for (CardRow row : rows) {
            mRowsAdapter.add(createCardRow(row));
        }
    }

    private ListRow createCardRow(CardRow cardRow) {
        PresenterSelector presenterSelector = new CardPresenterSelector(getActivity());
        ArrayObjectAdapter listRowAdapter = new ArrayObjectAdapter(presenterSelector);
        for (Card card : cardRow.getCards()) {
            listRowAdapter.add(card);
        }
        return new ListRow(listRowAdapter);
    }

    // setupUIElements()
    private void setupUIElements() {
        setTitle(getString(R.string.browse_title));
        setBadgeDrawable(getResources().getDrawable(R.drawable.rogo, null));
        setHeadersState(HEADERS_DISABLED);
        setHeadersTransitionOnBackEnabled(false);
        setBrandColor(getResources().getColor(R.color.fastlane_background));
    }

    // setupEventListeners
    private void setupEventListeners() {
        // OKButton Listener
        setOnItemViewClickedListener(new ItemViewClickedListener());
        // Arrow Keys
        setOnItemViewSelectedListener(new ItemViewSelectedListener());
    }

    private final class ItemViewClickedListener implements OnItemViewClickedListener {

        @Override
        public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item, //OK 버튼 !!
                                  RowPresenter.ViewHolder rowViewHolder, Row row) {
            Intent intent = null;
            Card card = (Card) item;
            int id = card.getId();
            switch (id) {

                case 1: {
                    intent = new Intent(getActivity().getBaseContext(),
                            SlideShow.class);
                    break;
                }

                case 2: {
                    intent = new Intent(getActivity().getBaseContext(),
                            GridExampleActivity.class);
                    break;
                }
                case 3: {
                    intent = new Intent(getActivity().getBaseContext(),
                            VideoGridExampleActivity.class);
                    break;
                }

                default:
                    break;
            }
            if (intent != null) {
                Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity())
                        .toBundle();
                startActivity(intent, bundle);
            }
        }
    }

    private final class ItemViewSelectedListener implements OnItemViewSelectedListener {
        @Override
        public void onItemSelected(Presenter.ViewHolder itemViewHolder, Object item,
                                   RowPresenter.ViewHolder rowViewHolder, Row row) {
        }
    }
}
