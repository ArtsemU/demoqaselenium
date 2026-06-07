package webtest.demoqa.com.tasks.sandbox;

public class MyPojo {
    String name;
    String data;
    int version;

    public MyPojo(String name, String data, int version) {
        this.name = name;
        this.data = data;
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "MyPojo{" +
                "name='" + name + '\'' +
                ", data='" + data + '\'' +
                ", version=" + version +
                '}';
    }
}
