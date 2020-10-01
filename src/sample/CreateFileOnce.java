package sample;
//this file is used to create the txt files i changed the file name when i need to create a new file
import java.io.*;

public class CreateFileOnce {
    public static void main(String[] args) throws IOException {
        File fileHandle = new File("Contract.txt");
        FileWriter fw;
        PrintWriter pw ;
        fw = new FileWriter(fileHandle,false);
        pw = new PrintWriter(fw, true);
        //write to the file
        pw.println("");



    }
}
