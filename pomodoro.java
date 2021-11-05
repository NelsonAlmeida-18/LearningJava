import java.awt.*;  
import javax.swing.*;  
import java.awt.event.*;  
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

public class pomodoro {

    public static void main(String[] args){


        JFrame painelPrincipal = new JFrame("Pomodoro");

        JPanel botoes = new JPanel();
        botoes.setBackground(Color.cyan);

        JPanel inputs = new JPanel();


        JButton comecar = new JButton("Iniciar");
        JButton cancelar = new JButton("Cancelar");

        JTextField tempoDeTrabalho = new JTextField("Tempo de Trabalho...");
        JTextField tempoDeDescanso = new JTextField("Tempo de Descanso...");
        JTextField intervalos = new JTextField("Intervalos...");

        botoes.add(tempoDeTrabalho);
        botoes.add(tempoDeDescanso);
        botoes.add(intervalos);

        botoes.add(comecar);
        botoes.add(cancelar);

        painelPrincipal.add(inputs);
        painelPrincipal.add(botoes);
        painelPrincipal.setSize(400,400);
        painelPrincipal.setVisible(true);

        tempoDeTrabalho.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                tempoDeTrabalho.setText("");
                botoes.add(tempodeTrabalho);
                painelPrincipal.add(botoes);
            }
        });

        comecar.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
                String tempnumeroDeIntervalos = intervalos.getText();
                int numerodeIntervalos = Integer.parseInt(tempnumeroDeIntervalos);

                String temptempodeTrabalho = tempoDeTrabalho.getText();
                int tempodeTrabalho = Integer.parseInt(temptempodeTrabalho);

                String temptempodeDescanso = tempoDeDescanso.getText();
                int tempodeDescanso = Integer.parseInt(temptempodeDescanso);

                painelPrincipal.dispose();
                timer(tempodeTrabalho, tempodeDescanso, numerodeIntervalos);
            }
        });


        cancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                    System.exit(0);
            }
        });

    }
       //fazer funcao para checkar se são só ints
    ///
    public static int[] horaFinal(int inithour, int initminutes, int tempodeTrabalho){
        while (tempodeTrabalho>0){
            if (initminutes+tempodeTrabalho>=60){
                inithour++;
                int temp = 60-initminutes;
                tempodeTrabalho = tempodeTrabalho-temp;
                initminutes = 0;
            }
            else{
                initminutes = initminutes+tempodeTrabalho;
                tempodeTrabalho = -1;
            }
        }
        int[] horas = {inithour, initminutes};
        return horas;
    }

    public static void timer(int tempoDeTrabalho, int tempoDeDescanso, int numeroDeIntervalos){
        LocalTime temp = LocalTime.now();
        DateTimeFormatter myTemp = DateTimeFormatter.ofPattern("HH:mm:ss");

        String formattedTime = temp.format(myTemp);
        String[] now = formattedTime.split(":",3);
        
        String temphour = now[0];
        String tempminutes = now[1];
        String tempseconds = now[2];

        int inithour = Integer.parseInt(temphour);
        int initminutes = Integer.parseInt(tempminutes);
        int initseconds = Integer.parseInt(tempseconds);

        int[] horaFinal = horaFinal(inithour,initminutes, tempoDeTrabalho);
        int horaLimite = horaFinal[0];
        int minutosLimite = horaFinal[1];

        JFrame mainFrame = new JFrame();

        JLabel tempoRestante = new JLabel(horaLimite+ ":" + minutosLimite + ":" + initseconds);
        tempoRestante.setFont(new Font(tempoRestante.getFont().getName(), tempoRestante.getFont().getStyle(), 50));
        tempoRestante.setBorder(new LineBorder(Color.BLACK));
        tempoRestante.setPreferredSize(new Dimension(300, 300));
        tempoRestante.setHorizontalAlignment(JLabel.CENTER);

        JPanel tempo = new JPanel();
        tempo.setBounds(0,0,300,300);

        int i = 10;
        mainFrame.setSize(400,400);

        tempo.add(tempoRestante);
        mainFrame.add(tempo);
        mainFrame.setVisible(true);

    }
}
