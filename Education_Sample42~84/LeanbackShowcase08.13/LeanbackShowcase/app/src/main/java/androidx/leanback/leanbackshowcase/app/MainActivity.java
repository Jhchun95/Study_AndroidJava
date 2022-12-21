/*
 * Copyright (C) 2014 The Android Open Source Project
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

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.leanback.leanbackshowcase.R;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.paging.PagedList;

import com.cloudike.cloudikephotos.core.PhotoManager;
import com.cloudike.cloudikephotos.core.data.dto.PhotoItem;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Objects;

import io.reactivex.CompletableObserver;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/*
 * MainActivity class that loads MainFragment
 */
public class MainActivity extends FragmentActivity {

    /**
     * Called when the activity is first created.
     */
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            Fragment fragment = new MainFragment();
            // activity_main.xml RelativeLayout id : fragmentContainer
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment)
                .commit();
        }
    }
}
