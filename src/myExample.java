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
   public static void main(String[] args) throws Exception
   {
      MazloomTree<String> sceneTree = new MazloomTree<String>(), sceneTree2;
      MazloomNode<String> tn;
      PrintObject<String> g = new PrintObject<String>();
      
      
      
      // create a scene in a room
      tn = sceneTree.addChild(null, "room");
      tn = sceneTree.find("room");
      sceneTree.addChild(tn, "alice");
      sceneTree.addChild(tn, "jerry");
      sceneTree.addChild(tn, "table");
      // omitted, add some items here like we did before

      // test cloning
      sceneTree2 = (MazloomTree<String>)sceneTree.clone();

      // remove something from the first tree
      sceneTree.remove("alice");
     
      // add some things to the second tree
      tn = sceneTree2.find("table");
      sceneTree2.addChild(tn, "shrink potion");
      sceneTree2.addChild(tn, "card");

      System.out.println("\nOriginal: check if some items have been removed:");
      sceneTree.traverse(g);
      System.out.println("\n\nClone: with the extra items:");
      sceneTree2.traverse(g);
   }
}
