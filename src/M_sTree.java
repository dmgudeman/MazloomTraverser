import java.util.NoSuchElementException;

public class M_sTree<E extends Comparable<? super E>> implements Cloneable
{
   protected int mSize;
   protected M_sNode<E> mRoot;

   public M_sTree()
   {
      clear();
   }
   boolean contains(E x)  
   { 
       return find(mRoot, x) != null; 
   }
   public boolean empty()
   {
      return (mSize == 0);
   }

   public void clear()
   {
      mSize = 0;
      mRoot = null;
   }

   public int size()
   {
      return mSize;
   }
   // public version available to client
   E find( E x )
   {
      M_sNode<E> resultNode;
      resultNode = find(mRoot, x);
      if (resultNode == null)
         throw new NoSuchElementException();
      return resultNode.data;
   }
// private version that does most of the work
   protected M_sNode<E> find( M_sNode<E> root, E x )
   {
      int compareResult;  // avoid multiple calls to compareTo()

      if (root == null)
         return null;

      compareResult = x.compareTo(root.data); 
      if (compareResult < 0)
         return find(root.lftChild, x);
      if (compareResult > 0)
         return find(root.rtChild, x);
      return root;   // found!
   }
   protected M_sNode<E> findMin(M_sNode<E> root)
   {
      if (root == null)
         return null;
      if (root.lftChild == null)
         return root;
      return findMin(root.lftChild);
   }

   public E findMin()
   {
      if (mRoot == null)
         throw new NoSuchElementException();
      return findMin(mRoot).data;
   }

   public E findMax()
   {
      return null;
   }

   public boolean insert(E x)
   {
      int oldSize = mSize;
      mRoot = insert(mRoot, x);
      return (mSize != oldSize);
   }

   public boolean remove(E x)
   {
      int oldSize = mSize;
      mRoot = remove(mRoot, x);
      return (mSize != oldSize);
   }

   protected M_sNode<E> insert(M_sNode<E> root, E x)
   {
      int compareResult;

      if (root == null)
      {
         mSize++;
         return new M_sNode<E>(x, null, null);
      }
      compareResult = x.compareTo(root.data);
      if (compareResult < 0)
         root.lftChild = insert(root.lftChild, x);
      else if (compareResult > 0)
         root.rtChild = insert(root.rtChild, x);

      return root;

   }

   protected M_sNode<E> remove(M_sNode<E> root, E x)
   {
      int compareResult;

      if (root == null)
         return null;

      compareResult = x.compareTo(root.data);
      if (compareResult < 0)
         root.lftChild = remove(root.lftChild, x);
      else if (compareResult > 0)
         root.rtChild = remove(root.rtChild, x);

      else if (root.lftChild != null && root.rtChild != null)
      {
         root.data = findMin(root.rtChild).data;
         root.rtChild = remove(root.rtChild, root.data);
      } else
      {
         root = (root.lftChild != null) ? root.lftChild : root.rtChild;
         mSize--;
      }
      return root;
   }
   public Object clone() throws CloneNotSupportedException
   {
      M_sTree<E> newObject = (M_sTree<E>)super.clone();
      newObject.clear();  // can't point to other's data

      newObject.mRoot = cloneSubtree(mRoot);
      newObject.mSize = mSize;
      
      return newObject;
   }
   protected M_sNode<E> cloneSubtree(M_sNode<E> root)
   {
      M_sNode<E> newNode;
      if (root == null)
         return null;

      // does not set myRoot which must be done by caller
      newNode = new M_sNode<E>
      (
         root.data, 
         cloneSubtree(root.lftChild), 
         cloneSubtree(root.rtChild)
      );
      return newNode;
   }
   
   protected int findHeight( M_sNode<E> treeNode, int height ) 
   {
      int leftHeight, rightHeight;
      if (treeNode == null)
         return height;
      height++;
      leftHeight = findHeight(treeNode.lftChild, height);
      rightHeight = findHeight(treeNode.rtChild, height);
      return (leftHeight > rightHeight)? leftHeight : rightHeight;
   }
   public < F extends Traverser<? super E > > 
   void traverse(F func)
   {
      traverse(func, mRoot);
   }
   protected <F extends Traverser<? super E>> 
   void traverse(F func, M_sNode<E> treeNode)
   {
      if (treeNode == null)
         return;

      traverse(func, treeNode.lftChild);
      func.visit(treeNode.data);
      traverse(func, treeNode.rtChild);
   }
   
}
