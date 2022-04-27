package com.example.campus;

import java.util.Date;
import java.util.List;

public class NewsBean {

    private Integer code;
    private String message;
    private List<DataDTO> data;

    @Override
    public String toString() {
        return "NewsBean{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataDTO> getData() {
        return data;
    }

    public void setData(List<DataDTO> data) {
        this.data = data;
    }

    public static class DataDTO {
        private Integer id;
        private String imageUrl;
        private String title;
        private String desc;
        private String publishAccount;
//        private String publishTime;
        private Date publishTime;

        @Override
        public String toString() {
            return "DataDTO{" +
                    "id=" + id +
                    ", imageUrl='" + imageUrl + '\'' +
                    ", title='" + title + '\'' +
                    ", desc='" + desc + '\'' +
                    ", publishAccount='" + publishAccount + '\'' +
                    ", publishTime='" + publishTime + '\'' +
                    '}';
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishAccount() {
            return publishAccount;
        }

        public void setPublishAccount(String publishAccount) {
            this.publishAccount = publishAccount;
        }

        public Date getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(Date publishTime) {
            this.publishTime = publishTime;
        }
    }
}
