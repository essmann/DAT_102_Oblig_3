package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {



        MengdeTabell<Integer> tabell = new MengdeTabell<Integer>();


        int[] nums = {1, 2, 3, 4, 5, 6, 7};

        for (int num : nums) {
            tabell.leggTil(num);
        }

        tabell.print();
        MengdeTabell<Integer> tabell2 = new MengdeTabell<Integer>();

        int[] duplicateNums = {1, 1, 1, 3, 3, 3, 4, 4, 12, 13, 12, 4, 5, 6, 7};
        for (int num : duplicateNums) {
            tabell2.leggTil(num);
        }
        tabell2.print();

        MengdeTabell<Integer> _snitt = (MengdeTabell<Integer>)tabell.snitt(tabell2);
        System.out.println(_snitt);
        _snitt.print();

        MengdeTabell<Integer> tabell3 = new MengdeTabell<Integer>();

        tabell3.leggTil(1);
        tabell3.leggTil(4);
        MengdeTabell<Integer> minus = (MengdeTabell<Integer>) _snitt.minus(tabell3);
        minus.print();

        LinkedMengde<Integer> linked = new LinkedMengde<Integer>();
        linked.leggTilAlleFra(minus);

        linked.print();
        linked.fjern(6);
        linked.print();



    }
}
