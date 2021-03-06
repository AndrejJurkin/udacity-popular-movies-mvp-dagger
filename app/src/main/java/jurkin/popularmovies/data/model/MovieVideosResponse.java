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

package jurkin.popularmovies.data.model;

import java.util.List;

import jurkin.popularmovies.data.model.Video;

/**
 * Created by Andrej Jurkin on 5/25/17.
 */

public class MovieVideosResponse {

    private String id;
    private List<Video> results;

    public String getId() {
        return id;
    }

    public List<Video> getVideos() {
        return results;
    }
}
