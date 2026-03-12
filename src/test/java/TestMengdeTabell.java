import org.example.MengdeADT;
import org.example.MengdeTabell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


import java.util.Arrays;

public class TestMengdeTabell {


    @BeforeEach
    void init() {

    }

    @Test
    public void TestDuplicateAdd() {

        MengdeTabell<Integer> mengde = new MengdeTabell<Integer>();
        int[] nums = {1, 1, 1, 12, 2, 2, 3, 4};
        //Unique length = 5;

        for (int num : nums) {
            mengde.leggTil(num);
        }
        Assertions.assertEquals(5, mengde.antallElementer());


    }
    @Test
    public void testErTom() {
        MengdeTabell<Integer> mengde = new MengdeTabell<>();
        Assertions.assertTrue(mengde.erTom());

        mengde.leggTil(1);
        Assertions.assertFalse(mengde.erTom());
    }

    @Test
    public void testInneholder() {
        MengdeTabell<Integer> mengde = new MengdeTabell<>();
        mengde.leggTil(5);

        Assertions.assertTrue(mengde.inneholder(5));
        Assertions.assertFalse(mengde.inneholder(2));
    }

    @Test
    public void testFjern() {
        MengdeTabell<Integer> mengde = new MengdeTabell<>();
        mengde.leggTil(10);

        Integer removed = mengde.fjern(10);
        Assertions.assertEquals(10, removed);
        Assertions.assertFalse(mengde.inneholder(10));
    }

    @Test
    public void testFjernIkkeEksisterende() {
        MengdeTabell<Integer> mengde = new MengdeTabell<>();
        mengde.leggTil(1);

        Assertions.assertNull(mengde.fjern(5));
    }

    @Test
    public void testUnion() {
        MengdeTabell<Integer> a = new MengdeTabell<>();
        MengdeTabell<Integer> b = new MengdeTabell<>();

        a.leggTil(1);
        a.leggTil(2);

        b.leggTil(2);
        b.leggTil(3);

        MengdeADT<Integer> union = a.union(b);

        Assertions.assertEquals(3, union.antallElementer());
        Assertions.assertTrue(union.inneholder(1));
        Assertions.assertTrue(union.inneholder(2));
        Assertions.assertTrue(union.inneholder(3));
    }

    @Test
    public void testSnitt() {
        MengdeTabell<Integer> a = new MengdeTabell<>();
        MengdeTabell<Integer> b = new MengdeTabell<>();

        a.leggTil(1);
        a.leggTil(2);
        a.leggTil(3);

        b.leggTil(2);
        b.leggTil(4);

        MengdeADT<Integer> snitt = a.snitt(b);

        Assertions.assertEquals(1, snitt.antallElementer());
        Assertions.assertTrue(snitt.inneholder(2));
    }

    @Test
    public void testMinus() {
        MengdeTabell<Integer> a = new MengdeTabell<>();
        MengdeTabell<Integer> b = new MengdeTabell<>();

        a.leggTil(1);
        a.leggTil(2);
        a.leggTil(3);

        b.leggTil(2);

        MengdeADT<Integer> minus = a.minus(b);

        Assertions.assertEquals(2, minus.antallElementer());
        Assertions.assertTrue(minus.inneholder(1));
        Assertions.assertTrue(minus.inneholder(3));
    }

    @Test
    public void testErLik() {
        MengdeTabell<Integer> a = new MengdeTabell<>();
        MengdeTabell<Integer> b = new MengdeTabell<>();

        a.leggTil(1);
        a.leggTil(2);

        b.leggTil(2);
        b.leggTil(1);

        Assertions.assertTrue(a.erLik(b));
    }

    @Test
    public void testErDisjunkt() {
        MengdeTabell<Integer> a = new MengdeTabell<>();
        MengdeTabell<Integer> b = new MengdeTabell<>();

        a.leggTil(1);
        a.leggTil(2);

        b.leggTil(3);
        b.leggTil(4);

        Assertions.assertTrue(a.erDisjunkt(b));
    }


}
