import javax.swing.JOptionPane;

public class EndGame {
	
	public EndGame() {
		
	}
	
	public void testEndGame(int endGame, int[][] arrPlayer, int[][] arrComputer) {
		if (endGame == 0){
	        int sumKilled = 330; 
	        int sumKilledOfPlayer = 0; 
	        int sumKilledOfComputer = 0; 
	            if (endGame == 0) {
	                for (int i = 0; i < 10; i++) {
	                    for (int j = 0; j < 10; j++) {
	                    if (arrPlayer[i][j] >= 15) sumKilledOfPlayer += arrPlayer[i][j];
	                    if (arrComputer[i][j] >= 15) sumKilledOfComputer += arrComputer[i][j];
	                }
	            }
	            if (sumKilledOfPlayer == sumKilled) {
	                JOptionPane.showMessageDialog(null,"�� ���������!",
	                        "�� ���������", JOptionPane.INFORMATION_MESSAGE);   
	                

	            } else if (sumKilledOfComputer == sumKilled) {
	                JOptionPane.showMessageDialog(null,
	                		"�� ��������!",
	                        "�� ��������", JOptionPane.INFORMATION_MESSAGE);
	            }
	            }
	        }
	}
}
