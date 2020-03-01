package App;

import App.Ventanas.Ventana;

import java.util.Stack;

import static Helpers.ValidatorArguments.referenceNotNull;
import static Helpers.ValidatorArguments.validate;

public class Gestor {
    final Stack<Ventana> stack;

    public Gestor() {
        this.stack = new Stack<>();
    }

    public void run(Ventana ventana) {
        referenceNotNull("ventana", ventana);
        validate("gestor ya esta en uso", stack.empty());

        stack.push(ventana);
        while (!stack.empty()) {
            Ventana result = stack.peek().run(this);
            if (result == null) stack.pop();
            else stack.push(result);
        }
    }
}
