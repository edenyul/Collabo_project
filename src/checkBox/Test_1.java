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

   private JPanel contentPane; //과일 이미지 보여줄 패널
   private JLabel lblfruit[]=new JLabel[3]; //선택할 수 있는 과일 이름
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
      //첫번째 패널에 체크박스 add
      for(int i=0;i<chkfruit.length;i++) {
         //체크박스 객체 생성해서 배열에 담기
         chkfruit[i]=new JCheckBox(fruitName[i]);
         //생성된 체크박스를 패널에 add
         panel.add(chkfruit[i]);
         //JLabel 객체 생성해서 배열에 담기
         lblfruit[i]=new JLabel();
         icon[i]=new ImageIcon(Test_1.class.getResource("/checkBox/"+fruitName[i]+".jpg"));
      }
      
      
      JPanel panel_1 = new JPanel();//사과
      contentPane.add(panel_1);
      panel_1.setLayout(new GridLayout(0,1));
      panel_1.add(lblfruit[0]);
      
      JPanel panel_2 = new JPanel();//포도
      contentPane.add(panel_2);
      panel_2.setLayout(new GridLayout(0,1));
      panel_2.add(lblfruit[1]);
      
      JPanel panel_3 = new JPanel();//오렌지
      contentPane.add(panel_3);
      panel_3.setLayout(new GridLayout(0,1));
      panel_3.add(lblfruit[2]);
      
      //이벤트 리스너 붙이기
      for(int i=0;i<chkfruit.length;i++)
         chkfruit[i].addItemListener(this);
   }

   @Override
   public void itemStateChanged(ItemEvent e) {
      JCheckBox check=(JCheckBox)e.getItem();//=e.getSource();
      
      for(int i=0;i<3;i++) {//체크 박스의 개수
         if(check==(chkfruit[i])) {//만약 check와 선택한 check[[i]가 같다면
            if(e.getStateChange()==ItemEvent.SELECTED) //만약 State의 리턴값과 SELCTED의 값이 같다면
               lblfruit[i].setIcon(icon[i]); //라벨 [i]에 icon[i]의 그림을 붙여라
            else
               lblfruit[i].setIcon(null);//아니라면 해당 라벨[i]의 값을 null로 지정해라.
         }//ItemEvent.SELECTED는 클릭할 때 마다 1의 값을 리턴한다. 선택의 유무는 상관없다. 
      }
   }


}
