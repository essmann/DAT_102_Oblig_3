package org.example;

public class Person {
    private MengdeSet<String> hobbyer = new MengdeSet<String>();
    private String navn;
    public Person(String navn, String[] hobbyer){
        for(String hobby : hobbyer){
            this.hobbyer.leggTil(hobby);
        }
        this.navn = navn;
    }

    public MengdeSet<String> getHobbyerSet(){
        return this.hobbyer;
    }


}
