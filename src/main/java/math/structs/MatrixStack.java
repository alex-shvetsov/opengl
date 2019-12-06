package math.structs;

import math.Matrix4;

import java.util.Stack;

public class MatrixStack {
    private Stack<Matrix4> stack = new Stack<Matrix4>();;

    public void push() {
        if (stack.size() == 0) {
            stack.push(Matrix4.getIdentity());
        } else {
            stack.push(stack.peek());
        }
    }

    public Matrix4 pop() {
        return stack.pop();
    }

    public void mult(Matrix4 matrix) {
        stack.push(stack.pop().multiply(matrix));
    }

    public Matrix4 peek() {
        return stack.peek();
    }
}
