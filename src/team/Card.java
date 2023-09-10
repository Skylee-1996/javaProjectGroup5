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
        String valueString = padValue(value); // 값을 출력 시 패딩을 추가
        

        return 
                "┌─────┐\n" +
                "│" + valueString + "  │\n" +
                "│  " + shape + "  │\n" +
                "│  " + valueString + "│\n" +
                "└─────┘";
    }

    // 값을 출력 시 패딩을 추가하는 함수
    private String padValue(String value) {
        if (value.equals("10")) {
            return  value + " "; // "10"의 경우 튀
        } else if (value.length() == 1) {
            return " " + value + " ";
        } else {
            return value;
        }
    }


}