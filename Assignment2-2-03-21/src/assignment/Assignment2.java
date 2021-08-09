
//Student Name: Maija Soares
//Student Number: C19478224
//Module: OOSD
//Due date: 2 March 2021

package assignment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Assignment2 {

    public static void main(String[] args) throws IOException{
        ExecutorService servicePool = Executors.newSingleThreadExecutor();
        ArrayList<String> arraylist = new ArrayList<String>();//Arraylist is intialised
        ProcessBuilder pB = new ProcessBuilder();//
        pB.command("cmd.exe", "/c", "ping -n 10 google.com");//ping google.com 10 times   
        Process p = pB.start();//command is given now Process p can start
        Callable<ArrayList<String>> call = new Read(p);//call is initialised and passes p as a reference
        Future<ArrayList<String>> future = servicePool.submit(call);//get object future
        try {
        	arraylist = future.get();//arraylist is equal to future.get()
            System.out.println("processing ping...");
            for (String s : arraylist) {//this for loop prints out all the values and results withing arraylist
                System.out.println(s);//print out string s
            }
        } catch (InterruptedException | ExecutionException e) {//catch exception
            e.printStackTrace();
        } finally {
        	servicePool.shutdown();//servicePool is shut down when finished running
        }
    }
    public static class Read implements Callable {
	    Process p = null;//p is null or empty
	    public Read(Process p) {
            this.p = p;
        }
        @Override
        public ArrayList<String> call() throws Exception{
        	String x = null;//x is null or empty
        	ArrayList<String> strings = new ArrayList<String>();//ArrayList is initialised
        	BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));//BufferedReader is intialised
        	while((x = br.readLine()) != null) {//while x is not empty and null
        		strings.add(x);//add string to the arraylist
        	}
        	return strings;//return the arraylist
        }
    }
}
