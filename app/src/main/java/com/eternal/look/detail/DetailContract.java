/*
 * Copyright 2017 lizhaotailang
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

package com.eternal.look.detail;

import com.eternal.look.BasePresenter;
import com.eternal.look.BaseView;


/**
 * Created by lizhaotailang on 2016/12/27.
 */

public class DetailContract {

    interface View extends BaseView<Presenter> {
        // 正在加载
        void showLoading();
        // 停止加载
        void stopLoading();
        // 弹出加载失败提示框
        void showError();
        // 回调请求到的HTML数据
        void showResult(String result);
        // 回调请求到的网址
        void showResultWithoutBody(String url);
        // 显示封面图片
        void showCover(String url);
        // 设置标题
        void setTitle(String title);
    }

    interface Presenter extends BasePresenter {
        // 请求数据
        void requestData();

    }

}
