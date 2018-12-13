import java.util.List;

public class TestClassMain {
    public static void main(String[] args) {
        List<LangStruct> files = StringFileHandler.getLangStructs("/Users/mateuszkaflowski/AndroidStudioProjects/podcast-freak");

        for (LangStruct file : files) {
            System.out.println(file);
        }

        new CSVFile().build();
    }
}
