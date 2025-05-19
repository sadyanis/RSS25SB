package fr.univrouen.rss25SB.Services;

import fr.univrouen.rss25SB.entity.*;
import fr.univrouen.rss25SB.model.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemConversionService {

    public static ItemJAXB toJAXB(Item item) {
        ItemJAXB itemJAXB = new ItemJAXB();

        itemJAXB.setGuid(item.getGuid());
        itemJAXB.setTitle(item.getTitle());
        itemJAXB.setPublished(item.getPublished().toString());

        // Categories
        List<CategoryJAXB> categoryJAXBs = item.getCategory().stream()
                .map(ItemConversionService::convertCategoryToJAXB)
                .collect(Collectors.toList());
        itemJAXB.setCategory(categoryJAXBs);


        List<AuthorJAXB> authorJAXBs = item.getAuthor().stream()
                .map(ItemConversionService::convertAuthorToJAXB)
                .collect(Collectors.toList());
        itemJAXB.setAuthor(authorJAXBs);

        // Contributors
        List<ContributorJAXB> contributorJAXBs = item.getContributors().stream()
                .map(ItemConversionService::convertContributorToJAXB)
                .collect(Collectors.toList());
        itemJAXB.setContributor(contributorJAXBs);

        // Image & Content
        itemJAXB.setImage(convertImageToJAXB(item.getImage()));
        itemJAXB.setContent(convertContentToJAXB(item.getContent()));

        return itemJAXB;
    }
    public static CategoryJAXB convertCategoryToJAXB(Category category){
         return new CategoryJAXB(category.getTerm());
    }
    public static ContentJAXB convertContentToJAXB(Content content){
         return new ContentJAXB(content.getType(),content.getContent());
    }
    public static ImageJAXB convertImageToJAXB(Image image){
         return new ImageJAXB(image.getType(),image.getHref(), image.getAlt(), image.getLength());
    }
    public static AuthorJAXB convertAuthorToJAXB( Author author){
         return new AuthorJAXB(author.getName(),author.getEmail(),author.getUri());
    }
    public static ContributorJAXB convertContributorToJAXB(Author author){
         return new ContributorJAXB(author.getName(),author.getEmail(),author.getUri());
    }
}
