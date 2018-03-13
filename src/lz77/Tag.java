package lz77;

import java.util.LinkedList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author moh
 */
public class Tag {

    public int index;
    public int length;
    public char next;

    public Tag(int index, int length, char next) {
        this.index = index;
        this.length = length;
        this.next = next;
    }
    
    public int countTags (List <Tag> list){
        int i = 0 ; 
        for (Tag t : list){
            i++;
        }
        return i ;
    }
    

    @Override
    public String toString() {
        return "<"+index + ","+length+","+next+">" ;
    }
    
    
}
