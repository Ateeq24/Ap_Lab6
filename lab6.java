/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ap_lab4;

import java.util.*;
import java.io.*;

/**
 *
 * @author hrehman.bscs13seecs
 */
public class ap_lab4 implements Runnable {

   static Thread t;
   static String threadName;
   static HashMap< String, List<String>> threadMap = new HashMap();

    ap_lab4(String name) {
        threadName = name;
        System.out.println("Creating " + threadName);
    }

    public void run() {
        System.out.println("Running " + threadName);
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            System.out.println("Thread " + threadName + " interrupted.");
        }
        System.out.println("Thread " + threadName + " exiting.");
    }
 public void start ()
   {
      System.out.println("Starting " +  threadName );
      if (t == null)
      {
         t = new Thread (this, threadName);
         t.run ();
      }
   }
    public static void addTree(File file, Collection< File> chain) {
        File[] children = file.listFiles();
        if (children != null) {
            for (File child : children) {
                addTree(child, chain);
            }
        } else 
        {
            chain.add(file);
            String filename=file.getName();
            List<String> filepaths=filepaths.add(file.getPath());
            threadMap.put(filename, List.add(filepath));
        }
    }

    public static void IndexSearch(HashMap filesMap) throws IOException {
        //for user input
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.println("Enter the keyword: ");
            System.out.println("Or Press q to exit: ");
            String s = in.nextLine();
            //exit method
            if (s.equals("q") || s.equals("Q")) {
                //System.out.println("Please see Summary.txt in the project folder for results.");
                System.out.println("Exiting the program...");
                return;
            }

            //to traverse the map
            Set set = filesMap.entrySet();
            Iterator i = set.iterator();

            while (i.hasNext()) {
                Map.Entry curr = (Map.Entry) i.next();
                String val = (String) curr.getValue();

                File file = new File(val);

                BufferedReader br = new BufferedReader(new FileReader(file));

                String line = null;
                while ((line = br.readLine()) != null) {
                    if (line.contains(s)) {
                        System.out.println("Content found in file: " + " " + val);
                    }
                }

            }
        }
    }

    public static void printIndex(HashMap filesMap) {

        Set set = filesMap.entrySet();
        Iterator s = set.iterator();
        System.out.println("Index mapping: ");
        while (s.hasNext()) {
            Map.Entry curr = (Map.Entry) s.next();
            System.out.print(curr.getKey() + ": ");
            System.out.println(curr.getValue());
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Collection< File> chain = new ArrayList< File>();
        //Start crawling from Desktop
        addTree(new File("C:\\Users\\hrehman.bscs13seecs\\Desktop\\1"), chain);

        try {

            FileWriter fWriter = null;
            fWriter = new FileWriter("summary.txt");
            BufferedWriter out = new BufferedWriter(fWriter);

   //creating HashMap for indexing
            HashMap< String, String> filesMap = new HashMap();

            Iterator itr = chain.iterator();
            while (itr.hasNext()) {
                //get file
                File tested = (File) itr.next();

                //get the file info123
                String Fname = tested.getName();
                String Fpath = tested.getPath();

                //write output to a file
                out.write(Fname + "  ----->  " + Fpath);
                out.newLine();

                //putting filesMap to hashmap
                filesMap.put(Fname, Fpath);

            }

            //print indexed filesMapMap
            printIndex(filesMap);
            //search the index
            IndexSearch(filesMap);

            out.close();
        } catch (IOException ex) {
            System.out.println("Error Occured!");
        }
    }

}

