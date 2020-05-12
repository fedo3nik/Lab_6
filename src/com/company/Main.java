package com.company;

import Skybase.*;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Main {

    public static final int AMOUNT_OF_OPERATORS = 3;
    public static final int AMOUNT_OF_CLIENTS = 30;

    public static void main(String[] args) {
        Random rand = new Random();
        Semaphore semaphore = new Semaphore(AMOUNT_OF_OPERATORS, true);
        Base base = new Base(AMOUNT_OF_OPERATORS);
        Client client;
        try {
            for (int i = 0; i < AMOUNT_OF_CLIENTS; i++) {
                int age = (int) (14 + Math.random() * 60);
                client = new Client(i, age, base, semaphore);

                client.start();
                Thread.sleep(rand.nextInt(10) + 300);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
