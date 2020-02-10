package com.company;

import java.util.*;

public class Main {

    public Main() {
    }

    private List<String> alphabet = new ArrayList<>();
    private List<String> independenceRelation = new ArrayList<>();
    private List<String> word = new ArrayList<>();
    private List<String> dependencyRelation = new ArrayList<>();




    public void getData()
    {
        System.out.println("Give alphabet: ");
        Scanner in = new Scanner(System.in);
        alphabet = Arrays.asList(in.nextLine().split(" "));
        System.out.println("Give independence relation: ");
        independenceRelation = Arrays.asList(in.nextLine().split(" "));
        System.out.println("Give word: ");
        word = Arrays.asList(in.nextLine().split(""));

        for(int i=0; i< independenceRelation.size(); i++)
        {
            System.out.println(i + " " + independenceRelation.get(i));
        }
        System.out.println(word);
    }


    public void setData()
    {
        String[] alphabetArray = new String[] {"a", "b", "c", "d"};
        alphabet = Arrays.asList(alphabetArray);
        String[] independanceArray = new String[] {"a", "d", "d", "a", "b", "c", "c", "b"};
        independenceRelation = Arrays.asList(independanceArray);
        String w = "baadcb";
        word = Arrays.asList(w.split(""));
    }

    public void setData2()
    {
        String[] alphabetArray = new String[] {"a", "b", "c", "d", "e", "f"};
        alphabet = Arrays.asList(alphabetArray);
        String[] independanceArray = new String[] {"a", "d", "d", "a", "b", "e", "e", "b", "c", "d", "d", "c", "c", "f", "f", "c"};
        independenceRelation = Arrays.asList(independanceArray);
        String w = "acdcfbbe";
        word = Arrays.asList(w.split(""));
    }

    public boolean isIndependant(String a, String b)
    {
        for(int i=0; i<independenceRelation.size(); i=i+2)
        {
            if(independenceRelation.get(i).equals(a) && independenceRelation.get(i+1).equals(b))
            {
                return true;
            }
        }
        return false;
    }

    public void stateDependencyRelation()
    {
        for(int i =0; i<alphabet.size(); i++)
        {
            System.out.println(alphabet.get(i));
        }

        for(int i =0; i<alphabet.size()-1; i++)
        {
            for(int j = i+1; j<alphabet.size(); j++)
            {
                if(!isIndependant(alphabet.get(i), alphabet.get(j)))
                {
                    dependencyRelation.add(alphabet.get(i));
                    dependencyRelation.add(alphabet.get(j));
                    dependencyRelation.add(alphabet.get(j));
                    dependencyRelation.add(alphabet.get(i));
                }
            }
        }
        for(int i=0; i<dependencyRelation.size()-1; i=i+2)
        {
            System.out.println("("+ dependencyRelation.get(i)+", "+ dependencyRelation.get(i+1) +")" );
        }
    }

    public List<String> createAndSwap(List<String> word, int a, int b)
    {
        List<String> newWord = new ArrayList<>();
        for(String s : word)
        {
            newWord.add(s);
        }
        String temp = new String(newWord.get(a));
        newWord.set(a, word.get(b));
        newWord.set(b, temp);
        return newWord;
    }

    public boolean hasWord(List<List<String>> lists, List<String> word)
    {
        for(List<String> s: lists)
        {
            for(int i=0; i<s.size() && i<word.size(); i++) {

                if (s.get(i).equals(word.get(i))) {
                    if (i == s.size() - 1) {
                        return true;
                    }
                } else {
                    break;
                }
            }
        }
        return false;
    }

    public void getTrace()
    {
        List<List<String>> stack = new ArrayList<>();
        List<List<String>> processed = new ArrayList<>();
        List<List<String>> trace = new ArrayList<>();

        stack.add(word);
        trace.add(word);
        while(!stack.isEmpty())
        {
            List<String> currentWord =  stack.remove(0);

            for(int i=0; i<currentWord.size()-1; i++)
            {
                if(isIndependant(currentWord.get(i), currentWord.get(i+1)))
                {
                    List<String> newWord = createAndSwap(currentWord, i, i+1);


                    if(!hasWord(stack, newWord));
                    {
                        if(!hasWord(processed, newWord)) stack.add(newWord);
                    }
                    if(!hasWord(trace, newWord))
                    {
                        trace.add(newWord);
                    }
                }
                /* debug
                System.out.println("Stack: " + stack.toString());
                System.out.println("Processed: " + processed.toString());
                System.out.println("Trace: " +trace.toString());
                System.out.println( " ");

                 */
            }
            processed.add(currentWord);
        }
        System.out.println("Trace: ");
        for(List<String> words : trace)
        {
            for(String s : words)
            {
                System.out.print(s);
            }
            System.out.println("");
        }
    }

    public void getFNF()
    {
        Map<String, List<String>> stacks = new HashMap<>();
        for(int i=0; i<alphabet.size(); i++)
        {
            stacks.put(alphabet.get(i), new ArrayList<>());
        }
        for(int i=word.size()-1; i>=0; i--)
        {
            List<String> directStack = stacks.get(word.get(i));
            directStack.add(word.get(i));
            for(int j=0; j<alphabet.size(); j++)
            {
                if(!isIndependant(alphabet.get(j), word.get(i)))
                {
                    if(!alphabet.get(j).equals(word.get(i)))
                    {
                        stacks.get(alphabet.get(j)).add("*");
                    }
                }
            }
        }
        // stacks displaying
        /*
        for(String key : stacks.keySet())
        {
            System.out.print(key + ": ");
            List<String> directStack = stacks.get(key);
            for(String s : directStack)
            {
                System.out.print(s + " ");
            }
            System.out.println(" ");
        }

         */

        // getting FNF
        List<String> FNF = new ArrayList<>();
        while(!isStacksEmpty(stacks))
        {
            String s = "";
            String rep = null;
            for(String key : stacks.keySet())
            {
                List<String> directStack = stacks.get(key);
                if(directStack.size()==0) continue;
                String lastString = directStack.get(directStack.size()-1);
                if(!lastString.equals("*"))
                {
                    s = s+ lastString;
                    if(rep == null)
                    {
                        rep = lastString;
                    }
                    stacks.get(key).remove(directStack.size()-1);
                }
            }
            for(String key : stacks.keySet())
            {
                if(!isIndependant(key, rep))
                {
                    if(!key.equals(rep))
                    {
                        List<String> directStack = stacks.get(key);
                        if(directStack.size()==0) continue;
                        stacks.get(key).remove(directStack.size()-1);
                    }
                }
            }
            FNF.add(s);
        }
        for(int i=0; i<FNF.size(); i++)
        {
            if(FNF.get(i).length()==0)
            {
                FNF.remove(i);
            }
        }
        System.out.println("Postać Foaty: " +  FNF.toString());
    }

    public boolean isStacksEmpty(Map<String, List<String>> stacks)
    {
        for(String key : stacks.keySet())
        {
            if(!stacks.get(key).isEmpty()) return false;
        }
        return true;
    }


    public static void main(String[] args) {
        Main main = new Main();
        //main.getData();
        main.setData();
        //main.setData2();
        System.out.println("Relacja zależności");
        main.stateDependencyRelation();
        main.getTrace();
        main.getFNF();


    }
}
