
public class MazloomHill<E>
{

 
   public static void  main(String[] args) throws CloneNotSupportedException
   {
      PrintString h = new PrintString();

      h.visit("Does this really work?");
     h.visit("Apparently");
      System.out.println();
      
      myExample.showArrayUsingThisFunc(h);
      
      PrintObject<String> stringPrinter = new PrintObject<String>();
      
      MazloomTree<String> mt = new MazloomTree<>();
      
      
      mt.addChild(mt.mRoot, "Hi there");
      mt.addChild(mt.mRoot, "Jersey");
      System.out.println(mt.size());
      
      mt.display();
      
     System.out.println( mt.find("Jersey"));
     
//    MazloomTree<String> sceneTree = new MazloomTree<String>();
//     MazloomNode<String> tn;
//     sceneTree.display();
//     PrintObject<String>     strPrntr =  new PrintObject<String>();
//     PrintObject<Integer>    intPrntr =  new PrintObject<Integer>();
//    // PrintObject<SongEntry>  tunePrntr = new PrintObject<SongEntry>();
//     mt.traverse(strPrntr);
//      
     
     MazloomTree<String> sceneTree = new  MazloomTree<String>(), sceneTree2;
     MazloomNode<String> tn;
     PrintObject<String> g = new PrintObject<String>();
     
     // create a scene in a room
     tn = sceneTree.addChild(null, "room");
     
     // omitted, add some items here like we did before

     // test cloning
     sceneTree2 = ( MazloomTree<String>)sceneTree.clone();

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
