//import io.github.bonigarcia.wdm.WebDriverManager;


import java.util.*;

public class Testing_Simply extends ParentStuff{


  public Testing_Simply(String a,String b)
    {
        System.out.println("yoyo");
    }

    public static void main(String[] args) {



        //Opening chrome browser
//        WebDriverManager.chromedriver().setup();
//        WebDriver driver = new ChromeDriver();

        //Type casting to string
        List l = new ArrayList();
        l.add("test");
        String test1 = (String) l.get(0);
        Collection C = new ArrayList();
        C.add("Hello");
        C.add(10);
        C.addAll(l);
        System.out.println("C before removal" + C);
        C.remove("Hello");
        System.out.println(C.toArray());
        Object[] t = C.toArray();

        String x="Triumph";
        int i=0;
        int k=0;


        for(char c:x.toCharArray())
        {
            i++;
        }

        System.out.println("Size of Array is "+i);


       try{
           for(k=0;;k++)
           {
               x.charAt(k);

           }

       }
       catch(Exception e)
       {
           System.out.println("Value is "+k);
       }

       Testing_Simply test=new Testing_Simply("a","b");

       String [] test21= {"one","two","one"};
       Set<String> s=new HashSet<String>();
       for(String jun:test21)
       {
           if(s.add(jun)==false)
           {
               System.out.println(jun);

           }
       }

       String a="Mc";
       String b="DOnalds";
       a=a+b;
       System.out.println("Value before swapping is"+a);
       b=a.substring(0, a.length()-b.length());
       a=a.substring(b.length());
       System.out.println("Value of a and b is"+a+b);



        //Star Pattern:

//    *
//   **
//  ***
// ****
//*****

        for(int m=1;m<=5;m++)
       {
           for(int n=4;n>=m;n--)
           {
           System.out.print(" ");
           }
           for(int o=1;o<=m;o++)
           {
               System.out.print("*");
           }

           System.out.println();

       }


//        for(int m=1;m<=5;m++)
//        {
//            for(int n=1;n<=m;n++)
//            {
//                System.out.print("*");
//            }
//            System.out.println();
//        }
//        for(int m=1;m<=5;m++)
//        {
//            for(int n=4;n<=m;n--)
//            {
//                System.out.print("*");
//            }
//            System.out.println();
//        }


//

        //Fibonacci(Easy Version)

//        import java.util.*;
//        public class Main
//        {
//
//
//            public static void calc(int y)
//            {
//                int n=y;
//                int first_num=0;
//                int second_num=1;
//                int next_num;
//
//
//                List<Integer> l=new ArrayList<Integer>();
//
//                //if(n!=0)
//                //{
//                //    System.out.println("Invalid Number");
//                //}
//
//                while(n<=0)
//                {
//                    System.out.println("Invalid Number");
//                    break;
//                }
//
//
//                if(n==1)
//                {
//                    l.add(first_num);
//
//                }
//
//                if(n==2)
//                {
//                    l.add(first_num);
//                    l.add(second_num);
//
//                }
//
//
//
//                if(n>2)
//                {
//                    l.add(first_num);
//                    l.add(second_num);
//                    for(int i=1;i<=n-2;i++)
//                    {
//                        next_num=second_num+first_num;
//                        first_num=second_num;
//                        second_num=next_num;
//                        l.add(next_num);
//
//                    }
//
//                }
//
//                System.out.println(l);
//
//
//            }
//
//
//
//            public static void main(String[] args) {
//
//                Main.calc(5);
//
//
//
//            }
//        }



        int num=123;
        int sum=0;
        int rem;

        while(num!=0)
        {
            rem=num%10;
            sum=sum+(rem*rem*rem);
            num=num/10;
        }

        System.out.println(sum);


    }
}
