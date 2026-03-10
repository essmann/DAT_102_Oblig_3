package org.example;

import java.util.Arrays;

public class MengdeTabell<T> implements MengdeADT<T> {

    private T[] tabell;
    private int antall;
    private static final int STANDARD_KAPASITET = 10;

    @SuppressWarnings("unchecked")
    public MengdeTabell() {
        this.tabell = (T[]) new Object[STANDARD_KAPASITET];
        this.antall = 0;
    }

    @SuppressWarnings("unchecked")
    public MengdeTabell(int kapasitet) {
        this.tabell = (T[]) new Object[kapasitet];


    }

    public Object[] getTabell() {
        return this.tabell;
    }

    @Override
    public boolean erTom() {
        return antall == 0;
    }

    @Override
    public boolean inneholder(T element) {
        for (int i = 0; i < antall; i++) {
            if (tabell[i] == element) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean erDelmengdeAv(MengdeADT annenMengde) {
        return false;
    }

    @Override
    public boolean erLik(MengdeADT annenMengde) {
        return false;
    }

    @Override
    public boolean erDisjunkt(MengdeADT annenMengde) {
        return false;
    }

    @Override
    public MengdeADT<T> snitt(MengdeADT<T> annenMengde) {
        MengdeADT<T> snittMengde = new MengdeTabell<T>();
        for (T elem : annenMengde.tilTabell()) {
            if (this.inneholder(elem)) {
                snittMengde.leggTil(elem);
            }
        }
        return snittMengde;
    }

    @Override
    public MengdeADT union(MengdeADT annenMengde) {
        return null;
    }

    @Override
    public MengdeADT<T> minus(MengdeADT<T> annenMengde) {
        MengdeTabell<T>  minus = new MengdeTabell<T>();
        for(T elem : tabell){
            if(elem != null && !annenMengde.inneholder(elem)){
                minus.leggTil(elem);
            }
        }
        return minus;
    }

    @Override
    public void leggTil(T element) {
        if (inneholder(element)) return;
        if (antall == tabell.length) {
            //full
            this.tabell = upsizeTabell(tabell, tabell.length + 20);

        }
        this.tabell[antall] = element;
        antall++;
    }

    private T[] upsizeTabell(T[] tabell, int newSize) {

        T[] ny = (T[]) new Object[newSize];
        int count = 0;
        for(T elem : tabell){
            if(elem != null){
                ny[count] = elem;
                count++;
            }
        }
        return ny;
    }

    @Override
    public void leggTilAlleFra(MengdeADT annenMengde) {
        for (Object obj : annenMengde.tilTabell()) {
            this.leggTil((T) obj);
        }
    }

    public void print() {
        String str = "";
        for (int i = 0; i < tabell.length; i++) {
            if (tabell[i] != null) {
                str += tabell[i] + " ";
            }
        }
        System.out.println(str);
    }

    @Override
    public T fjern(T element) {
        for (int i = 0; i < antall; i++) {
            if (tabell[i].equals(element)) {  //equals for object comparison
                for (int j = i; j < antall - 1; j++) {
                    tabell[j] = tabell[j + 1];
                }
                tabell[antall - 1] = null;
                antall--;
                return element;
            }
        }
        return null;
    }


    @Override
    public T[] tilTabell() {
        return Arrays.copyOf(tabell, tabell.length);
    }


    @Override
    public int antallElementer() {
        return antall;
    }
}
