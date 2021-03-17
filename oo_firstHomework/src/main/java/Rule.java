public class Rule {
    private String blank = "([ \\t]*)";
    private String number = "([+-]?\\d+)";
    private String index = "(\\*\\*" + blank + "(?<index>" + number + "))";//指数捕获
    private String function = "(x(" + blank + index + ")?)";
    private String constant = "(?<constant>" + number + ")";//常数捕获
    private String variable = "(?<coeffi>([+-]?|" + number + "))" +
            "(" + blank + "\\*)?" + blank + function;
    private String item = "((?<variable>" + variable + ")|" + constant + ")";
    private String expression = blank + "(?<symbol>[+-]?)" + blank + item;

    public String rule_of_expression() {
        return expression;
    }

    public String rule_of_item() {
        return item;
    }

    public String rule_of_variable() {
        return variable;
    }

    public String rule_of_function() {
        return function;
    }

    public String rule_of_constant() {
        return constant;
    }
}
