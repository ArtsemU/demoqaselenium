package sandbox;

public class TestURL {
    public static void main(String[] args) {
        String url = new TestUrlBuilder()
                .withK2("javascript:alert(1)")
                .build();
        System.out.println("url : " + url);

        String urlNoFragment = new TestUrlBuilder()
                .withoutFragment()
                .build();
        System.out.println("url : " + urlNoFragment);

        String urlDuplicateParam = new TestUrlBuilder()
                .withK1("dup1")
                // вручную добавляем дубль, либо отдельный метод addRawParam(...)
                .build();
        System.out.println("url : " + urlDuplicateParam);
    }
}
