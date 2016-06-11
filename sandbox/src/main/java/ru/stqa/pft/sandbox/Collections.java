package ru.stqa.pft.sandbox;

import java.util.Arrays;
import java.util.List;

public class Collections {
    public static void main(String[] args) {
        String[] languages = {"Java", "C", "Python", "PHP"};

        List<String> lang = Arrays.asList("Java", "C", "Python", "PHP");

        for (int i = 0; i < languages.length; i++) {
            System.out.println("I want to learn " + languages[i]);
        }

        for (int i = 0; i < lang.size(); i++) {
            System.out.println("I want to learn " + lang.get(i));
        }

        for (String l : languages) {
            System.out.println("I want to learn " + l);
        }

        for (String l : lang) {
            System.out.println("I want to learn " + l);
        }
    }
}
