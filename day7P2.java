import java.io.*;
import java.util.*;
public class day7P2 {   
   public static void main(String[] args) throws FileNotFoundException{
      Scanner f = new Scanner(new File("commands.txt"));
      ArrayList<dir> a = new ArrayList<>();
   	dir d = new dir("/", null);
   	dir c = null;
      a.add(d);
   	while(f.hasNextLine()){
   		String s = f.next();
   		if(s.equals("$")){
            s = f.next();
   			if(s.equals("cd")){
   				s = f.next();
   				if(s.equals("/")){
   					c = d;
   				}else if(s.equals("..")){
   					c = c.getPrev();
   				}else{
   					c = c.getDir(s);
   				}
   			}else{
   				//ls	
   			}
   		}else if(s.equals("dir")){
            dir t = new dir(f.next(), c);
   			c.addDir(t);
            a.add(t);
   		}else{
            c.addFile(Integer.parseInt(s));
            f.next();
   		}
      }
      int n = d.getNum()-40000000;
      ArrayList<Integer> dirs = new ArrayList<>();
      for(int i =0; i < a.size(); i++){
         if(a.get(i).getNum()>n){
            dirs.add(a.get(i).getNum());
         }
      }
      int min = 99999999;
      for(int i =0; i < dirs.size(); i++){
         if(dirs.get(i)<min)
            min = dirs.get(i);
      }
      System.out.print(min+"");
   }
}

class dir{
	private int num = 0; 
	private String name;
	private ArrayList<dir> dirs = new ArrayList<>();
	//private ArrayList<Integer> files = new ArrayList<>();
	private dir prev;
	
	public dir(String s, dir d){
		name = s;
		prev = d;
	}
	
	public void addDir(dir d){
		dirs.add(d);
	}
	
	public void addFile(int n){
		//files.add(n);
      addToPrev(n);
   }
   public void addToPrev(int n){
      num+=n;
      if(prev!=null){
         prev.addToPrev(n);
      }
   }
   public int getNum(){
   	return num;
   }
   public dir getPrev(){
   	return prev;
   }
   public String getName(){
   	return name;
   }
   public dir getDir(String s){
   	for(int i = 0; i < dirs.size(); i++){
   		if(s.equals(dirs.get(i).getName())){
   			return dirs.get(i);
   		}
      }
      return null;
   }
   
 }
