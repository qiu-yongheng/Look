package com.eternal.look.bean.news;

import java.util.List;

import static android.R.attr.order;
import static android.R.attr.priority;

/**
 * @author qiuyongheng
 * @time 2017/4/7  13:49
 * @desc 网易新闻数据
 */

public class NewsList {

    private List<NewsBean> T1348647909107;

    public List<NewsBean> getT1348647909107() {
        return T1348647909107;
    }

    public void setT1348647909107(List<NewsBean> T1348647909107) {
        this.T1348647909107 = T1348647909107;
    }

    public static class NewsBean {

        private String digest;
        private String docid;
        private String title;
        private String source;
        private String tname;
        private String imgsrc;
        private String ptime;
        private String TAG;


        public String getDigest() {
            return digest;
        }

        public void setDigest(String digest) {
            this.digest = digest;
        }

        public String getDocid() {
            return docid;
        }

        public void setDocid(String docid) {
            this.docid = docid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getOrder() {
            return order;
        }


        public int getPriority() {
            return priority;
        }


        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getTname() {
            return tname;
        }

        public void setTname(String tname) {
            this.tname = tname;
        }

        public String getImgsrc() {
            return imgsrc;
        }

        public void setImgsrc(String imgsrc) {
            this.imgsrc = imgsrc;
        }

        public String getPtime() {
            return ptime;
        }

        public void setPtime(String ptime) {
            this.ptime = ptime;
        }

        public String getTAG() {
            return TAG;
        }

        public void setTAG(String TAG) {
            this.TAG = TAG;
        }
    }
}
