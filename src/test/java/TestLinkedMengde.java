import org.example.LinkedMengde;
import org.example.MengdeADT;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestLinkedMengde {

    private LinkedMengde<Integer> a;
    private LinkedMengde<Integer> b;

    @BeforeEach
    void init() {
        a = new LinkedMengde<>();
        b = new LinkedMengde<>();
    }

    @Test
    void duplicateElementsAreNotAdded() {
        int[] nums = {1, 1, 1, 12, 2, 2, 3, 4};

        for (int n : nums) {
            a.leggTil(n);
        }

        Assertions.assertEquals(5, a.antallElementer());
    }

    @Test
    void emptySetWorksCorrectly() {
        Assertions.assertTrue(a.erTom());

        a.leggTil(1);

        Assertions.assertFalse(a.erTom());
    }

    @Test
    void containsWorks() {
        a.leggTil(5);

        Assertions.assertTrue(a.inneholder(5));
        Assertions.assertFalse(a.inneholder(2));
    }

    @Test
    void removeExistingElement() {
        a.leggTil(10);

        Integer removed = a.fjern(10);

        Assertions.assertEquals(10, removed);
        Assertions.assertFalse(a.inneholder(10));
    }

    @Test
    void removeNonExistingElementReturnsNull() {
        a.leggTil(1);

        Assertions.assertNull(a.fjern(5));
    }

    @Test
    void unionContainsAllUniqueElements() {
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
    void intersectionContainsCommonElements() {
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
    void minusRemovesElementsFromOtherSet() {
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
    void setsWithSameElementsAreEqual() {
        a.leggTil(1);
        a.leggTil(2);

        b.leggTil(2);
        b.leggTil(1);

        Assertions.assertTrue(a.erLik(b));
    }

    @Test
    void setsWithDifferentElementsAreNotEqual() {
        a.leggTil(1);
        a.leggTil(2);

        b.leggTil(3);
        b.leggTil(4);

        Assertions.assertFalse(a.erLik(b));
    }

    @Test
    void subsetWorksCorrectly() {
        a.leggTil(1);
        a.leggTil(2);
        a.leggTil(3);

        b.leggTil(1);
        b.leggTil(2);

        Assertions.assertTrue(b.erDelmengdeAv(a));
    }

    @Test
    void disjointSetsReturnTrue() {
        a.leggTil(1);
        a.leggTil(2);

        b.leggTil(3);
        b.leggTil(4);

        Assertions.assertTrue(a.erDisjunkt(b));
    }

    @Test
    void nonDisjointSetsReturnFalse() {
        a.leggTil(1);
        a.leggTil(2);

        b.leggTil(2);
        b.leggTil(3);

        Assertions.assertFalse(a.erDisjunkt(b));
    }

    @Test
    void removeFirstElementInList() {
        a.leggTil(1);
        a.leggTil(2);

        a.fjern(2);

        Assertions.assertFalse(a.inneholder(2));
        Assertions.assertEquals(1, a.antallElementer());
    }

    @Test
    void tilTabellReturnsCorrectSize() {
        a.leggTil(1);
        a.leggTil(2);
        a.leggTil(3);

        Object[] arr = a.tilTabell();

        System.out.println(arr.length);

        Assertions.assertEquals(3, arr.length);
    }

    @Test
    void addAllFromAnotherSet() {
        b.leggTil(1);
        b.leggTil(2);

        a.leggTilAlleFra(b);

        Assertions.assertEquals(2, a.antallElementer());
        Assertions.assertTrue(a.inneholder(1));
        Assertions.assertTrue(a.inneholder(2));
    }

    @Test
    void unionWithEmptySet() {
        a.leggTil(1);

        MengdeADT<Integer> result = a.union(b);

        Assertions.assertEquals(1, result.antallElementer());
        Assertions.assertTrue(result.inneholder(1));
    }

    @Test
    void minusWithEmptySet() {
        a.leggTil(1);
        a.leggTil(2);

        MengdeADT<Integer> result = a.minus(b);

        Assertions.assertEquals(2, result.antallElementer());
    }
}
