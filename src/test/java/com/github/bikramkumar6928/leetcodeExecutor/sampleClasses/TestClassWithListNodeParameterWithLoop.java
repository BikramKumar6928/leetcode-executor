package com.github.bikramkumar6928.leetcodeExecutor.sampleClasses;

import com.github.bikramkumar6928.leetcodeExecutor.leetcodeObjects.ListNode;
import com.github.bikramkumar6928.leetcodeExecutor.utils.ValueHandlerUtils;

import java.util.Objects;

public class TestClassWithListNodeParameterWithLoop {
    public void testMethod(ListNode head){
        ListNode pointer = head;
        while(Objects.nonNull(pointer) && Objects.nonNull(pointer.next) ){
            pointer = pointer.next;
        }
        assert pointer != null;
        pointer.next = head;
        System.out.println(ValueHandlerUtils.getPrintableString(head));
    }
}
