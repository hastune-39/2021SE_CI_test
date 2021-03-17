import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class List {
    private String expression;
    private ArrayList<Variable> list = new ArrayList<Variable>();

    public List() {

    }

    public void read() {
        Scanner in = new Scanner(System.in);
        String temp = "";
        while (in.hasNextLine()) {
            temp += in.nextLine();
        }
        this.expression = temp;
    }

    public void split() {
        Pattern pattern = Pattern.compile(new Rule().rule_of_expression());
        Matcher m = pattern.matcher(expression);
        while (m.find()) {
            String symbolIn = m.group("symbol");
            String item = m.group("variable");
            if (!(item == (null))) {
                if (item.contains("x")) {
                    list.add(new Variable(item, symbolIn));
                }
            }
        }
    }

    public void merge() {
        ArrayList<Variable> temp = new ArrayList<Variable>();
        Variable tem;
        for (int i = 0; i < list.size(); i++) {
            tem = list.get(i);
            for (int j = i + 1; j < list.size(); j++) {
                if (tem.add(list.get(j)) == true) {
                    list.remove(j);
                }
            }

            if (tem.getCoeif().compareTo(new BigInteger("0")) != 0) {
                temp.add(tem);
            }
        }
        list = temp;
    }

    public void sort() {
        for (int i = 0; i < list.size(); i++) {
            for (int j = i; j < list.size(); j++) {
                Variable temp;
                if (list.get(i).getIndex().compareTo(list.get(j).getIndex()) < 0) {
                    temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        }
    }

    public void deriva() {
        ArrayList<Variable> temp = new ArrayList<Variable>();
        for (Variable a : list) {
            a.deriva();
            temp.add(a);
        }
        list = temp;
    }

    public void print() {
        int i = 0;
        for (i = 0; i < list.size(); i++) {
            list.get(i).after_deriva_print(i);///////////////////////
        }
        if (i == 0) {
            System.out.println(0);
        }
    }

}
