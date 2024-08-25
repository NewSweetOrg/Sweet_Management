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
        Content content = new Content("Article 1");

        contentService.addContent(content);
        Content retrievedContent = contentService.getContentByTitle("Article 1");

        assertNotNull(retrievedContent);
        assertEquals("Article 1", retrievedContent.getTitle());
    }

    @Test
    public void testGetContentByTitle_ContentExists() {
        Content content1 = new Content("Article 1");
        Content content2 = new Content("Article 2");
        contentService.addContent(content1);
        contentService.addContent(content2);

        Content retrievedContent = contentService.getContentByTitle("Article 2");

        assertNotNull(retrievedContent);
        assertEquals("Article 2", retrievedContent.getTitle());
    }

    @Test
    public void testGetContentByTitle_ContentDoesNotExist() {
        Content retrievedContent = contentService.getContentByTitle("Nonexistent Article");

        assertNull(retrievedContent);
    }

    @Test
    public void testSetTitle() {
        Content content = new Content("Original Title");

        content.setTitle("Updated Title");

        assertEquals("Updated Title", content.getTitle());
    }
}
