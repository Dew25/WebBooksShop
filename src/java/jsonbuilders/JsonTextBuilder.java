/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonbuilders;

import entity.Text;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author jvm
 */
public class JsonTextBuilder {
    public JsonObject createJsonText(Text text){
        JsonObjectBuilder job = Json.createObjectBuilder();
        job.add("id", text.getId())
            .add("description", text.getDescription())
            .add("path", text.getPath());
        return job.build();
    }
}
