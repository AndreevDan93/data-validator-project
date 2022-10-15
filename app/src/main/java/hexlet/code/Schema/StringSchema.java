package hexlet.code.Schema;

public class StringSchema implements Schema{
    private boolean isRequired = false;
    private boolean isMinLength = false;
    private boolean isContains = false;

    private Integer minN;
    private String contain;

    public void required() {
        this.isRequired = true;
        this.isMinLength = false;
        this.isContains = false;
    }

    public StringSchema minLength(int minN) {
        this.isRequired = false;
        this.isMinLength = true;
        this.isContains = false;
        this.minN = minN;
        return this;
    }

    public StringSchema contains(String contain) {
        this.isRequired = false;
        this.isMinLength = false;
        this.isContains = true;
        this.contain = contain;
        return this;
    }

    public boolean isValid(String content) {
        if (isRequired) {
            if (content == null) return false;
            return !content.isEmpty();
        }
        if (isMinLength) {
            return content.length() >= this.minN;
        }
        if (isContains) {
            return content.contains(this.contain);
        }
        return true;
    }


}
