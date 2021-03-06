package ru.ac.uniyar.mf.sevas.practic.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Node {
    public String id;
    public String name;
    public List<Node> children;

    //дефолтный конструктор
    public Node(){;}

    //конструктор с параметром
    public Node(String nodeName) {
        this.id = UUID.randomUUID().toString();
        this.name = nodeName;
        this.children = new ArrayList<>();
    }

    //геттеры
    public List<Node> getChildren() { return children; }
    public String getName() { return name; }

    //добавление узла
    public void addNode(Node _child) {
        children.add(_child);
    }

    //удаление узла
    public void deleteNode(String ident){
        int idx = -1;
        for (int i = 0; i<children.size(); i++)
            if (children.get(i).id == ident)
                idx = i;
        if (idx != -1)
            children.remove(idx);
    }

    public void changeNode(String ident, String info){
        if (ident == id)
            name = info;
        for(int i = 0; i < children.size(); i++)
            children.get(i).changeNode(ident,info);
    }

    //дерево в строку со всеми отступами
    @Override
    public String toString() {
        return toString(this, 0);
    }

    private String toString(Node node, int space) {
        if (node == null) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < space; i++) {
            result.append("\t");
        }
        result.append(node.name).append("\n");
        if (node.children == null) {
            return result.toString();
        }
        for (Node child : node.children) {
            result.append(toString(child, space + 1));
        }
        return result.toString();
    }

    private String toHtml(Node pos) {
        if (pos == null) return "";
        StringBuilder answer = new StringBuilder();

        answer.append("<li>").append(pos.name);
        if (pos.children == null)
            return answer.append("</li>").toString();

        answer.append("<ul>");
        for (Node child : pos.children)
            answer.append(toHtml(child));
        answer.append("</ul>");
        return answer.append("</li>").toString();
    }


    public String printToHtml(Node node) {
        String res = "<ul>";
        if (node == null) return "";

        res += "<li>";
        res += node.name;

        if (node.children == null) {
            res += "</li>";
            return res.toString();
        }
        res += "<ul>";
        for (Node child : node.children)
            res += toHtml(child);
        res += ("</ul>" + "</li>");
        return res.toString();
    }

}
