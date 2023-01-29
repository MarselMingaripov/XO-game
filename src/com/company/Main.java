package com.company;

import com.company.game.Net;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    Net net = new Net(new Scanner(System.in));
        net.startGame();
    }
}
