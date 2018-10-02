package hw3;

import javax.swing.JOptionPane;

class Weapon {
	  String name;
	  
	  int attack=1;                                                                   //��������� �������� ����� ��� ������
	  
	  private String[] weaponsvariant= {"���","���","�����","��������","������"};			  
	  private String[][] weaponspower= {{"2",  "3",  "4"  },						  //������� ��������� �������������	����� ��� �������
			                            {"4",  "5",  "6"  },
			                            {"5",  "6",  "7"  },
			                            {"7",  "8",  "9"  },
			                            {"500","600","700"}};
	  private String ans; 
	  
	  Weapon(String caption){
	  	  //this.name=JOptionPane.showInputDialog(null, caption+" / �������� ������:","");
	  	  this.name=(String)JOptionPane.showInputDialog(null, caption+" / �������� ������:", "������ ������", 
	  	             		                            JOptionPane.QUESTION_MESSAGE, null,
	  			                                        weaponsvariant, weaponsvariant[0]);

	  	  if (this.name==null){
	  	      this.name="���"; return;
	  	  }                             //����� Cancel - ������ �� �����, ���� �� ���������
	  	  do {
			  try {
				  //this.ans=JOptionPane.showInputDialog(this.name+":������������ ���� �� �����:",this.attack);
				  this.ans = (String) JOptionPane.showInputDialog(null, this.name + ": ������������ ���� �� �����:", "����� ���� ������",
						  JOptionPane.QUESTION_MESSAGE, null,
						  listCombo(this.name), "");

				  if (this.ans == null) {                                            //����� Cancel - ��������� ���� ��� ���������� ������
					  this.attack = Integer.parseInt(listCombo(this.name)[0]);
					  return;
				  }
				  this.attack = Integer.parseInt(this.ans);
			  } catch (NumberFormatException e) {JOptionPane.showMessageDialog(null, e);}
		  }
	  	 while (this.attack<0);

	  }
	  int attack() {
	  	//return (int)Math.round(Math.random()*this.attack);                          //���� ����� �� 0 �� ����. ��������
	  	return (int)(Math.random()*(this.attack+1));
	  }  
	  
	  String weap(){
	  	return "->"+this.name+"#"+this.attack;
	  }

	  private String[] listCombo(String name) {										  //���� ������ ��������� ����� ��� ���������� ������
		  int i=0;
		  for (String s : this.weaponsvariant) {
			if (s==name) return this.weaponspower[i];
			i++;
		  }
		  return this.weaponspower[0];
	  }
}  //class Weapon

public class Person {
	String name;
	int hp=0;																		 //��������
	Weapon w;																		 //������
	private String ans;
 
    Person(String caption) {
    	this.name=JOptionPane.showInputDialog(null,"���:",caption);
    	if (this.name==null) {System.exit(0);}
    	do {
    	  try {                                                                      //�������� �� �����
    	   ans=JOptionPane.showInputDialog(null,"��������:","10"); 
    	   if (ans==null) System.exit(0);											 //����� Cancel
    	   this.hp= Integer.parseInt(ans);
    	  }		
    	  catch(NumberFormatException e){JOptionPane.showMessageDialog(null, "������! "+e);}
    	} while (hp<=0);
    	w=new Weapon(this.name);													 //���������� ������
    }
    
    String getInfo() {                                                               //���� ������� � ����������� �����
    	return this.name+"\n��������: "+this.hp+"     ������: " +this.w.name+" / ����. �����:"+this.w.attack+"\n\n";
    }
    
    boolean alive() {                                                                //��� �����?
    	return this.hp>0;
    }
    
    void lifeDecrease(int hpOut) {										             //�����
    	this.hp-=hpOut;
    	if (this.hp<0) this.hp=0;												     //����
    	System.out.print(padright(this.name+" �������� "+hpOut+
    			         " �������� ��������:"+this.hp,42));                     //���� ������� � ��������
    }

    int attack(){																	  //������ ���� �����
    	return this.w.attack();
	}
    
    String weap(){
    	return this.w.weap();
    }


	static String padright(String ss, int len){
		len=len-ss.length();
		return String.format(ss+"%1$"+len+ "s", "");
	}
	public static void main(String[] args) {

		int lenNum=6,lenStr=42;

		Person pers1=new Person("������"),
        	   pers2=new Person("������");					     			 //������������� ����������
        
        JOptionPane.showMessageDialog(null,                          //�������� ��������� ��������� ����������
                              pers1.getInfo()+
                                       pers2.getInfo(),
                                  "���!",JOptionPane.INFORMATION_MESSAGE);

        int i=1;                                                                     //������� ����
        boolean First=(int)(Math.random()*10) >=5;                                   //��������� �����: ��� ������ �������
        if (First)
        	System.out.println("������ ������� "+pers2.name);
        else
        	System.out.println("������ ������� "+pers1.name);

        System.out.println(padright("",lenNum)+padright(pers1.name+"("+pers1.hp+")"+pers1.weap(),lenStr)+pers2.name+"("+pers2.hp+")"+pers2.weap());

        while(pers1.alive() && pers2.alive() ) {									 //���� ��� ���� - ������
        	System.out.print(padright(Integer.toString(i)+": ",lenNum));
        	if (i==1) 														    	 //������ �����
        	  if (First)
        		  pers1.lifeDecrease(pers2.attack());                                //������ ����, ������ ������
        	  else System.out.print(padright("                      -",lenStr));//��������� ������� ������� ������ ������� ����� �������� �������

        	else
        	  pers1.lifeDecrease(pers2.attack());
        	if (pers1.alive())   													 //���� ����� ����� ���������
        	  pers2.lifeDecrease(pers1.attack());                                    //������ ����, ������ ������
        	System.out.println();
        	i++;
        }
        if (pers1.alive())                                                           //��� ����� ��� �������
        	System.out.println("������� "+pers1.name);
        else System.out.println("������� "+pers2.name);
        
	}		//main    	
}           //class Person