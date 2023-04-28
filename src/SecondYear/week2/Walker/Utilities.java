package SecondYear.week2.Walker;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.LinkedList;
import java.util.List;

public class Utilities {

    public static class FileWalker {

        private final List<File> filesToEmit = new LinkedList<>();
        private final List<File> foldersToEnter = new LinkedList<>();

        public FileWalker(File root) {
            newCurrent(root);
        }

        private void newCurrent(File folder) {
            File[] files = folder.listFiles();
            if ( files != null) {
                for( File f : files) {
                    if (f.isFile()) {
                        filesToEmit.add(f);
                    } else if (f.isDirectory()) {
                        foldersToEnter.add(f);
                    }
                }
            }
        }

        /**
         * Returns the next file or null if there are no more.
         * @return
         */
        public File nextFile() {
            while(true) {
                if (filesToEmit.isEmpty()) {
                    if (foldersToEnter.isEmpty()) {
                        return null;
                    } else {
                        newCurrent( foldersToEnter.remove(0));
                    }
                } else {
                    return filesToEmit.remove(0);
                }
            }
        }
    }

    public static byte[] computeHash(File file) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            FileInputStream fis = new FileInputStream(file);
            byte[] input = new byte[1024];
            while(true) {
                int count = fis.read(input);
                if (count == -1) {
                    break;
                } else {
                    md.update(input, 0, count);
                }
            }
            return md.digest();
        }catch(Exception x) {
            return new byte[0];
        }
    }

    public static String toHexString(byte[] hash) {
        // Convert byte array into signum representation
        BigInteger number = new BigInteger(1, hash);
        // Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));
        // Pad with leading zeros
        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }

}

