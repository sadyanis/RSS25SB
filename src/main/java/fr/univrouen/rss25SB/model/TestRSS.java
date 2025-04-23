package fr.univrouen.rss25SB.model;
import org.springframework.core.io.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class TestRSS {
    public String loadFileXML(){
        Resource resource = new DefaultResourceLoader().getResource("classpath:xml/item.xml");
        try {
            byte[] bytes = resource.getInputStream().readAllBytes();
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
