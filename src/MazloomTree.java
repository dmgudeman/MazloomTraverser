
public class MazloomTree<E>
{
   private int mSize;
   MazloomNode<E> mRoot;
   
   public MazloomTree() {clear();}
   
   public void clear()
   {
      mSize = 0;
      mRoot = null;
   }
   public boolean empty() {return (mSize == 0);}
   
   public int size() { return mSize;}
   
   public MazloomNode<E> addChild(MazloomNode<E> node, E x)
   {
      MazloomNode<E> childNode = new MazloomNode<E>(x, mRoot, node, null, null);
      node.firstChild.data = x;
      return childNode;      
   }
   
   public MazloomNode<E> find(E x)
   {
      return find(mRoot, x, 0);
   }
   
   public MazloomNode<E> find(MazloomNode<E> root, E x, int level)
   {
      return null;
   }
   
   public void display()
   {display(mRoot, 0);}
   
   public void display(MazloomNode<E> treeNode, int level)
   {
      if (treeNode == null)
         return;
      System.out.println(treeNode.data);
      display(treeNode.firstChild, level +1);
      if ( level > 0 )
         display( treeNode.sib, level);
   }
}
