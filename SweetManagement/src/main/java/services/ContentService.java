package  services;

import  models.Content;
import java.util.ArrayList;
import java.util.List;

public class ContentService {
    private List<Content> contents = new ArrayList<>();

    public void addContent(Content content) {
        contents.add(content);
    }

    public Content getContentByTitle(String title) {
        for (Content content : contents) {
            if (content.getTitle().equals(title)) {
                return content;
            }
        }
        return null;
    }

    public List<Content> getAllContents() {
        return new ArrayList<>(contents);
    }
}
