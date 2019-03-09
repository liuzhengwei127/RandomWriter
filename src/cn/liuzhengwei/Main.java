package cn.liuzhengwei;

import java.lang.*;
import java.util.*;
import java.io.*;

public class Main {
    static HashMap<String, Vector> model = new HashMap<>();
    static int order;

    public static void main(String[] args) {

        readFile();
    }

    public static void readFile(){

        while (true)
        {
            Scanner input=new Scanner(System.in);
            String file_name;

            System.out.println("Please enter filename containing source text:");
            file_name = input.next();

            while (true)
            {
                System.out.println("Please enter the order (from 1 to 10):");
                order = input.nextInt();
                if (order >= 1 && order <= 10)
                    break;
            }


            try (FileReader reader = new FileReader(file_name);
                 BufferedReader br = new BufferedReader(reader)
            ) {
                String line;
                char[] seed = new char[order];

                line = br.readLine();
                for (int i = 0; i < order; i++)
                    seed[i] = line.charAt(i);

                for (int i=order;i<line.length();i++){
                    if (!model.containsKey(seed))
                    {
                        Vector tmp = new Vector(1);
                        tmp.setElementAt(line.charAt(i),i);
                        String s = new String(seed);
                        model.put(s, tmp);
                    }
                    else
                        model.get(seed).addElement(line.charAt(i));
                    for (int j=0;j<order-1;j++)
                        seed[j] = seed[j+1];

                    seed[order - 1] = line.charAt(i);
                }

                while((line = br.readLine()) != null){
                    for (int i=0;i<line.length();i++){
                        if (!model.containsKey(seed))
                        {
                            Vector tmp = new Vector(1);
                            tmp.setElementAt(line.charAt(i),i);
                            String s = new String(seed);
                            model.put(s, tmp);
                        }
                        else
                            model.get(seed).addElement(line.charAt(i));
                        for (int j=0;j<order-1;j++)
                            seed[j] = seed[j+1];

                        seed[order - 1] = line.charAt(i);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
