package Prueba;



import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class prueba {

    public  void get(String name, String age) {
        try {
            //String name, String age
            MongoClient mongo = new MongoClient("localhost", 27017);
            DB db = mongo.getDB("Korea");
            DBCollection table = db.getCollection("Citizens");

            //insertamos un registro con informacion  
            BasicDBObject document = new BasicDBObject();
            document.put("Name", "kevin");
            document.put("Age", "cataño");
            table.insert(document);

            //insertamos otro registro con informacion
            document.put("Name", "Paulina");
            document.put("Age", 28);
            table.insert(document);

            //hacemos busqueda dentro de la coleccion
            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.put("Name", "peter");
            DBCursor cursor = table.find(searchQuery);
             
            //mostrar los datos que arrojo la consulta 
            while (cursor.hasNext()) {
                System.out.println("que tengo"+cursor.next());
            }

            //Actualizamos los datos de un registro 
            //donde el nombre sea paulia y lo cambiamos por yohana
            BasicDBObject query = new BasicDBObject();
            query.put("Name", "kevin");

            BasicDBObject newDocument = new BasicDBObject();
            newDocument.put("Name", "hola");

            BasicDBObject updateObj = new BasicDBObject();
            updateObj.put("$set", newDocument);

            //hacemos efectiva la actualizacion de los datos
            table.update(query, updateObj);
            
            
            //mostramos los registros  cuyo nombre sea yohana
            BasicDBObject searchQuery2 = new BasicDBObject().append("Name", "Natalia");
            DBCursor cursor2 = table.find(searchQuery2);

            //los resultados se presenta por pantalla
            while (cursor2.hasNext()) {
                System.out.println(cursor2);    
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
