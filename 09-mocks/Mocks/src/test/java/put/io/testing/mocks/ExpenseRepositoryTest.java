package put.io.testing.mocks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//import org.junit.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import put.io.students.fancylibrary.database.FancyDatabase;
import put.io.students.fancylibrary.database.IFancyDatabase;

import java.util.ArrayList;
import java.util.Collections;

public class ExpenseRepositoryTest {

    /*
    private ExpenseRepository repo = null;
    private MyDatabase database = null;

    @BeforeEach
    public void setUp() throws Exception  {
        database = new MyDatabase();
        repo = new ExpenseRepository(database);

    }

    @AfterEach
    public void tearDown() throws Exception{
        repo=null;

    }
    */

    @Test
    public void testloadExpenses() {
        IFancyDatabase mockFancyDatabase = mock(IFancyDatabase.class);
        when(mockFancyDatabase.queryAll()).thenReturn( Collections.emptyList());

        ExpenseRepository repo2 = new ExpenseRepository(mockFancyDatabase);
        repo2.loadExpenses();

//        verify(mockFancyDatabase).connect();
//        verify(mockFancyDatabase).queryAll();
//        verify(mockFancyDatabase).close();

        InOrder inOrder = inOrder(mockFancyDatabase);
        inOrder.verify(mockFancyDatabase).connect();
        inOrder.verify(mockFancyDatabase).queryAll();
        inOrder.verify(mockFancyDatabase).close();

        assertEquals(0,repo2.getExpenses().size(),"Nie jest pusto");
    }



    @Test
    public void testSaveExpenses() {
        IFancyDatabase mockFancyDatabase = mock(IFancyDatabase.class);
        when(mockFancyDatabase.queryAll()).thenReturn( Collections.emptyList());

        ExpenseRepository repo2 = new ExpenseRepository(mockFancyDatabase);
       // repo2.loadExpenses();

        repo2.addExpense(new Expense());
        repo2.addExpense(new Expense());
        repo2.addExpense(new Expense());
        repo2.addExpense(new Expense());
        repo2.addExpense(new Expense());

        //Expense wydatek2 = new Expense();

        repo2.saveExpenses();

        verify(mockFancyDatabase, times(5)).persist( any(Expense.class));


    }


}
