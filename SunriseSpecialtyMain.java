import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import javax.swing.JFrame;

public class SunriseSpecialtyMain extends JFrame implements ActionListener{
 
   JPanel maindishP, sidedishP, dessertP, greetingP, buttonP;
   Button calcB, exitB;
   JTextArea totalTA;
   double total, tax, totalandtax;
  
   NumberFormat currency = NumberFormat.getCurrencyInstance();
  
    SunriseSpecialtyMain(){
      
       setTitle("Order Calculator");
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setSize(700,275);
       setLocationRelativeTo(null);
      
       maindishP = new MainDishPanel();
       sidedishP = new SideDishPanel();
       dessertP = new DessertsPanel();
       greetingP = new GreetingsPanel();
      
       calcB = new Button("Calculate Charges");
       exitB = new Button("Exit");
       totalTA = new JTextArea("Subtotal: " + currency.format(total) +
                               "\nTax: " + currency.format(tax) +
                               "\nTotal: " + currency.format(totalandtax), 3, 1);
       totalTA.setEditable(false);
      
       calcB.addActionListener(this);
       exitB.addActionListener(new ExitListener());
      
       buttonP = new JPanel();
       buttonP.setLayout(new FlowLayout());
       buttonP.setPreferredSize(new Dimension(300,70));
       buttonP.add(calcB);
       buttonP.add(totalTA);
       buttonP.add(exitB);
      
       add(maindishP, new BorderLayout().WEST);
       add(sidedishP, new BorderLayout().CENTER);
       add(dessertP, new BorderLayout().EAST);
       add(greetingP, new BorderLayout().NORTH);
       add(buttonP, new BorderLayout().SOUTH);
      
       setVisible(true);
   }
  
 
          
   public void actionPerformed(ActionEvent arg0) {
       total = 0;
 
       double maindishST = ((MainDishPanel) maindishP).getSubtotal();
       double sidedishST = ((SideDishPanel) sidedishP).getSubtotal();
       double dessertST = ((DessertsPanel) dessertP).getSubtotal();
         JFrame f = new JFrame();
       if(maindishST == 0 && sidedishST == 0 && dessertST != 0)
           JOptionPane.showMessageDialog(null, "Please Select Either a Main or Side Dish (you cannot only order a Dessert)");
       else{
        
           total = maindishST + sidedishST + dessertST;
           tax = total * 0.06;
           totalandtax = total + tax;
          JOptionPane.showMessageDialog( f,"Your Receipt" +
                           ("Subtotal: " + currency.format(total) +
                           "\nTax: " + currency.format(tax) +
                           "\nTotal: " + currency.format(totalandtax)));
       }
   }

  
   private class ExitListener implements ActionListener {
   @Override
       public void actionPerformed(ActionEvent e) {
   System.exit(0);
   }
   }
  
   public static void main(String args[]){
      SunriseSpecialtyMain sunriseSpecialtyMain = new SunriseSpecialtyMain();
   }
}
