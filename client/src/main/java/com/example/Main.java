package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
        System.out.println("Client Partito");
        Socket s = new Socket("localhost", 3000);
        BufferedReader in = new BufferedReader(new InputStreamReader((s.getInputStream())));
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        Scanner input = new Scanner(System.in);
        boolean connessione = true, ins = true;
        System.out.println("Inserire username");
        while(ins){
            out.writeBytes("" + input.nextLine() + '\n');
            if(!in.readLine().equals("!"))
                ins = false;
            else
                System.out.println("nome già inserito");
        }
        while (connessione) {
            System.out.println("scegli cosa fare:");
            System.out.println("1)chiedere disponibilità biglietti");
            System.out.println("2)comprare biglietti");
            System.out.println("3)uscire");
            switch (input.nextLine()) {
                case "1":
                    out.writeBytes("N" + '\n');
                    System.out.println("di che tipo?: 1)Gold 2)Pit 3)Parterre");
                    boolean loop = true;
                    while(loop){
                        switch (input.nextLine()) {
                            case "1":
                                System.out.println("selezionati i Gold");
                                out.writeBytes("0" + '\n');
                                loop = false;
                                break;
    
                            case "2":
                                System.out.println("selezionati i Pit");
                                out.writeBytes("1" + '\n');
                                loop = false;
                                break;
    
                            case "3":
                                System.out.println("selezionati i Parterre");
                                out.writeBytes("2" + '\n');
                                loop = false;
                                break;
                        
                            default:
                                System.out.println("inserire 1 o 2 o 3");
                                break;
                        }
                    }
                    
                    System.out.println("sono disponibili " + in.readLine() + " biglietti");
                    break;
                case "2":
                    out.writeBytes("BUY" + '\n');
                    System.out.println("quanti biglietti?");
                    boolean richiesta = true;
                    while (richiesta) {
                        try {
                            int num = Integer.parseInt(input.nextLine());
                            if (num < 0)
                                throw new Exception();
                            richiesta = false;
                            out.writeBytes("" + num + '\n');
                        } catch (Exception e) {
                            System.out.println("inserire un numero valido");
                        }
                    }
                    System.out.println("di che tipo?: 1)Gold 2)Pit 3)Parterre");
                    loop = true;
                    while(loop){
                        switch (input.nextLine()) {
                            case "1":
                                System.out.println("selezionati i Gold");
                                out.writeBytes("0" + '\n');
                                loop = false;
                                break;
    
                            case "2":
                                System.out.println("selezionati i Pit");
                                out.writeBytes("1" + '\n');
                                loop = false;
                                break;
    
                            case "3":
                                System.out.println("selezionati i Parterre");
                                out.writeBytes("2" + '\n');
                                loop = false;
                                break;
                        
                            default:
                                System.out.println("inserire 1 o 2 o 3");
                                break;
                        }
                    }

                    switch (in.readLine()) {
                        case "KO":
                            System.out.println("impossibile acquistare biglietti");
                            break;

                        case "OK":
                            System.out.println("bigliatti acquistati con successo");
                            break;
                    }

                    break;
                case "3":
                    out.writeBytes("QUIT\n");
                    connessione = false;
                    break;
                default:
                    System.out.println("inserire una richiesta");
                    break;
            }
        }
        s.close;
    }
}
