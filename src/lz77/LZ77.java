/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lz77;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author moh
 */
public class LZ77 {

//    public static int check(char c, String s , int j) {
//        boolean flag = false;
//        int index = 0;
//        for (int i = 0; i < j ; i++) {
//            if (c == s.charAt(i)) {
//                flag = true;
//                index = i;
//            }
//        }
//        if (flag == true) {
//            return index;
//        }
//        return -1;
//    }
    public static int existsBefore(String s, StringBuilder substring, int i) {

        String before = s.substring(0, i);
//        System.out.println(before);
//        System.out.println(substring+" : "+before.lastIndexOf(substring.toString()));
        if (before.contains(substring)) {
            return before.lastIndexOf(substring.toString());
        }

        return -1;
    }
//    public static int existsBefore (List <Tag> li,StringBuilder sb)
//    {
//        StringBuilder sb2 = new StringBuilder ();
//       for (Tag t : li){
//           sb2.append(t.next);
//       }
//       if (sb2.toString().contains(sb)){
//           return sb2.lastIndexOf(sb.toString());
//       }
//        return -1 ;
//    }

    public static boolean charBefore(List<Tag> li, char c) {
        boolean flag = false;
        for (Tag t : li) {
            if (t.next == c) {
                flag = true;
                break;
            }
        }
        if (flag == true) {
            return true;
        }
        return false;
    }

//    public static Tag get (String s , List <Tag> list,int j)
//    {
//        StringBuilder sb = new StringBuilder ();
//        sb.append(s.charAt(j));
//        System.out.println("exists before " + existsBefore(list, sb));
//        int index = j - existsBefore(list, sb) ; 
//        
//        for (int i = j+1 ; i < s.length() ; i++ ){
//            sb.append(s.charAt(i));
//            
//            if (existsBefore(list,sb) == -1){
//                break;  
//            } 
//        }
//       System.out.println("index : "+index + " String : " +sb);
//        return new Tag (index,sb.length()-1,sb.charAt(sb.length()-1));
//        
//    }
    public static int getIndex(String s, StringBuilder substring, int i) {

        String before = s.substring(0, i);
        int lastIndex = before.lastIndexOf(substring.substring(0, substring.length() - 1));
        return i - lastIndex;
    }
//    public static boolean containsIndex(int i,StringBuilder sb){
//        if (sb.toString().contains(String.valueOf(i))){
//            return true ; 
//        } 
//        return false ;
//        
//    }

    public static int log2(int value) {
        return Integer.SIZE - Integer.numberOfLeadingZeros(value);
    }

    public static int CompressedDataSize(List<Tag> list) {
        int biggestIndex = list.get(0).index;
        int biggestLength = list.get(0).length;
        for (Tag t : list) {
            if (t.index > biggestIndex) {
                biggestIndex = t.index;
            }
            if (t.length > biggestLength) {
                biggestLength = t.length;
            }
        }
        return log2(biggestLength) + log2(biggestIndex) + 8;
    }

