# OOSD3-Project2
OOSD3, Second Year, Semester 2, Assignment 2

Using ProcessBuilder and either the ProcessBuilder constructor or its command() method, write a Java program that run a command (in Windows) or a shell script (Mac, Linux) and
ping a website of choice 10 times. Using BufferedReader, write the output (ping response) to the console.
Important commands for Windows and MacOS to invoke ping
For windows: processBuilder.command("cmd.exe", "/c", "<ping command> -n <number of times to ping><website>");
For Mac: processBuilder.command("bash", "-c", "<ping command> -c <number of times to ping> <website>");
The issue with the solution to Q1 is that the call to get the InputStream is blocking. Update the code so that a new Thread is started to handle the data from the InputStream.
Using the Java ExecutorService, Java Future and Callable are all required in your solution.
The main() method should:
1. Create a ExecutorService as follws:
ExecutorService servicePool = Executors.newSingleThreadExecutor();
2. Create a ProcessBuilder, pass the command and start it – same as Q1.
3. Instantiate a class which implements Callable and has a call method which reads the content in the InputStream and returns the content as a List of Strings – You need to
implement this class.
4. Call submit on servicePool which will return a Future object
5. Read the results from the Future object calling one of the Future.get() method and print it out to the console.
