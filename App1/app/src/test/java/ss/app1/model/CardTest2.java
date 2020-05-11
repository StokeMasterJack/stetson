package ss.app1.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CardTest2 {

    @Test
    public void test1() {
        Card c1 = new Card(1,1);
        assertEquals(1,c1.getValue());
    }

}
