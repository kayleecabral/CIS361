/**
 * CIS 361: Models of Computation
 * Simulate the DFA that recognizes { w | w contains substring "cat" } 
 * The SimulateDFA class: SimulateDFA.java
 *
 * @author Haiping Xu
 * Created on February 8, 2016 at the CIS Department, UMass Dartmouth
 **/

import java.util.HashMap;

public class SimulateDFA {
    private HashMap<String, String> hm = new HashMap<String, String>();
    private String[] finalState = {"q3"};
    private String curState = "q0";
    
    public SimulateDFA() {
        initTransitionFunction();
    }
    
    //METHOD THAT WAS CHANGED TO FIT LANGUAGE 
    private void initTransitionFunction() {
        // "*" refers to other symbols
        hm.put("q0"+"0", "q1");
        hm.put("q0"+"1", "q1"); 
        hm.put("q1"+"0", "q2");
        hm.put("q1"+"1", "q2");
        hm.put("q2"+"0", "q3");
        hm.put("q2"+"1", "q4");
        hm.put("q3"+"0", "q3");
        hm.put("q3"+"1", "q3");
        hm.put("q4"+"0", "q4");
        hm.put("q4"+"1", "q4");
    }
    
    public boolean computeString(String inputString) {
        for (int i=0; i<inputString.length(); i++) {
            transit(inputString.charAt(i));
        }
            
        if (isFinal(curState)) return true;
        else return false;
    }

    private boolean isFinal(String state) {
        for (String fs : finalState)
            if (fs.equals(state)) return true;
        return false;
    }

    private void transit(char symbol) {
        String next = hm.get(curState+symbol);
        
        if (next != null) curState = next;
        else curState = hm.get(curState+"*");
    }
    
    public static void main(String[] args) {
        String inputString = "11100011";
        SimulateDFA dfa = new SimulateDFA();

        if (dfa.computeString(inputString)) 
            System.out.println("The DFA accepts input string \"" + inputString + "\"");
        else 
            System.out.println("The DFA rejects input string \"" + inputString + "\"");
    }
}
