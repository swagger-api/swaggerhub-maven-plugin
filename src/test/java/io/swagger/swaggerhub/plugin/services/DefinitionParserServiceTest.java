package io.swagger.swaggerhub.plugin.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.swagger.models.Info;
import io.swagger.models.Swagger;
import io.swagger.swaggerhub.plugin.exceptions.DefinitionParsingException;
import io.swagger.util.Yaml;
import org.junit.Before;
import org.junit.Test;


import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

public class DefinitionParserServiceTest {

    private static DefinitionParserService definitionParserService;
    private Swagger swagger;
    private ObjectMapper objectMapper;

    @Before
    public void setupTestClass(){
        definitionParserService = new DefinitionParserService();

        this.swagger = new Swagger();
        Info info = new Info();
        swagger.setInfo(info);

        objectMapper = new ObjectMapper();
    }

    @Test
    public void validDefinition_canParseApiTitleWithSpacesTest() throws Exception {
        //Given
        swagger.getInfo().setTitle("Sample API Title");
        String swaggerString = objectMapper.writeValueAsString(swagger);

        //When
        String apiId = definitionParserService.getApiId(Yaml.mapper().readTree(swaggerString));

        //Then
        assertEquals("Sample_API_Title", apiId);

    }

    @Test
    public void validDefinition_canParseApiTitleWithSpecialCharsTest() throws Exception {
        //Given
        swagger.getInfo().setTitle("Sample!API%Title**");
        String swaggerString = objectMapper.writeValueAsString(swagger);

        //When
        String apiId = definitionParserService.getApiId(Yaml.mapper().readTree(swaggerString));

        //Then
        assertEquals("Sample_API_Title", apiId);

    }

    @Test(expected = DefinitionParsingException.class)
    public void definition_missingTitle_throwsExceptionWhenParsingForApiIdTest() throws Exception {
        //Given
        String swaggerString = objectMapper.writeValueAsString(swagger);
        JsonNode swaggerNode = Yaml.mapper().readTree(swaggerString);
        swaggerNode = removeElementFromNode(swaggerNode.get("info"), "title");

        //When
        definitionParserService.getApiId(swaggerNode);

        //Then
        fail();
    }

    @Test(expected = DefinitionParsingException.class)
    public void definition_missingInfoSection_throwsExceptionWhenParsingForApiIdTest() throws Exception {
        //Given
        String swaggerString = objectMapper.writeValueAsString(swagger);
        JsonNode swaggerNode = Yaml.mapper().readTree(swaggerString);
        swaggerNode = removeElementFromNode(swaggerNode, "info");

        //When
        definitionParserService.getApiId(swaggerNode);

        //Then
        fail();
    }

    @Test
    public void validDefinition_canParseVersionTest() throws Exception {
        //Given
        String apiVersion = "1.0.0";
        swagger.getInfo().setVersion(apiVersion);
        String swaggerString = objectMapper.writeValueAsString(swagger);

        //When
        String parsedApiVersion = definitionParserService.getVersion(Yaml.mapper().readTree(swaggerString));

        //Then
        assertEquals(apiVersion, parsedApiVersion);

    }

    @Test(expected = DefinitionParsingException.class)
    public void definition_missingVersion_throwsExceptionWhenParsingForVersionTest() throws Exception {
        //Given
        String swaggerString = objectMapper.writeValueAsString(swagger);
        JsonNode swaggerNode = Yaml.mapper().readTree(swaggerString);
        swaggerNode = removeElementFromNode(swaggerNode.get("info"), "version");

        //When
        definitionParserService.getVersion(swaggerNode);

        //Then
        fail();
    }

    @Test(expected = DefinitionParsingException.class)
    public void definition_missingInfoSection_throwsExceptionWhenParsingForVersionTest() throws Exception {
        //Given
        String swaggerString = objectMapper.writeValueAsString(swagger);
        JsonNode swaggerNode = Yaml.mapper().readTree(swaggerString);
        swaggerNode = removeElementFromNode(swaggerNode, "info");

        //When
        definitionParserService.getVersion(swaggerNode);

        //Then
        fail();
    }

    private JsonNode removeElementFromNode(JsonNode node, String fieldName){
        return ((ObjectNode) node).remove(fieldName);
    }
}
