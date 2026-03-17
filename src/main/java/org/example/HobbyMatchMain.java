package org.example;

import java.util.Random;

public class HobbyMatchMain {

    //match = antallFelles – (antallKunHosDenEne + antallKunHosDenAndre) / antallTotalt
    static double match(Person a, Person b){
        MengdeSet<String> hobbyerA = a.getHobbyerSet();
        MengdeSet<String> hobbyerB = b.getHobbyerSet();

        int antallTotalt = hobbyerA.antallElementer() + hobbyerB.antallElementer();

        int fellesHobbier = hobbyerA.snitt(hobbyerB).antallElementer();
        int hobbyerKunA = hobbyerA.minus(hobbyerB).antallElementer();
        int hobbyerKunB = hobbyerB.minus(hobbyerA).antallElementer();
        double match = fellesHobbier - (double) (hobbyerKunA + hobbyerKunB) /antallTotalt;
        return match;




    }
    public static void main(){
        Random rand = new Random();

        String[] alleHobbyer = {
                "Fotball", "Gaming", "Lesing", "Trening",
                "Musikk", "Reising", "Matlaging", "Film",
                "Sykling", "Svømming"
        };

        String[] hobbyer1 = new String[3];
        for (int i = 0; i < 3; i++) {
            hobbyer1[i] = alleHobbyer[rand.nextInt(alleHobbyer.length)];
        }

        String[] hobbyer2 = new String[3];
        for (int i = 0; i < 3; i++) {
            hobbyer2[i] = alleHobbyer[rand.nextInt(alleHobbyer.length)];
        }

        String[] hobbyer3 = new String[3];
        for (int i = 0; i < 3; i++) {
            hobbyer3[i] = alleHobbyer[rand.nextInt(alleHobbyer.length)];
        }

        Person p1 = new Person("Anna", hobbyer1);
        Person p2 = new Person("Jonas", hobbyer2);
        Person p3 = new Person("Emma", hobbyer3);


        System.out.println("Match p1 & p2: " + match(p1, p2));
        System.out.println("Match p1 & p3: " + match(p1, p3));
        System.out.println("Match p2 & p3: " + match(p2, p3));
        System.out.println("Match p2 & p1: " + match(p2, p1));
        System.out.println("Match p1 & p1: " + match(p1, p1));


     }
}
