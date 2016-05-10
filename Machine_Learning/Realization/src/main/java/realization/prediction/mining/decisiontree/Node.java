package realization.prediction.mining.decisiontree;

import java.util.ArrayList;

public class Node {
    public Node[] children;
    private Node parent;
    private ArrayList<Record> data;
    private double entropy;
    private boolean isUsed;
    private DiscreteAttribute testAttribute;

    public Node() {
        this.data = new ArrayList<Record>();
        setEntropy(0.0);
        setParent(null);
        setChildren(null);
        setUsed(false);
        setTestAttribute(new DiscreteAttribute("", 0));
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public ArrayList<Record> getData() {
        return data;
    }

    public void setData(ArrayList<Record> data) {
        this.data = data;
    }

    public double getEntropy() {
        return entropy;
    }

    public void setEntropy(double entropy) {
        this.entropy = entropy;
    }

    public Node[] getChildren() {
        return children;
    }

    public void setChildren(Node[] children) {
        this.children = children;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }

    public DiscreteAttribute getTestAttribute() {
        return testAttribute;
    }

    public void setTestAttribute(DiscreteAttribute testAttribute) {
        this.testAttribute = testAttribute;
    }
}
