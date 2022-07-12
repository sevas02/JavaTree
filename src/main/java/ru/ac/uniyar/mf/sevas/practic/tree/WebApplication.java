package ru.ac.uniyar.mf.sevas.practic.tree;

import javax.ws.rs.core.Application;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Web-приложение в котором регистрируются все ресурсы.
 */
public class WebApplication extends Application {

    private List<String> list = new ArrayList<>();
    private Node root;

    public WebApplication() {
        root = new Node("Корень");
        Node child = new Node("Лист1");
        Node child2 = new Node("Лист2");
        Node child3 = new Node("Лист3");
        Node child4 = new Node("Лист4");
        Node child5 = new Node("Лист5");
        root.addNode(child);
        child.addNode(child2);
        child2.addNode(child3);
        child2.addNode(child4);
        root.addNode(child5);

        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        list.add("ddd");
    }

    /**
     * Возвращает список всех ресурсов web-приложения.
     * @return список всех ресурсов web-приложения.
     */
    @Override
    public Set<Object> getSingletons() {
        Set<Object> resources = new HashSet<>();
        resources.add(new ListPresentationController(list, root));
        return resources;
    }
}