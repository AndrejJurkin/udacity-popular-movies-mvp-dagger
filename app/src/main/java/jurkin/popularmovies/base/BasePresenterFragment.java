/*
 * Copyright 2017 Andrej Jurkin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package jurkin.popularmovies.base;

import android.support.annotation.NonNull;

/**
 * Created by ajurkin on 5/20/17.
 */

public abstract class BasePresenterFragment<T extends BasePresenter> extends BaseFragment implements BaseView<T> {

    protected T presenter;

    @Override
    public void onResume() {
        super.onResume();

        if (presenter != null) {
            presenter.subscribe();
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        if (presenter != null) {
            presenter.unsubscribe();
        }
    }

    @Override
    public void setPresenter(T presenter) {
        this.presenter = presenter;
    }
}