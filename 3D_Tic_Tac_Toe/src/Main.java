import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        File outputFile = new File("output.txt");
        FileWriter fileWriter = new FileWriter(outputFile);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        int counter = 1;
        Cube cube = new Cube();
        int x = 0, y = 0, z = 0;
        boolean changeLvl =false;
        boolean backtracking = false;
        while (!cube.isFull()) {
            while (z < 3) {
                while (y < 3) {
                    while (x < 3) {
                        if (cube.getCell(z, y, x) == 'O' && x == 0 && y == 0 && z == 0) {
                            System.out.print("No Valid Combination Found");
                            return;
                        }
                        //Checks if the current looked at cell is empty
                        if (cube.getCell(z, y, x) == '\u0000') {
                            //Places an X if it doesn't lead to a win
                            if (cube.probe(z, y, x, 'X')) {
                                cube.setCell(z, y, x, 'X');
                                backtracking =false;
                                printWriter.print(counter+"\n"+cube.draw());
                                counter++;
                            }
                            //Places an X if it doesn't lead to a win
                            else if (cube.probe(z, y, x, 'O')) {
                                cube.setCell(z, y, x, 'O');
                                backtracking =false;
                                printWriter.print(counter+"\n"+cube.draw());
                                counter++;
                            }
                            else {
                                backtracking = true;
                            }
                        }
                        //If the current cell is an X it is replaced by an O
                        else if (cube.getCell(z, y, x) == 'X') {
                            cube.removeCell(z, y, x);
                            cube.setCell(z, y, x, 'O');
                            backtracking =false;
                            printWriter.print(counter+"\n"+cube.draw());
                            counter++;
                        }
                        //Goes back to previous Cell if nothing can be placed.
                        if(backtracking){
                            //Otherwise it's an O and its deleted
                            if (cube.getCell(z, y, x) == 'O') {
                                cube.removeCell(z, y, x);
                                printWriter.print(counter+"\n"+cube.draw());
                                counter++;
                            }
                            //If the currentt cell is the very first one of a level it goes to the previous level to the last cell
                            if (y == 0 && x == 0) {
                                z -= 2;
                                y = 2;
                                x = 2;
                                changeLvl = true;
                                break;
                            }
                            //If the current cell is on the left border it jumps up a row
                            else if (x == 0) {
                                y -= 2;
                                x = 2;
                                break;
                            }
                            //the event that backtracking needs to access a previous cell
                            else {
                                x--;
                                continue;
                            }
                        }
                        if (x == 2) {
                            x = 0;
                            break;
                        } else {
                            x++;
                        }

                    }
                    if(changeLvl) {
                        changeLvl = false;
                        break;
                    }
                    if (y == 2) {
                        y = 0;
                        break;
                    } else {
                        y++;
                    }
                }
                z++;
            }

        }
        cube.draw();


    }

}



      /*
        Cube cube = new Cube();
        cube.setCell(2,1,1,'X');
        cube.setCell(1,1,1,'X');
        cube.setCell(0,1,1,'X');
        if (cube.check()){
            cube.draw();
        }
        else {
            cube.draw();
            System.out.println("No Valid Combination found!");
        }
*/