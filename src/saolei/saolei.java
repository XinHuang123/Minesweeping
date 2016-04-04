package saolei;
import java.awt.*; //import package 
import java.awt.event.ActionEvent; //import package member
import java.awt.event.ActionListener; 
import java.util.*; 
import javax.swing.*;       
public class saolei extends JFrame implements ActionListener
{ 
	public static void main(String[] args) 
	{ 
		new saolei(9,9,10); 
	}  
	//define a class about mines. 0:no mines 1:mines
	class lei extends JButton 
	{ 
	     int flag=0;
		 int countBomb=0;
		 boolean open;

		 public int getFlag() //�have mines or not
		 {
		  return flag;
		 }
		 public boolean getopen() //�click or not
		 {
			  return open;
		 }
		 public int getcountBomb()//number of surrounded mines 
		 {
		  return countBomb;
		 }
		 public void setFlag(int f)//set flag here
		 {
			 flag=f;
		 }
		 public void setopen(boolean x)//change the status of this position(click or not)
		 {
			 open=x;
		 }
	}
	lei[][] jb = null;//�Instantiate a mine class
	GridLayout glo2;//set a layout
	JPanel[][] jp;//set a container for mines
	JPanel jp1;//����һ�����������е������� ����JPanel�Ž����� 
	JMenuBar a;//����һ�������� 
	JMenu a1;//����һ���Ӳ˵� 
	JMenu a2;//��������һ���Ӳ˵� 
	JMenuItem a11;//����һ���Ӳ˵����������ͬ 
	JMenuItem a12; 
	JMenuItem a13; 
	JMenuItem a14; 
	JMenuItem a15; 
	JMenuItem a16; 
	int row;//һ��ȫ�ֱ����������׵�������е�ֵ 
	int col;//һ��ȫ�ֱ����������׵�������е�ֵ 
	int bombNum;//һ��ȫ�ֱ���,����׵�����  
	//���캯�� 
	saolei(int row , int col , int bombNum)
	{
		this.setTitle("Mine Sweeping");//���ó���ı��� 
		this.row=row;//�Ѵ����row��ֵ���óɣ�ȫ��������row��ֵ 
		this.col=col;//�Ѵ����col��ֵ���óɣ�ȫ��������col��ֵ 
		this.bombNum=bombNum;//�Ѵ����bomdCount��ֵ���óɣ�ȫ��������bomdCount��ֵ 
		jb=new lei[row][col];//ʵ�����׵�����������row��col�������� 	 
		jp=new JPanel[row][col];//ʵ��������������������row��col�������ã�����װ��ÿ���� 
		glo2=new GridLayout(row,col);//����row��col�е����񲼾� 
		a=new JMenuBar();// �˵��� 
		a1=new JMenu("Game(G)");// �˵�1
		a2=new JMenu("Help(H)");// �˵�1 
		a11=new JMenuItem("Beginner(N)");// �˵�1�Ĳ˵���  
		a11.addActionListener(this); 
		a11.setAccelerator(KeyStroke.getKeyStroke("F1")); 
		a12=new JMenuItem("Medium");// �˵�1�Ĳ˵���  
		a12.addActionListener(this);
		a12.setAccelerator(KeyStroke.getKeyStroke("F2")); 
		a13=new JMenuItem("High");// �˵�1�Ĳ˵���  
		a13.addActionListener(this); 
		a13.setAccelerator(KeyStroke.getKeyStroke("F3")); 
		a14=new JMenuItem("Quit",'Q');// �˵�1�Ĳ˵��� 
		a14.addActionListener(this); 
		a14.setAccelerator(KeyStroke.getKeyStroke("Esc")); 
		a15=new JMenuItem("Help");// �˵�1�Ĳ˵��� 
		a15.addActionListener(this); 
		a15.setAccelerator(KeyStroke.getKeyStroke("H")); 
		a16=new JMenuItem("About");// �˵�1�Ĳ˵��� 
		a16.addActionListener(this);//Ϊa16����˵�����Ӽ����� 
		a16.setAccelerator(KeyStroke.getKeyStroke("A")); 
		a1.add(a11);//��a11 a12 a13 a14��ӽ�a1�� 
		a1.add(a12); 
		a1.add(a13); 
		a1.insertSeparator(3);//���һ������� 
		a1.add(a14); 
		a2.add(a15);//��a15 a16��ӽ�a2�� 
		a2.add(a16); 
		a.add(a1);//��a1 a2��ӽ�a����˵����� 
		a.add(a2); 
		this.setJMenuBar(a);//����������Ĳ˵����ó�a 
		jp1 = new JPanel();//ʵ����jp1���ڲ˵�������� 	 
		jp1.setLayout(glo2);//����jp1�Ĳ���Ϊ���񲼾� 	 
		for(int i=0;i<col;i++)
		{ 
			for(int j=0;j<row;j ++)
			{ 
				jp[i][j]=new JPanel(new BorderLayout());//�ֱ��jb����jp�У������������ 
				jb[i][j]=new lei(); 
				jb[i][j].addActionListener(this); 
				jp[i][j].add(jb[i][j]); 
			} 
		} 
		for(int i=0;i<col;i++)//��jp�ټ���jp1�� 
		{
			for(int j=0;j<row;j++)
			{ 
				jp1.add(jp[i][j]); 
			} 
		} 
		Random r=new Random();//����һ������� 
		for(int i=0;i<=(bombNum-1);i++)//����������� 
		{
			int index=r.nextInt(row-1); 
			int index2=r.nextInt(col-1); 
			if(jb[index][index2].getFlag()==1)//����Ѿ������� ������ȥ 
			{
				if(index>row)
				{ 
					index = 0; 
				} 
				else
				{ 
					index = index + 1; 
				} 
			} 
			jb[index][index2].setFlag(1);//��������� �����ó��� 
		} 		 
		this.add(jp1 , BorderLayout.CENTER);//��jp1���������������
		this.setLocation(356,20);//�������λ�� 
		this.pack();//���øպð�����������Ĵ�С 
		this.setResizable(true);// ������������촰�� 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//���Թرմ���  
		Aroundbomb();//�����Χ�׵����� 
		this.setVisible(true);//���ô�����ʾ 
		this.setSize(700,700);
	} 
	
