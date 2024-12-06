package com.bv.moneta_task.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NumberModifierServiceImpl implements NumberModifierService {

    @Override
    public long getResult(long input) {

        //get input number in a reversed list. Kept for future methods.
        List<Integer> listOfNumbers = parseLongToReversedList(input);
        moveDigitsLessThanFourByOnePlace(listOfNumbers);
        doubleEightsAndNines(listOfNumbers);
        //as 8 and 9 were multiplied, there cannot be digit 7
        listOfNumbers.removeIf(digit -> digit == 7);
        Collections.reverse(listOfNumbers);

        long updatedNumber = getNumberByJoiningDigits(listOfNumbers);
        int numberOfEvenDigits = countEvenDigits(updatedNumber);

        if(numberOfEvenDigits == 0) {
            throw new RuntimeException();
        }

        return updatedNumber / numberOfEvenDigits;
    }




    private List<Integer> parseLongToReversedList(long input) {
        List<Integer> listOfDigits = new ArrayList<>();
        while (input > 0) {
            listOfDigits.add((int) (input % 10));
            input = input / 10;
        }
        return listOfDigits;
    }

    private void moveDigitsLessThanFourByOnePlace(List<Integer> listOfNumbers) {
        for (int i = 1; i < listOfNumbers.size() - 1; i++) {
            if (listOfNumbers.get(i) < 4) {
                int numOne = listOfNumbers.get(i - 1);
                int numTwo = listOfNumbers.get(i);

                listOfNumbers.set(i, numOne);
                listOfNumbers.set(i - 1, numTwo);
            }
        }
    }

    private void doubleEightsAndNines (List<Integer> listOfNumbers) {
        for (int i = 0; i < listOfNumbers.size() - 1; i++) {
            if (listOfNumbers.get(i) == 8 || listOfNumbers.get(i) == 9) {
                listOfNumbers.set(i, listOfNumbers.get(i) * 2);
            }
        }
    }

    private long getNumberByJoiningDigits(List<Integer> listOfNumbers) {
        String joinedNumber = listOfNumbers.stream().map(String::valueOf).collect(Collectors.joining());
        return Integer.parseInt(joinedNumber);
    }

    private int countEvenDigits(long updatedNumber) {
        int numberOfEvenDigits = 0;
        while (updatedNumber > 0) {
            long digit = updatedNumber % 10;
            if (digit % 2 == 0) {
                numberOfEvenDigits++;
            }
            updatedNumber = updatedNumber / 10;
        }
        return numberOfEvenDigits;
    }
}
