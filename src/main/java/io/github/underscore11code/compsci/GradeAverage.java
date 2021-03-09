package io.github.underscore11code.compsci;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
* Name: E*** D*** (Redacted since I push this publicly to my github)
* Date: March 9th, 2021
* Program: GradeAverage
* Description: Calculates the average grade, given 4 courses + their grades
*/

public class GradeAverage implements Runnable{
  @Override
  public void run() {
    List<Integer> gradeList = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    for (int i = 0; i < 4; i++) {
      System.out.print("Enter the name of the course: ");
      scanner.nextLine();
      System.out.print("Enter the grade in the course: ");
      gradeList.add(scanner.nextInt());
      scanner.nextLine();
      System.out.println();
    }

    int total = 0;
    for (Integer integer : gradeList) {
      total += integer;
    }

    int average = total / gradeList.size();

    // Have to divide by 100 to convert "grade" / int to a 0.## decimal
    System.out.println("Your average is " + NumberFormat.getPercentInstance().format(average / 100f));
  }
}
