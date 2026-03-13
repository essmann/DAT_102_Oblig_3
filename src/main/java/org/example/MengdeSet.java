package org.example;

import java.util.HashSet;
import java.util.Set;

public class MengdeSet<T> implements MengdeADT<T> {

    private Set<T> hashSet = new HashSet<T>();

    @Override
    public boolean erTom() {
        return hashSet.isEmpty();
    }

    @Override
    public boolean inneholder(T element) {
        return hashSet.contains(element);
    }

    @Override
    public boolean erDelmengdeAv(MengdeADT<T> annenMengde) {
        for (T elem : this.tilTabell()) {
            if (!annenMengde.inneholder(elem)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean erLik(MengdeADT<T> annenMengde) {
        T[] andreTabell = annenMengde.tilTabell();
        if (this.antallElementer() != andreTabell.length) return false;
        for (T elem : andreTabell) {
            if (!hashSet.contains(elem)) return false;
        }
        return true;
    }

    @Override
    public boolean erDisjunkt(MengdeADT<T> annenMengde) {
        for (T elem : hashSet) {
            if (annenMengde.inneholder(elem)) return false;
        }
        return true;
    }

    @Override
    public MengdeADT<T> snitt(MengdeADT<T> annenMengde) {
        MengdeSet<T> snittMengde = new MengdeSet<>();
        for (T elem : hashSet) {
            if (annenMengde.inneholder(elem)) {
                snittMengde.leggTil(elem);
            }
        }
        return snittMengde;
    }

    @Override
    public MengdeADT<T> union(MengdeADT<T> annenMengde) {
        MengdeSet<T> unionMengde = new MengdeSet<>();
        unionMengde.leggTilAlleFra(this);
        unionMengde.leggTilAlleFra(annenMengde);
        return unionMengde;
    }

    @Override
    public MengdeADT<T> minus(MengdeADT<T> annenMengde) {
        MengdeSet<T> differanse = new MengdeSet<>();
        for (T elem : hashSet) {
            if (!annenMengde.inneholder(elem)) {
                differanse.leggTil(elem);
            }
        }
        return differanse;
    }

    @Override
    public void leggTil(T element) {
        hashSet.add(element);
    }

    @Override
    public void leggTilAlleFra(MengdeADT<T> annenMengde) {
        for (T elem : annenMengde.tilTabell()) {
            hashSet.add(elem);
        }
    }

    @Override
    public T fjern(T element) {
        if (hashSet.remove(element)) {
            return element;
        }
        return null;
    }

    @Override
    public T[] tilTabell() {
        if (hashSet.isEmpty()) {
            return (T[]) new Object[0];
        }
        T[] array = (T[]) java.lang.reflect.Array.newInstance(
                hashSet.iterator().next().getClass(), hashSet.size());
        return hashSet.toArray(array);
    }


    @Override
    public int antallElementer() {
        return hashSet.size();
    }
}
