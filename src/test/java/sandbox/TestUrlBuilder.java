package sandbox;

import java.util.ArrayList;
import java.util.List;

public class TestUrlBuilder {
    private String domain = "https://example.com";
    private String k1 = "validV1";
    private String k2 = "validV2";
    private String k3 = "validV3";
    private String fragment = "validFragment";

    private boolean includeK1 = true;
    private boolean includeK2 = true;
    private boolean includeK3 = true;
    private boolean includeFragment = true;

    public TestUrlBuilder withK1(String v) {
        this.k1 = v;
        return this;
    }
    public TestUrlBuilder withK2(String v) { this.k2 = v; return this; }
    public TestUrlBuilder withK3(String v) { this.k3 = v; return this; }
    public TestUrlBuilder withFragment(String v) { this.fragment = v; return this; }

    public TestUrlBuilder withoutK1() { this.includeK1 = false; return this; }
    public TestUrlBuilder withoutK2() { this.includeK2 = false; return this; }
    public TestUrlBuilder withoutFragment() { this.includeFragment = false; return this; }

    public String build() {
        StringBuilder sb = new StringBuilder(domain);
        List<String> params = new ArrayList<>();
        if (includeK1) params.add("k1=" + k1);
        if (includeK2) params.add("k2=" + k2);
        if (includeK3) params.add("k3=" + k3);

        if (!params.isEmpty()) sb.append("?").append(String.join("&", params));
        if (includeFragment) sb.append("#").append(fragment);

        return sb.toString();
    }
}
