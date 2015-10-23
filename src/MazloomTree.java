
public class MazloomTree<E> implements Cloneable
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
   public boolean remove(E x) { return remove(mRoot, x); }
   
   public int size() { return mSize;}
   
//   public MazloomNode<E> addChild(MazloomNode<E> treeNode, E x)
//   {
//      if(mSize == 0) 
//      {
//        // if(treeNode != null)
//      //      return null;
//      
//      mRoot = new MazloomNode<E>(x, null, null, null, null);
//      mRoot.myRoot = mRoot;
//      mSize = 1;
//     
//      return mRoot; 
//   }
//      if ( treeNode == null)
//         return null;
//      if ( treeNode.myRoot != mRoot )
//         return null; //silent error does not belong to this tree
//      
//      MazloomNode<E> newNode = new MazloomNode<>(x,
//            treeNode.firstChild, null, treeNode, mRoot);
//      treeNode.firstChild = newNode;
//      ++mSize;
//      return newNode;
//   }
   
   public MazloomNode<E> addChild( MazloomNode<E> treeNode,  E x )
   {
      // empty tree? - create a root node if user passes in null
      if (mSize == 0)
      {
        // if (treeNode != null)
       //     return null; // error something's fishy.  treeNode can't right
         mRoot = new MazloomNode<E>(x, null, null, null);
         mRoot.myRoot = mRoot;
         mSize = 1;
         return mRoot;
      }
      if (treeNode == null)
         return null; // error inserting into non_null tree with a null parent
      if (treeNode.myRoot != mRoot)
         return null;  // silent error, node does not belong to this tree

      // push this node into the head of the sibling list; adjust prev pointers
      MazloomNode<E> newNode = new MazloomNode<E>(x, 
         treeNode.firstChild, null, treeNode, mRoot);  // sb, chld, prv, rt
      treeNode.firstChild = newNode;
      if (newNode.sib != null)
         newNode.sib.prev = newNode;
      ++mSize;
      return newNode;  
   }
   
   
   public MazloomNode<E> find(E x)
   {
      return find(mRoot, x, 0);
   }
   
   public MazloomNode<E> find(MazloomNode<E> root, E x, int level)
   {
      MazloomNode<E> retVal;
      
      if ( mSize == 0 || root == null )
         return null;
      
      if(root.data.equals(x))
         return root;
      
      if ( level > 0 && (retVal = find(root.sib, x, level)) != null )
         return retVal;
      return find(root.firstChild, x, ++level);
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
   
   public <F extends Traverser<? super E>> void traverse(F func)
   {
      traverse(func, mRoot, 0);
   }
   public <F extends Traverser<? super E>> void traverse(F func, MazloomNode<E> treeNode, int level)
   {
      if (treeNode == null)
         return;
      func.visit(treeNode.data);
      
      traverse( func, treeNode.firstChild, level + 1);
      if(level > 0)
         traverse( func, treeNode.sib, level);
   }
   public boolean remove(MazloomNode<E> root, E x)
   {
      MazloomNode<E> tn = null;

      if (mSize == 0 || root == null)
         return false;
     
      if ( (tn = find(root, x, 0)) != null )
      {
         removeNode(tn);
         mSize--;
         return true;
      }
      return false;
   }
   private void removeNode(MazloomNode<E> nodeToDelete )
   {
      if (nodeToDelete == null || mRoot == null)
         return;
      if (nodeToDelete.myRoot != mRoot)
         return;  // silent error, node does not belong to this tree

      // remove all the children of this node
      while (nodeToDelete.firstChild != null)
         removeNode(nodeToDelete.firstChild);

      if (nodeToDelete.prev == null)
         mRoot = null;  // last node in tree
      else if (nodeToDelete.prev.sib == nodeToDelete)
         nodeToDelete.prev.sib = nodeToDelete.sib; // adjust left sibling
      else
         nodeToDelete.prev.firstChild = nodeToDelete.sib;  // adjust parent

      // adjust the successor sib's prev pointer
      if (nodeToDelete.sib != null)
         nodeToDelete.sib.prev = nodeToDelete.prev;
   }

   private MazloomNode<E> cloneSubtree(MazloomNode<E>root)
   {
      MazloomNode<E> newNode;
      if (root == null)
         return null;

      // does not set myRoot which must be done by caller
      newNode = new MazloomNode<E>
      (
         root.data, 
         cloneSubtree(root.sib), cloneSubtree(root.firstChild),
         null
      );
      
      // the prev pointer is set by parent recursive call ... this is the code:
      if (newNode.sib != null)
         newNode.sib.prev = newNode;
      if (newNode.firstChild != null)
         newNode.firstChild.prev = newNode;
      return newNode;
   }
// recursively sets all myRoots to mRoot
   private void setMyRoots(MazloomNode<E> treeNode)
   {
      if (treeNode == null)
         return;

      treeNode.myRoot = mRoot;
      setMyRoots(treeNode.sib);
      setMyRoots(treeNode.firstChild);
   }
   
   public Object clone() throws CloneNotSupportedException
   {
      MazloomTree<E>newObject = (MazloomTree<E>)super.clone();
      newObject.clear();  // can't point to other's data

      newObject.mRoot = cloneSubtree(mRoot);
      newObject.mSize = mSize;
      newObject.setMyRoots(newObject.mRoot);
      
      return newObject;
   }
   
   
}
