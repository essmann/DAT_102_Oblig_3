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
    public boolean erDelmengdeAv(MengdeADT<T> annenMengde) {
        if (annenMengde.erTom() || erTom()) {
            return true;
        }
        for (T elem : tabell) {
            if (!inneholder(elem) && elem != null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean erLik(MengdeADT<T> annenMengde) {
        if (antall != annenMengde.antallElementer()) return false;
        for (T elem : tabell) {
            if (elem == null) continue;
            if (!annenMengde.inneholder(elem)) return false;
        }
        return true;
    }

    @Override
    public boolean erDisjunkt(MengdeADT<T> annenMengde) {
        boolean erDisjunkt = true;
        if (annenMengde.antallElementer() == 0 && antall == 0) return true;
        for (T elem : annenMengde.tilTabell()) {
            if (this.inneholder(elem)) {
                erDisjunkt = false;
            }
        }
        return erDisjunkt;
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
    public MengdeADT<T> union(MengdeADT<T> annenMengde) {
        MengdeTabell<T> ny = new MengdeTabell<T>();

        for (T elem : this.tilTabell()) {
            if (elem != null) {
                ny.leggTil(elem);
            }
        }

        for (T elem : annenMengde.tilTabell()) {
            if (elem != null) {
                ny.leggTil(elem);
            }
        }

        return ny;
    }


    @Override
    public MengdeADT<T> minus(MengdeADT<T> annenMengde) {
        MengdeTabell<T> minus = new MengdeTabell<T>();
        for (T elem : tabell) {
            if (elem != null && !annenMengde.inneholder(elem)) {
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
        for (T elem : tabell) {
            if (elem != null) {
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
        return Arrays.copyOf(tabell, antall);
    }


    @Override
    public int antallElementer() {
        return antall;
    }
}
