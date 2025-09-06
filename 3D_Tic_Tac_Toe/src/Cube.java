import java.util.ArrayList;
import java.util.List;

public class Cube {

    List <char[][]> levels;
    char  firstLvl[][];
    char secondLvl[][];
    char thirdLvl[][];

    Cube(){
        firstLvl = new char[3][3];
        secondLvl = new char[3][3];
        thirdLvl = new char[3][3];

        levels = new ArrayList<>();
        levels.add(firstLvl);
        levels.add(secondLvl);
        levels.add(thirdLvl);
    }
    Cube(List <char[][]> levels){
        this.levels = levels;
    }

    char getCell (int level, int y, int x){
        return levels.get(level)[y][x];
    }

    void setCell (int level, int y, int x, char val){
        levels.get(level)[y][x] = val;
    }

    void removeCell (int level, int y, int x){
        levels.get(level)[y][x] = '\u0000';
    }

    Boolean probe(int level, int y, int x, char symb){
        this.setCell(level, y, x, symb);
        if(this.check()){
            removeCell(level, y, x);
            return true;
        }
        else{
            removeCell(level, y, x);
            return false;
        }
    }

    Boolean isFull(){
        if(thirdLvl[2][2]!='\u0000'){
            return true;
        }
        else{
            return false;
        }
    }


    //returns true, if the Cube is not won by anyone, therefore the cube has a valid combination
    Boolean check(){
        return (checkLvl() && checkCubeDiagonal() && checkVertical()&& checkRowDiagonal()&& checkColumnDiagonal());
    }

    //checks every cube-level in 2D fashion
    private Boolean checkLvl(){
        for (char[][] lvl : levels){
            String diagonal1 = "" + lvl[0][0] + lvl[1][1] + lvl[2][2];
            String diagonal2 = "" + lvl[0][2] + lvl[1][1] + lvl[2][0];
            //checks rows
            for (int i = 0; i < 3; i++) {
                if (lvl[i][0] == lvl[i][1] && lvl[i][1] == lvl[i][2]&&lvl[i][0]!='\u0000'){
                    return false;
                }
            }
            //checks columns
            for (int i = 0; i < 3; i++) {
                if (lvl[0][i] == lvl[1][i] && lvl[1][i] == lvl[2][i]&&lvl[0][i]!='\u0000'){
                    return false;
                }
            }
            if(diagonal1.equals("XXX")||diagonal1.equals("OOO")||diagonal2.equals("XXX")||diagonal2.equals("OOO")){
                return false;
            }
        }
        return true;
    }

