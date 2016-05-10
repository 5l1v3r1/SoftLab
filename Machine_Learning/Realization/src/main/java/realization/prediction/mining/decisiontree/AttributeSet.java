package realization.prediction.mining.decisiontree;

import java.util.ArrayList;

public class AttributeSet {
    private String name;
    private ArrayList<DiscreteAttribute> attributes;
    private double entropy;
    private boolean isUsed;

    public AttributeSet() {
        attributes = new ArrayList<DiscreteAttribute>();
        entropy = -1;
        isUsed = false;
    }

    public ArrayList<DiscreteAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(ArrayList<DiscreteAttribute> attributes) {
        this.attributes = attributes;
    }

    public double getEntropy() {
        return entropy;
    }

    public void setEntropy(double entropy) {
        this.entropy = entropy;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
