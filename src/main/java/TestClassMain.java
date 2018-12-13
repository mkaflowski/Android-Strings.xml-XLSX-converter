import java.util.List;

public class TestClassMain {
    public static void main(String[] args) {
        List<LangStruct> files = StringFileHandler.getLangStructs("/Users/mateuszkaflowski/AndroidStudioProjects/podcast-freak");

        CSVFileBuilder csvFileBuilder = new CSVFileBuilder();
        for (LangStruct file : files) {
            csvFileBuilder.addLangStructs(file);
        }
        csvFileBuilder.build(false);
    }
}
