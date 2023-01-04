import java.io.*;
import java.util.*;
public class day7 {   
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
      int n = 0;
      for(int i =0; i < a.size(); i++){
         if(a.get(i).getNum()<100000){
            n+= a.get(i).getNum();
         }
      }
      System.out.print(n+"");
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
   /*
   public void update(){
   	num = 0;
   	for(int i = 0; i < dirs.size(); i++){
   		num+= dirs.get(i).getNum();
   	}
   	for(int i = 0; i < files.size(); i++){
   		num+= files.get(i);
   	}
   }
   */
   /*
   public int dfs(dir d){
      if(dirs.size()>0){
      	int n = 0;
      	for(int i = 0; i < dirs.size(); i++){
      		n = dfs(dirs.get(i));
      	}
      	//update();
      	if(num < 100000){
      		n+=num;
      	}
      	return n;
      }
      return 0;
   }
   */
}
