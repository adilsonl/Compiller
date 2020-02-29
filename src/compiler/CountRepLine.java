/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler;

/**
 *
 * @author lucas
 */
public class CountRepLine {

    private int rep;
    private int line;

    public CountRepLine(int rep, int line) {
        this.rep = rep;
        this.line = line;
    }

    public int getRep() {
        return rep;
    }

    public void setRep(int rep) {
        this.rep = rep;
    }

    public void addRep(int rep) {
        this.rep++;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

}
