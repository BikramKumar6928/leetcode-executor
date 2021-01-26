# LeetCode Executer

## About

Leetcode is a platform for practicing competitive coding problems. 

During the process of coding a solution, one might need to debug to see what is going on with the code.

This process can be tiresome because there is no direct executable that one can execute and debug the code.

The executor takes the code and executes is based on input provided.

Thus, you are saved from writing boiler plate code for executing the code.

## Usage:-

These steps are common for both usages:-

1. Create a maven project.
2. Add leetcode-executer as a dependency.

```xml
<dependency>
    <groupId>org.github.bikramkumar6928</groupId>
    <artifactId>leetcode-executer</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

### For standalone programs:-

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


### For Intellij plugin leetcode editor

Set CodeFileName as:-
```
$!velocityTool.camelCaseName(${question.titleSlug})
```
Add this code to the CodeTemplate:-

```
${question.content}
  
package leetcode.editor.en;
public class $!velocityTool.camelCaseName(${question.titleSlug}){
    public static void main(String[] args) {
         Solution solution = new $!velocityTool.camelCaseName(${question.titleSlug})().new Solution();
         Invoker.invoke(solution, "");
    }
    ${question.code}
}
```

**Please copy from the actual text written if the dollar sign is shown as "backslash dollar" `$`.**