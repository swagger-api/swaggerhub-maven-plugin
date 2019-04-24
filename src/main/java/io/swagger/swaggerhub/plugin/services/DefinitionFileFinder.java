package io.swagger.swaggerhub.plugin.services;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service to search for and load definition files contained within downstream projects directory
 */
public class DefinitionFileFinder {

    /**
     * Given a directory and the optional regex pattern, find API definitions.
     * Definitions should be contained in a file with one of the following extensions: .json, .yml, .yaml.
     * @param directory
     * @param fileNameRegexPattern
     * @return
     * @throws IOException - Returned when the directory specified cannot be found
     */
    public static List<File> findDefinitionFiles(String directory, Optional<String> fileNameRegexPattern) throws IOException {


        File directoryFile = new File(modifyPathForOperatingFileSystem(directory));
        if(!directoryFile.exists()){
            throw new IOException(String.format("The given directory [%s] cannot be found", directory));
        }

        ArrayList<File> files = new ArrayList<>();
        File[] childFiles = directoryFile.listFiles();

        for (File childFile:childFiles){

            String fileExtension = FilenameUtils.getExtension(childFile.getName());
            String fileNameWithoutExtension = FilenameUtils.removeExtension(childFile.getName());

            boolean fileExtensionTypeIsSupported = DefinitionFileFormat.getByFileExtensionType(fileExtension).isPresent();

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

    /**
     * Utility function that modifies the path given to match the operating system that is being used to run the plugin.
     * @param path
     * @return
     */
    public static String modifyPathForOperatingFileSystem(String path){
        return path.replace("/",File.separator).replace("\\", File.separator);
    }
}
