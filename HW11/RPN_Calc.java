/**
 *
 * @author Robert McVey
 * this file establishes a class that functions as a RPN calculator
 */

import java.util.Scanner;

public class RPN_Calc {
    public static void main(String[] args) {
        DLinked_StackGeneric<Integer> stack = new DLinked_StackGeneric<>(); // Assuming you've imported the custom Stack class
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.next();

            if (input.matches("-?\\d+")) { // Operand (integer)
                int operand = Integer.parseInt(input);
                stack.push(operand);
            } else if ("?".equals(input)) { // Print stack
                System.out.println(stack.toString());
            } else if ("^".equals(input)) { // Pop and print top element
                if (!stack.isEmpty()) {
                    System.out.println(stack.pop());
                } else {
                    System.err.println("# Stack is empty.");
                }
            } else if ("$".equals(input)) { // Clear stack
                stack.clear();
            } else if ("!".equals(input)) { // Exit
                System.out.println("Goodbye");
                break;
            } else if ("+".equals(input) || "-".equals(input) || "*".equals(input) ||
                       "/".equals(input) || "%".equals(input)) {
                // Operator: Perform operation and push result
                if (stack.size() >= 2) {
                    int b = stack.pop();
                    int a = stack.pop();
                    int result;
                    switch (input) {
                        case "+":
                            result = a + b;
                            break;
                        case "-":
                            result = a - b;
                            break;
                        case "*":
                            result = a * b;
                            break;
                        case "/":
                            if (b != 0) {
                                result = a / b;
                            } else {
                                System.err.println("# Division by zero.");
                                result = 0; // Handle division by zero
                            }
                            break;
                        case "%":
                            if (b != 0) {
                                result = a % b;
                            } else {
                                System.err.println("# Modulo by zero.");
                                result = 0; // Handle modulo by zero
                            }
                            break;
                        default:
                            System.err.println("# Invalid operator: " + input);
                            result = 0; // Handle invalid operator
                    }
                    stack.push(result);
                    System.out.println(result);
                } else {
                    System.err.println("# Insufficient operands on the stack.");
                }
            } else {
                System.err.println("# Invalid input: " + input);
            }
        }

        scanner.close();
    }
}
