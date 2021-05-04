package com.galvanize.annotations;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

//import com.galvanize.model.Person;
import org.junit.Test;


public class JsonSerializerUnitTest {

    @Test
    public void personClassHasJsonSerializableAnnotation() {
        //SETUP
        Person person = new Person("John", "Volland", "25");
        Class<?> clazz = person.getClass();

        //EXECUTION
        boolean actual = clazz.isAnnotationPresent(JsonSerializable.class);

        assertTrue(actual, "Person class should have a JsonSerializable class");
    }

    @Test
    public void givenObjectNotSerializedThenExceptionThrown() throws JsonSerializationException {
        Object object = new Object();
        ObjectToJsonConverter serializer = new ObjectToJsonConverter();
        assertThrows(JsonSerializationException.class, () -> {
            serializer.convertToJson(object);
        });
    }

    @Test
    public void givenObjectSerializedThenTrueReturned() throws JsonSerializationException {
        Person person = new Person("soufiane", "cheouati", "34");
        ObjectToJsonConverter serializer = new ObjectToJsonConverter();
        String jsonString = serializer.convertToJson(person);
        System.out.println(jsonString);
        assertEquals("{\"personAge\":\"34\",\"firstName\":\"Soufiane\",\"lastName\":\"Cheouati\"}", jsonString);
    }
}

