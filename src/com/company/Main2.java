package com.company;

import CashDesk.Client;

import java.util.concurrent.Semaphore;

public class Main2 {
    private static final boolean[] PLACES = new boolean[2];
    private static final Semaphore SEMAPHORE = new Semaphore(2, true);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 7; i++) {
            new Thread(new Client(i, PLACES, SEMAPHORE)).start();
            Thread.sleep(200);
        }
    }
}


