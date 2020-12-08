package put.io.testing.junit;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;


class CalculatorTest {

    private Calculator calculator = null;

    //3.1 po zmianie na BeforeAll przestanie dzialac poniewaz wszystkie testy bylyby przeprowadzane na jednym obiekcie ktory moze ulegac zmianą
    @BeforeEach
    public void setUp() throws Exception  {
        calculator = new Calculator();

    }

    @AfterEach
    public void tearDown() throws Exception{
        calculator=null;

    }

    @Test
    public void testTryAddPositiveNumbers(){
        assertEquals(5,calculator.add(3,2),0,"Bląd dodawania");
    }

    @Test
    public void testTryAddNegativeNumbers(){
        assertEquals(-7,calculator.add(-2,-5),0,"Bląd dodawania");
    }

    @Test
    public void testTryAddOppositeNumbers(){
        assertEquals(0,calculator.add(9,-9),0,"Bląd dodawania");
    }


    @Test
    public void testTryMultiplyPositiveNumbers(){
        assertEquals(6,calculator.multiply(3,2),0,"Bląd mnożenia");
    }

    @Test
    public void testTryMultiplyNegativeNumbers(){
        assertEquals(10,calculator.multiply(-2,-5),0,"Bląd mnożenia");
    }

    @Test
    public void testTryMultiplyOppositeNumbers(){
        assertEquals(-81,calculator.multiply(9,-9),0,"Bląd mnożenia");
    }

    @Test
    public void testAddPositiveNumbers(){
        Assertions.assertThrows( IllegalArgumentException.class,() -> {calculator.addPositiveNumbers(-10,4);} );
    }


}
