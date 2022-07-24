package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DummyResponseBodyPojo {
    //    1- Tum keyler icin private variable lar olusturuyoruz
    private String status;
    private DummyDataPojo data;
    private String message;
    //    2- Tum parametrelerle ve parametresiz constructor larimizi olusturuyoruz

    public DummyResponseBodyPojo() {
    }

    public DummyResponseBodyPojo(String status, DummyDataPojo data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }
    //    3- Getter ve setter olusturuyoruz

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DummyDataPojo getData() {
        return data;
    }

    public void setData(DummyDataPojo data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    //    4- toString methodunu olusturuyoruz


    @Override
    public String toString() {
        return "ResponseBodyPojo{" +
                "status='" + status + '\'' +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
