package org.acme;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class CodecFruitService {


    @Inject
    MongoClient mongoClient;

    public List<Fruit> list(){
        List<Fruit> list = new ArrayList<>();
        MongoCursor<Fruit> cursor = getCollection().find().iterator();

        try {
            while (cursor.hasNext()) {
                list.add(cursor.next());
            }
        } finally {
            cursor.close();
        }
        return list;
    }

    public void add(Fruit fruit){
        getCollection().insertOne(fruit);
    }

    private MongoCollection<Fruit> getCollection(){
        return mongoClient.getDatabase("fruit").getCollection("fruit", Fruit.class);
    }
}
