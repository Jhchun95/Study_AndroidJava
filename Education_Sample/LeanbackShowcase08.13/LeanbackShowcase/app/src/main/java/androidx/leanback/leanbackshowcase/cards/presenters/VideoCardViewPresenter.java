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
//이거씀.///////////////////
package androidx.leanback.leanbackshowcase.cards.presenters;

import android.content.Context;
import androidx.leanback.leanbackshowcase.R;
import androidx.leanback.leanbackshowcase.models.Card;
import androidx.leanback.leanbackshowcase.models.VideoCard;
import androidx.leanback.widget.ImageCardView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

/**
 * Presenter for rendering video cards on the Vertical Grid fragment.
 */
public class VideoCardViewPresenter extends ImageCardViewPresenter {

    public VideoCardViewPresenter(Context context, int cardThemeResId) {
        super(context, cardThemeResId);
    }

    public VideoCardViewPresenter(Context context) {
        super(context);
    }

    @Override
    public void onBindViewHolder(Card card, final ImageCardView cardView) {
        super.onBindViewHolder(card, cardView);

        // videoCard declare
        VideoCard videoCard = (VideoCard) card;
        // Declare 'Options' to use RequestOptions
        RequestOptions options = new RequestOptions()
                .centerCrop()
                // .onlyRetrieveFromCache(true)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transform(new RoundedCorners(25))
                .override(140,100);

        Glide.with(getContext())
                .load(videoCard.getImageUrl())
                //.load("https://storage.googleapis.com/android-tv/Sample%20videos/Google%2B/Google%2B_%20New%20Dad.mp4")
                .apply(options)
                .into(cardView.getMainImageView())
        ;
    }
}
