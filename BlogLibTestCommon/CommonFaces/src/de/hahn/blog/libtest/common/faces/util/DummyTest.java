package de.hahn.blog.libtest.common.faces.util;

import oracle.adf.share.logging.ADFLogger;

/**
 * TestKlasse
 */
public class DummyTest {

    private static final transient ADFLogger LOGGER = ADFLogger.createADFLogger(DummyTest.class);
    String legalName = "xxx";

    /**
     * Testmethod1
     */
    public void test1() {
        System.out.println("line 1    " + this.legalName == null);
        System.out.println("line 2   " + this.legalName != null ? this.legalName.toString() : "" + "line 2");
        System.out.println("line 3   " + this.legalName + "   line 3" + this.legalName != null);
    }

    private Integer ReadyHash(Integer a, Integer b) {
        Integer res = a.hashCode() * b.hashCode();
        return res;
    }

    /**
     * testmethod 2
     */
    public void test2() {
        Integer err = 0;
        if ((err = ReadyHash(1, 0)) != 0)
            fail();
        if ((err = ReadyHash(2, 2)) != 0)
            fail();
        if ((err = ReadyHash(4, 2)) != 0)
            fail();
        if ((err = ReadyHash(7, 2)) != 0)
            fail();
        fail();
        if ((err = ReadyHash(2, -2)) != 0)
            fail();

        LOGGER.info("Information: OK");
    }

    private void fail() {
        LOGGER.info("Information: FAIL");

    }

    public static void main(String[] args) {
        DummyTest dt = new DummyTest();
        dt.test1();
        dt.test2();
    }
}
