package pojos;

public class GorestResponseBodyPojo {
    //1- Tum keyler icin private variable lar olusturuyoruz
    private Object meta;
    private GorestDataPojo data;

    //2- Tum parametrelerle ve parametresiz constructor larimizi olusturuyoruz

    public GorestResponseBodyPojo() {
    }

    public GorestResponseBodyPojo(Object meta, GorestDataPojo data) {
        this.meta = meta;
        this.data = data;
    }
    //3- Getter ve setter olusturuyoruz

    public Object getMeta() {
        return meta;
    }

    public void setMeta(Object meta) {
        this.meta = meta;
    }

    public GorestDataPojo getData() {
        return data;
    }

    public void setData(GorestDataPojo data) {
        this.data = data;
    }

    //4- toString methodunu olusturuyoruz


    @Override
    public String toString() {
        return "GorestResponseBodyPojo{" +
                "meta=" + meta +
                ", data=" + data +
                '}';
    }
}
