package realization.prediction.mining.decisiontree;

import java.util.ArrayList;

public class LearningSet {
    private ArrayList<AttributeSet> attributes;

    public LearningSet() {
        attributes = new ArrayList<AttributeSet>();
    }

    public ArrayList<AttributeSet> getAttributes() {
        return attributes;
    }

    public void setAttributes(ArrayList<AttributeSet> attributes) {
        this.attributes = attributes;
    }
}
