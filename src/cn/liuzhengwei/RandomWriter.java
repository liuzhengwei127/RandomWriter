package cn.liuzhengwei;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class RandomWriter {
    static HashMap<String, Vector> model = new HashMap<>();
    static int order;

    public static void randomWrite() {
        readFile();
        write();
    }


    public static void readFile(){

        Scanner input = new Scanner(System.in);
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


        try (FileReader reader = new FileReader("C:\\Users\\75667\\IdeaProjects\\RandomWriter\\src\\cn\\liuzhengwei\\"+file_name);
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
                    Vector tmp = new Vector(0);
                    tmp.addElement(line.charAt(i));
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
                        Vector tmp = new Vector(0);
                        tmp.addElement(line.charAt(i));
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

    public static void write(){
        int max_frequency = 0;
        int count = order;
        String key="";
        Random rand = new Random();

        Vector vec;
        Iterator iter = model.entrySet().iterator();
        while(iter.hasNext()) {
            Map.Entry entry = (Map.Entry)iter.next();
            vec = (Vector)entry.getValue();
            if (vec.size() > max_frequency){
                max_frequency = vec.size();
                key = (String)entry.getKey();
            }
        }

        System.out.print(key);

        while (count <= 2000)
        {
            String tmp = key;
            if (model.get(key).size() == 0)
                break;

            int random = rand.nextInt(model.get(key).size());

            char followed = (char)model.get(key).elementAt(random);
            System.out.print(followed);

            tmp = tmp.substring(1);
            tmp = tmp+ followed;
            key = tmp;
            count++;
        }
    }
}
