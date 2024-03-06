package sia.enjoyers.grunopolyfx;

import java.io.File;
import java.util.Objects;

public class AssetFiles {
    public static File recursiveFileSearch(File rootDirectory, String filename) {
        if (rootDirectory.isDirectory()) {
            for (File file : Objects.requireNonNull(rootDirectory.listFiles())) {
                if (file.getName().equals(filename)) {
                    return file;
                }
                File foundFile = recursiveFileSearch(file, filename);
                if (foundFile != null) {
                    return foundFile;
                }
            }
        }
        return null;
    }
}
