
public class PrintObject<E> implements Traverser<E>
{

   @Override
   public void visit(E x)
   {
      String out = "";
    //  System.out.print(x + "");
      
      if (x instanceof java.lang.String)
      {
         String str = (String) x;
         char[] strArray = new char[str.length()];
         str.getChars(0, str.length(), strArray, 0);
         
         for(int i = 0; i <= str.length()-1; i++)
         {
            if (strArray[i] == 'e')
            {
               strArray[i] = ' ';//strArray[i+1];
            }
            out = new String(strArray);
            
         }
         System.out.println(out);
      }
      
   }

}
