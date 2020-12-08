package put.io.testing.junit;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FailureOrErrorTest {

    private Calculator calculator = null;


    @BeforeEach
    public void setUp() throws Exception  {
        calculator = new Calculator();
    }

    @AfterEach
    public void tearDown() throws Exception{
        calculator=null;

    }

    @Test
    public void test1(){
        assertEquals(3, 1+1,0,"Bląd dodawania");
    }
    // 4.1 failure

    @Test
    public void test2(){
        throw new IllegalArgumentException("dowolny wyjątek");
    }
    // 4.1 error

    @Test
    public void test3(){
        try{
            assertEquals(5,1+1,0,"Bląd dodawania");
        }catch (Throwable t){
        System.out.println("obiekt " + t.getClass());
            t.printStackTrace();
        }
        //4.2  oczekuje na org.opentest4j.AssertionFailedError
        // na throwable ?
    }


}


