package hexlet.code.Schema;

public class NumberSchema implements Schema {
    private boolean isRequired = false;
    private boolean isPositive = false;
    private boolean isRange = false;
    private Integer min;
    private Integer max;

    public NumberSchema required() {
        this.isRequired = true;
        this.isPositive = false;
        this.isRange = false;
        return this;
    }

    public NumberSchema positive() {
        this.isRequired = false;
        this.isPositive = true;
        this.isRange = false;
        return this;
    }

    public NumberSchema range(Integer min, Integer max) {
        this.isRequired = false;
        this.isPositive = false;
        this.isRange = true;
        this.min = min;
        this.max = max;
        return this;
    }

    public boolean isValid(Object content) {
        if (!(content instanceof Integer) && isRequired) {
            return false;
        }
        Integer intContent = (Integer) content;
        if (isPositive) {
            return intContent > 0;
        }
        if (isRange) {
            return intContent >= min && intContent <= max;
        }
        return true;
    }
}
