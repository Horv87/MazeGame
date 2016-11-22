package at.martin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

/**
 * Created by martin on 05.08.16.
 */
public class MazeView{
private Game game = new Game();
private JFrame frame;
    public MazeView() throws IOException {
        frame = new JFrame();
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println(e.getKeyChar());
                game.move(e.getKeyChar());
                try {
                    game.printField();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                frame.getContentPane().repaint();

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        frame.setTitle("MAZE GAME");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setVisible(true);
        field panel = new field();
        panel.setBackground(Color.white);

        frame.add(panel);

        frame.pack();

    }

    private class field extends JPanel{


        @Override
        public void paint(Graphics g) {
            super.paint(g);
            char[][] field = game.getField();
            int width = getWidth() / field[0].length;
            int height = getHeight() / field.length;

            if (game.GAME_WON == false){

                for (int r = 0; r < field.length;r++){
                    for (int c = 0; c < field[r].length;c++){
                        if (Math.abs(r-game.playerRow) > 5 || Math.abs(c-game.playerCol ) > 5){
                            g.setColor(Color.gray);
                            g.drawRect(c*width,r*height,width,height);
                            g.fillRect(c*width,r*height,width,height);

                        } else  {
                            if (field[r][c] == game.WALL){
                                g.setFont(new Font("Courier",1,30));
                                g.setColor(Color.black);
                                g.drawRect(c*width,r*height,width,height);
                                g.fillRect(c*width,r*height,width,height);
                                g.setColor(Color.gray);
                                g.drawString("#",c*width,(r+1)*height);


                            } else if (field[r][c] == game.PLAYER_DOWN || field[r][c] == game.PLAYER_UP ||
                                    field[r][c] == game.PLAYER_LEFT ||
                                    field[r][c] == game.PLAYER_RIGHT ) {
                                g.setColor(Color.black);
                                g.drawRect(c*width,r*height,width,height);
                                g.fillRect(c*width,r*height,width,height);
                                g.setColor(Color.blue);
                                g.setFont(new Font("Courier",1,30));
                                g.drawString(String.valueOf(field[r][c]),c*width,(r+1)*height);


                            } else if (field[r][c] == game.FINISH){
                                g.setColor(Color.yellow);
                                g.drawRect(c*width,r*height,width,height);
                                g.fillRect(c*width,r*height,width,height);
                            } else if (field[r][c] == game.EMPTY){
                                g.setColor(Color.black);
                                g.drawRect(c*width,r*height,width,height);
                                g.fillRect(c*width,r*height,width,height);
                            }
                        }


                    }


                }
            } else {
                g.setFont(new Font("Courier",1,50));
                g.setColor(Color.orange);
                g.drawString("GAME WON",(getWidth()/2)-50,getHeight()/2);
            }
            }

        }
    }

