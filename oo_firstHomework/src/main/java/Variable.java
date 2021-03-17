import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Variable {
    private BigInteger coeif;
    private BigInteger index;

    public Variable(String item, String symbol) {
        Pattern pattern = Pattern.compile(new Rule().rule_of_item());
        Matcher m = pattern.matcher(item);
        if (m.find()) {
            String coeffiIn = m.group("coeffi");
            if (coeffiIn.equals("+") || coeffiIn.equals("")) {
                this.coeif = new BigInteger("+1");
            } else if (coeffiIn.equals("-")) {
                this.coeif = new BigInteger("-1");
            } else {
                this.coeif = new BigInteger(coeffiIn);
            }
            if (symbol.equals("-")) {
                this.coeif = this.getCoeif().multiply(new BigInteger("-1"));
            }
            String indexIn = m.group("index");
            if (!(indexIn == null)) {
                this.index = new BigInteger(indexIn);
            } else {
                this.index = new BigInteger("+1");
            }
        }
    }

    public BigInteger getCoeif() {
        return coeif;
    }

    public BigInteger getIndex() {
        return index;
    }

    public boolean add(Variable o) {
        if (this.index.compareTo(o.getIndex()) == 0) {
            coeif = coeif.add(o.getCoeif());
            return true;
        } else {
            return false;
        }
    }

    public void deriva() {
        if (this.index.compareTo(new BigInteger("0")) == 0) {
            this.coeif = new BigInteger("0");
            this.index = new BigInteger("0");
        } else {
            this.coeif = this.coeif.multiply(this.index);
            this.index = this.index.add(new BigInteger("-1"));
        }

    }

    public void after_deriva_print(int i) {
        String output = "";
        if (index.compareTo(new BigInteger("0")) == 0) {
            if (coeif.compareTo(new BigInteger("0")) > 0) {
                if (i != 0) {
                    output += "+" + coeif;
                } else {
                    output += coeif;
                }
            } else {
                output += coeif;
            }
            System.out.print(output);
            return;
        }
        if (coeif.equals(new BigInteger("1"))) {
            output += "";
        } else if (coeif.equals(new BigInteger("-1"))) {
            output += "-";
        } else if (coeif.compareTo(new BigInteger("0")) > 0) {
            if (i == 0) {
                output += coeif.toString() + "*";
            } else {
                output += "+" + coeif.toString() + "*";
            }
        } else {
            output += coeif.toString() + "*";
        }

        output += "x";
        if (index.equals(new BigInteger("1"))) {
            output += "";
        } else {
            output += "**" + index.toString();
        }
        System.out.print(output);
    }
}
