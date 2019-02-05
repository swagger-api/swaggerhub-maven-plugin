package io.swagger.swaggerhub.plugin.services;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Service to search for and load definition files contained within downstream projects directory
 */
public class DefinitionFileFinder {

    private String[] supportedFileExtensions = {"json", "yml", "yaml"};

    /**
     * Given a directory and the optional regex pattern, find API definitions.
     * Definitions should be contained in a file with one of the following extensions: .json, .yml, .yaml.
     * @param directory
     * @param fileNameRegexPattern
     * @return
     * @throws IOException - Returned when the directory specified cannot be found
     */
    public List<File> findDefinitionFiles(String directory, Optional<String> fileNameRegexPattern) throws IOException {

        File directoryFile = Paths.get(directory).toFile();
        if(!directoryFile.exists()){
            throw new IOException(String.format("The given directory [%s] cannot be found", directory));
        }

        ArrayList<File> files = new ArrayList<>();
        File[] childFiles = directoryFile.listFiles();

        for (File childFile:childFiles){

            String fileExtension = FilenameUtils.getExtension(childFile.getName());
            String fileNameWithoutExtension = FilenameUtils.removeExtension(childFile.getName());

            boolean fileExtensionTypeIsSupported = Arrays.stream(supportedFileExtensions)
                    .anyMatch(supportExtension -> fileExtension.equals(supportExtension));

            if(!fileExtensionTypeIsSupported){
                continue;
            }

            boolean addFile = fileNameRegexPattern
                    .map(pattern -> fileNameWithoutExtension.matches(pattern))
                    .orElse(!fileNameRegexPattern.isPresent());

            if(addFile){
                files.add(childFile);
            }

        }

        return files;
    }

}
