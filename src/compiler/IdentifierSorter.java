/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author lucas
 */
public class IdentifierSorter {

    ArrayList<Identifier> identifiers = new ArrayList<>();

    public IdentifierSorter(ArrayList<Identifier> identifiers) {
        this.identifiers = identifiers;
    }

    public ArrayList<Identifier> getIdentifierSorter() {
        Collections.sort(identifiers);
        return identifiers;
    }

}
