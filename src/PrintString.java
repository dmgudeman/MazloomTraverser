
public class PrintString implements Traverser<String>
{
   public void visit(String s)
   {
      System.out.print(s + "");
   }

}
