import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        String carJson = "{\"brand\" : \"Mercedes\", \"doors\" : \"5\"}";
        try{
            Car car = objectMapper.readValue(carJson, Car.class);

            System.out.println("car brand = " + car.getBrand());
            System.out.println("car doors = " + car.getDoors());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Car car = new Car();
        car.setBrand("BMW");
        car.setDoors(4);

        try{
            objectMapper.writeValue(new FileOutputStream("output.json"), car);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //do String
        String json = null;
        try{
            json = objectMapper.writeValueAsString(car);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(json);

        //Kiedy mamy liste

        String carJson2 = "{\"brand\" : \"Mercedes\", \"doors\" : 5," +
                        " \"owners\" : [\"John\", \"Jill\"]," +
                        " \"nestedObject\" : {\"field\" : \"value\"} }";

        try{
            //tworzymy obiekt JsonNode
            JsonNode node = objectMapper.readValue(carJson2, JsonNode.class);
            //pobieramy i wyświetlamy markę
            JsonNode brandNode = node.get("brand");
            String brand = brandNode.asText();
            System.out.println("brand = " + brand);

            //drzwi
            JsonNode doorsNode = node.get("doors");
            int doors = doorsNode.asInt();
            System.out.println("doors = " + doors);

            //listę właścicieli
            JsonNode array = node.get("owners");
            //pierwszego z listy
            JsonNode jsonNode = array.get(0);
            String john = jsonNode.asText();
            System.out.println("john = " + john);

            //obiekt w środku
            JsonNode child = node.get("nestedObject");
            //jego parametry wewnętrzne

            JsonNode childField = child.get(0);
            String field = childField.asText();
            System.out.println("field = " + field);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
