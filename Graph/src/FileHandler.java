import java.io.File;

public class FileHandler {

    public static File getFile(String fileName) {
        String currentPath = new File("").getAbsolutePath();
        File file = new File(currentPath + "\\" + fileName);
        return file;
    }

}
