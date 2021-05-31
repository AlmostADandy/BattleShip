import javax.swing.JOptionPane;

public class Gameplay {
	
	public EndGame endOfTheGame = new EndGame();
    
    public int PlayerDeck_1; //типы кораблей игрока
    
    public int PlayerDeck_2;
    
    public int PlayerDeck_3;
    
    public int PlayerDeck_4;
    
    public int ComputerDeck_1; //типы кораблей компьютера
    
    public int ComputerDeck_2;
    
    public int ComputerDeck_3;
    
    public int ComputerDeck_4;
    
    public static int endGame = 1; 
    
    public int arrPlayer[][]= new int[10][10]; //поле игрока
    
    public int arrComputer[][]= new int[10][10]; //поле компьютера
    
    public boolean playerMove = true; //ход игрока
    
    public boolean computerMove = false; //ход компьютера
    
    Thread thread = new Thread();
    /**
     * Инициализирует поля класса
     */
    Gameplay() {
    	PlayerDeck_1 = 0;
    	PlayerDeck_2 = 0;
    	PlayerDeck_3 = 0;
    	PlayerDeck_4 = 0;
    	ComputerDeck_1 = 0;
    	ComputerDeck_2 = 0;
    	ComputerDeck_3 = 0;
    	ComputerDeck_4 = 0;
    }
    /**
     Запускает игровой раунд. Игрок всегда ходит первым.
     */
    public void start() { 
    	for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
            	arrPlayer[i][j] = 0;
            	arrComputer[i][j] = 0;
            }
        }
    	endGame = 0; 
        setDecksOfComputerShips();
    }   
    /**
     * Метод для расстановки всех кораблей для компьютера
     */
    private void setDecksOfComputerShips(){
    	generateDecks(arrComputer, 4);
        for (int i = 1; i <= 2; i++) {
        	generateDecks(arrComputer, 3);
        }
        for (int i = 1; i <= 3; i++) {
        	generateDecks(arrComputer, 2);
        }
        for (int i = 1;i<= 4;i++){
        	generateDecks(arrComputer,1);
        }
    }
    /**
     * Генерирует и случайно расставляет корабли компьютера.
     */
    private void generateDecks(int[][] arr, int numberOfDecks){
        int i = 0, j = 0;
        while (true) {
            boolean flag = false;
            i = (int) (Math.random() * 10);
            j = (int) (Math.random() * 10);
            int direction = (int) (Math.random() * 2); 

            if (testDecks(arr, i, j) == true) {
                if (direction == 0) { //вверх
                    if (testDecks(arr, i -(numberOfDecks - 1), j) == true)  
                        flag = true;
                }
                else if (direction == 1){ 
                    if (testDecks(arr, i, j + (numberOfDecks - 1)) == true)
                        flag = true;
                }
                
            }
            if (flag == true) {
              
            	arr[i][j] = numberOfDecks;
              
            	search(arr, i, j, -2);
                if (direction == 0) {
                    for (int k = numberOfDecks - 1; k >= 1; k--) {
                    	arr[i -k][j] = numberOfDecks;
                    	search(arr, i - k, j, -2);
                    }
                }
                else if (direction == 1){ 
                    for (int k = numberOfDecks - 1; k >= 1; k--) {
                    	arr[i][j + k] = numberOfDecks;
                    	search(arr, i, j + k, -2);
                    }
                }
                
                break;
            }
        }
        replace(arr); 
    }
    /**
     * Проверяет возможность установки палубы игроком в соответствующую ячейку.
     */
    public boolean setDeck(int i, int j, int numberDecks, boolean dir){
        boolean flag = false;
        if (testDecks(arrPlayer, i, j) == true) {
            if (dir == false){ 
                if (testDecks(arrPlayer, i, j + (numberDecks - 1)) == true)
                    flag = true;
            }
            else if (dir){ 
                if (testDecks(arrPlayer, i + (numberDecks - 1), j) == true)
                    flag = true;
            }
        }
        if (flag == true) {
        	arrPlayer[i][j] = numberDecks;
        	search(arrPlayer, i, j, -2);
            if (dir==false){ 
                for (int k = numberDecks - 1; k >= 1; k--) {
                	arrPlayer[i][j + k] = numberDecks;
                	search(arrPlayer, i, j + k, -2);
                }
            }
            else if (dir){ 
                for (int k = numberDecks - 1; k >= 1; k--) {
                	arrPlayer[i + k][j] = numberDecks;
                	search(arrPlayer, i + k, j, -2);
                }
            }
        }
        replace(arrPlayer); 
        return flag;
    }
    
   /**
    Перебирает все элементы массива, соответствующие ячейкам поля вокруг корабля.
    */
    private void search(int[][] arr, int i, int j, int val) {
 	   setEnv(arr, i - 1, j - 1, val);
 	   setEnv(arr, i - 1, j, val);
 	   setEnv(arr, i - 1, j + 1, val);
 	   setEnv(arr, i, j + 1, val);
 	   setEnv(arr, i, j - 1, val);
 	   setEnv(arr, i + 1, j + 1, val);
 	   setEnv(arr, i + 1, j, val);
 	   setEnv(arr, i + 1, j - 1, val);
    }

    /**
     * Пересчитывает элемент массива после нажатия пользователем на соответствующую ячейку поля.
     */
    public void attackByPlayer(int arr[][], int i, int j) {
        
    	arr[i][j] += 7;
        testKilled(arr, i, j);
        endGame = endOfTheGame.testEndGame(endGame, arrPlayer, arrComputer);
        attackByComputer(i, j);
    }
    
    /**
     * Проверяет, уничтожена ли палуба.
     */
    private void testKilled(int arr[][], int i, int j){
        if (arr[i][j] == 8) { 
        	arr[i][j] += 7; 
        	setEnvKilled(arr, i, j);
        }
        else if (arr[i][j] == 9){
        	checkKilled(arr, i, j, 2);
        }
        else if (arr[i][j] == 10){
        	checkKilled(arr, i, j, 3);
        }
        else if (arr[i][j] == 11){
        	checkKilled(arr, i, j, 4);
        }
    }
    
    /**
     * Проверяет, убит ли корабль.
     */
    private void checkKilled(int[][] arr, int i, int j, int numberOfDecks) {
        int numberOfInjuredDecks = 0;

        for (int x = i - (numberOfDecks - 1); x <= i + (numberOfDecks - 1); x++) {
            for (int y = j - (numberOfDecks - 1); y <= j + (numberOfDecks - 1); y++) {
                if (testArrayOut(x, y) && (arr[x][y] == numberOfDecks + 7)) {
                	numberOfInjuredDecks++;
                }
            }
        }
        if (numberOfInjuredDecks == numberOfDecks) {
            for (int x = i - (numberOfDecks - 1); x <= i + (numberOfDecks - 1); x++) {
                for (int y = j - (numberOfDecks - 1); y <= j + (numberOfDecks - 1); y++) {
                    if (testArrayOut(x, y) && (arr[x][y] == numberOfDecks + 7)) {
                    	arr[x][y] += 7;
                        setEnvKilled(arr, x, y);
                    }
                }
            }
        }
    }
    
    /**
     * Симулирует ход компютера.
     */
    public void attackByComputer(int i, int j) {
    	thread = new Thread(new Runnable() {
            public void run() {

                if (arrComputer[i][j] < 8) {
                	playerMove = false;
                	computerMove = true; 

                    while (computerMove == true) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        computerMove = computersTurn(arrPlayer);
                    }
                    playerMove = true;
                }
            }
        });
        thread.start();
    }

    /**
     * Уменьшает на 1 значение массива
     */
    public void subArray(int arr[][], int i, int j){
        if (testArrayOut(i, j)){
            if (arr[i][j] == -1 || arr[i][j] == 6){
            	arr[i][j]--;
            }
        }
    }
    /**
     * Меняет значения тех элементов массива, которые соответствуют ячейкам поля, расположенных по перимтру убитого корабля.
     */
    private void setEnvKilled(int[][] arr, int i, int j) {
    	subArray(arr, i - 1, j - 1); 
    	subArray(arr, i - 1, j); 
    	subArray(arr, i - 1, j + 1); 
    	subArray(arr, i, j + 1); 
    	subArray(arr, i + 1, j + 1); 
    	subArray(arr, i + 1, j); 
    	subArray(arr, i + 1, j - 1); 
    	subArray(arr, i, j - 1); 
    }
    /**
     * Симулирует выстрел компьютера.
     */
    boolean computersTurn(int arr[][]) {
        if (computerMove) {
        	
            boolean hit = false;
            
            boolean injured = false;
            
            boolean directionH = false;
            _breakPoint:
            for (int i = 0; i < 10; i++)
                for (int j = 0; j < 10; j++)
                    
                    if ((arr[i][j] >= 9) && (arr[i][j] <= 11)) {
                    	injured = true;
                       
                        if ((testArrayOut(i - 3, j) && arr[i - 3][j] >= 9 && (arr[i - 3][j] <= 11))
                                || (testArrayOut(i - 2, j) && arr[i - 2][j] >= 9 && (arr[i - 2][j] <= 11))
                                || (testArrayOut(i - 1, j) && arr[i - 1][j] >= 9 && (arr[i - 1][j] <= 11))
                                || (testArrayOut(i + 3, j) && arr[i + 3][j] >= 9 && (arr[i + 3][j] <= 11))
                                || (testArrayOut(i + 2, j) && arr[i + 2][j] >= 9 && (arr[i + 2][j] <= 11))
                                || (testArrayOut(i + 1, j) && arr[i + 1][j] >= 9 && (arr[i + 1][j] <= 11))) {
                        	directionH = true;
                        } else if ((testArrayOut(i, j + 3) && arr[i][j + 3] >= 9 && (arr[i][j + 3] <= 11))
                              
                                || (testArrayOut(i, j + 2) && arr[i][j + 2] >= 9 && (arr[i][j + 2] <= 11))
                                || (testArrayOut(i, j + 1) && arr[i][j + 1] >= 9 && (arr[i][j + 1] <= 11))
                                || (testArrayOut(i, j - 3) && arr[i][j - 3] >= 9 && (arr[i][j - 3] <= 11))
                                || (testArrayOut(i, j - 2) && arr[i][j - 2] >= 9 && (arr[i][j - 2] <= 11))
                                || (testArrayOut(i, j - 1) && arr[i][j - 1] >= 9 && (arr[i][j - 1] <= 11))) {
                        	directionH = false;
                        }
                       
                        else for (int x = 0; x < 50; x++) {
                            int napr = (int) (Math.random() * 4);
                            if (napr == 0 && testArrayOut(i - 1, j) && (arr[i - 1][j] <= 4) && (arr[i - 1][j] != -2)) {
                            	arr[i - 1][j] += 7;
                                
                            	testKilled(arr, i - 1, j);
                                if (arr[i - 1][j] >= 8) hit = true;
                                
                                break _breakPoint;
                            } else if (napr == 1 && testArrayOut(i + 1, j) && (arr[i + 1][j] <= 4) && (arr[i + 1][j] != -2)) {
                            	arr[i + 1][j] += 7;
                            	testKilled(arr, i + 1, j);
                                if (arr[i + 1][j] >= 8) hit = true;
                                break _breakPoint;
                            } else if (napr == 2 && testArrayOut(i, j - 1) && (arr[i][j - 1] <= 4) && (arr[i][j - 1] != -2)) {
                            	arr[i][j - 1] += 7;
                            	testKilled(arr, i, j - 1);
                                if (arr[i][j - 1] >= 8) hit = true;
                                break _breakPoint;
                            } else if (napr == 3 && testArrayOut(i, j + 1) && (arr[i][j + 1] <= 4) && (arr[i][j + 1] != -2)) {
                            	arr[i][j + 1] += 7;
                            	testKilled(arr, i, j + 1);
                                if (arr[i][j + 1] >= 8) hit = true;
                                break _breakPoint;
                            }
                        }
                       
                        if (directionH) { 
                            if (testArrayOut(i - 1, j) && (arr[i - 1][j] <= 4) && (arr[i - 1][j] != -2)) {
                            	arr[i - 1][j] += 7;
                            	testKilled(arr, i - 1, j);
                                if (arr[i - 1][j] >= 8) hit = true;
                                break _breakPoint;
                            } else if (testArrayOut(i + 1, j) && (arr[i + 1][j] <= 4) && (arr[i + 1][j] != -2)) {
                            	arr[i + 1][j] += 7;
                            	testKilled(arr, i + 1, j);
                                if (arr[i + 1][j] >= 8) hit = true;
                                break _breakPoint;
                            }
                        }
                        else if (testArrayOut(i, j - 1) && (arr[i][j - 1] <= 4) && (arr[i][j - 1] != -2)) {
                        	arr[i][j - 1] += 7;
                        	testKilled(arr, i, j - 1);
                            if (arr[i][j - 1] >= 8) hit = true;
                            break _breakPoint;
                        } else if (testArrayOut(i, j + 1) && (arr[i][j + 1] <= 4) && (arr[i][j + 1] != -2)) {
                        	arr[i][j + 1] += 7;
                        	testKilled(arr, i, j + 1);
                            if (arr[i][j + 1] >= 8) hit = true;
                            break _breakPoint;
                        }
                    }

            
            if (injured == false) {
                
                for (int l = 1; l <= 100; l++) {
                  
                    int i = (int) (Math.random() * 10);
                    int j = (int) (Math.random() * 10);
                    
                    if ((arr[i][j] <= 4) && (arr[i][j] != -2)) {
                      
                    	arr[i][j] += 7;
                      
                    	testKilled(arr, i, j);
                       
                        if (arr[i][j] >= 8)
                        	hit = true;
                        
                        injured = true;
                        
                        break;
                    }
                }
            }
            
            endGame = endOfTheGame.testEndGame(endGame, arrPlayer, arrComputer);
            
            return hit;
        }else return false;
    }
    /**
     * Проверяет, был ли выход за пределы массива.
     */
    private boolean testArrayOut(int i, int j) {
        if (((i >= 0) && (i <= 9)) && ((j >= 0) && (j <= 9))) {
            return true;
        } 
        else {
        	return false;
        }
    }

    /**
     Устанавливает окружение корабля.
     */
    private void setEnv(int[][] arr, int i, int j, int val) {
        if (testArrayOut(i, j) && arr[i][j] == 0) {
        	arr[i][j] = val;
        }
    }
    
    /**
     Проверяет возможность разместить палубу в ячейке.
     */
    private boolean testDecks(int [][]mas, int i, int j){
        if (testArrayOut(i, j) == false) {
        	return false;
        }
        if ((mas[i][j]==0) || (mas[i][j]==-2)) {
        	return true;
        }
        else return false;
    }

    /**
     Заменяет значения массива.
     */
    private void replace(int[][] mas) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (mas[i][j] == -2) {
                	mas[i][j] = -1;
                }
            }
        }
    }  
}