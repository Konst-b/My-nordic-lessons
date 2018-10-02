package hw3;

import javax.swing.JOptionPane;

class Weapon {
	  String name;
	  
	  int attack=1;                                                                   //дефолтное значение атаки без оружия
	  
	  private String[] weaponsvariant= {"Нож","Меч","Топор","Алебарда","Базука"};			  
	  private String[][] weaponspower= {{"2",  "3",  "4"  },						  //матрица вариантов максимального	урона для веапона
			                            {"4",  "5",  "6"  },
			                            {"5",  "6",  "7"  },
			                            {"7",  "8",  "9"  },
			                            {"500","600","700"}};
	  private String ans; 
	  
	  Weapon(String caption){
	  	  //this.name=JOptionPane.showInputDialog(null, caption+" / Название оружия:","");
	  	  this.name=(String)JOptionPane.showInputDialog(null, caption+" / Название оружия:", "Выбери оружие", 
	  	             		                            JOptionPane.QUESTION_MESSAGE, null,
	  			                                        weaponsvariant, weaponsvariant[0]);

	  	  if (this.name==null){
	  	      this.name="НЕТ"; return;
	  	  }                             //нажат Cancel - оружия не будет, урон по умолчанию
	  	  do {
			  try {
				  //this.ans=JOptionPane.showInputDialog(this.name+":максимальный урон за атаку:",this.attack);
				  this.ans = (String) JOptionPane.showInputDialog(null, this.name + ": максимальный урон за атаку:", "Укажи силу оружия",
						  JOptionPane.QUESTION_MESSAGE, null,
						  listCombo(this.name), "");

				  if (this.ans == null) {                                            //нажат Cancel - дефолтный урон для выбранного оружия
					  this.attack = Integer.parseInt(listCombo(this.name)[0]);
					  return;
				  }
				  this.attack = Integer.parseInt(this.ans);
			  } catch (NumberFormatException e) {JOptionPane.showMessageDialog(null, e);}
		  }
	  	 while (this.attack<0);

	  }
	  int attack() {
	  	//return (int)Math.round(Math.random()*this.attack);                          //сила атаки от 0 до макс. значения
	  	return (int)(Math.random()*(this.attack+1));
	  }  
	  
	  String weap(){
	  	return "->"+this.name+"#"+this.attack;
	  }

	  private String[] listCombo(String name) {										  //дать список вариантов урона для выбранного оружия
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
	int hp=0;																		 //здоровье
	Weapon w;																		 //оружие
	private String ans;
 
    Person(String caption) {
    	this.name=JOptionPane.showInputDialog(null,"Имя:",caption);
    	if (this.name==null) {System.exit(0);}
    	do {
    	  try {                                                                      //проверка на число
    	   ans=JOptionPane.showInputDialog(null,"Здоровье:","10"); 
    	   if (ans==null) System.exit(0);											 //нажат Cancel
    	   this.hp= Integer.parseInt(ans);
    	  }		
    	  catch(NumberFormatException e){JOptionPane.showMessageDialog(null, "Ошибка! "+e);}
    	} while (hp<=0);
    	w=new Weapon(this.name);													 //проинитить оружие
    }
    
    String getInfo() {                                                               //дать строчку с параметрами перса
    	return this.name+"\nЗдоровье: "+this.hp+"     Оружие: " +this.w.name+" / макс. атака:"+this.w.attack+"\n\n";
    }
    
    boolean alive() {                                                                //ещё живой?
    	return this.hp>0;
    }
    
    void lifeDecrease(int hpOut) {										             //ранен
    	this.hp-=hpOut;
    	if (this.hp<0) this.hp=0;												     //убит
    	System.out.print(padright(this.name+" атакован "+hpOut+
    			         " осталось здоровья:"+this.hp,42));                     //дать строчку в протокол
    }

    int attack(){																	  //вернул силу удара
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

		Person pers1=new Person("Первый"),
        	   pers2=new Person("Второй");					     			 //инициализация персонажей
        
        JOptionPane.showMessageDialog(null,                          //показать стартовые параметры персонажей
                              pers1.getInfo()+
                                       pers2.getInfo(),
                                  "Бой!",JOptionPane.INFORMATION_MESSAGE);

        int i=1;                                                                     //счётчик атак
        boolean First=(int)(Math.random()*10) >=5;                                   //случайный выбор: кто первый атакует
        if (First)
        	System.out.println("Первым атакует "+pers2.name);
        else
        	System.out.println("Первым атакует "+pers1.name);

        System.out.println(padright("",lenNum)+padright(pers1.name+"("+pers1.hp+")"+pers1.weap(),lenStr)+pers2.name+"("+pers2.hp+")"+pers2.weap());

        while(pers1.alive() && pers2.alive() ) {									 //пока оба живы - бьёмся
        	System.out.print(padright(Integer.toString(i)+": ",lenNum));
        	if (i==1) 														    	 //первая атака
        	  if (First)
        		  pers1.lifeDecrease(pers2.attack());                                //второй бьёт, первый дохнет
        	  else System.out.print(padright("                      -",lenStr));//заполнить верхнюю строчку первой колонки чтобы избежать смещени

        	else
        	  pers1.lifeDecrease(pers2.attack());
        	if (pers1.alive())   													 //если живой можно атаковать
        	  pers2.lifeDecrease(pers1.attack());                                    //первый бьёт, второй дохнет
        	System.out.println();
        	i++;
        }
        if (pers1.alive())                                                           //кто живой тот победил
        	System.out.println("Победил "+pers1.name);
        else System.out.println("Победил "+pers2.name);
        
	}		//main    	
}           //class Person