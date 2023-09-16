package team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



class Card {
    private int index;
    private String shape;
    private String value;

    public Card(int index, String shape, String value) {
        this.index = index;
        this.shape = shape;
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        String valueString = padValue(value); 
        

        return 
                "┌─────┐\n" +
                "│" + valueString + "  │\n" +
                "│  " + shape + "  │\n" +
                "│  " + valueString + "│\n" +
                "└─────┘";
    }

    // 값을 출력 시 패딩을 추가하는 함수
   
    	public String padValue(String value) {
    	    if (value.equals("10")) {
    	        return value + " "; 
    	    } else {
    	        return " " + value + " ";
    	    }
    	}
    public String getBack() {
        return 
                "┌─────┐\n" +
                "│Ezen │\n" +  
                "│     │\n" +
                "│Ezen │\n" +
                "└─────┘";
    }

	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	


}