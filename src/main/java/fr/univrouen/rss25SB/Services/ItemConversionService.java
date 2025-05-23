package fr.univrouen.rss25SB.Services;

import fr.univrouen.rss25SB.entity.*;
import fr.univrouen.rss25SB.model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemConversionService {

    public static ItemJAXB toJAXB(Item item) {
        ItemJAXB itemJAXB = new ItemJAXB();

        itemJAXB.setGuid(item.getGuid());
        itemJAXB.setTitle(item.getTitle());
        // getPublished() peut etre null
        if (item.getUpdated() != null) {
            itemJAXB.setUpdated(item.getUpdated().toString());
        }
        if (item.getPublished() != null) {
            itemJAXB.setPublished(item.getPublished().toString());
          
        }
        

        // Categories
        List<CategoryJAXB> categoryJAXBs = item.getCategory().stream()
                .map(ItemConversionService::convertCategoryToJAXB)
                .collect(Collectors.toList());
        itemJAXB.setCategory(categoryJAXBs);

// verification du role de l'auteur si Role = CONTRIBUTOR  create ContributorJAXB
// sinon create AuthorJAXB

     List<AuthorJAXB> authorsJAXB = new ArrayList<>();
     List<AuthorJAXB> contributorsJAXB = new ArrayList<>();
      
      for (Author author : item.getAuthor()) {
         if (author.getRole() == Role.AUTHOR) {
            authorsJAXB.add(convertAuthorToJAXB(author));
         } else {
            contributorsJAXB.add(convertAuthorToJAXB(author));
         }
      }
      itemJAXB.setAuthor(authorsJAXB);
      itemJAXB.setContributor(contributorsJAXB);

     //    List<AuthorJAXB> authorJAXBs = item.getAuthor().stream()
     //            .map(ItemConversionService::convertAuthorToJAXB)
     //            .collect(Collectors.toList());
     //    itemJAXB.setAuthor(authorJAXBs);

       

        // Image & Content
        // image peut etre null

            if (item.getImage() != null) {
               ImageJAXB imageJAXB = convertImageToJAXB(item.getImage());
               itemJAXB.setImage(imageJAXB);
            } 
        
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
           // image.getLength() peut etre null
         //return new ImageJAXB(image.getType(),image.getHref(), image.getAlt(), image.getLength());
           ImageJAXB imageJAXB = new ImageJAXB();
               imageJAXB.setType(image.getType());
               imageJAXB.setHref(image.getHref());
               imageJAXB.setAlt(image.getAlt());
               if(image.getLength() != null){
                   imageJAXB.setLength(image.getLength());
               }
               return imageJAXB;
     }
    public static AuthorJAXB convertAuthorToJAXB( Author author){
          // email et uri peuvent etre null
         AuthorJAXB authorJAXB = new AuthorJAXB();
           authorJAXB.setName(author.getName());
           if( author.getEmail() != null){
               authorJAXB.setEmail(author.getEmail());
           }
          if(author.getUri() != null){
                    authorJAXB.setUri(author.getUri());
          }
          return authorJAXB;

    }
//     public static ContributorJAXB convertContributorToJAXB(Author author){
//          return new ContributorJAXB(author.getName(),author.getEmail(),author.getUri());
//     }
}
