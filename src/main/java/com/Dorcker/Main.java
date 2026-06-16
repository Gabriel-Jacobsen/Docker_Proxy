package com.Dorcker;

import java.util.Scanner;

import com.Dorcker.connection.Proxy;

public class Main {
    public static void main(String[] args) throws Exception {

        Proxy proxy = new Proxy("config.json");
        Scanner scan = new Scanner(System.in);

        while (true) {

            System.out.println("\nEscreva o registro que deseja encontrar (ou 'exit' para sair): ");
            String desc = scan.nextLine();
            
            if (desc.equalsIgnoreCase("exit")) {
                System.out.println("Encerrando...");
                break;
            }

            System.out.println(proxy.buscar(desc));
        }

        scan.close();
    }
}