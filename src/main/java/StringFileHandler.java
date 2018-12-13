import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class StringFileHandler {

    private static String defaultLanguage = "en";

    public static List<LangStruct> getLangStructs(String resPath){
        List<LangStruct> langStructs = new ArrayList<>();

        List<File> stringFiles = findStringFiles(resPath);

        for (File stringFile : stringFiles) {
            LangStruct langStruct = new LangStruct();
            langStruct.setStringFile(stringFile);
            langStruct.setLang(getFileLang(stringFile));

            langStructs.add(langStruct);
        }

        return langStructs;
    }

    private static String getFileLang(File stringFile) {
        String[] split = stringFile.getParent().split("values-");

        if(split.length<2)
            return defaultLanguage;

        String lang = split[split.length-1];
        return lang;
    }

    private static List<File> findStringFiles(String resPath){

        List<File> stringFiles = new ArrayList<>();

        File folder = new File(resPath);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            File file = listOfFiles[i];
            if (file.isFile()) {
                if(file.getName().contains("strings.xml"))
                    stringFiles.add(file);
            } else if (file.isDirectory()) {
                stringFiles.addAll(findStringFiles(resPath+"/"+ file.getName()));
            }
        }

        return stringFiles;
    }
}
