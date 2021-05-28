package markdowneditor.view.utils;

public abstract class PatternFactory {
    public static String generateMultilinedBalisePattern(String baliseRegex, String groupName) {
        return "(\\A|[^\\\\])(?<" +
                groupName +
                ">" +
                baliseRegex +
                "((?!" +
                baliseRegex +
                ")(.|[\n]))*[^\\\\]" +
                baliseRegex +
                ")";
    }

    public static String generateTitlePattern(int titleNumber) {
        return "(?<TITLE" +
                titleNumber +
                ">((^#{" +
                titleNumber +
                "})|(\n#{" +
                titleNumber +
                "}))\\h[^\n]+)";
    }
}
