import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import ru.ac.uniyar.mf.sevas.practic.tree.Node;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TreeTest {
    @Test
    void createNode(){
        Node node = new Node("Корень");
        assertEquals("Корень", node.getName());
    }

    @Test
    void addNode(){
        Node root = new Node("Корень");
        Node child = new Node("Лист");
        root.addNode(child);
        assertEquals(1, root.getChildren().size());
        assertEquals("Лист", root.getChildren().get(0).getName());
    }

    @Test
    void deleteNode(){
        Node root1 = new Node("Корень");
        Node child1 = new Node("Лист1");
        Node child2 = new Node("Лист2");
        Node child3 = new Node("Лист3");
        root1.addNode(child1);
        root1.addNode(child2);
        root1.addNode(child3);

        Node root2 = new Node("Корень");
        root2.addNode(child1);
        root2.addNode(child2);

        root1.deleteNode(child3.id);
        assertEquals(root1.children, root2.children);
    }

    @Test
    void treePrint(){
        Node root = new Node("Корень");
        Node child = new Node ("Лист");
        root.addNode(child);
        String actual = root.toString();
        String expected = "Корень\n\tЛист\n";
        assertEquals(expected, actual);
    }

    @Test
    void print2Json() throws JsonProcessingException {
        Node root = new Node("Корень");
        Node child = new Node("Лист");
        root.addNode(child);
        ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        String actual = objectMapper.writeValueAsString(root);
        System.out.println(actual);
    }

    @Test
    void readFromJson() throws JsonProcessingException {
        String jsonString = "{\n" +
                " \"name\" : \"Корень\", \n" +
                " \"children\" : [ {\n" +
                "   \"name\" : \"Лист\", \n" +
                "   \"children\" : [ ]\n" +
                " } ]\n" +
                "}";
                ;
        ObjectMapper objectMapper = new ObjectMapper();
        Node actual = objectMapper.readValue(jsonString, Node.class);
        assertEquals("Корень", actual.getName());
        assertEquals(1, actual.getChildren().size());
        assertEquals("Лист", actual.getChildren().get(0).getName());
    }
}
