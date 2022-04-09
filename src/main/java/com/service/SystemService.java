
package com.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * @author samuel
 */
@Service
public class SystemService {
    public List<String> logFiles() {
        final File folder = new File("/home/samuel/logger");
        List<String> fileNames = new ArrayList<>();
        final List<File> fileList = Arrays.asList(folder.listFiles());
        for (File file : fileList) {
            fileNames.add(file.getName());
        }
        Collections.sort(fileNames);
        return fileNames;
    }

}
