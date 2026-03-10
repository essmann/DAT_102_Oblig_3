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

}
