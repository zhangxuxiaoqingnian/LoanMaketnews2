package money.kuxuan.platform.factory.model.api.examine;

import java.io.Serializable;

public class InfoBanner implements Serializable {
     private String id;
     private String[] thumbnail;
     private String thumbnailsigle;
     private String create_time;
     private String page_views;
     private String source;
     private String title;

     public String getId() {
          return id;
     }

     public void setId(String id) {
          this.id = id;
     }

     public String[] getThumbnail() {
          return thumbnail;
     }

     public void setThumbnail(String[] thumbnail) {
          this.thumbnail = thumbnail;
     }

     public String getThumbnailsigle() {
          return thumbnailsigle;
     }

     public void setThumbnailsigle(String thumbnailsigle) {
          this.thumbnailsigle = thumbnailsigle;
     }

     public String getCreate_time() {
          return create_time;
     }

     public void setCreate_time(String create_time) {
          this.create_time = create_time;
     }

     public String getPage_views() {
          return page_views;
     }

     public void setPage_views(String page_views) {
          this.page_views = page_views;
     }

     public String getSource() {
          return source;
     }

     public void setSource(String source) {
          this.source = source;
     }

     public String getTitle() {
          return title;
     }

     public void setTitle(String title) {
          this.title = title;
     }
}