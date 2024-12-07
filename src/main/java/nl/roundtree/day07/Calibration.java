package nl.roundtree.day07;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Calibration {

    private final List<Equation> equations;
    private final Map<Integer, List<List<Operator>>> operatorSequencesByNumberOfOperators = new HashMap<>();

    public Calibration(final List<Equation> equations) {
        this.equations = equations;
    }

    public long sumOfValidCalculations(final Operator... operators) {
        return equations
                .stream()
                .filter(e -> {
                    final int numberOfOperators = e.equationNumbers.size() - 1;
                    final List<List<Operator>> sequences = operatorSequencesByNumberOfOperators
                            .computeIfAbsent(numberOfOperators, v -> determineOperatorSequences(numberOfOperators, operators));
                    return e.isValidCalculation(sequences);
                })
                .mapToLong(equation -> equation.testValue)
                .sum();
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

    public static class Equation {

        private final Long testValue;
        private final List<Integer> equationNumbers;

        public Equation(final Long testValue, final List<Integer> equationNumbers) {
            this.testValue = testValue;
            this.equationNumbers = equationNumbers;
        }

        boolean isValidCalculation(final List<List<Operator>> operatorSequences) {
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
    }

    public enum Operator {
        ADD,
        MULTIPLY,
        CONCAT
    }
}
