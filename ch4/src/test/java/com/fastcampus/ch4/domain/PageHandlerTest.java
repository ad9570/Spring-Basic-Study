package com.fastcampus.ch4.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class PageHandlerTest {
    @Test
    public void printNaviTest1() {
        PageHandler ph = new PageHandler(273, 1);
        assertEquals(ph.getBeginPage(), Integer.valueOf(1));
        assertEquals(ph.getEndPage(), Integer.valueOf(10));
        ph.printNavi();
    }

    @Test
    public void printNaviTest2() {
        PageHandler ph = new PageHandler(273, 14);
        assertEquals(ph.getBeginPage(), Integer.valueOf(11));
        assertEquals(ph.getEndPage(), Integer.valueOf(20));
        ph.printNavi();
    }

    @Test
    public void printNaviTest3() {
        PageHandler ph = new PageHandler(273, 26);
        assertEquals(ph.getBeginPage(), Integer.valueOf(21));
        assertEquals(ph.getEndPage(), Integer.valueOf(28));
        ph.printNavi();
    }

    @Test
    public void printNaviTest4() {
        PageHandler ph = new PageHandler(273, 20);
        assertEquals(ph.getBeginPage(), Integer.valueOf(11));
        assertEquals(ph.getEndPage(), Integer.valueOf(20));
        ph.printNavi();
    }
}