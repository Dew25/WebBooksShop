/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsoncovertors;

import entity.Cover;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author jvm
 */
public class JsonCoverBuilder {
    public JsonObject createJsonCover(Cover cover){
        JsonObjectBuilder job = Json.createObjectBuilder();
        job.add("id", cover.getId())
                .add("path", cover.getPath())
                .add("description", cover.getDescription());
        return job.build();
    }
}
