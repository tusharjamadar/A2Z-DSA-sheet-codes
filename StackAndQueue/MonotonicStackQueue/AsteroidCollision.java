package StackAndQueue.MonotonicStackQueue;
import java.util.*;

class AsteroidCollision {

    /*
     * 735. Asteroid Collision
     * 
     * 🧩 Problem:
     * Given an array 'asteroids' representing asteroids in a row:
     * - Positive value => moving right
     * - Negative value => moving left
     * - Absolute value = size of the asteroid
     * 
     * When two asteroids moving in opposite directions collide:
     * - Smaller one explodes
     * - If equal size, both explode
     * - Asteroids moving in the same direction never meet
     * 
     * Return the state of the asteroids after all collisions.
     * 
     * --------------------------------------------------------------
     * 💡 Intuition:
     * - Use a stack to simulate the process of asteroid movement.
     * - Right-moving asteroids (positive) are pushed onto the stack.
     * - When a left-moving asteroid (negative) comes:
     *     - It collides with the top of the stack if it's a right-moving asteroid.
     *     - Depending on their sizes:
     *         - The smaller one explodes.
     *         - If equal, both explode.
     *         - If incoming is smaller, it is destroyed.
     *     - Repeat until there's no more collision or the stack is empty.
     * - The stack will finally contain all surviving asteroids in order.
     * 
     * --------------------------------------------------------------
     * 🧪 Algorithm:
     * 1. Initialize a stack to keep surviving asteroids.
     * 2. For each asteroid:
     *    - If it's positive, push to the stack.
     *    - If it's negative:
     *        - While stack is not empty and top is positive (collision happens):
     *            - Compare sizes and decide which one(s) explode.
     *        - If it survives all collisions, push it to the stack.
     * 3. Convert stack to array and return it.
     * 
     * --------------------------------------------------------------
     * ⏱️ Time Complexity: O(n)
     * Each asteroid is pushed and popped at most once.
     * 
     * 🗃️ Space Complexity: O(n)
     * In worst case, all asteroids survive and are stored in the stack.
     */

    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();

        for (int asteroid : asteroids) {
            boolean destroyed = false;

            // Only collisions if asteroid is moving left and stack top is moving right
            while (!stack.isEmpty() && asteroid < 0 && stack.peek() > 0) {
                int top = stack.peek();
                if (top < -asteroid) {
                    stack.pop(); // top asteroid explodes
                } else if (top == -asteroid) {
                    stack.pop(); // both explode
                    destroyed = true;
                    break;
                } else {
                    destroyed = true; // incoming asteroid destroyed
                    break;
                }
            }

            // If asteroid survived all collisions, push it
            if (!destroyed) {
                stack.push(asteroid);
            }
        }

        // Convert stack to array
        int[] result = new int[stack.size()];
        for (int i = stack.size() - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }

        return result;
    }

    // 🔍 Main function to test the implementation
    public static void main(String[] args) {
        AsteroidCollision sol = new AsteroidCollision();

        int[] input1 = {5, 10, -5};
        System.out.println(Arrays.toString(sol.asteroidCollision(input1))); // Output: [5, 10]

        int[] input2 = {8, -8};
        System.out.println(Arrays.toString(sol.asteroidCollision(input2))); // Output: []

        int[] input3 = {10, 2, -5};
        System.out.println(Arrays.toString(sol.asteroidCollision(input3))); // Output: [10]

        int[] input4 = {-2, -1, 1, 2};
        System.out.println(Arrays.toString(sol.asteroidCollision(input4))); // Output: [-2, -1, 1, 2]
    }
}
