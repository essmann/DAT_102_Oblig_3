package org.example;

import java.util.*;

public class HashSetVsBinarySearch {
    public static void main(String[] args) {
        int antElement = 100_000;      // Antall element vi skal legge til
        int maxVal = 1_000_000;        // Maksverdi for tallene
        int[] numbers = new int[antElement]; // Tabell for binærsøk
        HashSet<Integer> hashSet = new HashSet<>(); // HashSet for raskt søk

        // Generer 100 000 unike tall
        int tall = 376; // startverdi (kan være vilkårlig)
        for (int i = 0; i < antElement; i++) {
            numbers[i] = tall;         // legg til i tabell
            hashSet.add(tall);         // legg til i HashSet
            tall = (tall + 45713) % maxVal; // generer neste tall unikt
        }

        // Sorter tabellen for binærsøk
        Arrays.sort(numbers);

        int searchCount = 10_000; // Antall tall vi skal søke etter
        int[] searchNumbers = new int[searchCount];
        Random rand = new Random();

        for (int i = 0; i < searchCount; i++) {
            searchNumbers[i] = rand.nextInt(maxVal); // helt tilfeldige tall 0…999999
        }

        // Søk i HashSet og mål tid
        long startHashSet = System.nanoTime();
        int foundInHashSet = 0;
        for (int num : searchNumbers) {
            if (hashSet.contains(num)) {
                foundInHashSet++;
            }
        }
        long endHashSet = System.nanoTime();
        long hashSetTime = endHashSet - startHashSet;

        // Søk i tabell med binærsøk og mål tid
        long startArray = System.nanoTime();
        int foundInArray = 0;
        for (int num : searchNumbers) {
            if (Arrays.binarySearch(numbers, num) >= 0) {
                foundInArray++;
            }
        }
        long endArray = System.nanoTime();
        long arrayTime = endArray - startArray;

        // Skriv ut resultater
        System.out.println("HashSet: Fant " + foundInHashSet + " / " + searchCount + " tall.");
        System.out.println("HashSet søketid (ms): " + hashSetTime / 1_000_000.0);

        System.out.println("Tabell: Fant " + foundInArray + " / " + searchCount + " tall.");
        System.out.println("Binærsøk søketid (ms): " + arrayTime / 1_000_000.0);
    }
}
