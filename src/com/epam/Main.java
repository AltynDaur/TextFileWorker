package com.epam;

import com.epam.textfileworker.TextFileWorker;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    TextFileWorker worker = new TextFileWorker();
        int userAnswer = 0;
        do {
            userAnswer = worker.menu();
            worker.makeAction(userAnswer);
        } while (userAnswer != 0);
        System.out.println("Finish Text File Worker!");

    }
}
