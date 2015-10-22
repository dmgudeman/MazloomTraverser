import java.util.NoSuchElementException;

public class M_sTree<E extends Comparable<? super E>> implements Cloneable
{
   protected int mSize;
   protected M_sNode<E> mRoot;

   public M_sTree()
   {
      clear();
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
}
