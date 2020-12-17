package put.io.testing.mocks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//import org.junit.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import put.io.students.fancylibrary.database.FancyDatabase;
import put.io.students.fancylibrary.database.IFancyDatabase;
import put.io.students.fancylibrary.service.FancyService;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExpenseManagerTest {

    /*
    private ExpenseManager manager = null;


    @BeforeEach
    public void setUp() throws Exception  {
        manager = new ExpenseManager();

    }

    @AfterEach
    public void tearDown() throws Exception{
        manager = null;
    }
    */
        
    @Test
    public void testCalculateTotal() {

        ExpenseRepository mockExpenseRepository = mock(ExpenseRepository.class);

        // w poleceniu nie bylo informacji o mozliwosci utworzenia konstruktra dlatego zrobilem w ten sposob
        List<Expense> sampleExpense  = new ArrayList<Expense>();
        sampleExpense.add(new Expense());
        sampleExpense.add(new Expense());
        sampleExpense.add(new Expense());
        sampleExpense.get(0).setTitle("Pizza");
        sampleExpense.get(0).setCategory("Jedzenie");
        sampleExpense.get(0).setAmount(18);
        sampleExpense.get(1).setTitle("Cola");
        sampleExpense.get(1).setCategory("Napój");
        sampleExpense.get(1).setAmount(3);
        sampleExpense.get(2).setTitle("Długopis");
        sampleExpense.get(2).setCategory("Przybór");
        sampleExpense.get(2).setAmount(1);


        when(mockExpenseRepository.getExpenses()).thenReturn(sampleExpense);


        ExpenseManager mockManager = new ExpenseManager(mockExpenseRepository,new FancyService() );

        assertEquals(22,mockManager.calculateTotal(),"Zly wynik kalkulacji");

    }

    @Test
    public void testCalculateTotalForCategory() {

        ExpenseRepository mockExpenseRepository = mock(ExpenseRepository.class);

        // w poleceniu nie bylo informacji o mozliwosci utworzenia konstruktra dlatego zrobilem w ten sposob
        List<Expense> homeExpenses  = new ArrayList<Expense>();
        List<Expense> carExpenses  = new ArrayList<Expense>();
        homeExpenses.add(new Expense());
        homeExpenses.add(new Expense());
        homeExpenses.add(new Expense());
        carExpenses.add(new Expense());
        carExpenses.add(new Expense());
        homeExpenses.get(0).setTitle("Vacuum");
        homeExpenses.get(0).setCategory("Home");
        homeExpenses.get(0).setAmount(400);
        homeExpenses.get(1).setTitle("Thermostat");
        homeExpenses.get(1).setCategory("Home");
        homeExpenses.get(1).setAmount(89);
        homeExpenses.get(2).setTitle("Coffee grinder");
        homeExpenses.get(2).setCategory("Home");
        homeExpenses.get(2).setAmount(290);

        carExpenses.get(0).setTitle("Tyres");
        carExpenses.get(0).setCategory("Car");
        carExpenses.get(0).setAmount(1200);
        carExpenses.get(1).setTitle("Car Wiper");
        carExpenses.get(1).setCategory("Car");
        carExpenses.get(1).setAmount(100);


        mockExpenseRepository.addExpense(homeExpenses.get(0));
        mockExpenseRepository.addExpense(homeExpenses.get(1));
        mockExpenseRepository.addExpense(homeExpenses.get(2));


        when(mockExpenseRepository.getExpensesByCategory(anyString())).thenReturn(new ArrayList<Expense>());
        when(mockExpenseRepository.getExpensesByCategory("Home")).thenReturn(homeExpenses);
        when(mockExpenseRepository.getExpensesByCategory("Car")).thenReturn(carExpenses);
        // 5.1 kolejnosc ma znaczenie
        ExpenseManager mockManager = new ExpenseManager(mockExpenseRepository,new FancyService() );

        assertEquals(779,mockManager.calculateTotalForCategory("Home"),"Zly wynik kalkulacji Home");
        assertEquals(1300,mockManager.calculateTotalForCategory("Car"),"Zly wynik kalkulacji Car");
        assertEquals(0,mockManager.calculateTotalForCategory("Food"),"Zly wynik kalkulacji Food");
        assertEquals(0,mockManager.calculateTotalForCategory("Sport"),"Zly wynik kalkulacji Sport");

    }

    @Test
    public void testcalculateTotalInDollars ()  throws
            ConnectException
    {
        ExpenseRepository mockExpenseRepository = mock(ExpenseRepository.class);
        FancyService mockFancyService = mock(FancyService.class);



        ExpenseManager mockManager = new ExpenseManager(mockExpenseRepository,mockFancyService);

        List<Expense> sampleExpense  = new ArrayList<Expense>();
        sampleExpense.add(new Expense());
        sampleExpense.add(new Expense());
        sampleExpense.get(0).setTitle("Vacuum");
        sampleExpense.get(0).setCategory("Home");
        sampleExpense.get(0).setAmount(400);
        sampleExpense.get(1).setTitle("Car Wiper");
        sampleExpense.get(1).setCategory("Car");
        sampleExpense.get(1).setAmount(100);

        when(mockExpenseRepository.getExpenses()).thenReturn(sampleExpense);
        //when(mockFancyService.convert(anyDouble(),eq("PLN"),eq("USD"))).thenReturn(125.0);
        //when(mockFancyService.convert(anyDouble(),eq("PLN"),eq("USD"))).thenThrow(new ConnectException());
        when(mockFancyService.convert(anyDouble(),eq("PLN"),eq("USD"))).thenAnswer(
                new Answer() {
                    public Object answer(InvocationOnMock invocation) {
                        Object[] args = invocation.getArguments();
                        double i = (double) args[0];
                        return i*0.25;
                    }
                });

        assertEquals(125,mockManager.calculateTotalInDollars(),"Błąd w obliczeniu sumy w USD");


    }



}
