/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsoncovertors;

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
                .add("cover", book.getCover().getPath())
                .add("text", book.getText().getPath())
                .add("discount", book.getDiscount());
                
        return job.build();
    }
}
