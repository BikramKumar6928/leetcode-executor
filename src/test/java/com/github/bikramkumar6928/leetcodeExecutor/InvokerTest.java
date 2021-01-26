package com.github.bikramkumar6928.leetcodeExecutor;

import com.github.bikramkumar6928.leetcodeExecutor.sampleClasses.TestClassWithIntegerAndIntArrayParam;
import com.github.bikramkumar6928.leetcodeExecutor.sampleClasses.TestClassWithPrivateMethod;
import com.github.bikramkumar6928.leetcodeExecutor.sampleClasses.TestClassWithStringParameter;
import org.junit.Test;

public class InvokerTest {
    @Test
    public void testInvokerWithOnePublicMethod(){
        Invoker.invoke(TestClassWithIntegerAndIntArrayParam.class, "1\n" +
                "[1,2,3]");
    }

    @Test
    public void testInvokerWithSpaceInInput(){
        Invoker.invoke(TestClassWithIntegerAndIntArrayParam.class, "   1 \n" +
                "[1,2,3]");
    }

    @Test
    public void testInvokerWithOnePublicMethodAndOnePrivateMethod(){
        Invoker.invoke(TestClassWithPrivateMethod.class, "");
    }

    @Test
    public void testInvokerWithStringInput(){
        Invoker.invoke(TestClassWithStringParameter.class, "\"sample stdring\"");
    }

    @Test
    public void testInvokerWithEmptyString(){
        Invoker.invoke(TestClassWithStringParameter.class, "");
        Invoker.invoke(TestClassWithIntegerAndIntArrayParam.class, "");
    }
}