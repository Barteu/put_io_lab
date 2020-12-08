package put.io.testing.audiobooks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import put.io.testing.junit.Calculator;

import static org.junit.jupiter.api.Assertions.*;

// 5.1 whitebox
// 5.2 4
class AudiobookPriceCalculatorTest {

    private AudiobookPriceCalculator calculator = null;
    private Customer customer = null;
    private Audiobook audiobook = null;

    @BeforeEach
    public void setUp() throws Exception  {
        calculator = new AudiobookPriceCalculator();
        audiobook = new Audiobook("Opowiadania Ryszarda",14.99);
    }

    @AfterEach
    public void tearDown() throws Exception{
        calculator=null;
        customer = null;
        audiobook = null;
    }

    @Test
    public void testSubscriber(){
        customer = new Customer("Zbigniew", Customer.LoyaltyLevel.STANDARD, true);
        assertEquals(0,calculator.calculate(customer,audiobook),0,"Bląd testu subskrybcji");
    }

    @Test
    public void testStandard(){
        customer = new Customer("Zbigniew", Customer.LoyaltyLevel.STANDARD, false);
        assertEquals(audiobook.getStartingPrice(),calculator.calculate(customer,audiobook),0,"Bląd testu standardowego uzytkownika");
    }

    @Test
    public void testSilver(){
        customer = new Customer("Zbigniew", Customer.LoyaltyLevel.SILVER, false);
        assertEquals(audiobook.getStartingPrice()*0.9,calculator.calculate(customer,audiobook),0,"Bląd testu srebrnego uzytkownika");
    }

    @Test
    public void testGold(){
        customer = new Customer("Zbigniew", Customer.LoyaltyLevel.GOLD, false);
        assertEquals(audiobook.getStartingPrice()*0.8,calculator.calculate(customer,audiobook),0,"Bląd testu zlotego uzytkownika");
    }



}