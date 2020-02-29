/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler;

import java.util.ArrayList;

/**
 *
 * @author lucas
 */
public class Identifier implements Comparable<Identifier> {

    private String identifier;
    private int totalRepetitions;
    private ArrayList<CountRepLine> repLines;

    public Identifier(String identifier, int totalRepetitions, ArrayList<CountRepLine> repLines) {
        this.identifier = identifier;
        this.totalRepetitions = totalRepetitions;
        this.repLines = repLines;
    }

    public Identifier() {
       this.repLines = new ArrayList<>();
       totalRepetitions = 1;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
    public void addRepLine(CountRepLine crl){
        this.repLines.add(crl);
        
    }
    
   public String printArrayLines(){
       String output = " ";
       int index = 0;
        for(CountRepLine cl : this.getRepLines()){
            if(index == 0){
                output = " " + cl.getLine() + "(" + cl.getRep()+")";
            }
            else{
                output += ", " + cl.getLine() + "(" + cl.getRep()+")";
            }
            index++;
            
        }
        return output;
    }

    public int getTotalRepetitions() {
        return totalRepetitions;
    }

    public void setTotalRepetitions(int totalRepetitions) {
        this.totalRepetitions = totalRepetitions;
    }
    
    public void addTotalRepetitions(){
        this.totalRepetitions++;
    }

    public ArrayList<CountRepLine> getRepLines() {
        return repLines;
    }

    public void setRepLines(ArrayList<CountRepLine> repLines) {
        this.repLines = repLines;
    }
    
 

    @Override
    public int compareTo(Identifier id) {
        
        return this.getIdentifier().toLowerCase().compareTo(id.getIdentifier().toLowerCase());
        
    }
    
    

}
