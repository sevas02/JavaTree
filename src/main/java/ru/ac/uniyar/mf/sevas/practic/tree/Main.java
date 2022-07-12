package ru.ac.uniyar.mf.sevas.practic.tree;

public class Main {

    public static void main(String[] args) {
        Node root = new Node("Корень");
        Node child = new Node("Лист1");
        Node child2 = new Node("Лист2");
        Node child3 = new Node("Лист3");
        root.addNode(child);
        child.addNode(child2);
        root.addNode(child3);
        System.out.println(root.printToHtml(root));
        System.out.println(root.height());
    }
}
