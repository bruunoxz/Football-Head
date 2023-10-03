package Utilitários;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;

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
    
    
        private int getField(String username, String fieldName) {
            MongoClient mongo = new MongoClient("localhost", 27017);
            MongoDatabase db = mongo.getDatabase("headfootball");
            MongoCollection<Document> usuariosCollection = db.getCollection("usuarios");

            // Crie um filtro para encontrar o usuário pelo nome de usuário
            Bson filter = Filters.eq("username", username);

            // Execute uma consulta para obter o documento do usuário
            Document userDocument = usuariosCollection.find(filter).first();

            // Verifique se o documento foi encontrado
            if (userDocument != null && userDocument.containsKey(fieldName)) {
                return userDocument.getInteger(fieldName);
            }

            // Valor padrão se o campo não existir ou o documento não for encontrado
            return 0;
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
    
        public void incrementVitorias(String username) {
        incrementField(username, "vitorias");
        }

        public void incrementEmpates(String username) {
            incrementField(username, "empates");
        }

        public void incrementDerrotas(String username) {
            incrementField(username, "derrotas");
        }

        private void incrementField(String username, String fieldName) {
            int valorAtual = getField(username, fieldName);

            // Incremente o valor
            valorAtual++;

            updateField(username, fieldName, valorAtual);
        }
    
        private void updateField(String username, String fieldName, int value) {
            MongoClient mongo = new MongoClient("localhost", 27017);
            MongoDatabase db = mongo.getDatabase("headfootball");
            MongoCollection<Document> usuariosCollection = db.getCollection("usuarios");

            // Crie um filtro para encontrar o usuário pelo nome de usuário
            Bson filter = Filters.eq("user", username);

            // Crie um documento de atualização com o novo valor para o campo especificado
            Bson update = new Document("$set", new Document(fieldName, value));

            // Atualize o documento do usuário com base no filtro
            UpdateResult result = usuariosCollection.updateOne(filter, update);

            // Verifique se a atualização foi bem-sucedida
            if (result.getModifiedCount() > 0) {
                System.out.println(fieldName + " atualizado com sucesso para o usuário: " + username);
            } else {
                System.out.println("Usuário não encontrado ou nenhum valor foi atualizado.");
            }

            // Feche a conexão com o MongoDB
            mongo.close();
    }
    public int getVitorias(String username) {
        return getField(username, "vitorias");
    }

    public int getEmpates(String username) {
        return getField(username, "empates");
    }

    public int getDerrotas(String username) {
        return getField(username, "derrotas");
    }

}