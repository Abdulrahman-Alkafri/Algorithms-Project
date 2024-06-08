package com.mycompany.visual;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
    Node root = null;
    try {
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("->");
                String parentVal = parts[0].trim();
                String[] children = parts[1].split(",");
                Node parentNode = findOrCreateNode(root, parentVal);
                if (root == null) {
                    root = parentNode;
                }
                for (String child : children) {
                    Node childNode = new Node(child.trim());
                    parentNode.children.add(childNode);
                }
            }
        }
    } catch (IOException e) {
    }
    Node binaryRoot = Generic.genericToBinary(root);
    Node generalRoot = Generic.binaryToGeneric(binaryRoot);    
      writeOutputToFile(generalRoot, "outputTree.txt");
    writeBinaryTreeToFile(binaryRoot, "outputBinary.txt");
}

    public static Node findOrCreateNode(Node root, String val) {
        if (root == null) {
            return new Node(val);
        }
        if (root.val.equals(val)) {
            return root;
        }
        for (Node child : root.children) {
            Node result = findOrCreateNode(child, val);
            if (result != null) {
                return result;
            }
        }
        return null;
    }
    public static void writeOutputToFile(Node root, String fileName) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writeTreeToFile(root, writer);
    }
}

    public static void writeTreeToFile(Node node, BufferedWriter writer) throws IOException {
    if (node == null) {
        return;
    }
    String line;
    BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
    while((line = reader.readLine()) != null){
        writer.write(line);
        writer.write("\n");
    }
}
    public static void writeBinaryTreeToFile(Node node, String fileName) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
        writeBinaryTree(node, writer);
    }
}
public static void writeBinaryTree(Node node, BufferedWriter writer) throws IOException {
    if (node == null) {
        return;
    }
    
    
    if (node.left != null || node.right != null) {
        writer.write(node.val);
        
        if (node.left != null || node.right != null) {
            writer.write(" -> ");
            if (node.left != null) {
                writer.write(node.left.val);
            }
            if (node.right != null) {
                if (node.left != null) {
                    writer.write(", ");
                }
                writer.write(node.right.val);
            }
        }
        
        writer.write("\n");
    }
    
    writeBinaryTree(node.left, writer);
    writeBinaryTree(node.right, writer);
}
}