    public static void main(String[] args) throws IOException {
        List<Tag> li = new LinkedList<Tag>();

        String s = "ABAABABAABBBBBBBBBBBBA";

//      StringBuilder sb = new StringBuilder ();
//      sb.append("AA");
//        System.out.println(existsBefore(s, sb,3));
        // 0,0,A 
        // 0,0,B
        // 2,1,A
        // 3,2,B
        // 
//      //   String s = "ABAABAB";
        int count = 0;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {

//         
            if (count > i) {
                continue;
            }
            if (charBefore(li, s.charAt(i))) {
//                System.out.println(i);

                sb.append(s.charAt(i));// A 
                count = i + 1; // 3 

                while (existsBefore(s, sb, i) != -1 && count < s.length()) {

                    sb.append(s.charAt(count)); // AA

                    count++; // 4 then break

                }

                int index = getIndex(s, sb, i);
               //System.out.println("SB "+sb+" sb length  "+(sb.length()-1));
                // System.out.println(count+" "+i);

               // System.out.println(index);
                Tag t = new Tag(index, sb.length() - 1, sb.charAt(sb.length() - 1));
                li.add(t);
                sb.setLength(0);

            } else {
                Tag t = new Tag(0, 0, s.charAt(i));
                li.add(t);
            }

        }

        for (Tag t : li) {
            System.out.println(t);
        }
        System.out.println("Compressed Data Size is  " + CompressedDataSize(li));

//       boolean flag = false ;
//          StringBuilder sb = new StringBuilder ();
//          
//        for (int i = 0 ; i < s.length() ; i++ ){
//            if (!charBefore(li, s.charAt(i))){
//                Tag tag = new Tag (0,0,s.charAt(i));
//                li.add(tag);
//            } else {
//               Tag tag = get(s, li, i);
//                li.add(tag);
//                
//            }
//        }
//     
//         for (Tag t : li){
//             System.out.println(t);
//         }
//         
//*********************************LAST EDITION***************************************   
//        for ( int i = 0 ; i < s.length() ; i++ ){
//            
//            // if it exists before 
//            
//           if (charBefore(li, s.charAt(i))){
////               System.out.println(i);
////               System.out.println(j);
//               //System.out.println("i : " +i);
//               StringBuilder sb2 = new StringBuilder ();
//             //  System.out.println(i);
////               sb2.append(s.charAt(i));
//               int index = i ;
//               
//               int  j = i ; 
//               
//               //System.out.println("j : "+j);
//               //System.out.println(i);
//               while (existsBefore(li, sb2)){
//                   
//                   sb2.append(s.charAt(j));
//               //    System.out.println(sb2);
//                    if ( j == s.length()-1 ){
//                       break;
//                   }
//                    
//                   j++;
//                   //System.out.println(j);
//                   
//                
//                 
//               }
//               System.out.println(sb2);
//              // System.out.println(j);
//               i = j ;
//               
//              
//               j = 0 ; 
//               char c = sb2.charAt(sb2.length()-1);
////               System.out.println(c);
//               Tag tag = new Tag (index,sb2.length()-1,c);
//               li.add(tag);
//                   continue;
//               
//           }
//            
//            
//            // if it doesn't exist before 
//            else {
////               System.out.println(i);
//                Tag tag = new Tag (0,0,s.charAt(i));   
//                li.add(tag);
//            } 
//           
////            System.out.println(i); 
//            
//        }
//         //*********************************LAST EDITION***************************************
//        StringBuilder sb = new StringBuilder("AA");
//        System.out.println(exist(sb, s, 2));
//        FileReader f = new FileReader();;
//        String s = f.readFile("C:\\Users\\moh\\Desktop\\LZ77.txt");
//        System.out.println(s);
//        LZ77 l  = new LZ77();
//         ArrayList<Tag> tag = new ArrayList<Tag>();
//         StringBuilder sb = new StringBuilder();
//        String s = "ABAABA";
//        for (int i = 0; i < s.length(); i++) {
//            if ( l.check(s.charAt(i), s,i) != -1 )
//            {
//                if ( i !=s.length()-1 &&  s.charAt(i) == s.charAt(i+1)){
//                    sb.append(s.charAt(i));
//                    sb.append(s.charAt(i+1));
//                } else {
//                    
//                }
//               
////                if ( i == s.length()-1 ){
////                    Tag obj = new Tag (l.check(s.charAt(i), s,i),sb.length(),sb.charAt(i));
////                }
//                
//            } else {
//                Tag obj = new Tag (0,0,s.charAt(i));
//                tag.add(obj);
//            }
//        }
////       for (int i = 0 ; i < tag.size() ;  i++ ){
////           System.out.println(tag.get(i).index + "  " + tag.get(i).length + " "+ tag.get(i).next);
////       }
//        System.out.println(sb);
        //**********************************************************************
//        List <Tag> li = new LinkedList<Tag>();
//        String s = "ABAABABAABBBBBBBBBBBBA";
//        StringBuilder sb = new StringBuilder ();
//        String substring =  s.substring(0,3);
//        System.out.println(substring.contains("AA"));
//        for (int i = 0 ; i < s.length() ; i++ ){
//            
//            if (check(s.charAt(i), s, i) != -1 ){
//               int index =s.lastIndexOf(s.charAt(i));
//               boolean existsBefore = true ;
//               while (existsBefore){
//                   sb.append(s.charAt(index));
//                   
//                   
//               }
////               Tag tag = new Tag (index,0 , 'T');
////               li.add(tag);
//                
//                
//            } else {
//                Tag tag = new Tag (0,0,s.charAt(i));
//                li.add(tag);
//            }
//        }
//        for (Tag t : li){
//            System.out.println(t);
//        }
        //***************************************************************************
//        List <Tag> li = new LinkedList<Tag>();
//        li.add(new Tag (0,0,'A'));
//        Tag tag = new Tag (0,0,'A');
////       char a = 'A';
//        System.out.println(li.lastIndexOf(tag));
//      for (Tag t : li){
//          if (t.next == 'A'){
//              System.out.println("Find at "+li.lastIndexOf(t));
//          }
//          
//      }
//      Tag t =;
//        System.out.println(li.contains(t.nex));
//        for (int i = 0 ; i < s.length() ; i++ ){
//            if (li.contains(s.charAt(i))){
//                
//            } else {
//                Tag tag = new Tag (0,0,s.charAt(i));
//                li.add(tag);
//            }
//        }
        //Tag test = new Tag (0,0,'A');
        //System.out.println(li.contains('A'));
        //System.out.println(li.lastIndexOf(test));
//        for (Tag t : li){
//            if (li.contains(new Tag (0,0,'A'))){
//                System.out.println("test");
//            }
//        }
        //System.out.println(li.contains());
        // System.out.println();
//        for (Tag t : li){
//            System.out.println(li.lastIndexOf(t.next));
//        }
    }

}
