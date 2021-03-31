package com.github.bikramkumar6928.leetcodeExecutor.handlers.impl;

import com.github.bikramkumar6928.leetcodeExecutor.handlers.abstracts.ArrayValueHandler;
import com.github.bikramkumar6928.leetcodeExecutor.leetcodeObjects.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;


public class ListNodeValueHandler extends ArrayValueHandler {
    @Override
    protected Object getRandom() {
        return null;
    }

    @Override
    protected Object processMatch(String foundMatch) {
        String[] stringArray = getStringArrayFromMatch(foundMatch);
        return getListNodeFromStringArray(stringArray);
    }

    private ListNode getListNodeFromStringArray(String[] stringArray){
        ListNode head = null;
        ListNode pointer = null;
        for(String string : stringArray){
            int number = Integer.parseInt(string);
            ListNode newNode = new ListNode(number);
            if(Objects.isNull(head)){
                head = newNode;
            }
            else{
                pointer.next = newNode;
            }
            pointer = newNode;
        }
        return head;
    }

    @Override
    public String getPrintableObject(Object object) {
        assertThat(object, instanceOf(getClazz()));
        List<Integer> listNodeAsList = new ArrayList<>();
        ListNode listNode = (ListNode) object;
        while(Objects.nonNull(listNode)){
            listNodeAsList.add(listNode.val);
            listNode = listNode.next;
        }
        return listNodeAsList.toString();
    }

    @Override
    protected Class<?> getClazz() {
        return ListNode.class;
    }
}
