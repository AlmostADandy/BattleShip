import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameWindow extends JFrame {
	private JMenuBar menuBar;
    private JMenu menuGame;
    private JMenuItem menuHelp;
    private JMenuItem menuStartGame;
    private JMenuItem menuItemExit;
    
    GameWindow() {
        super("BattleShip");
        GameField field = new GameField();
        menuBar = new JMenuBar();
        menuGame = new JMenu("Игра");
        menuHelp = new JMenuItem("Правила");
        
        
        menuStartGame = new JMenuItem("Новая игра");
        
        menuStartGame.addActionListener(new ActionListener() {       
            public void actionPerformed(ActionEvent e) {
            	field.callPlacement();
            }
        });
        
        menuItemExit = new JMenuItem("Выход");
        
        menuItemExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	AppExit ax = new AppExit();
                ax.sysExit();
            }
        });
        
        
        menuHelp.addActionListener(new ActionListener() {     
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                JOptionPane.showMessageDialog(frame, "Есть два поля - поле игрока и его противника. На них располагаются корабли: \n4 однопалубных судна размером в 1 клетку;\n"
                		+ "3 двухпалубных эсминца по 2 клетки;\n"
                		+ "2 трехпалубных крейсера по 3 клетки;\n"
                		+ "1 четырехпалубный линкор, занимающий 4 клеточки." + "\nФигуры не должны соприкасаться друг с другом сторонами либо углами, \nчастично или полностью совпадать. "
                		+ "\nВ классическом варианте игры все кораблики размещают по вертикали или горизонтали."
                		+ "\nЦель игры состоит в том, чтобы утопить все боевые единицы соперника. Игрок выбирает, \nкакую клетку хочет проверить, после чего нажимает на соответствующую клетку."
                		+ "\nЕсли у его противника в такой клеточке располагается корабль, это отображается на поле. \nПосле попадания игрок получает право на еще один выстрел.\n"
                		+ "Когда он назовет клетку, которая у противника пустая, \nэто также отобразится на поле. После этого ход переходит его противнику."
                		+ " \nПобедителем становится тот, кто первым обнаружил все суда соперника.", "Правила", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        menuBar.add(menuGame);
        menuBar.add(menuHelp);
        menuGame.add(menuStartGame);
        menuGame.add(menuItemExit); 
        setJMenuBar(menuBar);
        
        Container container=getContentPane();
        container.add(field);
        setSize(field.getSize());
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
}

