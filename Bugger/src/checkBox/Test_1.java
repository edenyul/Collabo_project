package checkBox;

import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JLabel;

public class Test_1 extends JFrame implements ItemListener{

   private JPanel contentPane; //���� �̹��� ������ �г�
   private JLabel lblfruit[]=new JLabel[3]; //������ �� �ִ� ���� �̸�
   private JCheckBox chkfruit[]=new JCheckBox[3]; 
   private String fruitName[]= {"apple","grape","orange"};
   private ImageIcon icon[]=new ImageIcon[3];

   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               Test_1 frame = new Test_1();
               frame.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }

   /**
    * Create the frame.
    */
   public Test_1() {
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 450, 300);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(new GridLayout(0, 4, 0, 0));
      
      JPanel panel = new JPanel();
      contentPane.add(panel);
      panel.setLayout(new GridLayout(0, 1, 0, 0));
      //ù��° �гο� üũ�ڽ� add
      for(int i=0;i<chkfruit.length;i++) {
         //üũ�ڽ� ��ü �����ؼ� �迭�� ���
         chkfruit[i]=new JCheckBox(fruitName[i]);
         //������ üũ�ڽ��� �гο� add
         panel.add(chkfruit[i]);
         //JLabel ��ü �����ؼ� �迭�� ���
         lblfruit[i]=new JLabel();
         icon[i]=new ImageIcon(Test_1.class.getResource("/checkBox/"+fruitName[i]+".jpg"));
      }
      
      
      JPanel panel_1 = new JPanel();//���
      contentPane.add(panel_1);
      panel_1.setLayout(new GridLayout(0,1));
      panel_1.add(lblfruit[0]);
      
      JPanel panel_2 = new JPanel();//����
      contentPane.add(panel_2);
      panel_2.setLayout(new GridLayout(0,1));
      panel_2.add(lblfruit[1]);
      
      JPanel panel_3 = new JPanel();//������
      contentPane.add(panel_3);
      panel_3.setLayout(new GridLayout(0,1));
      panel_3.add(lblfruit[2]);
      
      //�̺�Ʈ ������ ���̱�
      for(int i=0;i<chkfruit.length;i++)
         chkfruit[i].addItemListener(this);
   }

   @Override
   public void itemStateChanged(ItemEvent e) {
      JCheckBox check=(JCheckBox)e.getItem();//=e.getSource();
      
      for(int i=0;i<3;i++) {//üũ �ڽ��� ����
         if(check==(chkfruit[i])) {//���� check�� ������ check[[i]�� ���ٸ�
            if(e.getStateChange()==ItemEvent.SELECTED) //���� State�� ���ϰ��� SELCTED�� ���� ���ٸ�
               lblfruit[i].setIcon(icon[i]); //�� [i]�� icon[i]�� �׸��� �ٿ���
            else
               lblfruit[i].setIcon(null);//�ƴ϶�� �ش� ��[i]�� ���� null�� �����ض�.
         }//ItemEvent.SELECTED�� Ŭ���� �� ���� 1�� ���� �����Ѵ�. ������ ������ �������. 
      }
   }


}
