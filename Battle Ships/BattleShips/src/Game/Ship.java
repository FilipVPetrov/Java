package Game;

import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Ship {
	float strength,attack,defence;int experience;
	Map<String,Float> items;

	public Ship(float strength, float attack, float defence, int experience){
		this.strength=strength;
		this.attack=attack;
		this.defence=defence;
		this.experience=experience;
		items= new HashMap<String, Float>();
	}
	private Ship (int f, int g, int h, int i) {
		this.strength=strength;
		this.attack=attack;
		this.defence=defence;
		this.experience=experience;
		items= new HashMap<String, Float>();
	}
	public void items_appending(JLabel al){
		if(this.experience==3){
			al.setText("<html>Congratulations! You receive additional <br> strength upgrade- double hull!</html>");
			items.put("strength",(float) 1.0);
			
		}
		else if(this.experience==6){
			al.setText("<html>Congratulations! You receive additional <br> attack upgrade- 1 ship canon!</html>");
			items.put("attack",(float) 1.0);
		}
		else if(this.experience==9){
			al.setText("<html> Congratulations! You receive additional defence upgrade-<html> <br> the hull  fortified with metal</html>");
			items.put("defence",(float) 1.0);
		}
	}
	
	public void ship_reviewing(){
		System.out.println("The Straight of the ship is : " + this.strength);
		System.out.println("The Attacking power of the ship is : " +this.attack);
		System.out.println("The Defence power of the ship is : "+ this.defence);
	}
	
	public void level_up(JLabel al){
		this.experience++;
		this.items_appending(al);
		this.strength+=0.5;
		        //#self.attack+=1
		        //#self.defence+=1
		this.choose_upgrade(al);
	}
	
	public void choose_upgrade(JLabel al){
		al.setText("You have 3 Points. Choose between Strength, Attack and Defence.");
		
			
		}
	
	public float checking_strength(){
		if(items.containsKey("strength")){
	           return (this.strength + this.items.get("strength"));
		}    else
            return this.strength;	
	}
	
	public float checking_attack(){
		if(items.containsKey("attack")){
	           return (this.attack + this.items.get("attack"));
		}    else
            return this.attack;
	}
	
	public float checking_defence(){
		if(items.containsKey("defence")){
	           return (this.defence + this.items.get("defence"));
		}    else
            return this.defence;
		}
	
	public void damage(float dmg){
        if (items.containsKey("strength")){
            if (dmg==this.items.get("strength")){
                this.items.put("strength", (float) 0);
            }
            else if(dmg< this.items.get("strength")){
                this.items.put("strength",(float)items.get("strength")-dmg);
                }
            else{
                float total= dmg;
                total-=this.items.get("strength");
                this.items.put("strength",(float)0);
                this.strength-=total;
            }
        }else{
            this.strength-=dmg;
	}
        }
	
	public  void showing_1(JLabel a){
		a.setText("<html>The strength of ship:  <html>"+this.strength+"<br>The attacking power: <html>"
	+this.attack+" <br>The defence power :<html>"+this.defence+"</html>");
		}
	
	public  void showing(JLabel a, JLabel b,Ship enemy ){
		a.setText("<html>The strength of ship:  <html>"+this.strength+"<br>The attacking power: <html>"
	+this.attack+" <br>The defence power :<html>"+this.defence+"</html>");
		b.setText("<html>The strength of ship:  <html>"+enemy.strength+"<br>The attacking power: <html>"+enemy.attack
				+" <br>The defence power :<html>"+enemy.defence+"</html>");
		}
	
	public void picture_show(JLabel a){
		if(this.experience==1 || this.experience==2 ){
//			a.setIcon(new ImageIcon("D:\\Battleships\\Gunboat.jpg"));
			a.setIcon(new ImageIcon(Battle_ships.class.getResource("/Battleships/Gunboat.jpg")));
		}else if(this.experience==3 || this.experience==4){	
//			a.setIcon(new ImageIcon("D:\\Battleships\\Schooner.jpg"));
			a.setIcon(new ImageIcon(Battle_ships.class.getResource("/Battleships/Schooner.jpg")));
		}else if(this.experience==5 || this.experience==6){
//			a.setIcon(new ImageIcon("D:\\Battleships\\Brig.jpg"));
			a.setIcon(new ImageIcon(Battle_ships.class.getResource("/Battleships/Brig.jpg")));
		}else if(this.experience==7 || this.experience==8){
//			a.setIcon(new ImageIcon("D:\\Battleships\\Frigate.jpg"));
			a.setIcon(new ImageIcon(Battle_ships.class.getResource("/Battleships/Frigate.jpg")));
		}else if(this.experience==9 || this.experience==10){
//			a.setIcon(new ImageIcon("D:\\Battleships\\Warship.jpg"));			
			a.setIcon(new ImageIcon(Battle_ships.class.getResource("/Battleships/Warship.jpg")));
		}
	}
	
	public void battle(JLabel alfa,JLabel beta,JLabel gamma,JLabel shw_result,JLabel item,Ship enemy){
		for (int i = 0; i <100; i++) {
			float dmg_1=this.checking_attack() - enemy.checking_defence();
			if(dmg_1 <0)
				dmg_1=0;
			enemy.damage(dmg_1);
			if(enemy.strength<=0){
				enemy.strength=0.0f;
				shw_result.setText("The enemy ship is destroyed! ");
				this.showing(alfa, beta, enemy);
				this.picture_show(gamma);	
				this.experience++;
				items_appending(item);							
				return;
			}
			float dmg_2=enemy.checking_attack() - checking_defence();
			if(dmg_2<0)
				dmg_2=0.0f;
			this.damage(dmg_2);
			if(this.strength<=0.0f){
				this.strength=0.0f;
				shw_result.setText("The enemy ship  destroys  our ship! ");
				this.showing(alfa, beta, enemy);
				return;
			}
		}

	}
}


