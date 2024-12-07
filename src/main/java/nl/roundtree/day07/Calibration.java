package nl.roundtree.day07;

import java.util.ArrayList;
import java.util.List;

public record Calibration(List<Equation> equations, int minEquationLength, int maxEquationLength) {

    public long sumOfValidCalculations() {
        return equations
                .stream()
                .filter(Equation::isValidCalculation)
                .mapToLong(equation -> equation.testValue)
                .sum();
    }

    public static class Equation {
        
        private final Long testValue;
        private final List<Integer> equationNumbers;

        public Equation(final Long testValue, final List<Integer> equationNumbers) {
            this.testValue = testValue;
            this.equationNumbers = equationNumbers;
        }
        
        boolean isValidCalculation() {
            final List<List<Operator>> operatorSequences = determineOperatorSequences(equationNumbers.size() - 1);

            for (final List<Operator> sequence : operatorSequences) {
                long sum = equationNumbers.getFirst();
                for (int i = 1; i < equationNumbers.size(); i++) {
                    final int currentValue = equationNumbers.get(i);
                    final Operator operator = sequence.get(i - 1);
                    
                    sum = Operator.MULTIPLY.equals(operator)
                            ? sum * currentValue
                            : sum + currentValue;
                }
                
                if (sum == testValue) {
                    return true;
                }
            }

            return false;
        }

        private List<List<Operator>> determineOperatorSequences(int numberOfOperators) {
            final List<List<Operator>> operatorInstructionList = new ArrayList<>();

            operatorInstructionList.add(new ArrayList<>(List.of(Operator.MULTIPLY)));
            operatorInstructionList.add(new ArrayList<>(List.of(Operator.ADD)));

            for (int i = 0; i < numberOfOperators - 1; i++) {
                final List<List<Operator>> copiedList = new ArrayList<>();
                operatorInstructionList.forEach(instructionList -> copiedList.add(new ArrayList<>(instructionList)));

                operatorInstructionList.forEach(operatorList -> operatorList.addLast(Operator.MULTIPLY));
                copiedList.forEach(operatorList -> operatorList.addLast(Operator.ADD));
                operatorInstructionList.addAll(copiedList);
            }

            return operatorInstructionList;
        }
    }

    private enum Operator {
        ADD,
        MULTIPLY
    }
}



