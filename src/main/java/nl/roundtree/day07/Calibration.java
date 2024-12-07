package nl.roundtree.day07;

import java.util.ArrayList;
import java.util.List;

public record Calibration(List<Equation> equations) {

    public long sumOfValidCalculations(final Operator... operators) {
        return equations
                .stream()
                .filter(e -> e.isValidCalculation(operators))
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

        boolean isValidCalculation(final Operator... operators) {
            final List<List<Operator>> operatorSequences = determineOperatorSequences(equationNumbers.size() - 1, operators);

            for (final List<Operator> sequence : operatorSequences) {
                long sum = equationNumbers.getFirst();
                for (int i = 1; i < equationNumbers.size(); i++) {
                    final int currentValue = equationNumbers.get(i);
                    final Operator operator = sequence.get(i - 1);

                    if (Operator.MULTIPLY.equals(operator)) {
                        sum *= currentValue;
                    } else if (Operator.ADD.equals(operator)) {
                        sum += currentValue;
                    } else {
                        sum = Long.parseLong(sum + "" + currentValue);
                    }
                }

                if (sum == testValue) {
                    return true;
                }
            }

            return false;
        }

        private List<List<Operator>> determineOperatorSequences(int numberOfOperators, final Operator... operatorsToUse) {
            List<List<Operator>> completeInstructionList = new ArrayList<>();

            for (final Operator operator : operatorsToUse) {
                completeInstructionList.add(new ArrayList<>(List.of(operator)));
            }

            for (int i = 0; i < numberOfOperators - 1; i++) {
                final List<List<Operator>> instructionListCurrentOperator = new ArrayList<>();
                for (Operator operator : operatorsToUse) {
                    final List<List<Operator>> copiedList = new ArrayList<>();
                    completeInstructionList.forEach(instructionList -> copiedList.add(new ArrayList<>(instructionList)));

                    copiedList.forEach(operatorList -> operatorList.addLast(operator));
                    instructionListCurrentOperator.addAll(copiedList);
                }

                completeInstructionList = instructionListCurrentOperator;
            }

            return completeInstructionList;
        }
    }

    public enum Operator {
        ADD,
        MULTIPLY,
        CONCAT
    }
}
