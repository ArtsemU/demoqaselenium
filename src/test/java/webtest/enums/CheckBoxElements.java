package webtest.enums;

public enum CheckBoxElements {
    HOME("home"),
    DESKTOP("desktop"),
    NOTES("notes"),
    COMMANDS("commands"),
    DOCUMENTS("documents"),
    WORKSPACE("workspace"),
    REACT("react"),
    ANGULAR("angular"),
    VEU("veu"),
    OFFICE("office"),
    PUBLIC("public"),
    PRIVATE("private"),
    CLASSIFIED("classified"),
    GENERAL("general"),
    DOWNLOADS("downloads"),
    WORD_FILE("wordFile"),
    EXCEL_FILE("excelFile");

    private final String value;

    CheckBoxElements(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
