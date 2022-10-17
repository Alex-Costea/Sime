import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Objects;

public class Main {

    private static final HashMap<Integer,String> names=new HashMap<>();

    static {
        names.put(1,"one");
        names.put(2,"two");
        names.put(3,"three");
        names.put(4,"four");
        names.put(5,"five");
        names.put(6,"six");
        names.put(7,"seven");
        names.put(8,"eight");
        names.put(9,"nine");
        names.put(10,"ten");
        names.put(11,"eleven");
        names.put(12,"dozen");
    }

    private static String combineNames(String a,String b)
    {
        int aLength=a.length();
        int bLength=b.length();
        for(int index=2;index<=Math.max(aLength,bLength);index++)
        {
            int i=Math.min(index,aLength);
            for(int j=Math.min(i,bLength);j<=Math.min(index+1,bLength);j++)
            {
                StringBuilder newValue = new StringBuilder();
                newValue.append(a, 0, i);
                newValue.append(b, bLength-j, bLength);
                if(!names.containsValue(newValue.toString()))
                    return newValue.toString();
            }
        }
        return combineNames(a+b,a+b);
    }

    public static void main(String[] args) {
        int max=200;
        LinkedHashSet<Integer> sieve=new LinkedHashSet<>();
        sieve.add(2);
        sieve.add(3);
        sieve.add(5);
        sieve.add(7);
        sieve.add(11);
        int k=5;
        for(int i=11;i<=max*max/4;i++)
            sieve.add(i);
        for(int sum=1;sum<=max;sum++)
        {
            int i = sum/2;
            int j = sum/2 + (sum % 2 == 1 ? 1 : 0);
            for(;i>=1;i--,j++)
            {
                int result=i*j;
                if(!names.containsKey(result))
                {
                    String name1=names.get(i);
                    Objects.requireNonNull(name1);
                    String name2;
                    if(!names.containsKey(j))
                    {
                        k++;
                        sieve.remove(sieve.stream().findFirst().get());
                        name1=names.get(k);
                        StringBuilder sb=new StringBuilder();
                        for(char ch : name1.toCharArray())
                        {
                            switch(ch)
                            {
                                case 'i' -> sb.append('o');
                                case 'e' -> sb.append('i');
                                case 'o' -> sb.append('e');
                                default -> sb.append(ch);
                            }
                        }
                        name1=sb.toString();
                        name2=name1;
                    }
                    else name2=names.get(j);
                    Objects.requireNonNull(name2);
                    if(names.containsValue(name1))
                        names.put(i*j,combineNames(name1,name2));
                    else names.put(i*j,name1);
                    sieve.remove(i*j);
                }
            }
        }
        for(int i=1;i<=max*max/4;i++)
        {
            if(names.containsKey(i))
                System.out.println(i+": "+names.get(i));
        }
    }
}