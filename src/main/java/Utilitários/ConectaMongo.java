package Utilitários;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

public class ConectaMongo {
    
        MongoClient mongo = new MongoClient("localhost", 27017);
        MongoDatabase db = mongo.getDatabase("headfootball");
        MongoCollection<Document> docs = db.getCollection("usuarios");
        
        
    public void getValues(){
        System.out.println("Método getValues()");
        for(Document doc : docs.find()) {
        System.out.println("item: "+doc);
        }
        System.out.println("getValues() - ok - finalizou");
        }
    
    
            
        public void insertValues(String username, int vitorias, int empates, int derrotas){
        MongoClient mongo = new MongoClient("localhost", 27017);
        MongoDatabase db = mongo.getDatabase("headfootball");
        MongoCollection<Document> docs = db.getCollection("usuarios");
        System.out.println("Método insertValues()");
        //Conexão Mongo
        Entrada user = createUser(username, vitorias, empates, derrotas);

        Document doc = createDocument(user);

        docs.insertOne(doc);
        getValues();
        System.out.println("insertValues ok");
        }
        
    public Entrada createUser(String user, int vitorias, int empates, int derrotas){
        Entrada u = new Entrada();
        u.setUser(user);
        u.setVitorias(vitorias);
        u.setEmpates(empates);
        u.setDerrotas(derrotas);
        return u;
    }
    
    public Document createDocument(Entrada user){
        Document docBuilder = new Document();
        docBuilder.append("user", user.getUser());
        docBuilder.append("vitorias", user.getVitorias());
        docBuilder.append("empates", user.getEmpates());
        docBuilder.append("derrotas", user.getDerrotas());
        return docBuilder;
    }
    
    public void findValuesUser(String user){
            System.out.println("Método findName()");
            for (Document doc : docs.find(Filters.eq("User", user))){
            System.out.println("achou pelo user: "+doc);
            }
            System.out.println("findName() - finalizou");
        }
    
    public boolean userExists(String user){
        MongoClient mongo = new MongoClient("localhost", 27017);
        MongoDatabase db = mongo.getDatabase("headfootball");
        MongoCollection<Document> docs = db.getCollection("usuarios");
        
        System.out.println("Método userExists()");
    
        // Use o método `countDocuments` para contar os documentos que correspondem ao critério de pesquisa
        long count = docs.countDocuments(Filters.eq("user", user));

        mongo.close(); // Certifique-se de fechar a conexão com o MongoDB

        boolean exists = count > 0;
        System.out.println("Usuário '" + user + "' existe? " + exists);

        return exists;
    }

}