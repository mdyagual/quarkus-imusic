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
import java.util.Optional;

@ApplicationScoped
public class ClientRepository {
    @Inject
    MongoClient mc;
    private final MongoCollection<Document> collection = mc.getDatabase("imusic").getCollection("Client");

    @Transactional
    public ArrayList<Client> listAll(){
       var clients = new ArrayList<Client>();
       collection.find().into(new ArrayList<>()).forEach(document -> {
           Client c = makeClient(document);
           clients.add(c);
       });
       return clients;

    }

    @Transactional
    public void add(Client c){
        var newClient = new Document()
                .append("id",c.getId())
                .append("name",c.getName())
                .append("surname",c.getSurname())
                .append("mail",c.getMail())
                .append("age",c.getAge())
                .append("gender",c.getGender());

        collection.insertOne(newClient);

    }

    @Transactional
    public Client get(String idClient){
        var document = Optional.ofNullable(collection.find(Filters.eq("id", idClient)).first());
        return document.map(this::makeClient).orElse(new Client());

    }

    @Transactional
    public void update(String idClient, Client c){
        collection.updateOne(makeDocument(get(idClient)),makeDocument(c));
    }

    @Transactional
    public void delete(String idClient){
        collection.deleteOne(makeDocument(get(idClient)));
    }

    private Client makeClient(Document d){
        Client c = new Client();
        c.setId(d.getString("id"));
        c.setName(d.getString("name"));
        c.setSurname(d.getString("surname"));
        c.setMail(d.getString("mail"));
        c.setAge(d.getInteger("age"));
        c.setGender(d.getString("gender"));

        return c;
    }

    private Document makeDocument(Client c){
        return new Document().append("id",c.getId())
                .append("name",c.getName())
                .append("surname",c.getSurname())
                .append("mail",c.getMail())
                .append("age",c.getAge())
                .append("gender",c.getGender());
    }




}
