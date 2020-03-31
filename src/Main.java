import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
        String path =System.getProperty("user.dir")+"/";
        try (FileReader fileReader = new FileReader(path+"unece.json")){
            Object object = parser.parse(fileReader);
            JSONArray jsonArray =(JSONArray) object;

            // Print antallet af sæt ud.
            System.out.println("Længde på JSON array (Antal objekter): "+jsonArray.size());

            // Tag fat i et enkelt object i et JSONarray.
            JSONObject jsonObject1 = (JSONObject) jsonArray.get(0);

            // Print størrelsen ud på antallet af keys i første sæt - object(0).
            System.out.println("Antal keys i JSON filen: "+jsonObject1.keySet().size());

            // Print listen ud på antallet af keys i sættet, som tilhører object(0).
            System.out.println("Print alle keys ud i keysættet: "+jsonObject1.keySet());

            // Udskriv enkelte dele af et JSONObject.
            JSONObject jsonObject2 = (JSONObject) jsonArray.get(0);
            System.out.println("Print en specifik key ud på object 0 (Country): "+jsonObject2.get("Country"));
            System.out.println("Print en specifik key ud på object 0 (Year): "+jsonObject2.get("Year"));
            System.out.println("Print en specifik key ud på object 0 (Population): "+jsonObject2.get("Total population"));

            JSONArray worldInfo = new JSONArray();

            for (Object o: jsonArray) {
                JSONObject worldObject = new JSONObject();
                JSONObject jsonObject = (JSONObject) o;

                worldObject.put("Country", jsonObject.get("Country"));
                worldObject.put("Year", jsonObject.get("Year"));
                worldObject.put("Total population", jsonObject.get("Total population"));
                worldInfo.add(worldObject);
            }

            System.out.println(worldInfo.get(0));

            FileWriter file = new FileWriter("newJSONfile.json");
            file.write(String.valueOf(worldInfo));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
