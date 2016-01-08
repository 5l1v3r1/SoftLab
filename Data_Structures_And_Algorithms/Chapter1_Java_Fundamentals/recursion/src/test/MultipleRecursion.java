package test;

import java.io.File;

/**
 * @author Hikamt Dhamee
 * @email me.hemant.available@gmail.com
 */
public class MultipleRecursion {
    public static long diskUsage(File fileSystemRoot){
        long totalSize = fileSystemRoot.length(); // immediate size, only the current file's size
        if (fileSystemRoot.isDirectory()){
            for (String path : fileSystemRoot.list()){
                File child = new File(fileSystemRoot,path);
                totalSize += diskUsage(child);
            }
        }
        return totalSize;
    }
}
