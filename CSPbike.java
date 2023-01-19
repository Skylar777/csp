import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CSPbike extends CSP {
	
	static Set<Object> varCol = new HashSet<Object>(Arrays.asList(new String[] {"blue", "green", "black", "red", "white"}));
	static Set<Object> varDri = new HashSet<Object>(Arrays.asList(new String[] {"apple", "cranberry", "orange", "grapefruit", "pineapple"}));
	static Set<Object> varNat = new HashSet<Object>(Arrays.asList(new String[] {"adrian", "charles", "henry", "joel", "richard"}));
	static Set<Object> varPet = new HashSet<Object>(Arrays.asList(new String[] {"bacon", "chicken", "cheese", "pepperoni", "tuna"}));
	static Set<Object> varCig = new HashSet<Object>(Arrays.asList(new String[] {"baseball", "basketball", "hockey", "soccer", "swimming"}));
	static Set<Object> varage = new HashSet<Object>(Arrays.asList(new String[] {"12","13","14","15","16"}));
	
	public boolean isGood(Object X, Object Y, Object x, Object y) {
		//if X is not even mentioned in by the constraints, just return true
		//as nothing can be violated
		if(!C.containsKey(X))
			return true;
		
		//check to see if there is an arc between X and Y
		//if there isn't an arc, then no constraint, i.e. it is good
		if(!C.get(X).contains(Y))
			return true;
		


		//swiming next to baseball
		if(X.equals("swimming") && Y.equals("baseball") && Math.abs((Integer)x-(Integer)y)!=1)
			return false;
		//joel next to 16y/o
		if(X.equals("joel") && Y.equals("16") && Math.abs((Integer)x-(Integer)y)!=1)
			return false;
		//baseball next to applejuice
		if(X.equals("baseball") && Y.equals("apple") && Math.abs((Integer)x-(Integer)y)!=1)
			return false;






		//hockey eats pepperoni
		if(X.equals("hockey") && Y.equals("pepperoni") && !x.equals(y))
			return false;
		//16y/o eats cheese
		if(X.equals("cheese") && Y.equals("16") && !x.equals(y))
			return false;



		//bacon somewhere right of white bike
		if(X.equals("bacon") && Y.equals("white") && !((Integer)x>(Integer)y))
			return false;
		




		//henry directly to the left of soccer fan
		if(X.equals("soccer") && Y.equals("henry") && (Integer)x-(Integer)y!=1)
			return false;
		//adrian directly to the left of pepperoni fan
		if(X.equals("pepperoni") && Y.equals("adrian") && (Integer)x-(Integer)y!=1)
			return false;


		//white bike between 15y/o and 12y/o, order
		if(X.equals("white") && Y.equals("15") && !((Integer)x>(Integer)y))
			return false;
		if(X.equals("12") && Y.equals("white") && !((Integer)x>(Integer)y))
			return false;

		//grapefruit juice between tuna and pineapple, order
		if(X.equals("grapefruit") && Y.equals("tuna") && !((Integer)x>(Integer)y))
			return false;
		if(X.equals("pineapple") && Y.equals("grapefruit") && !((Integer)x>(Integer)y))
			return false;

		//pineappple between 14y/o and orange, order
		if(X.equals("pineapple") && Y.equals("14") && !((Integer)x>(Integer)y))
			return false;
		if(X.equals("orange") && Y.equals("pineapple") && !((Integer)x>(Integer)y))
			return false;

		//white bike between blue and black bikes, order
		if(X.equals("white") && Y.equals("blue") && !((Integer)x>(Integer)y))
			return false;
		if(X.equals("black") && Y.equals("white") && !((Integer)x>(Integer)y))
			return false;

		//12 bike between 14y/o and 16y/o, order
		if(X.equals("12") && Y.equals("14") && !((Integer)x>(Integer)y))
			return false;
		if(X.equals("16") && Y.equals("12") && !((Integer)x>(Integer)y))
			return false;

		//white bike between richard and red, order
		if(X.equals("white") && Y.equals("richard") && !((Integer)x>(Integer)y))
			return false;
		if(X.equals("red") && Y.equals("white") && !((Integer)x>(Integer)y))
			return false;

		//charles bike between richard and adrian, order
		if(X.equals("charles") && Y.equals("richard") && !((Integer)x>(Integer)y))
			return false;
		if(X.equals("adrian") && Y.equals("charles") && !((Integer)x>(Integer)y))
			return false;
		
		
		//Uniqueness constraints
		
		if(varCol.contains(X) && varCol.contains(Y) && !X.equals(Y) && x.equals(y))
			return false;
		
		if(varDri.contains(X) && varDri.contains(Y) && !X.equals(Y) && x.equals(y))
			return false;
		
		if(varNat.contains(X) && varNat.contains(Y) && !X.equals(Y) && x.equals(y))
			return false;
		
		if(varPet.contains(X) && varPet.contains(Y) && !X.equals(Y) && x.equals(y))
			return false;
		
		if(varCig.contains(X) && varCig.contains(Y) && !X.equals(Y) && x.equals(y))
			return false;

		if(varage.contains(X) && varage.contains(Y) && !X.equals(Y) && x.equals(y))
			return false;
		
		return true;
	}
		
	public static void main(String[] args) throws Exception {
		CSPbike csp = new CSPbike();
		
		Integer[] dom = {1,2,3,4,5};
		
		for(Object X : varCol) 
			csp.addDomain(X, dom);
		
		for(Object X : varDri) 
			csp.addDomain(X, dom);
		
		for(Object X : varNat) 
			csp.addDomain(X, dom);
		
		for(Object X : varPet) 
			csp.addDomain(X, dom);
		
		for(Object X : varCig) 
			csp.addDomain(X, dom);

		for(Object X : varage) 
			csp.addDomain(X, dom);
		
		
		//unary constraints: just remove values from domains
		
		//baseball is drunk in the middle house.
		for(int i=1; i<=5; i++)
			if(i != 3)
				csp.D.get("baseball").remove(i);
		//black middle
		for(int i=1; i<=5; i++)
			if(i != 3)
				csp.D.get("black").remove(i);
		//pineapple 4th pos
		for(int i=1; i<=5; i++)
			if(i != 4)
				csp.D.get("pineapple").remove(i);
		//tuna on an end
		for(int i=1; i<=5; i++)
			if(i != 1 && i!=5)
				csp.D.get("tuna").remove(i);
		//green on an end
		for(int i=1; i<=5; i++)
			if(i != 1 && i!=5)
				csp.D.get("green").remove(i);
		
		//hockey last
		for(int i=1; i<=5; i++)
			if(i!=5)
				csp.D.get("hockey").remove(i);
		//13y/o last
		for(int i=1; i<=5; i++)
			if(i!=5)
				csp.D.get("13").remove(i);
		
		
		
		//binary constraints: add constraint arcs
		
		
		//swiming next to baseball
		csp.addBidirectionalArc("swimming", "baseball");
		csp.addBidirectionalArc("baseball", "swimming");
		//joel next to 16y/o
		csp.addBidirectionalArc("joel", "16");
		csp.addBidirectionalArc("16", "joel");
		//baseball next to applejuice
		csp.addBidirectionalArc("baseball", "apple");
		csp.addBidirectionalArc("apple", "baseball");






		//hockey eats pepperoni
		csp.addBidirectionalArc("hockey", "pepperoni");
		csp.addBidirectionalArc("pepperoni", "hockey");
		//16y/o eats cheese
		csp.addBidirectionalArc("16", "cheese");
		csp.addBidirectionalArc("cheese", "16");



		//bacon somewhere right of white bike
		csp.addBidirectionalArc("bacon", "white");
		csp.addBidirectionalArc("white", "bacon");
		




		//henry directly to the left of soccer fan
		csp.addBidirectionalArc("henry", "soccer");
		csp.addBidirectionalArc("soccer", "henry");
		//adrian directly to the left of pepperoni fan
		csp.addBidirectionalArc("adrian", "pepperoni");
		csp.addBidirectionalArc("pepperoni", "adrian");

		//white bike between 15y/o and 12y/o, order
		csp.addBidirectionalArc("white", "15");
		csp.addBidirectionalArc("white", "12");
		csp.addBidirectionalArc("15", "white");
		csp.addBidirectionalArc("12", "white");

		//grapefruit juice between tuna and pineapple, order
		csp.addBidirectionalArc("grapefruit", "pineapple");
		csp.addBidirectionalArc("grapefruit", "tuna");
		csp.addBidirectionalArc("pineapple", "grapefruit");
		csp.addBidirectionalArc("tuna", "grapefruit");

		//pineappple between 14y/o and orange, order
		csp.addBidirectionalArc("pineapple", "14");
		csp.addBidirectionalArc("pineapple", "orange");
		csp.addBidirectionalArc("14", "pineapple");
		csp.addBidirectionalArc("orange", "pineapple");

		//white bike between blue and black bikes, order
		csp.addBidirectionalArc("white", "blue");
		csp.addBidirectionalArc("white", "black");
		csp.addBidirectionalArc("blue", "white");
		csp.addBidirectionalArc("black", "white");

		//12 bike between 14y/o and 16y/o, order
		csp.addBidirectionalArc("12", "14");
		csp.addBidirectionalArc("12", "16");
		csp.addBidirectionalArc("14", "12");
		csp.addBidirectionalArc("16", "12");

		//white bike between richard and red, order
		csp.addBidirectionalArc("white", "richard");
		csp.addBidirectionalArc("white", "red");
		csp.addBidirectionalArc("richard", "white");
		csp.addBidirectionalArc("red", "white");

		//charles bike between richard and adrian, order
		csp.addBidirectionalArc("charles", "richard");
		csp.addBidirectionalArc("charles", "adrian");
		csp.addBidirectionalArc("richard", "charles");
		csp.addBidirectionalArc("adrian", "charles");
		
		


		//Uniqueness constraints
		
		for(Object X : varCol)
			for(Object Y : varCol)
				csp.addBidirectionalArc(X,Y);
		
		for(Object X : varDri)
			for(Object Y : varDri)
				csp.addBidirectionalArc(X,Y);

		for(Object X : varNat)
			for(Object Y : varNat)
				csp.addBidirectionalArc(X,Y);

		for(Object X : varPet)
			for(Object Y : varPet)
				csp.addBidirectionalArc(X,Y);

		for(Object X : varCig)
			for(Object Y : varCig)
				csp.addBidirectionalArc(X,Y);

		for(Object X : varage)
			for(Object Y : varage)
				csp.addBidirectionalArc(X,Y);


		//Now let's search for solution 
		
		Search search = new Search(csp);
		System.out.println(search.BacktrackingSearch());
	}
}