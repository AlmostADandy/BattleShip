import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;

public class GameField extends JPanel {
    private Gameplay game;
    private int mX, mY; 
    private Timer timer;
    private BufferedImage  injured, away, killed, deck, minideck;
    private Rectangle2D line1;
    private Rectangle2D line2;
    private Rectangle2D line3;
    private Rectangle2D line4;
    private boolean isSelectP1 = false;
    private boolean isSelectP2 = false;
    private boolean isSelectP3 = false;
    private boolean isSelectP4 = false;
    private int p1;
    private int p2;
    private int p3;
    private int p4;
    public boolean verticalAlignment = true; 
    private JButton checkDir;
    private String number[] = {
    		"А", 
    		"Б", 
    		"В", 
    		"Г", 
    		"Д", 
    		"Е", 
    		"Ж", 
    		"З", 
    		"И", 
    		"К"};
    public static boolean AutoPlacement;

    public GameField() {    
        setFocusable(true);
        game = new Gameplay();
        setSize(680,520);
        addMouseListener(new Mouse());
        checkDir = new JButton("Повернуть");
        checkDir.setBackground(new Color(248, 248, 255));
        checkDir.setBounds(235, 430, 161, 23);
        checkDir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (verticalAlignment) {
                	verticalAlignment = false;
                }
                else {
                	verticalAlignment = true;
                }
            }
        });
        add(checkDir);
        checkDir.setVisible(false);
        try {
        	injured = ImageIO.read(getClass().getResource("image/injured.png"));
        	away = ImageIO.read(getClass().getResource("image/away.png"));
            killed = ImageIO.read(getClass().getResource("image/killed.png"));
            deck = ImageIO.read(getClass().getResource("image/deck.png"));
            minideck = ImageIO.read(getClass().getResource("image/minideck.png"));
        } catch (IOException e) {e.printStackTrace();}
        timer = new Timer(100, new ActionListener() {          
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        
        setLayout(null);
        
    }


    public void paintComponent(Graphics g) { 
        BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResource("image/bg.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        g.drawImage(image, 0, 0, this); 
        Graphics2D g2 = (Graphics2D) g;
        g.setFont(new Font("Times New Roman", 0, 18));
        g2.setColor(new Color(330099));
        g2.setStroke(new BasicStroke(2));
        
        if (verticalAlignment) {
            line4 = new Rectangle2D.Double(190, 360, 92, 23);
            line3 = new Rectangle2D.Double(300, 360, 69, 23);
            line2 = new Rectangle2D.Double(385, 360, 46, 23);
            line1 = new Rectangle2D.Double(448, 360, 23, 23);
            if (p1 != 0) {
            	((Graphics2D) g).draw(line1);
            	g.drawImage(minideck, 448, 360, this); 
            }
            if (p2 != 0) {
            	((Graphics2D) g).draw(line2);
            	for (int i = 385; i < 409; i += 23) {
            		g.drawImage(minideck, i, 360, this);
            	}
            }
            if (p3 != 0) {
            	((Graphics2D) g).draw(line3);
            	for (int i = 300; i < 347; i += 23) {
            		g.drawImage(minideck, i, 360, this);
            	}
            }
            if (p4 != 0) {
            	((Graphics2D) g).draw(line4);
            	for (int i = 190; i < 260; i += 23) {
            		g.drawImage(minideck, i, 360, this);
            	}  	
            }
            if ((p1 + p2 + p3 + p4) != 0) {
            	g.drawLine(0, 300, 680, 300);
                g.drawString("Расставьте корабли", 240, 320);
                checkDir.setVisible(true);
            }
            else {
            	checkDir.setVisible(false);
            }
        } 
        else {
            line4 = new Rectangle2D.Double(240, 330, 23, 92);
            line3 = new Rectangle2D.Double(280, 330, 23, 69);
            line2 = new Rectangle2D.Double(320, 330, 23, 46);
            line1 = new Rectangle2D.Double(360, 330, 23, 23);
            if (p1 != 0) {
            	((Graphics2D) g).draw(line1);
            	g.drawImage(minideck, 360, 330, this); 
            }
            if (p2 != 0) {
            	((Graphics2D) g).draw(line2);
            	for (int j = 330; j < 354; j += 23) {
            		g.drawImage(minideck, 320, j, this);
            	}
            }
            if (p3 != 0) {
            	((Graphics2D) g).draw(line3);
            	for (int j = 330; j < 377; j += 23) {
            		g.drawImage(minideck, 280, j, this);
            	}
            }
            if (p4 != 0) {
            	((Graphics2D) g).draw(line4);
            	for (int j = 330; j < 400; j += 23) {
            		g.drawImage(minideck, 240, j, this);
            	}  	
            }
            if ((p1 + p2 + p3 + p4) != 0) {
            	g.drawLine(0, 300, 680, 300);
                g.drawString("Расставьте корабли", 240, 320);
                checkDir.setVisible(true);
            }
            else {
            	checkDir.setVisible(false);
            }
        }
        

        
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (game.arrComputer[i][j] != 0) {
                    
                    if ((game.arrComputer[i][j] >= 8) && (game.arrComputer[i][j] <= 11)) {
                        g.drawImage(injured, 359 + 23 * i, 60 + 23 * j, 23, 23, null);
                    }
                    else if ((game.arrComputer[i][j] >= 15)) {
                        g.drawImage(killed, 359 + 23 * i, 60 + 23 * j, 23, 23, null);
                    }
                    else if ((game.arrComputer[i][j] >= 5 && game.arrComputer[i][j] < 8 || game.arrComputer[i][j] == -2)) {
                        g.drawImage(away, 359 + 23 * i, 60 + 23 * j, 23, 23, null);
                    }
                    else if (Gameplay.endGame != 0 && (game.arrComputer[i][j] >= 1 && game.arrComputer[i][j] <= 4)) {
                        g.drawImage(deck, 359 + 23 * i, 60 + 23 * j, 23, 23, null);
                        g.setColor(new Color(0));
                        g.drawRect(359 + 23 * i, 60 + 23 * j, 23, 23);
                    }
                }

                if (game.arrPlayer[i][j] != 0){
                    if ((game.arrPlayer[i][j] >= 1) && (game.arrPlayer[i][j] <= 4)) {
                        g.drawImage(deck, 60 + 23 * i, 60 + 23 * j, 23, 23, null);
                    }else if ((game.arrPlayer[i][j] >= 8) && (game.arrPlayer[i][j] <= 11)) {
                        g.drawImage(injured, 60 + 23 * i, 60 + 23 * j, 23, 23, null);
                    }else if ((game.arrPlayer[i][j] >= 15)) {
                        g.drawImage(killed, 60 + 23 * i, 60 + 23 * j, 23, 23, null);
                    }else if ((game.arrPlayer[i][j] >= 5) && game.arrPlayer[i][j] < 8) {
                        g.drawImage(away, 60 + 23 * i, 60 + 23 * j, 23, 23, null);
                    }else if (game.arrPlayer[i][j] == -2){
                        g.drawImage(away, 60 + 23 * i, 60 + 23 * j, 23, 23, null);
                    }
                }
            }
        }
        
        
        
        if (p1 + p2 + p3 + p4 == 0) {  	
        	g.drawRect(60 + 23 * 5, 60 + 14 * 23 + 30, 23, 23);
            ((Graphics2D) g).drawString(String.valueOf("—  " + (4 - Gameplay.ComputerDeck_1)), 50 + 23 * 5 + 2 * 23, 60 + 15 * 23 + 30);
            for (int i = 0; i < 1; i++) {
            	g.drawImage(minideck, 60 + 23 * 5 + 23 * i, 60 + 14 * 23 + 30, this);
            }
            g.drawRect(60 + 23 * 4, 60 + 13 * 23 + 20, 2 * 23, 23);
            ((Graphics2D) g).drawString(String.valueOf("—  " + (3 - Gameplay.ComputerDeck_2)), 50 + 23 * 4 + 3 * 23, 60 + 14 * 23 + 20);
            for (int i = 0; i < 2; i++) {
            	g.drawImage(minideck, 60 + 23 * 4 + 23 * i, 60 + 13 * 23 + 20, this);
            }
            g.drawRect(60 + 23 * 3, 60 + 12 * 23 + 10, 3 * 23, 23);
            ((Graphics2D) g).drawString(String.valueOf("—  " + (2 - Gameplay.ComputerDeck_3)), 50 + 23 * 3 + 4 * 23, 60 + 13 * 23 + 10);
            for (int i = 0; i < 3; i++) {
            	g.drawImage(minideck, 60 + 23 * 3 + 23 * i, 60 + 12 * 23 + 10, this);
            }
            g.drawRect(60 + 23 * 2, 60 + 11 * 23, 4 * 23, 23);
            ((Graphics2D) g).drawString(String.valueOf("—  " + (1 - Gameplay.ComputerDeck_4)), 50 + 23 * 2 + 5 * 23, 60 + 12 * 23 - (23 / 4));
            for (int i = 0; i < 4; i++) {
            	g.drawImage(minideck, 60 + 23 * 2 + 23 * i, 60 + 11 * 23, this);
            }

                    
            g.drawRect(60 + 23 * 5 + 13 * 23, 60 + 14 * 23 + 30, 1 * 23, 23);
            ((Graphics2D) g).drawString(String.valueOf("—  " + (4 - Gameplay.PlayerDeck_1)), 50 + 23 * 5 + 15 * 23, 60 + 15 * 23+30);
            for (int i = 0; i < 1; i++) {
            	g.drawImage(minideck, 60 + 23 * 5 + 13 * 23 + 23 * i, 60 + 14 * 23 + 30, this);
            }   
            g.drawRect(60 + 23 * 4 + 13 * 23, 60 + 13 * 23 + 20, 2 * 23, 23);
            ((Graphics2D) g).drawString(String.valueOf("—  " + (3 - Gameplay.PlayerDeck_2)), 50 + 23 * 4 + 16 * 23, 60 + 14 * 23+20);
            for (int i = 0; i < 2; i++) {
            	g.drawImage(minideck, 60 + 23 * 4 + 13 * 23 + 23 * i, 60 + 13 * 23 + 20, this);
            }
            g.drawRect(60 + 23 * 3 + 13 * 23, 60 + 12 * 23 + 10, 3 * 23, 23); 
            ((Graphics2D) g).drawString(String.valueOf("—  " + (2 - Gameplay.PlayerDeck_3)), 50 + 23 * 3 + 17 * 23, 60 + 13 * 23+10);
            for (int i = 0; i < 3; i++) {
            	g.drawImage(minideck, 60 + 23 * 3 + 13 * 23 + 23 * i, 60 + 12 * 23 + 10, this);
            }
            g.drawRect(60 + 23 * 2 + 13 * 23, 60 + 11 * 23, 4 * 23, 23);
            ((Graphics2D) g).drawString(String.valueOf("—  " + (1 - Gameplay.PlayerDeck_4)), 50 + 23 * 2 + 18 * 23, 60 + 12 * 23-(23/4));
            for (int i = 0; i < 4; i++) {
            	g.drawImage(minideck, 60 + 23 * 2 + 13 * 23 + 23 * i, 60 + 11 * 23, this);
            }
        }
            


        for (int i = 60; i <= 290; i += 23) {
            g2.setStroke(new BasicStroke(1));
            g.setColor(new Color(330099));
            g.drawLine(60, i, 290, i); 
            g.drawLine(i, 60, i, 290);
            g.drawLine(359, i, 589, i); 
            g.drawLine(i + 299, 60, i + 299, 290);

            g2.setStroke(new BasicStroke(2));
            g.setColor(new Color(330099));
            g.drawRect(60, 60, 230, 230);
            g.drawRect(359, 60, 230, 230);
        }
        
        for (int i = 1; i <= 10; i++) {
            g.drawString(String.valueOf(i), 37, 60 + i * 23 - (23 / 4));
            g.drawString(String.valueOf(i), 336, 60 + i * 23 - (23 / 4));
            g.drawString(number[i-1], 60 + (i - 1) * 23 + (23 / 4), 57);
            g.drawString(number[i-1], 359 + (i - 1) * 23 + (23 / 4), 57);
        }

        
        if (Gameplay.endGame == 1) {
            timer.stop();

        }
        if (Gameplay.endGame == 2) {
            timer.stop();
        }
        
    }
    
    
    public void callAutoPlacement(){
    	AutoPlacement = true;
    	timer.start();
        game.start();
    }
    
    
    public void callPlacement(){
    	timer.start();
        game.start();
        p1 = 4;
        p2 = 3;
        p3 = 2;
        p4 = 1;
    }

    public class Mouse implements MouseListener{
        

        public void mousePressed(MouseEvent e) {
            if ((e.getButton() == 1) && (e.getClickCount() == 1)) {
                mX = e.getX();
                mY = e.getY();
                if ((p1 + p2 + p3 + p4 == 0) 
                        && mX > 359 && mY > 60 && mX < 589 && mY < 290) {
                    if (game.playerMove && Gameplay.endGame ==0 && !game.computerMove){
                       int i = (mX - 359) / 23;
                       int j = (mY - 60) / 23;
                        if ((i >= 0 && i <= 9) && (j >= 0 && j <= 9)) {
                            if (game.arrComputer[i][j] <= 4 && game.arrComputer[i][j] >= -1) {
                                game.attackByPlayer(game.arrComputer, i, j);
                            }
                        }
                    }
                }
            }
            Graphics g = getGraphics();    
            g.setColor(new Color(330099));
    		((Graphics2D) g).setStroke(new BasicStroke(4));
            if (line4.contains(e.getPoint())){
                isSelectP4 =true;
                isSelectP3 =false;
                isSelectP2 =false;
                isSelectP1 =false;
                
                if (verticalAlignment) {
                	g.drawRect(190, 360, 92, 23); 
                }
                
                else {
                	g.drawRect(240, 330, 23, 92); 
            	     		
            	}  
            }
           
            if (line3.contains(e.getPoint())){
                isSelectP4 =false;
                isSelectP3 =true;
                isSelectP2 =false;
                isSelectP1 =false;
                if (verticalAlignment) {
            		g.drawRect(300, 360, 69, 23); 
                }
                
                else {
            		g.drawRect(280, 330, 23, 69); 
            	     		
            	}  
            }
            if (line2.contains(e.getPoint())){
                isSelectP4 =false;
                isSelectP3 =false;
                isSelectP2 =true;
                isSelectP1 =false;
                if (verticalAlignment) {
            		g.drawRect(385, 360, 46, 23); 
                }
                
                else {
            		g.drawRect(320, 330, 23, 46); 
            	     		
            	}  
            }
            if (line1.contains(e.getPoint())){
                isSelectP4 =false;
                isSelectP3 =false;
                isSelectP2 =false;
                isSelectP1 =true;
                if (verticalAlignment) {
            		g.drawRect(448, 360, 23, 23); 
                }
                
                else {
            		g.drawRect(360, 330, 23, 23); 
            	     		
            	}  
            }
        
        }

        
        public void mouseReleased(MouseEvent e) {
        	mX = e.getX();
            mY = e.getY();
            int i = (mX - 60) / 23;
            int j = (mY - 60) / 23;
            if (p4 != 0 && isSelectP4 && mX > 60 && mY > 60 && mX < 290 && mY < 290) {
                isSelectP4 = false;
                if (game.setDeck(i, j, 4, verticalAlignment)) {
                    p4--;
                }

            } else if (p3 != 0 && isSelectP3 && mX > 60 && mY > 60 && mX < 290 && mY < 290) {
                isSelectP3 = false;
                if (game.setDeck(i, j, 3, verticalAlignment)) {
                    p3--;
                }

            } else if (p2 != 0 && isSelectP2 && mX > 60 && mY > 60 && mX < 290 && mY < 290) {
                isSelectP2 = false;
                if (game.setDeck(i, j, 2, verticalAlignment)) {
                    p2--;
                }

            } else if (p1 != 0 && isSelectP1 && mX > 60 && mY > 60 && mX < 290 && mY < 290) {
                isSelectP1 = false;
                if (game.setDeck(i, j, 1, verticalAlignment)) {
                    p1--;
                }
            }
            
            
        }
        
        public void mouseClicked(MouseEvent e) {

        }
        
        
        public void mouseEntered(MouseEvent e) {

        }
      
        public void mouseExited(MouseEvent e) {
        }
    
    }
}