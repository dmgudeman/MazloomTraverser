

public class M_sTreeTest
{
public static void main(String[] args) throws Exception
{
   int k;
   M_sTree<Integer> searchTree = new M_sTree<Integer>();
   PrintObject<Integer> printer = new PrintObject<Integer>();

   searchTree.traverse(printer);

   System.out.println("initial size: " + searchTree.size() );
   searchTree.insert(50);
   searchTree.insert(20);
   searchTree.insert(30);
   searchTree.insert(70);
   searchTree.insert(10);
   searchTree.insert(60);
   System.out.println("new size: " + searchTree.size() );
   System.out.println("traversal: ");
   searchTree.traverse(printer);

   M_sTree<Integer> searchTree2 
      = (M_sTree<Integer>)searchTree.clone();

   System.out.println("\n\nAttempting 100 removals:");
   for (k = 100; k > 0; k--)
      if (searchTree.remove(k))
         System.out.println("removed " + k );

   System.out.println("\nsearch_tree after deletes:");
   System.out.println("search_tree size " + searchTree.size());
   searchTree.traverse(printer);

   System.out.println("\nsearch_tree2 after deletes:");
   searchTree2.traverse(printer);
   System.out.println("search_tree2 size " + searchTree2.size());
   System.out.println();
   

   System.out.println
   (
      searchTree2.contains(30)? "30 found" : "30 not found"
   );
   System.out.println
   (
      searchTree2.contains(65)? "65 found" : "65 not found"
   );
   System.out.println("search_tree2 size " + searchTree2.size());
}
}