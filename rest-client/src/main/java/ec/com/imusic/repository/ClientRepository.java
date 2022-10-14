package ec.com.imusic.repository;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import ec.com.imusic.entity.Client;
import org.bson.Document;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;


@ApplicationScoped
public class ClientRepository {
    @Inject
    MongoClient mc;

    //MongoClient data = MongoClients.create("mongodb+srv://mdyagual:mdyagual@clusterferreteria.aum6z.mongodb.net/?retryWrites=true&w=majority");
    //private final MongoCollection<Document> collection = getCollection();

    @Transactional
    public ArrayList<Client> list(){
       return getCollection().find().into(new ArrayList<>()).stream().map(this::makeClient).collect(Collectors.toCollection(ArrayList::new));

    }

    @Transactional
    public Client get(String idClient){
        return makeClient(Objects.requireNonNull(getCollection().find(Filters.eq("id", idClient)).first()));

    }


    @Transactional
    public void add(Client c){
        getCollection().insertOne(makeDocument(c));
    }

    @Transactional
    public void update(Client c){
        getCollection().updateOne(Filters.eq("id", c.getId()),new Document("$set",makeDocument(c)));
    }

    @Transactional
    public void delete(String idClient){
        getCollection().deleteOne(Filters.eq("id", idClient));
    }

    /*@Transactional
    public void deleteAll(){
        getCollection().deleteMany(Filters.empty());
    }*/

    private Client makeClient(Document d){
        Client c = new Client();
        c.setId(d.getString("id"));
        c.setName(d.getString("name"));
        c.setSurname(d.getString("surname"));
        c.setMail(d.getString("mail"));
        c.setAge(d.getInteger("age"));
        c.setGender(d.getString("gender"));
        c.setSubscriptionActivate(d.getBoolean("subscriptionActivate"));

        return c;
    }
    private Document makeDocument(Client c) {
        Document document = new Document();
        document.append("id", c.getId())
                .append("name", c.getName())
                .append("surname", c.getSurname())
                .append("mail", c.getMail())
                .append("age", c.getAge())
                .append("gender", c.getGender())
                .append("subscriptionActivate",c.getSubscriptionActivate());
        return document;
    }

    private MongoCollection<Document> getCollection(){
        return mc.getDatabase("imusic").getCollection("client");
    }

}
