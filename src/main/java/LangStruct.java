import java.io.File;

public class LangStruct {

    private String lang;
    private File stringFile;

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public File getStringFile() {
        return stringFile;
    }

    public void setStringFile(File stringFile) {
        this.stringFile = stringFile;
    }

    @Override
    public String toString() {
        return getLang() + "\t"  +getStringFile().getPath();
    }
}
