package unitTesting;

import models.Content;
import services.ContentService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ContentServiceTest {

    private ContentService contentService;

    @Before
    public void setUp() {
        contentService = new ContentService();
    }

    @Test
    public void testAddContent() {
        // Arrange
        Content content = new Content("Article 1");

        // Act
        contentService.addContent(content);
        Content retrievedContent = contentService.getContentByTitle("Article 1");

        // Assert
        assertNotNull(retrievedContent);
        assertEquals("Article 1", retrievedContent.getTitle());
    }

    @Test
    public void testGetContentByTitle_ContentExists() {
        // Arrange
        Content content1 = new Content("Article 1");
        Content content2 = new Content("Article 2");
        contentService.addContent(content1);
        contentService.addContent(content2);

        // Act
        Content retrievedContent = contentService.getContentByTitle("Article 2");

        // Assert
        assertNotNull(retrievedContent);
        assertEquals("Article 2", retrievedContent.getTitle());
    }

    @Test
    public void testGetContentByTitle_ContentDoesNotExist() {
        // Act
        Content retrievedContent = contentService.getContentByTitle("Nonexistent Article");

        // Assert
        assertNull(retrievedContent);
    }

    @Test
    public void testSetTitle() {
        // Arrange
        Content content = new Content("Original Title");

        // Act
        content.setTitle("Updated Title");

        // Assert
        assertEquals("Updated Title", content.getTitle());
    }
}
