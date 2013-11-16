

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Random;

// View interface
interface IView {
	public void updateView();
}

public class SantaModel 
{
	private SendMail mailer = new SendMail();
	
	private int numPart;
	private ArrayList<String> names = new ArrayList<String>();
	private Hashtable<String, String> emails = new Hashtable<String, String>();
	private Hashtable<String, String> matchings = new Hashtable<String, String>();
	private Random randomNum = new Random();
	private int nameCount = 0;
	
	private String curName = "";
	private String curEmail = "";
	
	private Boolean getNum = false;
	private Boolean readySend = false;
	
	// all views of this model
	private ArrayList<IView> views = new ArrayList<IView>();

	// set the view observer
	public void addView(IView view) {
		views.add(view);
		// update the view to current state of the model
		notifyObservers();
	}
	
	// notify the IView observer
	private void notifyObservers() {
		for (IView view : this.views) {
			System.out.println("Model: notify View");
			view.updateView();
		}
	}
	
	public Boolean getStateNum(){
		return getNum;
	}
	public void setStateNum(){
		getNum = true;
	}
	public Boolean getReadySend(){
		return readySend;
	}
	public void setReadySend(){
		readySend = true;
	}
	public int getNum(){
		return numPart;
	}
	public void setNum(int n){
		numPart = n;
		notifyObservers();
	}
	
	public String getCurName(){
		return curName;
	}
	public String getCurEmail(){
		return curEmail;
	}
	public int getNameCount(){
		return nameCount;
	}
	public ArrayList<String> getNames (){
		return names;
	}
	public Hashtable<String, String> getEmails(){
		return emails;
	}
	public Hashtable<String, String> getMatchings(){
		return matchings;
	}
	
	public void addName(String name, String email){
		names.add(name);
		emails.put(name,email);
		nameCount++;
		curName = name;
		curEmail = email;
		notifyObservers();
	}
	
	public void printNames(){
		Enumeration<String> enumKey = emails.keys();
		while(enumKey.hasMoreElements()){
			String name = enumKey.nextElement();
			System.out.println(name + " "+ emails.get(name));
		}
	}
	
	public void printMatch(){
		Enumeration<String> enumKey = matchings.keys();
		while(enumKey.hasMoreElements()){
			String name = enumKey.nextElement();
			System.out.println(name + "--->"+ matchings.get(name));
		}
	}
	
	public void match(){
		Enumeration<String> enumKey = emails.keys();
		
		
		while(enumKey.hasMoreElements()){
			String name = enumKey.nextElement();
			String tempName = "";
			String tempEmail = "";
			String[] foo = names.toArray(new String[0]);
			do {
				int idx = new Random().nextInt(foo.length);
				tempName = (foo[idx]);
			}while (tempName == name);
			names.remove(tempName);
			tempEmail = emails.get(tempName);
			matchings.put(name, tempName);
		}
	}
	
	public void sendEmails(){
		Enumeration<String> enumKey = matchings.keys();
		while(enumKey.hasMoreElements()){
			String name = enumKey.nextElement();
			System.out.println(name + "--->"+ matchings.get(name));
			mailer.send(name, matchings.get(name), emails.get(name));
		}
	}
}

