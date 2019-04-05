package com.test.jpa.entity;

public class TestClass {
    public static void main(String[] args) {
        TestClass testClass = new TestClass();
        testClass.method1();
    }

    private void method1() {
        try {
            method2();
        } catch (Exception e) {
            System.out.println("exception catched in method1");
        }
        method2();
    }

    private void method2() throws RuntimeException {
        throw new RuntimeException("Run Time Exception");
    }
}
