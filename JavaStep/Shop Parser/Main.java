package com.sablin.shopparser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {



    public static void main(String[] args) throws IOException {
        ArrayList<Client> clients = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        while (!str.isEmpty()){
            String[] buffStr = str.split(" ");
            Client buffClient = new Client(buffStr[0]);
            buffClient.addProduct(new Product(buffStr[1], Integer.parseInt(buffStr[2])));

            if(clients.isEmpty()){
                clients.add(buffClient);
            }else {
                if(Client.findClient(clients, buffClient.name) == null){
                    clients.add(buffClient);
                }else {
                    Client.findClient(clients, buffClient.name).addProduct(new Product(buffStr[1], Integer.parseInt(buffStr[2])));
                }
            }
            str = sc.nextLine();
        }
        for (Client client : clients){
            System.out.println(client.name + " :");
            for (Product product : client.products){
                System.out.println(product.name + " " + product.count);
            }
        }
    }

}

class Client{
    String name;
    ArrayList<Product> products = new ArrayList<>();

    Client(String name){
        this.name = name;
    }

    public void addProduct(Product product){
        if(Product.findProduct(products, product.name) != null){
            Product.findProduct(products, product.name).addCount(product.count);
        }else{
            products.add(product);
        }
    }

    public static Client findClient(ArrayList<Client> list, String variableValue) {
        for (Client client : list) {
            if (client.name.equals(variableValue)) {
                return client;
            }
        }
        return null; // объект не найден
    }

}

class Product{
    String name;
    int count;

    Product(String name, int count){
        this.name = name;
        this.count = count;
    }
    public void addCount(int count){
        this.count += count;
    }

    public static Product findProduct(ArrayList<Product> list, String variableValue) {
        for (Product product : list) {
            if (product.name.equals(variableValue)) {
                return product;
            }
        }
        return null; // объект не найден
    }
}
