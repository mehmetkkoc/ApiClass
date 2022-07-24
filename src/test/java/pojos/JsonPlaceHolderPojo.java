package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonPlaceHolderPojo {

    /*
    1- Tum keyler icin private variable lar olusturuyoruz
    2- Tum parametrelerle ve parametresiz constructor larimizi olusturuyoruz
    3- Getter ve setter olusturuyoruz
    4- toString methodunu olusturuyoruz
    */

    //1- Tum keyler icin private variable lar olusturuyoruz
    private Integer userId;
    private String title;
    private Boolean completed;

    //2- Tum parametrelerle ve parametresiz constructor larimizi olusturuyoruz


    public JsonPlaceHolderPojo() {
    }

    public JsonPlaceHolderPojo(Integer userId, String title, Boolean completed) {
        this.userId = userId;
        this.title = title;
        this.completed = completed;
    }

    //3- Getter ve setter olusturuyoruz

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    //4- toString methodunu olusturuyoruz

    @Override
    public String toString() {
        return "JsonPlaceHolderPojo{" +
                "userId=" + userId +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }

    // farkli key value ikililerinin uyusmazligini @JsonIgnoreProperties(ignoreUnknown = true)
    // annotation ini Pojo Class imizin basina yazarak cozebiliriz

}
