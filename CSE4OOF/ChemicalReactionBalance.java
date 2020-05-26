import java.util.Scanner;
import java.lang.Math;

class ChemicalReactionBalance {
    public static void main(String args[]) {
        System.out.println("*** Mani Kumar Reddy Kancharla 13B81A0579 ***");
        System.out.println("Chemical Reaction Balance Check");

        String reactant1, reactant2, product1, product2;
        int reactant1Coefficient = 1, reactant2Coefficient = 1, product1Coefficient = 1, product2Coefficient = 1;
        char temp;
        Scanner keyboardScanner = new Scanner(System.in);

        System.out.print("Enter Reactant 1: ");
        reactant1 = keyboardScanner.next();
        System.out.print("Enter Reactant 2: ");
        reactant2 = keyboardScanner.next();
        System.out.print("Enter Product 1: ");
        product1 = keyboardScanner.next();
        System.out.print("Enter Product 2: ");
        product2 = keyboardScanner.next();

        if((temp = reactant1.charAt(0)) > '1' && temp <= '9' ) {
            reactant1Coefficient = temp - '0';
            reactant1 = reactant1.substring(1);
        }
        if((temp = reactant2.charAt(0)) > '1' && temp <= '9' ) {
            reactant2Coefficient = temp - '0';
            reactant2 = reactant2.substring(1);
        }
        if((temp = product1.charAt(0)) > '1' && temp <= '9' ) {
            product1Coefficient = temp - '0';
            product1 = product1.substring(1);
        }
        if((temp = product2.charAt(0)) > '1' && temp <= '9' ) {
            product2Coefficient = temp - '0';
            product2 = product2.substring(1);
        }

        int reactant1Length = reactant1.length();
        int product1Length = product1.length();
        String reactants = reactant1 + reactant2;
        String products = product1 + product2;
        boolean isBalanced = true;

        System.out.println();

        int currentElementAtoms = 1;
        String currentElement = "" + reactant1.charAt(0);

        for (int i = 1; i < reactants.length() + 1; i ++) {
            temp = i < reactants.length() ? reactants.charAt(i) : 0;
            if (temp >= 'a' && temp <= 'z') {
                currentElement += temp;
            }
            else if(temp >= '1' && temp <= '9') {
                currentElementAtoms = temp - '0';
            }
            else {
                int totalNumberOfAtoms = 0;
                if(i <= reactant1Length) {
                    int secondOccurrenceStartIndex = reactants.indexOf(currentElement, i);
                    if(secondOccurrenceStartIndex != -1) {
                        int secondOccurrenceCount = 1;
                        if(secondOccurrenceStartIndex + currentElement.length() < reactants.length() - 1 && (temp = reactants.charAt(secondOccurrenceStartIndex + currentElement.length())) > '0' && temp <= '9') {
                            secondOccurrenceCount = temp - '0';
                        }
                        totalNumberOfAtoms = reactant1Coefficient * currentElementAtoms + reactant2Coefficient * secondOccurrenceCount;
                        reactants = reactants.substring(0, secondOccurrenceStartIndex) + reactants.substring(secondOccurrenceStartIndex + currentElement.length() + (secondOccurrenceCount > 1 ? 0 : 1));
                    }
                    totalNumberOfAtoms = reactant1Coefficient * currentElementAtoms;
                }
                else {
                    totalNumberOfAtoms = reactant2Coefficient * currentElementAtoms;
                }

                String currentProductElement = "" + products.charAt(0);
                int currentProductElementAtoms = 1;
                int balanceCheck = 0;

                for (int j = 1; j < products.length() + 1; j ++) {
                    temp = j < products.length() ? products.charAt(j) : 0;
                    if (temp >= 'a' && temp <= 'z') {
                        currentProductElement += temp;
                    }
                    else if(temp >= '1' && temp <= '9') {
                        currentProductElementAtoms = temp - '0';
                    }
                    else {
                        if (currentElement.equals(currentProductElement)) {
                            balanceCheck += j <= product1Length ? product1Coefficient * currentProductElementAtoms : product2Coefficient * currentProductElementAtoms;
                        }
                        if(j != products.length()) {
                            currentProductElement = "" + products.charAt(j);
                            currentProductElementAtoms = 1;
                        }
                    }
                }

                if (totalNumberOfAtoms != balanceCheck) {
                    isBalanced = false;
                    System.out.println(currentElement + "\t" + totalNumberOfAtoms + " != " + balanceCheck);
                }

                if(i != reactants.length()) {
                    currentElementAtoms = 1;
                    currentElement = "" + reactants.charAt(i);
                }
            }
        }

        System.out.println();

        System.out.println(
            (reactant1Coefficient > 1 ? reactant1Coefficient : "") +
            reactant1 +
            " + " +
            (reactant1Coefficient > 1 ? reactant2Coefficient : "") +
            reactant2 +
            " -> " +
            (product1Coefficient > 1 ? product1Coefficient : "") +
            product1 +
            " + " +
            (product2Coefficient > 1 ? product2Coefficient : "") +
            product2
        );

        System.out.println("is " + (isBalanced ? "" : "NOT ") + "balanced");

        keyboardScanner.close();
    }
}