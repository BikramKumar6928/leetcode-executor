# LeetCode Executer

## About

Leetcode is a platform for practicing competitive coding problems. 

During the process of coding a solution, one might need to debug to see what is going on with the code.

This process can be tiresome because there is no direct executable that one can execute and debug the code.

The executor takes the code and executes is based on input provided.

Thus, you are saved from writing boiler plate code for executing the code.

## Usage:-

Create a class where the input is to be passed:- 

```java
class Solution {
    
    public void calculate(String value){
//        do stuff with the value
    }
    
}
```

And just write the main method with the class name and the input:-

```java
class Solution {
    public static void main(String[] args) {
        Invoker.invoke(Solution.class, "input");
    }
//    ... Rest of the class
}
```

Mark the debugger and debug the method. 

It just works.

