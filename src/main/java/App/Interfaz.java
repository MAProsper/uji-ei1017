package App;


import com.google.common.base.Strings;
import com.google.common.collect.Range;

import java.util.*;

import static Helpers.ValidatorArguments.referenceNotNull;
import static Helpers.ValidatorArguments.validate;

public class Interfaz {
    final static Scanner scanner = new Scanner(System.in);
    final String title;
    final String info;
    final List<String> list;
    final List<String> form;
    final List<String> buttons;
    final Range<Integer> range;
    final Map<String, String> formData;

    public Interfaz(final String title, final String info, final List<String> list, final List<String> form, final List<String> buttons) {
        this.title = referenceNotNull("header", title);
        this.info = referenceNotNull("info", info);
        this.list = referenceNotNull("list", list);
        this.form = referenceNotNull("form", form);
        this.buttons = validate("buttons no puede estar vacia", referenceNotNull("buttons", buttons), buttons.size() != 0);

        range = Range.closedOpen(0, form.size() + buttons.size());
        formData = new HashMap<>();
        clearForm();
    }

    void clearForm() {
        for (String key : form) formData.put(key, "");
    }

    void renderWindow() {
        int index = 0;
        final StringBuilder window = new StringBuilder();

        window.append("\n\n");
        window.append(title).append("\n");
        window.append(Strings.repeat("-", title.length())).append("\n");
        window.append("\n");
        window.append(info).append("\n");
        window.append("\n");
        for (String line : list) window.append(line).append("\n");
        window.append("\n");
        for (String key : form) window.append(String.format("(%d) %s: %s\n", index++, key, formData.get(key)));
        window.append("\n");
        for (String button : buttons) window.append(String.format("(%d) %s\n", index++, button));
        window.append("\n\n");

        System.out.print(window);
    }

    int selectOption() {
        int option = -1;

        while (!range.contains(option)) {
            renderWindow();
            System.out.format("Seleciona opcion %s: ", range);
            try {
                option = scanner.nextInt();
            } catch (InputMismatchException e) {
                option = -1;
            }
            scanner.nextLine();
        }

        return option;
    }

    public String getTitle() {
        return title;
    }

    public String getInfo() {
        return info;
    }

    public List<String> getList() {
        return Collections.unmodifiableList(list);
    }

    public Map<String, String> getForm() {
        return Collections.unmodifiableMap(formData);
    }

    void setForm(int index) {
        final String key = form.get(index);

        renderWindow();
        System.out.format("%s: ", key);
        formData.put(key, scanner.nextLine());
    }

    public List<String> getButtons() {
        return Collections.unmodifiableList(buttons);
    }

    public String nextButton() {
        clearForm();
        int option = -1;
        final int form = this.form.size();

        while (option < form) {
            option = selectOption();
            if (option < form) setForm(option);
        }

        return buttons.get(option - form);
    }
}
