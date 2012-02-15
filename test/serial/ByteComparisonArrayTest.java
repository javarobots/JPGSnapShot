/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serial;

import util.data.ByteComparisonArray;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Parham
 */
public class ByteComparisonArrayTest {

    private ByteComparisonArray instance;

    public ByteComparisonArrayTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        instance = new ByteComparisonArray(5);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addByte method, of class ByteComparisonArray.
     */
    @Test
    public void testAddByte() {
        System.out.println("addByte");
        for (byte b = 5; b > 0; b--){
            instance.addByte(b);
        }
        assertArrayEquals(new byte[]{5,4,3,2,1}, instance.getCompareArray());
    }

    /**
     * Test of addByte method, of class ByteComparisonArray.
     */
    @Test
    public void testCcompareArray() {
        System.out.println("compareArray");
        for (byte b = 5; b > 0; b--){
            instance.addByte(b);
        }
        assertTrue(instance.compareArray(new byte[]{5,4,3,2,1}));
        assertFalse(instance.compareArray(new byte[]{5,4,3,3,1}));
        assertFalse(instance.compareArray(new byte[]{5,4,3,1}));
    }
}
