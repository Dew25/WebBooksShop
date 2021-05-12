/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonbuilders;

import entity.Book;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author jvm
 */
public class JsonBookBuilder {
    public JsonObject createJsonBook(Book book){
        JsonObjectBuilder job = Json.createObjectBuilder();
        job.add("id", book.getId())
            .add("name", book.getName())
            .add("author", book.getAuthor())
            .add("publishedYear", book.getPublishedYear())
            .add("price", book.getPrice()) 
            .add("cover", new JsonCoverBuilder().createJsonCover(book.getCover()))
            .add("text", new JsonTextBuilder().createJsonText(book.getText()))
            .add("discount", book.getDiscount())
            .add("discountDuration", book.getDiscountDuration());   
        return job.build();
    }
}