    private Boolean checkVertical(){
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    char firstLvlchar = firstLvl[j][i];
                    char secondLvlchar = secondLvl[j][i];
                    char thirdLvlchar = thirdLvl[j][i];
                    if (firstLvlchar == secondLvlchar && firstLvlchar == thirdLvlchar&&(firstLvlchar!= '\u0000')){
                        return false;
                    }
                }
            }
        return true;
    }
    /*
    Checks the diagonals in the Rows and in the Columns
     */
    private boolean checkRowDiagonal(){
        char firstLvlchar = ' ';
        char secondLvlchar = ' ';
        char thirdLvlchar = ' ';
        for (int i = 0; i < 3; i+=2) {
            for (int j = 0; j < 3; j+=2) {
                if(firstLvl[i][j] != '\u0000'){
                    if(i==0){
                        if(j==0){
                            firstLvlchar = firstLvl[i][j];
                            secondLvlchar = secondLvl[i][j+1];
                            thirdLvlchar = thirdLvl[i][j+2];
                            if(firstLvlchar==secondLvlchar&&firstLvlchar==thirdLvlchar){
                                return false;
                            }
                        }

                        if(j==2){
                            firstLvlchar = firstLvl[i][j];
                            secondLvlchar = secondLvl[i][j-1];
                            thirdLvlchar = thirdLvl[i][j-2];
                        }
                        if(firstLvlchar==secondLvlchar&&firstLvlchar==thirdLvlchar){
                            return false;
                        }
                    }
                    else if(i==2){
                        if(j==0){
                            firstLvlchar = firstLvl[i][j];
                            secondLvlchar = secondLvl[i][j+1];
                            thirdLvlchar = thirdLvl[i][j+2];
                        }
                        if(firstLvlchar==secondLvlchar&&firstLvlchar==thirdLvlchar){
                            return false;
                        }
                        if(j==2){
                            firstLvlchar = firstLvl[i][j];
                            secondLvlchar = secondLvl[i][j-1];
                            thirdLvlchar = thirdLvl[i][j-2];
                        }
                        if(firstLvlchar==secondLvlchar&&firstLvlchar==thirdLvlchar){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private boolean checkColumnDiagonal(){
        char firstLvlchar = ' ';
        char secondLvlchar = ' ';
        char thirdLvlchar = ' ';
        for (int i = 0; i < 3; i+=2) {
            for (int j = 0; j < 3; j+=2) {
                if(firstLvl[i][j] != '\u0000'){
                    if(i==0){
                        if(j==0){
                            firstLvlchar = firstLvl[j][i];
                            secondLvlchar = secondLvl[j+1][i];
                            thirdLvlchar = thirdLvl[j+2][i];
                            if(firstLvlchar==secondLvlchar&&firstLvlchar==thirdLvlchar&&firstLvlchar!='\u0000'){
                                return false;
                            }
                        }

                        if(j==2){
                            firstLvlchar = firstLvl[j][i];
                            secondLvlchar = secondLvl[j-1][i];
                            thirdLvlchar = thirdLvl[j-2][i];
                        }
                        if(firstLvlchar==secondLvlchar&&firstLvlchar==thirdLvlchar&&firstLvlchar!='\u0000'){
                            return false;
                        }
                    }
                    else if(i==2){
                        if(j==0){
                            firstLvlchar = firstLvl[j][i];
                            secondLvlchar = secondLvl[j+1][i];
                            thirdLvlchar = thirdLvl[j+2][i];
                        }
                        if(firstLvlchar==secondLvlchar&&firstLvlchar==thirdLvlchar&&firstLvlchar!='\u0000'){
                            return false;
                        }
                        if(j==2){
                            firstLvlchar = firstLvl[j][i];
                            secondLvlchar = secondLvl[j-1][i];
                            thirdLvlchar = thirdLvl[j-2][i];
                        }
                        if(firstLvlchar==secondLvlchar&&firstLvlchar==thirdLvlchar&&firstLvlchar!='\u0000'){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    // checks the diagnal ways of the cube from one corner of the cube to the others through the midpoint [1][1]
    private Boolean checkCubeDiagonal(){
        char firstLvlchar = ' ';
        char secondLvlchar = ' ';
        char thirdLvlchar = ' ';
        for(int i=0;i<3;i+=2){
            for(int j=0;j<3;j+=2){
                if(firstLvl[i][j]!='\u0000'){
                    if(i==0){
                        if(j==0){
                            firstLvlchar = firstLvl[i][j];
                            secondLvlchar = secondLvl[i+1][j+1];
                            thirdLvlchar = thirdLvl[i+2][j+2];
                            if(firstLvlchar == secondLvlchar && firstLvlchar == thirdLvlchar){
                                return false;
                            }
                        }
                        if(j==2){
                            firstLvlchar=firstLvl[i][j];
                            secondLvlchar=secondLvl[i+1][j-1];
                            thirdLvlchar=thirdLvl[i+2][j-2];
                            if(firstLvlchar == secondLvlchar && firstLvlchar == thirdLvlchar){
                                return false;
                            }
                        }
                    }
                    else if(i==2){
                        if(j==0){
                            firstLvlchar=firstLvl[i][j];
                            secondLvlchar= secondLvl[i-1][j+1];
                            thirdLvlchar=thirdLvl[i-2][j+2];
                            if(firstLvlchar == secondLvlchar && firstLvlchar == thirdLvlchar){
                                return false;
                            }
                        }
                        if(j==2){
                            firstLvlchar=firstLvl[i][j];
                            secondLvlchar=secondLvl[i-1][j-1];
                            thirdLvlchar=thirdLvl[i-2][j-2];
                            if(firstLvlchar == secondLvlchar && firstLvlchar == thirdLvlchar){
                                return false;
                            }
                        }
                    }
                }
            }

        }
        return true;
    }


    String draw(){
        String print = "";
        for (char[][] lvl : levels){
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++){
                    if (j == 2) {
                        print += lvl[i][j];
                    }
                    else {
                        print += (lvl[i][j] + "-");
                    }
                }
                if (i == 2) {
                    print += "\n";
                }
                else {
                    print += "\n" + "| | |" + "\n";
                }
            }
        }
        print+="_____________"+"\n";
        return print;
    }
}
