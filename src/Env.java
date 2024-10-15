import java.util.ArrayList;
import java.util.HashMap;

public class Env
{
    public Env prev;


    HashMap<String,Object> hashB = new HashMap<String,Object>();
    //should have initialised hashmap here as member var, public (unsafe)
    public Env(Env prev)
    {
        //initialise a instanmce DS here, HashMap, dict?
        this.prev = prev;
        hashB = new HashMap<>();

    }
    public void Put(String name, Object value)
    {
        //add new symbol table entry here, easy check and add using DS in constr
        //value = the ival
        this.hashB.put(name,value);

    }
   /* public Object Get(String name)
    {
        //name is key
        //search here
        //return should be value of  given key
        //Search if  given string in DS
        Object targetKey = name;
        Env current = this;

        while (current != null) {
            HashMap<String, Object> hashMap = current.hashB;
            if (hashMap.containsKey(targetKey)) {
                return hashMap.get(name);
                }
            current = current.prev;
        }
        return null;

        Object r = hashB.get(name);
        if (r==null)
            return null;
        else
            return r;

    }*/
   public Object Get(String name) {
       //name is key
       //search here
       //return should be value of  given key
       //Search if  given string in DS
       Object value = hashB.get(name);
       if (value == null && prev!= null){
           value = prev.Get(name);
       }
       return value;
   }



}



