/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.question2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * read student information from unsort.txt and display them
 * then sort the list according to GPA, First name and ID
 * 
 * @author michaelzhang
 * @version 1.0
 */
public class StudentInfoList {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        StudentInfoList studentInfoList = new StudentInfoList();
        List<StudentInfo> sl = new ArrayList();
        String fileName = "test/question2/unsort.txt";
        
        try{
          String filepath = StudentInfoList.class.getResource("/").getFile() +fileName;
          File file = new File(filepath);
          BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
          String str = null;
          int id_1 = 0;
          float gpa_1 = 0;
          bufferedReader.readLine();//ignor first line
          System.out.println("Input: ");
          
          //read data then split them
          while((str=bufferedReader.readLine())!=null){           
                 String[] array;
                 array = str.split("\\s+");
                 System.out.println(array[0] + " " + array[1]+ " " + array[2]);
                 try{
                     id_1 = Integer.parseInt(array[0]);
                     gpa_1 = Float.parseFloat(array[2]);
                 }catch(NumberFormatException ne){//if wrong format, set id and gpa 0
                     id_1 = 0;
                     gpa_1 = 0;
                 }
                 StudentInfo si = studentInfoList.new StudentInfo(id_1,array[1],gpa_1);
                 sl.add(si);
                 
          }
          bufferedReader.close();
          
          sl.sort(studentInfoList.new StudentInfoComparator()); 
          
          System.out.println("Output: ");
          Iterator it = sl.iterator();
          while(it.hasNext()){
              StudentInfo si1 = (StudentInfo)(it.next());
              System.out.println(si1.getFirstName());//+ " " + si1.getID()+" "+si1.getGpa());
          }
          
        }catch(IOException e) {
          System.out.println(e.getMessage());
            
        }
        
    }
    /**
     * StudentInfo class hold student id, first name and GPA record
     */
    class StudentInfo {
        private int id;
        private String firstName;
        private float gpa;
        
        StudentInfo (int id, String firstName, float gpa){
            this.id = id;
            this.firstName = firstName;
            this.gpa = gpa;
        }
        public int getID()
        {
            return id;
        }
        
        public String getFirstName()
        {
            return firstName;
        }
        
        public float getGpa()
        {
            return gpa;
        }
        
    }
    /**
     * implement Comparator
     * arrange the list according to GPA in descending order,
     * if same GPA, arrange according to in alphabetical order,
     * if same first name, arrange according to ID in ascending order.
     */
    class StudentInfoComparator implements Comparator <StudentInfo>
    {
        @Override
        public int compare(StudentInfo si1, StudentInfo si2)
        {
            if(si1.getGpa() > si2.getGpa()) return -1;
            else if(si1.getGpa() == si2.getGpa()){
               if(si1.getFirstName().compareTo(si2.getFirstName()) < 0)
                   return -1;
               else if(si1.getFirstName().compareTo(si2.getFirstName()) == 0){
                   if(si1.getID() < si2.getID()) return -1;
                   else return 1;
               }
               else return 1;
            }
            else return 1;
        }
        
        
    }
    
}
