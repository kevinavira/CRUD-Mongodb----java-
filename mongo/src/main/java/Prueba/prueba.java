package Prueba;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.MongoQueryException;
import com.mongodb.MongoWriteConcernException;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

public class prueba {

    //inicializamos los atributos de mongo para utlizarlos globalmente
    public static MongoClient mongo;
    public static MongoDatabase db;
    public static MongoCollection<Document> table;

    public static MongoCollection<Document> getTableConexion() {
        try {
            //metodo de conexion a mongodb con su respectivos atributos
            if (mongo == null) {
                mongo = new MongoClient("localhost", 27017);
            }
            if (db == null) {
                db = mongo.getDatabase("Korea");
            }
            if (table == null) {
                table = db.getCollection("Citizens");
            }
            return table;
        } catch (MongoException e) {
            System.out.println("hay un error" + e);
        }
        return null;
    }

    public static void get(String tipo, String name, String newName, String age, String nombre_fichero, String f1) {
        
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            int remplace = 0;

            if (tipo.equals("insert")) {
                //metodo para hacer insert en mongo
                try {
                    //insertamos un registro con informacion  
                    BasicDBObject document = new BasicDBObject();

                    String fecha = sdf.format(new Date());

                    document.put("Name", name);
                    document.put("Age", remplace = Integer.parseInt(age));
                    document.put("fecha", fecha);

                    //--
                    getTableConexion().insertOne(new Document(document));

                } catch (MongoWriteException | MongoWriteConcernException e) {
                    System.out.println("Error" + e);
                }

            } else if (tipo.equals("update")) {
                try {
                    //metodo para actualizar por medio del id
                    /*
                    esto sirve si vas ha traer una lista de la consulta o que te traiga toda la coleccion
                    //hacemos busqueda dentro de la coleccion
                    BasicDBObject searchQuery2 = new BasicDBObject();
                    //searchQuery.put("Name", name);
                    searchQuery2.put("_id", f1);
                    MongoCursor<Document> cursor = table.find(searchQuery2).iterator();
                    System.out.println("que trae cursor" + cursor);
                    //mostrar los datos que arrojo la consulta 
                    while (cursor.hasNext()) {
                        System.out.println("que tengo" + cursor.next());
                    }*/
                    // Insertamos registros
                    Document newDocument = new Document();
                    newDocument.put("Name", newName);
                    newDocument.put("Age", remplace = Integer.parseInt(age));
                    //pasamos los los objetos para actualizar  con el comando $set 
                    BasicDBObject updateObj = new BasicDBObject();
                    updateObj.append("$set", newDocument);
                    //hacemos efectiva la actualizacion de los datos
                    BasicDBObject searrchQuery = new BasicDBObject("_id", new ObjectId(f1));
                    getTableConexion().updateOne(searrchQuery, updateObj);
                } catch (MongoWriteException | MongoWriteConcernException e) {
                    System.out.println("El error es" + e);
                }
            }

            //mostramos los registros  cuyo nombre sea yohana
            BasicDBObject searchQuery2 = new BasicDBObject();
            MongoCursor<Document> cursor2 = getTableConexion().find(searchQuery2).iterator();
            //los resultados se presenta por consola
            while (cursor2.hasNext()) {
                //System.out.println("curso2"+cursor2.next());
                Document resultado = cursor2.next();
                System.out.println("horrr" + resultado);
                //aca  comvertimos linkmap en arraylist para poder recorrerlo
                List array = new ArrayList(resultado.toString().length());
                //aca rrecorremos el array para poder obtener la posiciones
                for (int i = 0; i < array.size(); i++) {
                    String id = array.get(i).toString();
                    //aca comvertimos el dato que que esta en estring y lo pasamos a integer
                    int p = Integer.parseInt(array.get(2).toString());

                    System.out.println("esto es el array" + id);
                    System.out.println("esta es la posision de int" + p);

                }
                /*
             //este for iteratos sirve para lista o arreglos objetos tipo iteratores
             for (Iterator iterator = cursor2.iterator(); iterator.hasNext();) {
            Object next = iterator.next();
            
        }*/
            }
            //se le agrega las excepciones pertinentes
        } catch (MongoQueryException | NullPointerException | NumberFormatException e) {
            System.out.println(e.getMessage());

        }
    }

    public static void eliminar(String id) {
        // metodo para eliminar datos por medio del id
        try {
            getTableConexion().deleteOne(new Document("_id", new ObjectId(id)));
        } catch (MongoWriteException | MongoWriteConcernException e) {
            System.out.println("Error" + e);
        }
    }

    public static List<String> d() {
        //muestra los datos por pantalla
        List<String> resultados = new ArrayList<>();
        System.out.println("tablefind"+getTableConexion().find());
        for (Document cur : getTableConexion().find()) {
            
            resultados.add(cur.toJson());
            //System.out.println("toda la collection" + cur.toJson());
        }
        return resultados;

    }
}
