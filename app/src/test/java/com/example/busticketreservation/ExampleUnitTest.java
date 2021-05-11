package com.example.busticketreservation;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
  private Book_Bus book_bus;

  @Before
  public void setup(){
    book_bus = new Book_Bus();
  }

  @Test
  public void testFinalAmount(){
    int finalAmount = book_bus.calculation(2,100);
    assertEquals(200, finalAmount);
  }





}