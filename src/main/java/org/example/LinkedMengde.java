package org.example;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Collectors;

class Node<T> {
    T data;
    Node<T> neste;

    public Node() {
        this.data = null;
        this.neste = null;
    }
}


public class LinkedMengde<T> implements MengdeADT<T> {

    private Node forste;
    private int antall;

    @Override
    public boolean erTom() {

        return antall == 0;
    }

    @Override
    public boolean inneholder(T element) {
        Node<T> current = forste;
        if (element == null) return true;
        while (current != null) {

            if (current.data == element) {
                return true;
            }

            current = current.neste;
        }
        return false;

    }

    @Override
    public boolean erDelmengdeAv(MengdeADT<T> annenMengde) {
        for (T elem : tilTabell()) {
            if (elem != null && !annenMengde.inneholder(elem)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean erLik(MengdeADT<T> annenMengde) {
        if(annenMengde.antallElementer() != antall) return false;
        for (T elem : annenMengde.tilTabell()){
            if(!inneholder(elem)) return false;
        }
        return true;
    }

    @Override
    public boolean erDisjunkt(MengdeADT<T> annenMengde) {
        for (T elem : annenMengde.tilTabell()) {
            if (inneholder(elem)) return false;
        }
        return true;
    }

    @Override
    public MengdeADT snitt(MengdeADT<T> annenMengde) {
        LinkedMengde<T> ny = new LinkedMengde<T>();
        for (T elem : annenMengde.tilTabell()) {
            if (inneholder(elem)) {
                ny.leggTil(elem);
            }
        }
        return ny;
    }

    @Override
    public MengdeADT union(MengdeADT<T> annenMengde) {
        LinkedMengde<T> ny = new LinkedMengde<T>();
        ny.leggTilAlleFra(annenMengde);
        ny.leggTilAlleFra(this);


        return ny;
    }

    @Override
    public MengdeADT minus(MengdeADT<T> annenMengde) {
        LinkedMengde<T> minus = new LinkedMengde<T>();

        Node<T> current = forste;
        while (current != null) {

            T elem = current.data;
            if (elem != null && !annenMengde.inneholder(elem)) {
                minus.leggTil(elem);
            }
            current = current.neste;
        }

        return minus;

    }

    @Override
    public void leggTil(T element) {

        if (!inneholder(element)) {
            Node<T> node = new Node();
            node.data = element;
            node.neste = this.forste;
            this.forste = node;
            antall++;
        }
    }

    @Override
    public void leggTilAlleFra(MengdeADT<T> annenMengde) {

        for (T elem : annenMengde.tilTabell()) {
            if (elem == null) continue;
            leggTil(elem);
        }
    }

    @Override
    public T fjern(T element) {
        if (fjern(forste, null, element)) {
            antall--;
        return element;
        }
        return null;
    }

    private boolean fjern(Node node, Node prev, T element) {
        if(node.data == element && prev == null){
            //This is the first node of the list
            if(node.neste != null){
                forste = node.neste; //De-facto deletion
                return true;
            }
            node.data = null;
            return true;
        }
        else if(node.data == element && prev != null){
            prev.neste = node.neste; //deletion
            return true;
        }
        else if(node.neste != null){
           return fjern(node.neste, node, element);

        }
        return false;

    }
    @Override
    @SuppressWarnings("unchecked")
    public T[] tilTabell() {
        T[] arr = (T[]) new Object[antall];

        Node<T> current = forste;
        int i = 0;

        while (current != null) {
            arr[i++] = current.data;
            current = current.neste;
        }

        return arr;
    }



    @Override
    public int antallElementer() {
        return antall;
    }

    public void print() {

        System.out.println("Linked mengde: ");
        print(this.forste);
    }

    private void print(Node node) {
        if (node.neste != null) {
            if (node.data != null) {
                System.out.print(node.data + " ");
            }
            print(node.neste);
        }
    }
}
