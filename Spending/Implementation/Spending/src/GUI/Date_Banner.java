package GUI;

import java.util.Date;

import javax.swing.JLabel;

public class Date_Banner extends Thread {
	private JLabel label;

	public Date_Banner(JLabel jl) {
		this.label = jl;
		this.start();
	}

	@SuppressWarnings({ "deprecation", "static-access" })
	public void run() {
		while (true) {
			Date date = new Date();
			String dt = "";String seconds="";String minutes="";String hours="";
			if(date.getHours()<10)
				hours="0"+date.getHours();
			else
				hours=""+date.getHours();
			if(date.getMinutes()<10)
				minutes="0"+date.getMinutes();
			else 
				minutes=""+ date.getMinutes();
			if((int)date.getSeconds()<10)
				seconds="0"+date.getSeconds();
			else 
				seconds=""+date.getSeconds();
			dt = "<html>" + date.getDate() + "." + (date.getMonth() + 1) + "."
					+ (date.getYear() + 1900) + "<html> <br> <html>"
					+ hours + ":" + minutes + ":"
					+ seconds + "</html>";

			label.setText(dt);
			try {
				this.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
