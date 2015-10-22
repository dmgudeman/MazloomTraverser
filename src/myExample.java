public class myExample
{
   static void showArrayUsingThisFunc(PrintString g)
   {
      String myArray[] =
      { "hi", "mom", "3", "45", "this is wild!" };
      int k;

      for (k = 0; k < myArray.length - 1; k++)
      {
         g.visit(myArray[k] + ", ");
      }
      g.visit(myArray[myArray.length - 1]);
   }
}
