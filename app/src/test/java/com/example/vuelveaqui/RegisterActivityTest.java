package com.example.vuelveaqui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import com.example.vuelveaqui.util.Validations;

import org.junit.Test;

public class RegisterActivityTest {

    Validations validations = new Validations();

    @Test
    public void passEqualsTest(){
        assertEquals(validations.passEquals("12345","23456"),false);
    }

    @Test
    public void passMore5CaracterTest(){
        assertEquals(validations.passMore5Caracter("123"),false);
    }
}
