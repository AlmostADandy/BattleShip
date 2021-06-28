import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameWindow extends JFrame {
	private JMenuBar menuBar;
    private JMenu menuGame;
    private JMenuItem menuHelp;
    private JMenu menuNewGame;
    private JMenuItem menuStartDefaultPlacement;
    private JMenuItem menuStartAutoPlacement;
    private JMenuItem menuItemExit;
    
    GameWindow() {
        super("BattleShip");
        GameField field = new GameField();
        menuBar = new JMenuBar();
        menuGame = new JMenu("����");
        menuHelp = new JMenuItem("�������");
        
        
        menuNewGame = new JMenu("����� ����");
        
        menuStartDefaultPlacement = new JMenuItem("���������� �������");
        
        menuStartAutoPlacement = new JMenuItem("�������������� �����������");
        
        menuStartDefaultPlacement.addActionListener(new ActionListener() {       
            public void actionPerformed(ActionEvent e) {
            	field.callPlacement();
            }
        });
        
        menuStartAutoPlacement.addActionListener(new ActionListener() {       
            public void actionPerformed(ActionEvent e) {
            	field.callAutoPlacement();
            }
        });
        
        menuItemExit = new JMenuItem("�����");
        
        menuItemExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	AppExit ax = new AppExit();
                ax.sysExit();
            }
        });
        
        
        menuHelp.addActionListener(new ActionListener() {     
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                JOptionPane.showMessageDialog(frame, "���� ��� ���� - ���� ������ � ��� ����������. �� ��� ������������� �������: \n4 ������������ ����� �������� � 1 ������;\n"
                		+ "3 ������������ ������� �� 2 ������;\n"
                		+ "2 ������������ �������� �� 3 ������;\n"
                		+ "1 ��������������� ������, ���������� 4 ��������." + "\n������ �� ������ ������������� ���� � ������ ��������� ���� ������, \n�������� ��� ��������� ���������. "
                		+ "\n� ������������ �������� ���� ��� ��������� ��������� �� ��������� ��� �����������."
                		+ "\n���� ���� ������� � ���, ����� ������� ��� ������ ������� ���������. ����� ��������, \n����� ������ ����� ���������, ����� ���� �������� �� ��������������� ������."
                		+ "\n���� � ��� ���������� � ����� �������� ������������� �������, ��� ������������ �� ����. \n����� ��������� ����� �������� ����� �� ��� ���� �������.\n"
                		+ "����� �� ������� ������, ������� � ���������� ������, \n��� ����� ����������� �� ����. ����� ����� ��� ��������� ��� ����������."
                		+ " \n����������� ���������� ���, ��� ������ ��������� ��� ���� ���������.", "�������", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        menuBar.add(menuGame);
        menuBar.add(menuHelp);
        menuGame.add(menuNewGame);
        menuNewGame.add(menuStartDefaultPlacement);
        menuNewGame.add(menuStartAutoPlacement);
        menuGame.add(menuItemExit); 
        setJMenuBar(menuBar);
        
        Container container=getContentPane();
        container.add(field);
        setSize(field.getSize());
        setResizable(false);
        setLocationRelativeTo (null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
}