	public void actionPerformed(ActionEvent e) //�����ͬ��ť�ķ�Ӧ
	{ 
		if(e.getSource()==a11)//�����Ѷ�
		{ 
			new saolei(4,4,5); 
		}	 
		else if(e.getSource()==a12)//�м��Ѷ�
		{ 
			new saolei(9,9,10); 
		} 		 
		else if(e.getSource()==a13)//�߼��Ѷ�
		{ 
			new saolei(16,16,40);
		} 		 
		else if(e.getSource()==a14)//�˳�
		{ 
			System.exit(0); 
		} 		 
		else if(e.getSource()==a15)//����
		{ 
			JOptionPane.showMessageDialog(this,"Requirement of winning:No mines left"); 
			return; 
		} 		 
		else if(e.getSource()==a16)//����
		{ 
			JOptionPane.showMessageDialog(this,"Xin"); 
			return; 
		} 		 
		for(int i=0;i<col;i++)//�������
		{ 
			for(int j=0;j<row;j++)
			{ 				 
				if(e.getSource()==jb[i][j])//�����������׵İ�ť 
				{						 
					jb[i][j].setEnabled(false);//���ð�ťΪ������ 
					jb[i][j].setopen(true);//�����׵�����Ϊ��״̬ 
				 
					if(1==jb[i][j].flag)//�����������ף���ô��ʾ 
					{ 
						jb[i][j].setText("X"); 
						JOptionPane.showMessageDialog(this,"Game over"); 
						showAll(); 
						JOptionPane.showMessageDialog(this,"Restart"); 
						new saolei(9,9,10); 
					} 
					else if(bombNum==leaveGrid())//���ʣ�µ�û�򿪵ķ����� �����׵����� ��ôӮ
					{
						jb[i][j].setText(""+jb[i][j].getcountBomb()); 
						JOptionPane.showMessageDialog(this,"Congratulations!"); 
						showAll(); 
						JOptionPane.showMessageDialog(this,"Restart"); 
						new saolei(9,9,10); 
					} 
				 
					else if(0==jb[i][j].flag)
					{ 
						jb[i][j].setText(""+jb[i][j].getcountBomb()); //��ʾ��Χ���׵����� 
					} 
					diaoyongxianshi(i,j); 
			    } 
		    } 
	    } 
	}
	private void diaoyongxianshi(int i , int j) //��������ǵ���DiaplayAround����ѯ�Ա��Ƿ����ף�������� 
	{
		if((i-1)>0&&(j-1)>0)
		{ 
			DiaplayAround(i-1,j-1); 
			DiaplayAround(i,j-1); 
			DiaplayAround(i-1,j); 
		} 
		if((i+1)<row&&(j-1)>0)
		{ 
			DiaplayAround(i+1,j-1); 
			DiaplayAround(i+1,j); 
		} 
		if((i-1)>0&&(j+1)<col)
		{ 
			DiaplayAround(i-1,j+1); 
		} 
		if((i+1)<row&&(j+1)<col)
		{ 
			DiaplayAround(i,j+1); 
			DiaplayAround(i+1,j+1); 
		} 
	} 
	private void Aroundbomb()//�����Χ�׵����� 
	{
		for(int i=0;i<row;i++)
		{ 
			for(int j=0;j<col;j++)//����Ҫ���ĵ�Ԫ�����޵��׵������,ͳ����Χ�ĵ��׸��� 
			{ 				
				int count=0; 
				if(jb[i][j].flag==0)
				{ 
                    //�������Ƿ�Ϊ����1 
					if((j-1)>=0)
					{ 
						if(jb[i][j-1].flag==1)
						{ 
							count+=1;  
						} 
					}  
                    //����ұ��Ƿ�Ϊ����2 
					if((j+1)<col)
					{ 
						if(jb[i][j+1].flag==1) 
						{ 
							count+=1; 
						} 
					} 
					//����Ϸ��ո��Ƿ�Ϊ����3 
					if((i-1)>=0) 
					{ 
						if(jb[i-1][j].flag==1) 
						{ 
							count+=1;  
						} 
					} 
					//����·��ո��Ƿ�Ϊ����4 
					if((i+1)<row) 
					{ 
						if(jb[i+1][j].flag==1) 
						{ 
							count+=1;  
						} 
					} 
                    //��������Ƿ�Ϊ����5 
					if((j-1>=0)&&(i-1>=0)) 
					{ 
						if(jb[i-1][j-1].flag==1) 
						{ 
							count += 1; // 
						} 
					} 
                    //��������Ƿ�Ϊ����6 
					if((j-1>=0)&&(i+1<row)) 
					{ 
						if(jb[i+1][j-1].flag==1) 
						{ 
							count += 1; 
						} 
					} 
					//������Ϸ��Ƿ�Ϊ����7 
					if((i-1>=0)&&(j+1<col)) 
					{ 
						if(jb[i-1][j+1].flag==1) 
						{ 
							count+=1;  
						} 
					} 
                    //��������Ƿ�Ϊ����8 
					if((i+1<row)		&&(j+1<col)) 
					{ 
						if(jb[i+1][j+1].flag==1) 
						{ 
							count+=1; // 
						} 
					} 
					jb[i][j].countBomb=count; 
				} 
			} 
		} 
	}  
	private int leaveGrid()//����ʣ�µĸ����� 
	{
		int leaveGrid=0; 
		for(int i=0;i<col;i++)
		{ 
			for(int j=0;j<row;j++)
			{ 
				if(jb[i][j].getopen()==false) 
					leaveGrid+=1; 
			} 
		} 
		return leaveGrid; 
	} 
	public void showAll()//��ʾ���еĵ���λ�� 
	{
		for(int i=0;i<col;i++)
		{ 
			for(int j=0;j<row;j++)
			{ 
				if(1==jb[i][j].flag)
				{ 
					jb[i][j].setEnabled(false);//������� ����û����ʾ���� Ҫ�Զ��򿪲����ñ���ΪX 
					jb[i][j].setText("X"); 
				} 
			} 
		} 
	}  
	public void DiaplayAround(int x,int y)//��ʾ��Χû���׵ķ��� 
	{
		if(jb[x][y].getopen()==true||jb[x][y].getFlag()==1)
		{ 
			return ; 
		} 
		if(jb[x][y].countBomb==0)
		{ 
			jb[x][y].setEnabled(false); 
			jb[x][y].setopen(true); 
			jb[x][y].setText(""+jb[x][y].getcountBomb()); 
		} 
	} 	 
} 


