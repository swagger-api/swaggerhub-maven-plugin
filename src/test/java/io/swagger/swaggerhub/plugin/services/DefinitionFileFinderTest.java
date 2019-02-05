package io.swagger.swaggerhub.plugin.services;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

public class DefinitionFileFinderTest {

    private static final String MATCHING_DEFINITION_REGEX_PATTERN = "^api-\\w*";
    private static final String VALID_DEFINITION_DIRECTORY = "src/test/resources/file-finder-test-definitions";
    private static final String INVALID_DEFINITION_DIRECTORY = "fakedirectory/test/";
    private static final String NON_MATCHING_REGEX_PATTERN = "^no-api-\\w*";

    private static DefinitionFileFinder definitionFileFinder;

    @Before
    public void setupTestClass(){
        definitionFileFinder = new DefinitionFileFinder();
    }

    @Test
    public void filesCanBeFetchedFromGivenDirectory_whenUsingRegexTest() throws IOException {
        //Given

        //When
        List<File> files = definitionFileFinder.findDefinitionFiles(VALID_DEFINITION_DIRECTORY, Optional.ofNullable(MATCHING_DEFINITION_REGEX_PATTERN));

        //Then
        assertEquals(2, files.size());
    }

    @Test(expected = IOException.class)
    public void invalidDirectoryThrowsExceptionTest() throws IOException {
        //Given

        //When
        definitionFileFinder.findDefinitionFiles(INVALID_DEFINITION_DIRECTORY, Optional.ofNullable(MATCHING_DEFINITION_REGEX_PATTERN));

        //Then
        fail();
    }

    @Test
    public void filesCanBeFetchedFromGivenDirectory_whenNotUsingRegexTest() throws IOException {
        //Given
        String regexPattern = null;

        //When
        List<File> files = definitionFileFinder.findDefinitionFiles(VALID_DEFINITION_DIRECTORY, Optional.ofNullable(regexPattern));

        //Then
        assertEquals(3,files.size());
    }


    @Test
    public void noFilesCanBeFetchedFromGivenDirectory_whenUsingRegex_thatDoesntMatchTest() throws IOException {
        //Given

        //When
        List<File> files = definitionFileFinder.findDefinitionFiles(VALID_DEFINITION_DIRECTORY, Optional.ofNullable(NON_MATCHING_REGEX_PATTERN));

        //Then
        assertEquals(0, files.size());
    }
}
