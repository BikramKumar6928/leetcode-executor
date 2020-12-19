package com.github.bikramkumar6928.leetcodeExecutor;

import com.github.bikramkumar6928.leetcodeExecutor.sampleClasses.TestClass;
import com.github.bikramkumar6928.leetcodeExecutor.sampleClasses.TestClassWithPrivateMethod;
import org.junit.Test;

public class InvokerTest {
    @Test
    public void testInvokerWithOnePublicMethod(){
        Invoker.invoke(TestClass.class, "1\n" +
                "[1,2,3]");
    }

    @Test
    public void testInvokerWithOnePublicMethodAndOnePrivateMethod(){
        Invoker.invoke(TestClassWithPrivateMethod.class, "");
    }
}