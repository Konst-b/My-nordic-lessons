
public abstract class Figure {
  String figure;
   /*Figure(){
	   figure="Figure"; 
   }*/
   void View(){}
   void Viewln(){
		System.out.println();
	  }
   public static void main(String[] args) {
   //static {
       System.out.println("ObjTest");
       for (String  s:args) {
    	   System.out.print(s+" ");
       }
       System.out.println();
       //Figure F1,F2,F3,F4,F5,F6;
       Figure[] F=new Figure[30];
       
       for (int i=0;i<F.length;i++) {
    	   switch (i%3){
    	   case 0: F[i]=new Point(i,10,null); break;
    	   case 1: F[i]=new Circle(i,5,10,"Circle"); break; 
    	   case 2: F[i]=new Elipse(i,7,5,8); break;
    	   }
    	   F[i].View();F[i].Viewln();
       }
       System.exit(0);
	}
}

class Point extends Figure{
	  int X,Y;
	  
	  
	  Point (int x, int y, String F){
	    this.X=x;
	    this.Y=y;
	    if (F!=null) {figure=F;}
	    else {figure="Point";}
	  } 
	  void View(){

		System.out.print(this.figure+" x="+this.X+" y="+this.Y);  
	  } 
	}

class Circle extends Point {
	
    int Radius;
    
	Circle(Point a, int radius, String F) {
      super(a.X, a.Y, F);
	  this.Radius=radius;
		
	}
	
	Circle(int x, int y, int radius, String F) {
	  super(x,y,F);
	  this.Radius=radius;
	}
	void View(){
      super.View();
	  System.out.print(" r="+this.Radius);  
	}
}

class Elipse extends Circle {
    int Radius2;
    
	Elipse(Point a, int radius, int radius2) {
      super(a.X, a.Y, radius, "Elipse");
	  this.Radius2=radius2;
		
	}
	Elipse(Circle c, int radius2) {
	      super(c.X, c.Y, c.Radius, "Elipse");
		  this.Radius2=radius2;
			
		}
	
	Elipse(int x, int y, int radius, int radius2) {
	  super(x,y,radius,"Elipse");
	  this.Radius2=radius2;

	}
	void View(){
      super.View(); 
	  System.out.print(" r2="+this.Radius2);  
	}
}
